/*
 * @(#)BookServviceImpl.java 1.0 Nov 26, 2017
 */
package service.impl;

import builder.BookBuilder;
import data.BookData;
import domain.Book;
import domain.BookCopy;
import domain.repository.BookCopyRepository;
import domain.repository.BookRespository;
import exception.ValidationError;
import exception.ValidationException;
import service.BookService;
import service.ServiceFactory;
import util.Utilties;

import java.util.ArrayList;
import java.util.List;

/**
 * <code>BookServviceImpl</code> class is  implemantation
 * <p>
 * <p>
 * <pre>
 * <strong>History</strong>    Name              Date            Description
 * <strong>History</strong>    --------------------------------------------------------------------
 * <strong>History</strong>   Pratyush Giri    11/26/17
 * </pre>
 *
 * @author Pratyush Giri
 * @since Nov 26, 2017
 */
public class BookServiceImpl implements BookService {

    private BookCopyRepository bookCopyRepository;
    private BookRespository bookRespository;

    public BookServiceImpl() {
        bookCopyRepository = ServiceFactory.getBookCopyRepository();
        bookRespository = ServiceFactory.getBookRepository();
    }

    @Override
    public BookData addBook(BookData bookData) throws ValidationException {
       return addBook(bookData, 1);
    }

    @Override
    public BookData addBook(BookData bookData, int quantity) throws ValidationException {
        validateBookData(bookData);
        //save book
        Book book = bookRespository.save(new BookBuilder().bookData(bookData).toBook());
        //save book copy for quantity times
        return addBookCpoies(book.getId(), quantity);

    }

    @Override
    public BookData addBookCpoies(long bookId, int quantity) throws ValidationException {
        return addBookCpoies(null, bookId, quantity);
    }


    private  BookData addBookCpoies(Book book, long bookId, int quantity) throws ValidationException {
        if (book == null){
            book = bookRespository.findById(bookId);
        }
        if (book != null) {
            List<Long> list = new ArrayList<>();
            for (int i = 0; i < quantity; i++) {
                BookCopy bk = new BookCopy();
                bk.setBookId(bookId);
                bk = bookCopyRepository.save(bk);
                list.add(bk.getId());
            }
            BookData bookData = new BookBuilder().book(book).toBookData();
            bookData.setBookCopyIds(list);
            return bookData;
        }
        return null;
    }

    private void validateBookData(BookData bookData) throws ValidationException {
        if (bookData == null){
            throw new ValidationException("Can't Save a NULL Book",
                    new ValidationError("user", "business-error", "Can't Save a NULL Book"));
        }

        if (Utilties.isBlank(bookData.getAuthor()) ||
                Utilties.isBlank(bookData.getTitle())){
            throw new ValidationException("Bad Data to save book",
                    new ValidationError("book", "business-error", "mandatory fields missing"));
        }

        if (bookData.getBookId() != null){
            throw new ValidationException("Can't save a book with Id.",
                    new ValidationError("book", "business-error", "Can't save a book with Id"));
        }
    }
}



