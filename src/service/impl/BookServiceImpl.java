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
import service.LoanService;
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
    private LoanService loanService;

    public BookServiceImpl() {
        bookCopyRepository = ServiceFactory.getBookCopyRepository();
        bookRespository = ServiceFactory.getBookRepository();
        loanService  = ServiceFactory.getLoanService();
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
        return addBookCopies(book.getId(), quantity);

    }

    @Override
    public BookData deleteBook(BookData bookData, int[] bookCopies) throws ValidationException {
        return null;
    }

    @Override
    public BookData addBookCopies(long bookId, int quantity) throws ValidationException {
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
    @Override
    public int getNumberOfTotalCopies(Long bookId) {
        List books = bookCopyRepository.findByBookId(bookId);
        return (books == null ? 0 : books.size());
    }


    @Override
    public int getNumberOfAvailableCopies(Long bookId) {
        int loanedCopies = loanService.getNumberOfLoanedCopies(bookId);
        int totalCopies =  getNumberOfTotalCopies( bookId);
        return (totalCopies - loanedCopies);
    }

    @Override
    public List<BookData> searchBook(String srchCriteria) {

        //search by partial Author
        List<Book> authorBased = bookRespository.findByPartialAuthor(srchCriteria);
        //search by partial title
        List<Book> titleBased  = bookRespository.findByPartialTitle(srchCriteria);

        //List<Book> IDBased = bookRespository.findById();

        List<Book> titleCopy = new ArrayList<>(titleBased);
        titleCopy.removeAll(authorBased);
        authorBased.addAll(titleCopy);

        List<BookData> output = new ArrayList<>();

        if (!authorBased.isEmpty()){
            for (Book b : authorBased){
                BookData bookData = new BookBuilder().book(b).toBookData();
                output.add(bookData);
            }
        }
        return output;

//
//
//        List<BookData> books  =  new ArrayList<>();
//        //todo list
//        BookData hp1 = new BookData();
//        BookData hp2 = new BookData();
//        BookData hp3 = new BookData();
//        BookData unrec = new BookData();
//        hp1.setTitle("Harry Potter and the Sorcerers Stone");
//        hp2.setTitle("Harry Potter and the Chamber of Secrets");
//        hp3.setTitle("Harry Potter and the Prisoner of Azkaban");
//        unrec.setTitle("The Giving Tree");
//        hp1.setAuthor("J.K. Rowling");
//        hp2.setAuthor("J.K. Rowling");
//        hp3.setAuthor("J.K. Rowling");
//        unrec.setAuthor("Shel Silverstein");
//        hp1.setBookId(1000L);
//        hp2.setBookId(2000L);
//        hp3.setBookId(3000L);
//        unrec.setBookId(4000L);
//        books.add(0, hp1);
//        books.add(1, hp2);
//        books.add(2, hp3);
//        books.add(3, unrec);
//        /*
//        0000 - Harry Potter and the Sorcerers Stone - J.K. Rowling
//        0001 - Harry Potter and the Chamber of Secrets - J.K. Rowling
//        0002 - Harry Potter and the Prisoner of Azkaban - J.K. Rowling
//         */
//        return books;
    }

}



