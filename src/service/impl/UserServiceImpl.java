/*
 * @(#)UserServiceImpl.java 1.0 Sep 22, 2017
 */
package service.impl;

import data.RegistrationData;
import exception.ValidationError;
import exception.ValidationException;
import service.UserService;

import java.util.HashMap;

/**
 * <code>UserServiceImpl</code> class is  Implementation class for UserService interface.
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
public class UserServiceImpl implements UserService {

    private static HashMap<String,RegistrationData > userTable = new HashMap<>();

    /**
     * This is a void class for now. But we will see how this evolves.
     *
     * @param data user Registration Data.
     */
    @Override
    public void registerUser(RegistrationData data) throws ValidationException {
        runBusinessValidation(data);
        //add the user data to table
        userTable.put(data.getEmail(), data);
    }

    private void runBusinessValidation(RegistrationData data) throws ValidationException {
        //We may need to validate if the user is already registered
        if (userTable.containsKey(data.getEmail())){
            throw new ValidationException("User is registered.", new ValidationError("email", "registered", "User is registered."));
        }
    }

}



