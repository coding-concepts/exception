package domain;



public class BookCopy {
    /***
     *
     ID BIGINT NOT NULL  AUTO_INCREMENT, -- BOOK COPY ID
     BOOK_ID BIGINT NOT NULL, -- the book id. Foreign key to book table(ben)
     */

    private Long id;

    private Long bookId;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getBookId() {
        return bookId;
    }
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
