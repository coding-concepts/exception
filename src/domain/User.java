/*
 * @(#)User.java 1.0 Oct 25, 2017
 */
package domain;

import java.util.Date;

/**
 * <code>User</code> class is  Entity Class for table User
 * <p>
 * <p>
 * <pre>
 * <strong>History</strong>    Name              Date            Description
 * <strong>History</strong>    --------------------------------------------------------------------
 * <strong>History</strong>   Pratyush Giri    10/25/17
 * </pre>
 *
 * @author Pratyush Giri
 * @since Oct 25, 2017
 */
public class User {
    /*
EMAIL VARCHAR(255),
FIRST_NAME  VARCHAR(255),
LAST_NAME  VARCHAR(255),
GENDER CHAR(1),
PHONE CHAR(10),
DOB DATE,
PASSWORD VARCHAR(255),
SALT  VARCHAR(255),
PRIMARY KEY (EMAIL)
     */
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Date dob;

    private Character gender; //M, F, O - Others, N - Rather Not Say

    private Long phone;

    private String salt;

    private String hashingAlgo;


    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHashingAlgo() {
        return hashingAlgo;
    }

    public void setHashingAlgo(String hashingAlgo) {
        this.hashingAlgo = hashingAlgo;
    }
}



