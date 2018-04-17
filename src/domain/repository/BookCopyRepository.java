package domain.repository;

import domain.BookCopy;
import exception.ValidationException;
import java.util.List;

public interface BookCopyRepository {

    /**
     *
     * @param bookCopyId takes the bookCopyId
     * @return the Book Copy with the bookCopyId given
     */
    BookCopy findById(long bookCopyId);

    /**
     *
     * @param bookId takes the Id
     * @returns the list of all books with the foreign key in the bookId
     */
    List<BookCopy> findByBookId(long bookId);

    /**
     *
     * @param bookCopy takes a book copy
     * @return the bookCopy that was saved
     * @throws ValidationException
     */
    BookCopy save(BookCopy bookCopy) throws ValidationException;

    /**
     *@return the list of all the bookCopies in the database
     */
    List<BookCopy> findAll();

    /***
     * Deletes the given bookCopy
     * @param bookCopy takes a bookCopy
     * @throws ValidationException
     */
    void delete(BookCopy bookCopy)  throws ValidationException;


}
