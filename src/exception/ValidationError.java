/*
 * @(#)ValidationError.java 1.0 Sep 22, 2017
 */
package exception;

/**
 * <code>ValidationError</code>  is  a class to represent the Validation Errors.
 * <p>
 * <p>
 * <pre>
 * <strong>History</strong>    Name              Date            Description
 * <strong>History</strong>    --------------------------------------------------------------------
 * <strong>History</strong>   Pratyush Giri    9/22/17          Created.
 * </pre>
 *
 * @author Pratyush Giri
 * @since Sep 22, 2017
 */
public class ValidationError {
    private String field;
    private String errorType;
    private String fix;

    /**
     * Constructor.
     * @param field field.
     * @param errorType error type.
     * @param fix fix message.
     */
    public ValidationError(String field, String errorType, String fix){
        this.field = field;
        this.errorType = errorType;
        this.fix = fix;
    }

    /**
     * Gets errorType
     * @return errorType
     */
    public String getErrorType() {
        return errorType;
    }

    /**
     * Sets errorType
     * @param errorType errorType
     */
    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    /**
     * Gets field
     * @return field
     */
    public String getField() {
        return field;
    }

    /**
     * Sets field
     * @param field field
     */
    public void setField(String field) {
        this.field = field;
    }

    /**
     * Gets Fix Message.
     * @return Fix Message.
     */
    public String getFix() {
        return fix;
    }

    /**
     * Sets  Fix Message.
     * @param fix  Fix Message.
     */
    public void setFix(String fix) {
        this.fix = fix;
    }

    /**
     * To String method.
     * @return string representation of the class.
     */
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ValidationError{");
        sb.append("field='").append(field).append('\'');
        sb.append(", errorType='").append(errorType).append('\'');
        sb.append(", fix='").append(fix).append('\'');
        sb.append('}');
        return sb.toString();
    }
}



