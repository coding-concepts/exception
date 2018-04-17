/*
 * @(#)Validator.java 1.0 Sep 22, 2017
 */
package validator;

import exception.ValidationError;
import exception.ValidationException;

/**
 * <code>Validator<code> class is  Interface all validators must implement
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
public interface Validator {

    /**
     * Validates the object.
     * @param t the object
     * @throws ValidationException
     */
    void validate (Object t) throws ValidationException;

    /**
     * Checks for null.
     * @param o default object.
     */
    default void checkNull(Object o) throws ValidationException {
        if (o == null) {
            throw new ValidationException("Passed Object is null", new ValidationError("object", "null", "pass a valid one."));
    }
    }

}