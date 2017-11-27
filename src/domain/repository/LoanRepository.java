/*
 * @(#)LoanRepository.java 1.0 Nov 27, 2017
 */
package domain.repository;

import domain.Loan;

import java.util.List;

/**
 * <code>LoanRepository<code> class is  Repository class for Loans
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
public interface LoanRepository {

    /**
     * Saves a Loan
     * @param loan Loan
     * @return Loan
     */
    Loan save(Loan loan);

    /**
     * Finds a loan with loan id.
     * @param LoanId loan id
     * @return loan.
     */
    Loan findById(Long LoanId);

    /**
     * Returns a loan
     * @param LoanId  ID
     * @return Loan
     */
    Loan returnBook(Long LoanId);

    /**
     * Find all loans with book Copy id. History...
     * @param bookCopyId book copy id.
     * @return ListOfLoan.
     */
    List<Loan> findAllLoansByCopyId(Long bookCopyId);

    /**
     * Find the active loan with book Copy id.
     * @param bookCopyId book copy id.
     * @return ListOfLoan.
     */
    Loan findCurrentLoanByBookCopyId(Long bookCopyId);

    /**
     * Find outstanding Loans.
     * @return outstanding loans
     */
    List <Loan> findOutstandingLoans();

    /**
     * Find all loans
     * @return all loans
     */
    List<Loan> findAllLoans();

    /**
     * Counts all Loans
     * @return loan count
     */
    int countAllLoans();

    /**
     * Counts outstanding Loans
     * @return outstanding loans.
     */
    int countOutstandingLoans();

    /**
     * Gets the loaned copy count of the bookId.
     * @param bookId
     * @return  number of Loaned copies
     */
    int getNumberOfLoanedCopies(Long bookId);


}