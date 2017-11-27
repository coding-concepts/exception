/*
 * @(#)LoanServiceImpl.java 1.0 Nov 27, 2017
 */
package service.impl;

import builder.LoanDataBuilder;
import data.LoanData;
import domain.Loan;
import domain.repository.BookCopyRepository;
import domain.repository.BookRespository;
import domain.repository.LoanRepository;
import domain.repository.UserRepository;
import exception.BookNotFoundException;
import service.LoanService;
import service.ServiceFactory;

import java.util.List;

/**
 * <code>LoanServiceImpl</code> class is  LImplementtion of loan service
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
public class LoanServiceImpl implements LoanService {

    private LoanRepository loanRepository;

    private BookRespository bookRespository;

    private BookCopyRepository bookCopyRepository;

    private UserRepository userRepository;

    public LoanServiceImpl() {
        loanRepository = ServiceFactory.getLoanRepository();
        bookRespository = ServiceFactory.getBookRepository();
        bookCopyRepository = ServiceFactory.getBookCopyRepository();
        userRepository = ServiceFactory.getUserRepository();
    }

    @Override
    public LoanData issueBook(Long bookCopyId, Long userId) {


        return null;
    }

    @Override
    public LoanData getLoanById(Long loanId) {
        Loan loan =  loanRepository.findById(loanId);
        LoanData data = new LoanDataBuilder().loan(loan).buildLoanData();
        return data;
    }

    @Override
    public List<LoanData> getLoansByBookCopyId(Long bookCopyId) {
        return null;
    }

    @Override
    public List<LoanData> getOutstandingLoans() {
        return null;
    }

    @Override
    public List<LoanData> getAllLoans() {
        return null;
    }

    @Override
    public void returnBook(Long bookCopyId) {

    }

    @Override
    public int getNumberOfLoanedCopies(Long bookId) throws BookNotFoundException {
        return 5;
    }


}



