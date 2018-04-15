package domain.repository;

import data.OverDueLoanData;
import domain.Loan;
import service.DatabaseUtility;
import exception.SystemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by sniper825 on 4/8/18.
 */
public class OverDueLoanRepositoryImpl implements OverDueLoanRepository {

    public List<OverDueLoanData> getOverdueLoans(){

        List<OverDueLoanData> returnValue = new ArrayList<>();
        StringBuilder sb = new StringBuilder()
                .append(" select TITLE, AUTHOR, EMAIL, DUE_DATE, FIRST_NAME, LAST_NAME ")
                .append(" from BOOK b, USER u, LOAN l, BOOK_COPY bc ")
                .append(" WHERE l.USER_ID = u.ID and l.BOOK_COPY_ID = bc.ID and bc.BOOK_ID = b.ID ")
                .append(" and l.RETURNED_DATE is NULL and DATE_ADD(current_date(), INTERVAL 3 DAY) > l.DUE_DATE ");
        try{
            Connection connection = DatabaseUtility.getConnection();
            PreparedStatement ps = connection.prepareStatement(sb.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                OverDueLoanData ODloan = new OverDueLoanData();
                ODloan.setDueDate(rs.getDate("DUE_DATE"));
                ODloan.setFirstName(rs.getString("FIRST_NAME"));
                ODloan.setLastName(rs.getString("LAST_NAME"));
                ODloan.setEmail(rs.getString("EMAIL"));
                ODloan.setTitle(rs.getString("TITLE"));
                ODloan.setAuthor(rs.getString("AUTHOR"));
                //no need to put returned date
                returnValue.add(ODloan);
            }
        } catch (SQLException e) {

            e.printStackTrace();
            DatabaseUtility.rollbackTransaction();
            throw new SystemException("Exception Happened while trying findOutstandingLoans");
        } finally {
            DatabaseUtility.releaseConnection();
        }
        return returnValue;
    }
}
