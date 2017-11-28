/*
 * @(#)LoanDataBuilder.java 1.0 Nov 27, 2017
 */
package builder;

import data.BookCopyData;
import data.LoanData;
import domain.Loan;

/**
 * <code>LoanDataBuilder</code> class is  Builder class.
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
public class LoanDataBuilder {
    private Loan loan;


    public LoanDataBuilder loan(Loan loan) {
        this.loan = loan;
        return this;
    }

    public LoanData buildLoanData() {
        LoanData data = new LoanData();

        if (loan != null) {
            data.setUserId(loan.getUserId());
            data.setLoanId(loan.getId());
            data.setReturnedDate(loan.getReturnedDate());
            data.setDueDate(loan.getDueDate());
            data.setIssueDate(loan.getIssueDate());
            BookCopyData bcd  = new BookCopyData();
            bcd.setBookCopyId(loan.getBookCopyId());
            data.setBookCopyData(bcd);
        }

        return data;
    }

}



