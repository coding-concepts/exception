/*
 * @(#)BookBuilder.java 1.0 Nov 26, 2017
 */
package builder;

import data.BookData;
import domain.Book;

/**
 * <code>BookBuilder</code> class is  .
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
public class BookBuilder {
    private Book book;
    private BookData bookData;

    /**
     * BookBuilder
     * @param book nook
     * @return BookBuilder
     */
    public BookBuilder book(Book book) {
        this.book = book;
        return this;
    }

    /**
     * BookBuilder
     * @param bookData book data
     * @return BookBuilder
     */
    public BookBuilder bookData(BookData bookData) {
        this.bookData = bookData;
        return this;
    }

    /**
     * COnverts to Book Data
     * @return  Book Data
     */
    public BookData toBookData() {
        BookData bd = new BookData();
        if (book != null) {
            bd.setBookId(book.getId());
            bd.setAuthor(book.getAuthor());
            bd.setTitle(book.getTitle());
        }
        return bd;
    }

    /**
     * Returns Book
     * @return Book
     */
    public Book toBook() {
        Book bk = new Book();
        if (bookData != null) {
//            bk.setId(bookData.getBookId());
            bk.setAuthor(bookData.getAuthor());
            bk.setTitle(bookData.getTitle());
        }
        return bk;
    }
}



