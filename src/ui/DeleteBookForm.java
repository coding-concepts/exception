/*
 * @(#)DeleteBookForm.java 1.0 Nov 26, 2017
 */
package ui;

import data.BookData;
import domain.Book;
import domain.BookCopy;
import exception.ValidationException;
import service.BookService;
import service.ServiceFactory;
import util.FrameUtility;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <code>DeleteBookForm</code> class is
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
public class DeleteBookForm implements IScreen{
    private JPanel mainPanel;

    private JPanel titlePanel;

    private JPanel btnPanel;

    private JButton cancelButton;
    private JTextField Titletxt;
    private JTextField authorTxt;
    private JTextField BookCopiesTxt;
    private JButton SaveBtn;

    public DeleteBookForm() {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //gotoHomePage();
                FrameUtility.displayPreviousScreen();
            }
        });
    }
//        SaveBtn.addActionListener(new ActionListener() {
////            @Override
////            public void actionPerformed(ActionEvent e) {
//////                BookData b = new BookData();
//////                b.setTitle(Titletxt.getText());
//////                b.setAuthor(authorTxt.getText());
//////                int [] bookCopies = getIntArray(BookCopiesTxt);
//////
//////                BookService bookService = ServiceFactory.getBookService();
//////                try {
//////                    b = bookService.deleteBook(b,bookCopies);
//////
//////
//////                } catch (ValidationException e1) {
//////                    e1.printStackTrace();
//////                }
//////
//////            }
////        });
//    }


    private void gotoHomePage() {
        FrameUtility.displayNextScreen(this, new UserHome(), "User Home");
    }
//
//    private int[] getIntArray(String bookCopies){
//
//    }

    @Override public JPanel getMainPanel() {
        return mainPanel;
    }
}



