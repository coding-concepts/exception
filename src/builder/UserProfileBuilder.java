/*
 * @(#)UserBuilder.java 1.0 Oct 05, 2017
 */
package builder;

import data.RegistrationData;
import data.UserProfile;
import domain.User;
import service.HashingService;
import service.ServiceFactory;
import service.EncryptionService;
import service.ServiceFactory;
import service.EncryptionService;
import service.ServiceFactory;

/**
 * <code>UserBuilder</code> class is  Builder Class for User.
 * <p>
 * <p>
 * <pre>
 * <strong>History</strong>    Name              Date            Description
 * <strong>History</strong>    --------------------------------------------------------------------
 * <strong>History</strong>   Pratyush Giri    10/5/17
 * </pre>
 *
 * @author Pratyush Giri
 * @since Oct 05, 2017
 */
public class UserProfileBuilder {

    private RegistrationData registrationData;

    private static final String HASHING_ALGO = "SHA-256";

    private HashingService hashingService = ServiceFactory.getHashingService();

    private EncryptionService encryptionService = ServiceFactory.getEncryptionService();

    private User user;

    public UserProfileBuilder registrationData(RegistrationData data) {
        this.registrationData = data;
        return this;
    }

    public UserProfileBuilder user(User u) {
        this.user = u;
        return this;
    }


    public User buildUser(){
        if (registrationData == null) {
            return null;
        }

        User user = new User();

        String[] firstNames = encryptionService.encrypt(registrationData.getFirstName());
        user.setFirstName(firstNames[0]);
        user.setFirstNameKey(firstNames[1]);

        String[] lastNames = encryptionService.encrypt(registrationData.getLastName());
        user.setLastName(lastNames[0]);
        user.setLastNameKey(lastNames[1]);

        user.setEmail(registrationData.getEmail());
        user.setDob(registrationData.getDob());
        user.setGender(registrationData.getGender());
        user.setPhone(registrationData.getPhone());

        try {
            String[] pwdAndSalt  = hashingService.getHashAndSalt(HASHING_ALGO, registrationData.getPassword().getBytes());
            user.setPassword(pwdAndSalt[1]);
            user.setSalt(pwdAndSalt[0]);
            user.setHashingAlgo(HASHING_ALGO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public UserProfile build() {

        if (registrationData == null) {
            return null;
        }

        UserProfile profile = new UserProfile();
        profile.setFirstName(registrationData.getFirstName());
        profile.setLastName(registrationData.getLastName());
        profile.setEmail(registrationData.getEmail());
        profile.setDob(registrationData.getDob());
        char gender = registrationData.getGender();
        profile.setGender(gender == 'M' ? "Male" : gender == 'F' ? "Female" : gender == 'O' ? "Others" : "Rather Not Say");
        profile.setPhone(registrationData.getPhone());

        return profile;
    }
    public UserProfile build2() {

        if (user == null) {
            return null;
        }

        UserProfile profile = new UserProfile();


        profile.setFirstName(encryptionService.decrypt(user.getFirstName(), user.getFirstNameKey()));
        profile.setLastName(encryptionService.decrypt(user.getLastName(), user.getLastNameKey()));


        profile.setEmail(user.getEmail());
        profile.setDob(user.getDob());
        char gender = user.getGender();
        profile.setGender(gender == 'M' ? "Male" : gender == 'F' ? "Female" : gender == 'O' ? "Others" : "Rather Not Say");
        profile.setPhone(user.getPhone());

        return profile;
    }

}



