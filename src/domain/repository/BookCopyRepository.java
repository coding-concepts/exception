package domain.repository;

import domain.BookCopy;
import exception.ValidationException;
import java.awt.print.Book;
import java.util.List;

public interface BookCopyRepository {

    /**
     *
     * @param bookCopyId
     * @return
     */
    BookCopy findById(long bookCopyId);

    /**
     *
     * @param bookId
     * @return
     */
    List<BookCopy> findByBookId(long bookId);

    /**
     *
     * @param b
     * @return
     * @throws ValidationException
     */
    BookCopy save(BookCopy b) throws ValidationException;

    /**
     *
     */
    List<BookCopy> findAll();
}
