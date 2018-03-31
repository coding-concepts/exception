/*
 * @(#)UserReporsitoryImpl.java 1.0 Oct 25, 2017
 */
package domain.repository;

import domain.User;
import exception.SystemException;
import exception.ValidationError;
import exception.ValidationException;
import service.DatabaseUtility;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <code>UserReporsitoryImpl</code> class is  Implementation
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
public class UserRepositoryImpl implements  UserRepository {

    @Override
    public User findByEmailId(String emailId) {

        String query = "SELECT  ID, EMAIL, FIRST_NAME, LAST_NAME, GENDER, PHONE, DOB, PASSWORD, SALT, FIRST_NAME_KEY, LAST_NAME_KEY FROM USER WHERE EMAIL = ? ";

        try {
            PreparedStatement ps = DatabaseUtility.getConnection().prepareStatement(query);
            ps.setString(1, emailId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setId(rs.getLong("ID"));
                u.setEmail(rs.getString("EMAIL"));
                u.setFirstName(rs.getString("FIRST_NAME"));
                u.setLastName(rs.getString("LAST_NAME"));
                u.setGender(rs.getString("GENDER").charAt(0));
                u.setPhone(rs.getLong("PHONE"));
                u.setDob(rs.getDate("DOB"));
                u.setPassword(rs.getString("PASSWORD"));
                u.setSalt(rs.getString("SALT"));
                u.setFirstNameKey(rs.getString("FIRST_NAME_KEY"));
                u.setLastNameKey(rs.getString("LAST_NAME_KEY"));
                u.setHashingAlgo(rs.getString("HASHING_ALGO"));
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SystemException("Exception Happened while trying to find  a user: "+ emailId);
        } finally {
            DatabaseUtility.releaseConnection();
        }
        return null;
    }

    @Override
    public User save(User u) throws ValidationException {
        if (u == null){
            throw new ValidationException("Can't Save a NULL user",
                    new ValidationError("user", "business-error", "Can't Save a NULL user"));
        }

        User dbUser = findByEmailId(u.getEmail());

        if (dbUser == null){
            u = insert(u);
        } else {
            u = update (dbUser, u);
        }


        return u;
    }

    private User update(User oldUser, User newUser) {
        String query = "Update USER SET  FIRST_NAME = ?, LAST_NAME = ?, GENDER = ?, PHONE = ?, DOB = ? , PASSWORD = ?, SALT = ?  WHERE EMAIL = ?  ";

        try {

            PreparedStatement ps = DatabaseUtility.getConnection().prepareStatement(query);
            //first name
            if (newUser.getFirstName() != null && !"".equals(newUser.getFirstName() )) {
                ps.setString(1, newUser.getFirstName());
            } else {
                ps.setString(1, oldUser.getFirstName());
            }
            //last name
            if (newUser.getLastName() != null && !"".equals(newUser.getLastName() )) {
                ps.setString(2, newUser.getLastName());
            } else {
                ps.setString(2, oldUser.getLastName());
            }

            //gender
            if (newUser.getGender() != ' '){
                ps.setString(3, String.valueOf(newUser.getGender()));
            } else {
                ps.setString(3, String.valueOf(oldUser.getGender()));
            }

            //phone
            if (newUser.getPhone() != null){
                ps.setLong(4, newUser.getPhone());
            } else {
                ps.setLong(4, oldUser.getPhone());
            }

            //DOB
            if (newUser.getDob() != null){
                ps.setDate(5, new java.sql.Date(newUser.getDob().getTime()) );
            } else {
                ps.setDate(5, new java.sql.Date(oldUser.getDob().getTime()) );
            }

            //password
            if (newUser.getPassword() != null){
                ps.setString(6, newUser.getPassword() );
            } else {
                ps.setString(6, oldUser.getPassword() );
            }

            //password
            if (newUser.getSalt() != null){
                ps.setString(7, newUser.getSalt() );
            } else {
                ps.setString(7, oldUser.getSalt() );
            }

            ps.setString(8, oldUser.getEmail());

            //now Run this in database server

            ps.executeUpdate();

            //commit it.
            DatabaseUtility.commitTransaction();

        }catch (SQLException e) {

            e.printStackTrace();
            DatabaseUtility.rollbackTransaction();
            throw new SystemException("Exception Happened while trying to update a user: "+ oldUser.getEmail());
        } finally {
            DatabaseUtility.releaseConnection();
        }
        return findByEmailId(oldUser.getEmail());

    }

    private User insert(User u) {
        String query = "INSERT INTO USER"
                + "(EMAIL, FIRST_NAME, LAST_NAME, GENDER, PHONE, DOB, PASSWORD, SALT, FIRST_NAME_KEY, LAST_NAME_KEY, HASHING_ALGO ) "
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = DatabaseUtility.getConnection().prepareStatement(query);
            ps.setString(1, u.getEmail());
            ps.setString(2, u.getFirstName());
            ps.setString(3, u.getLastName());
            ps.setString(4, String.valueOf(u.getGender()));
            ps.setLong(5, u.getPhone());
            if (u.getDob() != null){
                ps.setDate(6, new java.sql.Date(u.getDob().getTime()));
            }
            ps.setString(7, u.getPassword());
            ps.setString(8, u.getSalt()); //we will do this later
            ps.setString(9, u.getFirstNameKey()); //we will do this later
            ps.setString(10, u.getLastNameKey()); //we will do this later
            ps.setString(11, u.getHashingAlgo());
            ps.executeUpdate();
            DatabaseUtility.commitTransaction();
        } catch (SQLException e) {
            DatabaseUtility.rollbackTransaction();
            e.printStackTrace();
            throw new SystemException("Exception Happened while trying to insert a user: "+ u.getEmail());
        } finally {
            DatabaseUtility.releaseConnection();
        }
        return findByEmailId(u.getEmail());
    }

