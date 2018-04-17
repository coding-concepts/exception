/*
 * @(#)UserRepository.java 1.0 Oct 25, 2017
 */
package domain.repository;

import domain.User;
import exception.ValidationException;

import java.util.List;

/**
 * <code>UserRepository<code> class is  defines methods for usert Table.
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
public interface UserRepository {

    User findByEmailId(String emailId);

    User save (User u) throws ValidationException  ;

    void delete(User u)  throws ValidationException;

    List<User> getUsers();

    User findById(Long userId);
}