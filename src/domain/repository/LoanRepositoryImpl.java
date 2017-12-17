/*
 * @(#)LoanRepositoryImpl.java 1.0 Nov 27, 2017
 */
package domain.repository;

import domain.Loan;
import exception.SystemException;
import service.DatabaseUtility;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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
    Logger logger = Logger.getLogger("LoanRepositoryImpl");

    @Override
    public Loan save(Loan loan) {

        logger.info("Started Saving Loan ");


        String query  = "INSERT INTO LOAN (BOOK_COPY_ID , USER_ID, ISSUE_DATE, DUE_DATE) VALUES (?, ?, ?, ?)";
        try{
            PreparedStatement ps = DatabaseUtility.getConnection().prepareStatement(query);
            ps.setLong(1, loan.getBookCopyId());
            ps.setLong(2, loan.getUserId());
            if (loan.getIssueDate() != null) {
                ps.setDate(3, new java.sql.Date(loan.getIssueDate().getTime()));
            }
            if (loan.getIssueDate() != null) {
                ps.setDate(4, new java.sql.Date(loan.getDueDate().getTime()));
            }
            ps.executeUpdate();

        } catch (SQLException e) {
            DatabaseUtility.rollbackTransaction();
            e.printStackTrace();
            DatabaseUtility.rollbackTransaction();
            throw new SystemException("Exception Happened while trying to get All Loans");
        } finally {
            DatabaseUtility.releaseConnection();
        }
        logger.info("Finished Saving Loan ");
        return findCurrentLoanByBookCopyId(loan.getBookCopyId());
    }

    @Override
    public Loan returnBook(Long LoanId) {
        logger.info("Started Returning Book");
        String query = "UPDATE LOAN SET RETURNED_DATE = NOW() WHERE ID = ?";
        try{
            PreparedStatement ps = DatabaseUtility.getConnection().prepareStatement(query);
            ps.setLong(1, LoanId);
            ps.executeUpdate();

        } catch (SQLException e) {
            DatabaseUtility.rollbackTransaction();
            e.printStackTrace();
            DatabaseUtility.rollbackTransaction();
            throw new SystemException("Exception Happened while trying to get All Loans");
        } finally {
            DatabaseUtility.releaseConnection();
        }
        logger.info("Finished Returning Book ");
        return findById(LoanId);
    }

    @Override
    public Loan findById(Long LoanId) {
        logger.info("Started findById@ ");

        Loan loan = null;
        StringBuilder sb = new StringBuilder()
                .append("SELECT ID, BOOK_COPY_ID, USER_ID, ISSUE_DATE, DUE_DATE, RETURNED_DATE")
                .append("  FROM LOAN WHERE ID = ? ");

        try{
            PreparedStatement ps = DatabaseUtility.getConnection().prepareStatement(sb.toString());
            ps.setLong(1, LoanId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                loan = new Loan();
                loan.setId(rs.getLong("ID"));
                loan.setBookCopyId(rs.getLong("BOOK_COPY_ID"));
                loan.setUserId(rs.getLong("USER_ID"));
                loan.setIssueDate(rs.getDate("ISSUE_DATE"));
                loan.setDueDate(rs.getDate("DUE_DATE"));
                loan.setReturnedDate(rs.getDate("RETURNED_DATE"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            DatabaseUtility.rollbackTransaction();
            throw new SystemException("Exception Happened while trying to get All Loans");
        } finally {
            DatabaseUtility.releaseConnection();
        }
        logger.info("Finished findById");
        return loan;
    }

    @Override
    public List<Loan> findAllLoansByCopyId(Long bookCopyId) {
        logger.info("Started findAllLoansByCopyId: "+ bookCopyId);

        List<Loan> returnValue = new ArrayList<>();
        StringBuilder sb = new StringBuilder()
                .append("SELECT ID, BOOK_COPY_ID, USER_ID, ISSUE_DATE, DUE_DATE, RETURNED_DATE")
                .append("  FROM LOAN WHERE BOOK_COPY_ID = ? ");

        try{
            PreparedStatement ps = DatabaseUtility.getConnection().prepareStatement(sb.toString());
            ps.setLong(1, bookCopyId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Loan loan = new Loan();
                loan.setId(rs.getLong("ID"));
                loan.setBookCopyId(rs.getLong("BOOK_COPY_ID"));
                loan.setUserId(rs.getLong("USER_ID"));
                loan.setIssueDate(rs.getDate("ISSUE_DATE"));
                loan.setDueDate(rs.getDate("DUE_DATE"));
                loan.setReturnedDate(rs.getDate("RETURNED_DATE"));
                returnValue.add(loan);
            }
        } catch (SQLException e) {

            e.printStackTrace();
            DatabaseUtility.rollbackTransaction();
            throw new SystemException("Exception Happened while trying to get All Loans");
        } finally {
            DatabaseUtility.releaseConnection();
        }
        logger.info("Finished findAllLoansByCopyId:"+bookCopyId);

        return returnValue;
    }

    @Override
    public Loan findCurrentLoanByBookCopyId(Long bookCopyId) {
        logger.info("Started findCurrentLoanByBookCopyId@:"+bookCopyId);

        Loan loan =null;
        StringBuilder sb = new StringBuilder()
                .append("SELECT ID, BOOK_COPY_ID, USER_ID, ISSUE_DATE, DUE_DATE, RETURNED_DATE")
                .append("  FROM LOAN  ")
                .append(" WHERE DUE_DATE IS NOT NULL")
                .append(" AND BOOK_COPY_ID = ? ")
                .append("   AND RETURNED_DATE IS NULL")
                .append("   LIMIT 1 ");

        try{
            PreparedStatement ps = DatabaseUtility.getConnection().prepareStatement(sb.toString());
            ps.setLong(1, bookCopyId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                loan = new Loan();
                loan.setId(rs.getLong("ID"));
                loan.setBookCopyId(rs.getLong("BOOK_COPY_ID"));
                loan.setUserId(rs.getLong("USER_ID"));
                loan.setIssueDate(rs.getDate("ISSUE_DATE"));
                loan.setDueDate(rs.getDate("DUE_DATE"));
            }
        } catch (SQLException e) {

            e.printStackTrace();
            DatabaseUtility.rollbackTransaction();
            throw new SystemException("Exception Happened while trying findCurrentLoanByBookCopyId ");
        } finally {
            DatabaseUtility.releaseConnection();
        }
        logger.info("Finished findCurrentLoanByBookCopyId@:"+bookCopyId);

        return loan;
    }

    @Override
    public List<Loan> findOutstandingLoans() {
        logger.info("Started findOutstandingLoans");

        List<Loan> returnValue = new ArrayList<>();
        StringBuilder sb = new StringBuilder()
                    .append("SELECT ID, BOOK_COPY_ID, USER_ID, ISSUE_DATE, DUE_DATE, RETURNED_DATE")
                    .append("  FROM LOAN  ")
                    .append(" WHERE DUE_DATE IS NOT NULL")
                    .append("   AND RETURNED_DATE IS NULL");

        try{
            PreparedStatement ps = DatabaseUtility.getConnection().prepareStatement(sb.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
               Loan loan = new Loan();
                loan.setId(rs.getLong("ID"));
                loan.setBookCopyId(rs.getLong("BOOK_COPY_ID"));
                loan.setUserId(rs.getLong("USER_ID"));
                loan.setIssueDate(rs.getDate("ISSUE_DATE"));
                loan.setDueDate(rs.getDate("DUE_DATE"));
                //no need to put returned date
                returnValue.add(loan);
            }
        } catch (SQLException e) {

            e.printStackTrace();
            DatabaseUtility.rollbackTransaction();
            throw new SystemException("Exception Happened while trying findOutstandingLoans");
        } finally {
            DatabaseUtility.releaseConnection();
        }
        logger.info("Finished findOutstandingLoans");

        return returnValue;
    }

    @Override
    public List<Loan> findAllLoans() {
        logger.info("Started findAllLoans");

        List<Loan> returnValue = new ArrayList<>();
        StringBuilder sb = new StringBuilder()
                .append("SELECT ID, BOOK_COPY_ID, USER_ID, ISSUE_DATE, DUE_DATE, RETURNED_DATE FROM LOAN  ");
        try{
            PreparedStatement ps = DatabaseUtility.getConnection().prepareStatement(sb.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Loan loan = new Loan();
                loan.setId(rs.getLong("ID"));
                loan.setBookCopyId(rs.getLong("BOOK_COPY_ID"));
                loan.setUserId(rs.getLong("USER_ID"));
                loan.setIssueDate(rs.getDate("ISSUE_DATE"));
                loan.setDueDate(rs.getDate("DUE_DATE"));
                loan.setReturnedDate(rs.getDate("RETURNED_DATE"));
                returnValue.add(loan);
            }
        } catch (SQLException e) {

            e.printStackTrace();
            DatabaseUtility.rollbackTransaction();
            throw new SystemException("Exception Happened while trying to get All Loans");
        } finally {
            DatabaseUtility.releaseConnection();
        }
        logger.info("Finished findAllLoans");

        return returnValue;
    }

    @Override
    public int countAllLoans() {
        logger.info("Started countAllLoans");
        int i = findAllLoans().size();
        logger.info("Finished countAllLoans");
        return i;
    }

    @Override
    public int countOutstandingLoans() {
        logger.info("Started countOutstandingLoans");
        int i = findOutstandingLoans().size();
        logger.info("Finished countOutstandingLoans");
        return i;
    }

    @Override
    public int getNumberOfLoanedCopies(Long bookId) {
        logger.info("Started getNumberOfLoanedCopies");
        int returnValue = 0;
        StringBuilder sb = new StringBuilder()
        .append("SELECT COUNT(1) ")
        .append(" FROM LOAN L, ")
        .append("        BOOK_COPY BC ")
        .append(" WHERE L.BOOK_COPY_ID = BC.ID")
        .append("  AND L.DUE_DATE IS NOT NULL")
        .append("  AND L.RETURNED_DATE IS NULL")
        .append("  AND BC.BOOK_ID = ? ");

        try{
            PreparedStatement ps = DatabaseUtility.getConnection().prepareStatement(sb.toString());
            ps.setLong(1, bookId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                returnValue = rs.getInt(1);
            }
        } catch (SQLException e) {

            e.printStackTrace();
            DatabaseUtility.rollbackTransaction();
            throw new SystemException("Exception Happened while trying to get Loaned Book Count for Book id: "+ bookId);
        } finally {
            DatabaseUtility.releaseConnection();
        }
        logger.info("Finished getNumberOfLoanedCopies");
        return returnValue;
    }
}



