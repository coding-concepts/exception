/*
 * @(#)BookService.java 1.0 Nov 26, 2017
 */
package service;

import data.BookData;
import exception.ValidationException;

/**
 * <code>BookService</code> class is  Service For Book
 * <p>
 * <p>
 * <pre>
 * <strong>History</strong>    Name              Date            Description
 * <strong>History</strong>    --------------------------------------------------------------------
 * <strong>History</strong>   Pratyush Giri    11/26/17
 * </pre>
 *
 * @author Pratyush Giri
 * @since Nov 26, 2017
 */
public interface BookService {

    /**
     * Adds a book.]
     * @param bookData  bookData
     * @return Book Data with newly created Ids.
     */
    BookData addBook(BookData bookData) throws ValidationException;

    /**
     * Adds a book.
     * @param bookData  bookData
     * @param quantity  quantity
     * @return
     * @throws ValidationException
     */
    BookData addBook(BookData bookData, int quantity) throws ValidationException;


    /**
     * Adds book copies
     * @param bookId  bookId
     * @param  quantity quantity
     * @return Book Data with newly created Ids.
     */
    BookData addBookCpoies(long bookId, int quantity) throws ValidationException;




}



