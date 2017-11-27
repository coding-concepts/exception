/*
 * @(#)LoanRepositoryImpl.java 1.0 Nov 27, 2017
 */
package domain.repository;

import domain.Loan;

import java.util.List;

/**
 * <code>LoanRepositoryImpl</code> class is  Implementation class.
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
public class LoanRepositoryImpl implements LoanRepository {
    @Override public Loan save(Loan loan) {
        return null;
    }

    @Override public Loan returnBook(Long LoanId) {
        return null;
    }

    @Override public List<Loan> findAllLoansByCopyId(Long bookCopyId) {
        return null;
    }

    @Override public List<Loan> findCurrentLoanByBookCopyId(Long bookCopyId) {
        return null;
    }

    @Override public List<Loan> findOutstandingLoans() {
        return null;
    }

    @Override public List<Loan> findAllLoans() {
        return null;
    }

    @Override public int countAllLoans() {
        return 0;
    }

    @Override public int countOutstandingLoans() {
        return 0;
    }

    @Override public int getNumberOfAvalableCopies(Long bookId) {
        return 0;
    }

    @Override public int getNumberOfLoanedCopies(Long bookId) {
        return 0;
    }

    @Override public int getNumberOfTotalCopies(Long bookId) {
        return 0;
    }
}



