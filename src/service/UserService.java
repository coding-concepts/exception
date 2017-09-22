/*
 * @(#)UserService.java 1.0 Sep 22, 2017
 */
package service;

import data.RegistrationData;
import exception.ValidationException;

/**
 * <code>UserService</code> class is  Service Interfact for User
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
public interface UserService {

    /**
     * This is a void class for now. But we will see how this evolves.
     * @param data user Registration Data.
     */
    void registerUser(RegistrationData data) throws ValidationException;



}



