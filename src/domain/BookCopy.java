package domain;



public class BookCopy {
    /***
     *
     ID BIGINT NOT NULL  AUTO_INCREMENT, -- BOOK COPY ID
     BOOK_ID BIGINT NOT NULL, -- the book id. Foreign key to book table(ben)
     */

    private long id;

    private long bookId;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getBookId() {
        return bookId;
    }
    public void setBookId(long bookId) {
        this.bookId = bookId;
    }
}
