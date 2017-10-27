/*
 * @(#)TestRegistrationDataValidator.java 1.0 Sep 22, 2017
 */

import data.RegistrationData;
import domain.User;
import domain.repository.UserRepositoryImpl;
import exception.ValidationError;
import exception.ValidationException;
import validator.RegistrationDataValidator;
import validator.Validator;

import java.util.Calendar;
import java.util.Date;

/**
 * <code>TestRegistrationDataValidator</code> class is
 * <p>
 * <p>
 * <pre>
 * <strong>History</strong>    Name              Date            Description
 * <strong>History</strong>    --------------------------------------------------------------------
 * <strong>History</strong>   Pratyush Giri    9/22/17
 * </pre>
 *
 * @author Pratyush Giri
 * @since Sep 22, 2017
 */
public class TestRegistrationDataValidator {

    static Validator validator =  RegistrationDataValidator.getInstance();

    public static void main(String[] args){

        UserRepositoryImpl repo = new UserRepositoryImpl();

        User u = repo.findByEmailId("a@a.com");

        u.setPassword("password2");
        u.setFirstName("FirstName2");
        u.setLastName("LastName2");
        u.setDob(new Date());
        u.setGender('M');
        u.setSalt("SALT_2");
        u.setPhone(9876543211L);

        try {
            u = repo.save(u);
        } catch (ValidationException e) {
            e.printStackTrace();
        }

        try {
            repo.delete(u);
        } catch (ValidationException e) {
            e.printStackTrace();
        }

        RegistrationData data = new RegistrationData();

        try {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.MONTH, -10);
            data.setDob(c.getTime());
            validator.validate(data);
        } catch (ValidationException e) {
            for (ValidationError error : e.getErrorList() ){
                System.out.println(error.toString());
            }
        }
    }

}



