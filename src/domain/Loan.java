/*
 * @(#)Loan.java 1.0 Nov 27, 2017
 */
package domain;

import java.util.Date;

/**
 * <code>Loan</code> class is  Domain Class
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
public class Loan {
    /*
ID BIGINT NOT NULL  AUTO_INCREMENT,
BOOK_ID BIGINT NOT NULL, -- Foreign key to book table(ben)
USER_ID BIGINT NOT NULL,
ISSUE_DATE DATE NOT NULL,
DUE_DATE DATE NOT NULL,
RETURNED_DATE DATE,
     */

    private Long id;
    private Long bookId;
    private Long userId;
    private Date issueDate;
    private Date dueDate;
    private Date returnedDate;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}



