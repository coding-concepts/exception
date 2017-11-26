import domain.Book;
import domain.BookCopy;
import domain.User;
import domain.repository.BookCopyRepository;
import org.junit.Before;
import org.junit.Test;
import service.ServiceFactory;

import java.util.Random;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class TestBookCopyRepository {
    private BookCopyRepository repository;



    @Before
    public void setup() {
        repository = ServiceFactory.getBookCopyRepository();
    }


    @Test
     public void testSingleBookCopy() {
            BookCopy bookCopy = getBookCopy(1L);
            BookCopy dbBookCopy = repository.findById(bookCopy.getId());
            assertNull(dbBookCopy);
            try {
                dbBookCopy = repository.save(bookCopy);
                assertNotNull(dbBookCopy);
                match(bookCopy, dbBookCopy);
            } catch (Exception e) {
                e.printStackTrace();
                fail("Test failed to create bookCopy");
                }

            try {
                dbBookCopy = repository.findById(bookCopy.getId());
                assertNotNull(dbBookCopy);
                match(dbBookCopy, dbBookCopy);
            } catch (Exception e) {
                e.printStackTrace();
                fail("Test failed to find Book copy");
            }
    }

    private BookCopy getBookCopy(Long BookID) {
        BookCopy bookCopy = new BookCopy();
        bookCopy.setBookId(100L);
        bookCopy.setId(getRandomId());
        return bookCopy;
    }

    private Long getRandomId() {
        Random r = new Random();
        return (long)r.nextInt(1_00_00_00);
    }


    private void match(BookCopy bookCopy, BookCopy dbBookCopy){
        assertEquals(bookCopy.getBookId(), dbBookCopy.getBookId());
        assertEquals(bookCopy.getId(), dbBookCopy.getId());

    }
}
