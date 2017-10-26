/*
 * @(#)SystemException.java 1.0 Oct 25, 2017
 */
package exception;

/**
 * <code>SystemException</code> class is
 * <p>
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
public class SystemException extends RuntimeException {

    public SystemException() {
        super ("Something Unexpected Happened. Please check");
    }

    public SystemException(String s) {
        super (s);
    }
}



