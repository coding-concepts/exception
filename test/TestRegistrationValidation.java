/*
 * @(#)TestRegistrationValidation.java 1.0 Sep 22, 2017
 */

import data.RegistrationData;
import exception.ValidationError;
import exception.ValidationException;
import service.UserService;
import service.impl.UserServiceImpl;

/**
 * <code>TestRegistrationValidation</code> class is
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
public class TestRegistrationValidation {

    public static void main(String[] args){

        RegistrationData data = new RegistrationData();

        UserService service = new UserServiceImpl();

        try {
            service.registerUser(data);
        } catch (ValidationException e) {
           for (ValidationError error : e.getErrorList() ){
               System.out.println(error.toString());
           }
        }

    }

}



