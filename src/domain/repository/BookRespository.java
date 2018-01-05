/*
 * @(#)BookRespository.java 1.0 Nov 26, 2017
 */
package domain.repository;

import domain.Book;

import java.util.List;

/**
 * <code>BookRespository<code> class is  repository
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
public interface BookRespository {

    /**
     * saves a book
     * @param book book
     * @return saved book
     */
    Book save(Book book);

    /**
     * finds a book by title.
     * @param title title.
     * @return book
     */
    Book findByTitle(String title);

    /**
     * finds a book by title Partial case in sensitive.
     * @param title title.
     * @return book
     */
    List<Book> findByPartialTitle(String title);

    /**
     * finds a book by title Partial case in sensitive.
     * @param author author.
     * @return book
     */
    List<Book> findByPartialAuthor(String author);

    /**
     * FInd a book by Id.
     * @param bookId id
     * @return Book
     */
    Book findById(long bookId);
}