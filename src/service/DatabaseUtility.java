/*
 * @(#)DatabaseUtility.java 1.0 Oct 25, 2017
 */
package service;

import exception.SystemException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * <code>DatabaseUtility</code> class is
 * <p>
 * <p>
 * <pre>
 * <strong>History</strong>    Name              Date            Description
 * <strong>History</strong>    --------------------------------------------------------------------
 * <strong>History</strong>   Pratyush Giri    10/25/17
 * </pre>
 *
 * @author Pratyush Giri
 * @since Oct 25, 2017
 */
public class DatabaseUtility {

    private static Connection mysqlConnection;

    private static String server = "jdbc:mysql://codingconcepts.cwtnmn4dnmoa.us-east-1.rds.amazonaws.com:3306/hacker_kids";

    private static String password = "hacker_kids";

    private static String username = "hacker_kids";

    public static Connection getConnection() throws SystemException {
        try {
            if (mysqlConnection == null ) {

                Class.forName("com.mysql.jdbc.Driver");
                mysqlConnection = DriverManager.getConnection(server, username, password);
                mysqlConnection.setAutoCommit(Boolean.FALSE);
                return mysqlConnection;
            }
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SystemException();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SystemException();
        }
        return null;
    }


    public  static void releaseConnection()  {
        if (mysqlConnection != null) {
            try {
                mysqlConnection.close();
                mysqlConnection = null;
            } catch (SQLException e) {
                e.printStackTrace();
                throw new SystemException();
            }
        }
    }


    public  static void commitTransaction()  {
        if (mysqlConnection != null){
            try {
                mysqlConnection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new SystemException();
            }
        }
    }

    public  static void rollbackTransaction()  {
        if (mysqlConnection != null){
            try {
                mysqlConnection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new SystemException();
            }
        }
    }


}



