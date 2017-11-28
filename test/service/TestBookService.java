/*
 * @(#)TestBookService.java 1.0 Nov 27, 2017
 */
package service;

import data.BookData;
import exception.ValidationException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * <code>TestBookService</code> class is
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
public class TestBookService {

    private BookService bookService = ServiceFactory.getBookService();


    @Test
    public void testSaveBook() throws ValidationException {
        BookData bd = BookFactory.getBookData();
        assertNull(bd.getBookCopyIds());
        bd = bookService.addBook(bd);
        assertNotNull(bd);
        assertNotNull(bd.getBookCopyIds());
        assertEquals(1, bd.getBookCopyIds().size());
    }

    @Test
    public void testSaveBookMoreThan1() throws ValidationException {
        BookData bd = BookFactory.getBookData();
        assertNull(bd.getBookCopyIds());
        bd = bookService.addBook(bd, 5);
        assertNotNull(bd);
        assertNotNull(bd.getBookCopyIds());
        assertEquals(5, bd.getBookCopyIds().size());
    }

}



