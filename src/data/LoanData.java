/*
 * @(#)LoanData.java 1.0 Nov 27, 2017
 */
package data;

import java.util.Date;

/**
 * <code>LoanData</code> class is  Loan Data Transfer class.
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
public class LoanData {
    private Long loanId;
   // private Long bookCopyId;
    private Long userId;
    private Date issueDate;
    private Date dueDate;
    private Date returnedDate;

    private BookCopyData  bookCopyData;

    public BookCopyData getBookCopyData() {
        return bookCopyData;
    }

    public void setBookCopyData(BookCopyData bookCopyData) {
        this.bookCopyData = bookCopyData;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
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



