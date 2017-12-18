/*
 * @(#)AddBookForm.java 1.0 Nov 26, 2017
 */
package ui;

import data.BookData;
import exception.ValidationException;
import service.BookService;
import service.ServiceFactory;
import util.FrameUtility;
import domain.Book;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <code>AddBookForm</code> class is
 * <p>
 * <p>
 * <pre>
 * <strong>History</strong>    Name              Date            Description
 * <strong>History</strong>    --------------------------------------------------------------------
 * <strong>History</strong>   Pratyush Giri    11/26/17
 * <strong>History</strong>   Rinisha Giri    12/17/17          it works now

 * </pre>
 *
 * @author Pratyush Giri
 * @since Nov 26, 2017
 */
public class AddBookForm  implements  IScreen {
    private JPanel mainPanel;

    private JPanel titlePanel;

    private JPanel btnPanel;

    private JLabel title;

    private JButton cancelButton;
    private JTextField titletxt;
    private JTextField authortxt;
    private JButton saveButton;
    private JLabel titlelbl;
    private JLabel authorlbl;
    private JLabel Copieslbl;
    private JTextField Copiestxt;
    private JLabel CopyIdslbl;

    public AddBookForm() {

        saveButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                BookData b = new BookData();
                b.setTitle(titletxt.getText());
                b.setAuthor(authortxt.getText());
                int copies = Integer.parseInt(Copiestxt.getText());

                BookService bookService = ServiceFactory.getBookService();
                try {
                    b = bookService.addBook(b,copies);
                    StringBuilder sb = new StringBuilder("The book copy Ids are: ");
                    for (long l: b.getBookCopyIds()){
                        sb.append(l+ " ");
                    }

                    JOptionPane.showMessageDialog(mainPanel,sb.toString());
                } catch (ValidationException e1) {
                    e1.printStackTrace();
                }

            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameUtility.displayPreviousScreen();
            }
        });
    }


    private void gotoHomePage() {
        FrameUtility.displayNextScreen(this, new UserHome(), "User Home");
    }

    @Override public JPanel getMainPanel() {
        return mainPanel;
    }
}