    @Override
    public void delete(User u) throws ValidationException {
        User dbUser = findByEmailId(u.getEmail());
        if (dbUser == null){
            throw new ValidationException("User Does not exist!", new ValidationError("user", "business-error", "User does not exist"));
        }
        String query = "DELETE FROM USER WHERE EMAIL = ? ";
        try {
            PreparedStatement ps = DatabaseUtility.getConnection().prepareStatement(query);
            ps.setString(1, u.getEmail());
            ps.executeUpdate();
            DatabaseUtility.commitTransaction();
        } catch (SQLException e) {
            DatabaseUtility.rollbackTransaction();
            e.printStackTrace();
            throw new SystemException("Exception Happened while trying to delete a user: "+ u.getEmail());
        } finally {
            DatabaseUtility.releaseConnection();
        }
    }

    @Override
    public List<User> getUsers() {

        /*
           this can be a very expensive operation.
           So once your user table reach more than 1000 users
           you should change this.
        */

        List<User> users = new ArrayList<>();

        String query = "SELECT ID,  EMAIL, FIRST_NAME, LAST_NAME, GENDER, PHONE, DOB, PASSWORD, SALT FROM USER ";

        try {
            PreparedStatement ps = DatabaseUtility.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getLong("ID"));
                u.setEmail(rs.getString("EMAIL"));
                u.setFirstName(rs.getString("FIRST_NAME"));
                u.setLastName(rs.getString("LAST_NAME"));
                u.setGender(rs.getString("GENDER").charAt(0));
                u.setPhone(rs.getLong("PHONE"));
                u.setDob(rs.getDate("DOB"));
                u.setPassword(rs.getString("PASSWORD"));
                u.setSalt(rs.getString("SALT"));
                users.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SystemException("Exception Happened while trying to find  all users");
        } finally {
            DatabaseUtility.releaseConnection();
        }


        return users;
    }

    @Override
    public User findById(Long userId) {

        String query = "SELECT  ID, EMAIL, FIRST_NAME, LAST_NAME, GENDER, PHONE, DOB, PASSWORD, SALT FROM USER WHERE ID = ? ";

        try {
            PreparedStatement ps = DatabaseUtility.getConnection().prepareStatement(query);
            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setId(rs.getLong("ID"));
                u.setEmail(rs.getString("EMAIL"));
                u.setFirstName(rs.getString("FIRST_NAME"));
                u.setLastName(rs.getString("LAST_NAME"));
                u.setGender(rs.getString("GENDER").charAt(0));
                u.setPhone(rs.getLong("PHONE"));
                u.setDob(rs.getDate("DOB"));
                u.setPassword(rs.getString("PASSWORD"));
                u.setSalt(rs.getString("SALT"));
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SystemException("Exception Happened while trying to find  a user: "+ userId);
        } finally {
            DatabaseUtility.releaseConnection();
        }
        return null;
    }
}



