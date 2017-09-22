/*
 * @(#)UserServiceImpl.java 1.0 Sep 22, 2017
 */
package service.impl;

import data.RegistrationData;
import exception.ValidationException;
import service.UserService;
import validator.RegistrationDataValidator;
import validator.Validator;

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

    Validator validator = new RegistrationDataValidator();
    /**
     * This is a void class for now. But we will see how this evolves.
     *
     * @param data user Registration Data.
     */
    @Override
    public void registerUser(RegistrationData data) throws ValidationException {

        validator.validate(data);

    }

}



