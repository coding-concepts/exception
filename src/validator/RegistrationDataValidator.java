/*
 * @(#)RegistrationDataValidator.java 1.0 Sep 22, 2017
 */
package validator;

import data.RegistrationData;
import exception.ValidationError;
import exception.ValidationException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <code>RegistrationDataValidator</code> class is  Validates RegistrationData
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
public class RegistrationDataValidator implements Validator {

    @Override
    public void validate(Object object) throws ValidationException {
        checkNull(object);
        /*
         *  This should always be called with RegistrationData, but we do not know if we can trust it.
         *  so Lets check. Note that this exception will be uncovered during the testing time and
         *  when our code goes to production, we will not have this at all.
         *  So lets not make a if to check it. rather we will call this with a try catch block.
         */
        List<ValidationError> errors = new ArrayList<>();
        try {

            RegistrationData data = (RegistrationData)object;
            validateFirstName(errors, data.getFirstName());
            validateLastName(errors, data.getLastName());
            validateEmail(errors, data.getEmail());
            validatePassword(errors, data.getPassword(), data.getConfirmPassWord());
            validatePhone(errors, data.getPhone());
            validateDateOfBirth(errors, data.getDob());
            validateGender(errors, data.getGender());

            //now that we have done all validations. we check and see if we have any Errors, then we should throw an exception.
            if (! errors.isEmpty()) {
                throw new ValidationException("validation errors happened.", errors);
            }

        } catch(ClassCastException e) {
            // This is because there is a piece of bad code somewhere.
            //Log it.
            String message = "RegistrationDataValidator should be called to validate RegistrationData objects only.";
            System.out.println(message);
            throw new ValidationException(message, new ValidationError("data-object", "bad-type", "provide-correct-class"));
        }
    }

    private void validateGender(List<ValidationError> errors, char gender) {
        if ( gender != 'M' || gender  != 'F' ||gender != 'O' || gender  != 'N' ) {
            errors.add(new ValidationError("gender", "bad-value", "valid genders are M, F, O, and N."));
        }
    }

    private void validateDateOfBirth(List<ValidationError> errors, Date dob) {
        if (dob == null){
            errors.add(new ValidationError("date-of-birth", "empty", "date of birth must be present."));
        } else if (dob.after(Calendar.getInstance().getTime())){
            errors.add(new ValidationError("date-of-birth", "bad-date", "date of birth can't be future."));
        }
    }

    private void validatePhone(List<ValidationError> errors, long phone) {
        //this is just a basic one
        if (phone < 1000000000 || phone > 9999999999L) {
            errors.add(new ValidationError("phone", "bad-format", "provide a valid phone"));
        }
    }

    private void validatePassword(List<ValidationError> errors, String password, String confirmPassWord) {
        if (password == null || password.isEmpty()){
            errors.add(new ValidationError("password", "empty", "provide a valid password"));
        } else if (!password.equals(confirmPassWord)){
            errors.add(new ValidationError("password", "mismatch", "passwords should match"));
        }
    }

    private void validateEmail(List<ValidationError> errors, String email) {
        if (email == null || email.isEmpty()){
            errors.add(new ValidationError("email", "empty", "provide a valid email"));
        } else if (!email.contains("@")) {
            errors.add(new ValidationError("email", "bad-format", "provide a valid email"));
        }
    }

    private void validateFirstName(List<ValidationError> errors, String firstName) {
        if (firstName == null || firstName.isEmpty()){
            errors.add(new ValidationError("first-name", "empty", "provide a valid first name"));
        }
        //may be you can check more things later...
    }

    private void validateLastName(List<ValidationError> errors, String lastName) {
        if (lastName == null || lastName.isEmpty()){
            errors.add(new ValidationError("last-name", "empty", "provide a valid last name"));
        }
        //may be you can check more things later...
    }
}



