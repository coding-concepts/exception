/*
 * @(#)BookNotFoundException.java 1.0 Nov 27, 2017
 */
package exception;

/**
 * <code>BookNotFoundException</code> class is  WHen Book is not found.
 * <p>
 * <p>
 * <pre>
 * <strong>History</strong>    Name              Date            Description
 * <strong>History</strong>    --------------------------------------------------------------------
 * <strong>History</strong>   Pratyush Giri    11/27/17
 * </pre>
 *
 * @author Pratyush Giri
 * @since Nov 27, 2017
 */
public class BookNotFoundException  extends RuntimeException{
    public BookNotFoundException(Long bookId) {
        super ("Did not find the book. BookId : "+ bookId);
    }

    public BookNotFoundException(String s) {
        super (s);
    }
}



