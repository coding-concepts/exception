/*
 * @(#)RegistrationData.java 1.0 Sep 22, 2017
 */
package data;

import java.util.Date;

/**
 * <code>RegistrationData</code> class is  Data Class For Registration Data. This class is for learning purpose only.
 *
 * <p>
 * <p>
 * <pre>
 * <strong>History</strong>    Name              Date            Description
 * <strong>History</strong>    --------------------------------------------------------------------
 * <strong>History</strong>   Pratyush Giri    9/22/17           Created. Sample from : https://accounts.google.com/SignUp?hl=en
 * </pre>
 *
 * @author Pratyush Giri
 * @since Sep 22, 2017
 */
public class RegistrationData {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String confirmPassWord;

    private Date dob;

    private char gender; //M, F, O - Others, N - Rather Not Say

    private long phone;

    /**
     * Gets Date of Birth.
     * @return Date of Birth.
     */
    public Date getDob() {
        return dob;
    }

    /**
     * Sets  Date of Birth.
     * @param dob  Date of Birth.
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * Gets Confirm Password;
     *
     * @return Confirm Password;
     */
    public String getConfirmPassWord() {
        return confirmPassWord;
    }

    /**
     * Sets Confirm Password;
     * @param confirmPassWord Confirm Password;
     */
    public void setConfirmPassWord(String confirmPassWord) {
        this.confirmPassWord = confirmPassWord;
    }

    /**
     * Gets Email.
     * @return Email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets Email.
     * @param email Email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets FirstName.
     * @return FirstName.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets  FirstName.
     * @param firstName  FirstName.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets Gender.
     * @return Gender.
     */
    public char getGender() {
        return gender;
    }

    /**
     * Sets  Gender.
     * @param gender  Gender.
     */
    public void setGender(char gender) {
        this.gender = gender;
    }

    /**
     * Gets Last Name.
     * @return Last Name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets   Last Name.
     * @param lastName Last Name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets  Password;
     * @return Confirm Password;
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets Password;
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets Phone.
     * @return Phone.
     */
    public long getPhone() {
        return phone;
    }

    /**
     * Sets  Phone.
     * @param phone  Phone.
     */
    public void setPhone(long phone) {
        this.phone = phone;
    }
}



