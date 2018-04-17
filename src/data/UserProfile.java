/*
 * @(#)UserProfile.java 1.0 Oct 05, 2017
 */
package data;

import java.util.Date;

/**
 * <code>UserProfile</code> class is
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
public class UserProfile {

    private String firstName;

    private String lastName;

    private String email;

    private Date dob;

    private String gender; //M, F, O - Others, N - Rather Not Say

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
    public String getGender() {
        return gender;
    }

    /**
     * Sets  Gender.
     * @param gender  Gender.
     */
    public void setGender(String gender) {
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



