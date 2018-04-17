/*
 * @(#)LoanService.java 1.0 Nov 27, 2017
 */
package service;

import data.LoanData;
import exception.BookNotFoundException;
import exception.ValidationException;

import java.util.List;

/**
 * <code>LoanService<code> class is  Loan Service
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
public interface LoanService {

    /**
     * Issues a Loan Given the user id and the book id.
     * @param bookCopyId book copy id
     * @param userId userId.
     * @return Loan Data Object after saving.
     */
    LoanData issueBook(Long bookCopyId, Long userId) throws ValidationException;

    /**
     * Given a loan id, retrieves the loan data.
     * @param loanId loan id.
     * @return LoanData
     */
    LoanData getLoanById(Long loanId);

    /**
     * Given a bookcopyId, retrieves all the loans. The history.
     * @param bookCopyId bookCopyId
     * @return list of LoanData
     */
    List<LoanData> getLoansByBookCopyId(Long bookCopyId);

    /**
     * Lists all the outstanding loans
     * @return list of LoanData outstanding
     */
    List<LoanData> getOutstandingLoans();

    /**
     * Lists the entire loans.
     * @return list of LoanData
     */
    List<LoanData> getAllLoans();

    /**
     * Returns a book copy.
     * @param bookCopyId book copy id.
     */
    void returnBook(Long bookCopyId) throws ValidationException ;

    /**
     * Gets the loaned copy count of the bookId.
     * @param bookId
     * @return  number of Loaned copies
     * @throws BookNotFoundException
     */
    int getNumberOfLoanedCopies(Long bookId) throws BookNotFoundException;



}