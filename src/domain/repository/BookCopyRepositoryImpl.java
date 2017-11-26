package domain.repository;

import domain.BookCopy;
import exception.SystemException;
import exception.ValidationError;
import exception.ValidationException;
import service.DatabaseUtility;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BookCopyRepositoryImpl implements BookCopyRepository {

    /**
     /***
     *
     ID BIGINT NOT NULL  AUTO_INCREMENT, -- BOOK COPY ID
     BOOK_ID BIGINT NOT NULL, -- the book id. Foreign key to book table(ben)
     */

    @Override
    public BookCopy findById(long bookCopyId) {
        String query = "SELECT ID, BOOK_ID FROM BOOK_COPY  WHERE ID = ? ";

        try {
            PreparedStatement ps = DatabaseUtility.getConnection().prepareStatement(query);
            ps.setLong(1, bookCopyId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                BookCopy b = new BookCopy();
                b.setId(rs.getLong("ID"));
                b.setBookId(rs.getLong("BOOK_ID"));
                return b;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SystemException("Exception Happened while trying to find book copy ID "+ bookCopyId);
        } finally {
            DatabaseUtility.releaseConnection();
        }

        return null;
    }

    @Override
    public List<BookCopy> findByBookId(long bookId) {
        String query = "SELECT ID, BOOK_ID FROM BOOK_COPY  WHERE BOOK_ID = ? ";

        try {
            PreparedStatement ps = DatabaseUtility.getConnection().prepareStatement(query);
            ps.setLong(1, bookId);
            ResultSet rs = ps.executeQuery();
            List<BookCopy> books = new ArrayList<>();

            while (rs.next()) {
                BookCopy bookCopy = new BookCopy();
                bookCopy.setId(rs.getLong("ID"));
                bookCopy.setBookId(rs.getLong("BOOK_ID"));
                books.add(bookCopy);
            }
            return books;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SystemException("Exception Happened while trying to find book ID "+ bookId);
        } finally {
            DatabaseUtility.releaseConnection();
        }
    }

    @Override
    public BookCopy save(BookCopy bookCopy) throws ValidationException {
        if (bookCopy == null){
            throw new ValidationException("Unable to save a null book. Please add a book.",
                    new ValidationError("Book", "null. Please enter value", "Can't Save a null book"));
        }

        if (bookCopy.getId() == null) {
            //now I will generate the new book id
            bookCopy.setId(getRandomId());

        }

        BookCopy dbBookCopy  = findById(bookCopy.getId());
        if (dbBookCopy != null){
            return dbBookCopy;
        }
        else {
            return (insert(bookCopy));
        }
    }

    private BookCopy insert(BookCopy bookCopy) {
        String query = "INSERT INTO BOOK_COPY (ID, BOOK_ID)  VALUES (?, ?)";
        try {
            PreparedStatement ps = DatabaseUtility.getConnection().prepareStatement(query);
            ps.setLong(1, bookCopy.getId());
            ps.setLong(2, bookCopy.getBookId());
            ps.executeUpdate();
            DatabaseUtility.commitTransaction();

        } catch (SQLException e) {
            DatabaseUtility.rollbackTransaction();
            e.printStackTrace();
            throw new SystemException("Exception Happened while trying to insert a BookCopy: "+ bookCopy.getId());
        } finally {
            DatabaseUtility.releaseConnection();
        }
        if (bookCopy.getBookId() == 0l){
            throw new SystemException("Cannot pass null ");
        }
        return bookCopy;
    }

    @Override
    public List<BookCopy> findAll() {

        String query = "SELECT  ID, BOOK_ID FROM BOOK_COPY ";
        try {
            PreparedStatement ps = DatabaseUtility.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            List<BookCopy> copies = new ArrayList<>();

            while (rs.next()) {
                BookCopy bookCopy = new BookCopy();
                bookCopy.setId(rs.getLong("ID"));
                bookCopy.setBookId(rs.getLong("BOOK_ID"));
                copies.add(bookCopy);
            }
            return copies;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SystemException("Exception Happened while trying to find books ");
        } finally {
            DatabaseUtility.releaseConnection();
        }
    }

    @Override
    public void delete(BookCopy bookCopy) throws ValidationException {
        BookCopy dbBookCopy = findById(bookCopy.getId());
        if (dbBookCopy == null){
            throw new ValidationException("Book Copy Does not exist!", new ValidationError("bookCopy", "business-error", "BookCopy does not exist"));
        }
        String query = "DELETE FROM BOOK_COPY WHERE ID = ? ";
        try {
            PreparedStatement ps = DatabaseUtility.getConnection().prepareStatement(query);
            ps.setLong(1, bookCopy.getId());
            ps.executeUpdate();
            DatabaseUtility.commitTransaction();
        } catch (SQLException e) {
            DatabaseUtility.rollbackTransaction();
            e.printStackTrace();
            throw new SystemException("Exception Happened while trying to delete a bookCopy: "+ bookCopy.getId());
        } finally {
            DatabaseUtility.releaseConnection();
        }
    }


    private Long getRandomId() {
        Random r = new Random();
        return (long)r.nextInt(1_00_00_00);
    }
}
