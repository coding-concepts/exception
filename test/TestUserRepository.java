/*
 * @(#)TestUserRepository.java 1.0 Oct 26, 2017
 */

import domain.User;
import domain.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import service.ServiceFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * <code>TestUserRepository</code> class is
 * <p>
 * <p>
 * <pre>
 * <strong>History</strong>    Name              Date            Description
 * <strong>History</strong>    --------------------------------------------------------------------
 * <strong>History</strong>   Pratyush Giri    10/26/17
 * </pre>
 *
 * @author Pratyush Giri
 * @since Oct 26, 2017
 */
public class TestUserRepository {

    private UserRepository repository;

    @Before
    public void setup() {
        repository = ServiceFactory.getUserRepository();
    }

    @Test
    public void testSingleUser(){

        User usr = getUser("createUser@codingconcepts.com");

        User dbUser = repository.findByEmailId(usr.getEmail());

        assertNull(dbUser);

        //Create User

        try {
            dbUser = repository.save(usr);
            assertNotNull(dbUser);
            match(usr, dbUser);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Test failed to create user");
        }

        //Find User By Email
        try {
            dbUser = repository.findByEmailId(usr.getEmail());
            assertNotNull(dbUser);
            match(usr, dbUser);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Test failed to find user");
        }

        //UPDATE
        usr = updateUser(usr);
        try {
            repository.save(usr);
            assertNotNull(dbUser);
            dbUser = repository.findByEmailId(usr.getEmail());
            match(usr, dbUser);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Test failed to update user");
        }

        //Delete User
        try {
            repository.delete(dbUser);
            assertNotNull(dbUser);
            dbUser = repository.findByEmailId(usr.getEmail());
            assertNull(dbUser);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Test failed to delete user");
        }
    }

    @Test
    public void testMultipleUsers() {
        User user1 = getUser("user1@codingconcepts.com");
        User user2 = getUser("user2@codingconcepts.com");
        User user3 = getUser("user3@codingconcepts.com");

        try {
            repository.save(user1);
            repository.save(user2);
            repository.save(user3);
        }catch (Exception e) {
            e.printStackTrace();
            fail("Test failed to create multiple users");
        }

        //find them
        List<User> users = repository.getUsers();
        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertEquals(3, users.size());

        //now delete them
        try{
            repository.delete(user1);
            users = repository.getUsers();
            assertNotNull(users);
            assertFalse(users.isEmpty());
            assertEquals(2, users.size());

            repository.delete(user2);
            users = repository.getUsers();
            assertNotNull(users);
            assertFalse(users.isEmpty());
            assertEquals(1, users.size());

            repository.delete(user3);
            users = repository.getUsers();
            assertNotNull(users);
            assertTrue(users.isEmpty());

        }catch (Exception e) {
            e.printStackTrace();
            fail("Test failed to delete multiple users");
        }

    }


    private void match(User our, User dbUser){
        assertEquals(our.getEmail(), dbUser.getEmail());
        assertEquals(our.getFirstName(), dbUser.getFirstName());
        assertEquals(our.getLastName(), dbUser.getLastName());
        assertEquals(our.getGender(), dbUser.getGender());
        assertEquals(getFormattedDate(our.getDob()), getFormattedDate(dbUser.getDob()));
        assertEquals(our.getPassword(), dbUser.getPassword());
        assertEquals(our.getPhone(), dbUser.getPhone());
        assertEquals(our.getSalt(), dbUser.getSalt());
    }

    private User getUser(String userEmail) {
        User usr = new User();
        usr.setEmail(userEmail);
        usr.setPassword("password");
        usr.setFirstName("FirstName");
        usr.setLastName("LastName");
        usr.setPhone(1234567890L);
        usr.setDob(new Date());
        usr.setGender('M');
        usr.setSalt("SALT_1");
        return usr;
    }


    private User updateUser(User usr) {
        usr.setPassword("UpdatedPassword");
        usr.setFirstName("UpdatedFirstName");
        usr.setLastName("UpdatedLastName");
        usr.setPhone(999_999_9999L);
        usr.setDob(new Date());
        usr.setGender('F');
        usr.setSalt("Updated_SALT");
        return usr;
    }


    private Date getFormattedDate(Date dt){
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formatter.parse(formatter.format(dt));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}



