/*
 * @(#)LoanServiceImpl.java 1.0 Nov 27, 2017
 */
package service.impl;

import builder.LoanDataBuilder;
import data.LoanData;
import domain.BookCopy;
import domain.Loan;
import domain.User;
import domain.repository.BookCopyRepository;
import domain.repository.BookRespository;
import domain.repository.LoanRepository;
import domain.repository.UserRepository;
import exception.BookNotFoundException;
import exception.ValidationError;
import exception.ValidationException;
import service.LoanService;
import service.ServiceFactory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.util.Calendar.DATE;

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

    public static final int ISSUE_DAYS = 21;

    public LoanServiceImpl() {
        loanRepository = ServiceFactory.getLoanRepository();
        bookRespository = ServiceFactory.getBookRepository();
        bookCopyRepository = ServiceFactory.getBookCopyRepository();
        userRepository = ServiceFactory.getUserRepository();
    }

    @Override
    public LoanData issueBook(Long bookCopyId, Long userId) throws ValidationException {
        BookCopy bookCopy  = bookCopyRepository.findById(bookCopyId);
        User u = userRepository.findById(userId);

        LoanData data = null;

        if (bookCopy == null || u == null){
            throw new ValidationException("Can't loan a book. Either the copy or the user is invalid.",
                    new ValidationError("book", "invalid", "Can't loan a book. Either the copy or the user is invalid.."));
        }

        if (bookCopy != null){
            //see if it is already Loaned out.
            Loan currentLoan = loanRepository.findCurrentLoanByBookCopyId(bookCopyId);
            if (currentLoan != null){
                throw new ValidationException("Can't loan a book that is already loaned.",
                        new ValidationError("book", "already loaned", "Can't loan a book that is already loaned."));
            }
            Loan loan = new Loan();
            loan.setBookCopyId(bookCopyId);
            loan.setIssueDate(getCurrentDate());
            loan.setDueDate(getDateFromNow(ISSUE_DAYS));
            loan.setUserId(userId);
            loan = loanRepository.save(loan);
            data = new LoanDataBuilder().loan(loan).buildLoanData();
        }
        return data;
    }

    @Override
    public LoanData getLoanById(Long loanId) {
        Loan loan =  loanRepository.findById(loanId);
        LoanData data = new LoanDataBuilder().loan(loan).buildLoanData();
        return data;
    }

    @Override
    public List<LoanData> getLoansByBookCopyId(Long bookCopyId) {
        List<LoanData> loanDataList = new ArrayList<>();
        List<Loan> loans = loanRepository.findAllLoansByCopyId(bookCopyId);

        if (loans != null && !loans.isEmpty()){
            for (Loan loan : loans){
                LoanData data = new LoanDataBuilder().loan(loan).buildLoanData();
                loanDataList.add(data);
            }
        }
        return loanDataList;
    }

    @Override
    public List<LoanData> getOutstandingLoans() {
        List<LoanData> loanDataList = new ArrayList<>();
        List<Loan> loans = loanRepository.findOutstandingLoans();

        if (loans != null && !loans.isEmpty()){
            for (Loan loan : loans){
                LoanData data = new LoanDataBuilder().loan(loan).buildLoanData();
                loanDataList.add(data);
            }
        }
        return loanDataList;
    }

    @Override
    public List<LoanData> getAllLoans() {
        List<LoanData> loanDataList = new ArrayList<>();
        List<Loan> loans = loanRepository.findAllLoans();

        if (loans != null && !loans.isEmpty()){
            for (Loan loan : loans){
                LoanData data = new LoanDataBuilder().loan(loan).buildLoanData();
                loanDataList.add(data);
            }
        }
        return loanDataList;
    }

    @Override
    public void returnBook(Long bookCopyId) throws ValidationException {
        //find the loan id.
        Loan loan  = loanRepository.findCurrentLoanByBookCopyId(bookCopyId);
        if (loan != null){
            loan  = loanRepository.returnBook(loan.getId());
            return;
        }

        throw new ValidationException("Can't return a book that is not loaned.",
                new ValidationError("book", "not loaned", "Can't return a book that is not loaned"));
    }

    @Override
    public int getNumberOfLoanedCopies(Long bookId) throws BookNotFoundException {
        return loanRepository.getNumberOfLoanedCopies(bookId);
    }

    private Date getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        return cal.getTime();
    }

    private Date getDateFromNow(int howManyDays){
        Calendar cal = Calendar.getInstance();
        cal.add(DATE, howManyDays);
        return cal.getTime();
    }


}



