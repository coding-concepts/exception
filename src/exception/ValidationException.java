/*
 * @(#)ValidationException.java 1.0 Sep 22, 2017
 */
package exception;

import java.util.ArrayList;
import java.util.List;

/**
 * <code>ValidationException</code> class is  is a Checked Exception class.
 * <p>
 * <p>
 * <pre>
 * <strong>History</strong>    Name              Date            Description
 * <strong>History</strong>    --------------------------------------------------------------------
 * <strong>History</strong>   Pratyush Giri    9/22/17            Created.
 * </pre>
 *
 * @author Pratyush Giri
 * @since Sep 22, 2017
 */
public class ValidationException extends Exception {

    private List<ValidationError> errorList;

    /**
     * Constructor.
     * @param message message
     * @param errorList list of errors.
     */
    public ValidationException(String message, List<ValidationError> errorList) {
        super(message);
        this.errorList  = errorList;
    }

    /**
     * Constructor. If you want to use this for one exception only.
     * @param message message
     */
    public ValidationException(String message, ValidationError anError) {
        super(message);
        List<ValidationError> list = new ArrayList<>();
        list.add(anError);
        this.errorList = list;
    }



    /**
     * This methods lets you add a ValidationError to an existing ValidationException.
     * @param anError a ValidationError
     */
    public void addError(ValidationError anError){
        if (errorList == null){

        }
        errorList.add(anError);
    }

    /**
     * Gets errorList
     * @return errorList
     */
    public List<ValidationError> getErrorList() {
        return errorList;
    }

    /**
     * Sets errorList
     * @param errorList errorList
     */
    public void setErrorList(List<ValidationError> errorList) {
        this.errorList = errorList;
    }
}



