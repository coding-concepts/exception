/*
 * @(#)BookData.java 1.0 Nov 26, 2017
 */
package data;

import java.util.List;

/**
 * <code>BookData</code> class is  BookData
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
public class BookData {

    private List<Long> bookCopyIds;

    private String title;

    private String author;

    private Long bookId;


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Long> getBookCopyIds() {
        return bookCopyIds;
    }

    public void setBookCopyIds(List<Long> bookCopyIds) {
        this.bookCopyIds = bookCopyIds;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}



