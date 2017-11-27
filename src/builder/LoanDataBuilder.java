/*
 * @(#)LoanDataBuilder.java 1.0 Nov 27, 2017
 */
package builder;

import data.LoanData;
import domain.Book;
import domain.BookCopy;
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
    private Book book;
    private BookCopy bookCopy;

    public LoanDataBuilder loan(Loan loan) {
        this.loan = loan;
        return this;
    }

    public LoanDataBuilder bookCopy(BookCopy bookCopy) {
        this.bookCopy = bookCopy;
        return this;
    }
    public LoanDataBuilder book(Book book) {
        this.book = book;
        return this;
    }

    public LoanData buildLoanData() {
        LoanData data = new LoanData();

        //TODO FILL THIS UP.

        return data;
    }

}



