/*
 * @(#)TestRegistrationDataValidator.java 1.0 Sep 22, 2017
 */

import data.RegistrationData;
import exception.ValidationError;
import exception.ValidationException;
import validator.RegistrationDataValidator;
import validator.Validator;

import java.util.Calendar;

/**
 * <code>TestRegistrationDataValidator</code> class is
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
public class TestRegistrationDataValidator {

    static Validator validator =  RegistrationDataValidator.getInstance();

    public static void main(String[] args){
        RegistrationData data = new RegistrationData();

        try {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.MONTH, 10);
            data.setDob(c.getTime());
            validator.validate(data);
        } catch (ValidationException e) {
            for (ValidationError error : e.getErrorList() ){
                System.out.println(error.toString());
            }
        }
    }

}



