package ui;

import data.BookData;
import service.BookService;
import service.LoanService;
import service.ServiceFactory;
import util.FrameUtility;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by sniper825 on 11/26/17.
 */
public class Search implements IScreen {
    private JPanel TitlePanel;
    private JPanel SearchPanel;
    private JPanel ResultsPanel;
    private JLabel Title;
    private JTextField searchBar;
    private JButton searchButton;
    private JList<Object> ResultsList;
    private JPanel MainPanel;
    private JPanel BookInfoPanel;
    private JLabel CoverArt;
    private JLabel TitleLabel;
    private JLabel AuthorLabel;
    private JLabel IdLabel;
    private JLabel CopiesLabel;
    private JButton UpdateButton;
    private JButton LoansButton;
    private JButton issueButton;
    private JProgressBar progressBar;
    private JScrollPane sp;

    BookService bookService = ServiceFactory.getBookService();
    LoanService loanService = ServiceFactory.getLoanService();

    List<BookData> books;
    List<String> copies;

    public Search() {
        ResultsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                BookInfoPanel.setVisible(true);
                try {
                    try{
                        copies.get(ResultsList.getSelectedIndex());
                    } catch(java.lang.IndexOutOfBoundsException E){
                        copies.add(ResultsList.getSelectedIndex(), bookService.getNumberOfAvailableCopies(books.get(ResultsList.getSelectedIndex()).getBookId()) + " out of " + bookService.getNumberOfTotalCopies(books.get(ResultsList.getSelectedIndex()).getBookId()) + " copies available");
                    }
                    BookData book = books.get(ResultsList.getSelectedIndex());
                    TitleLabel.setText(book.getTitle());
                    AuthorLabel.setText(book.getAuthor());
                    IdLabel.setText(book.getBookId().toString());
                    CopiesLabel.setText(copies.get(ResultsList.getSelectedIndex()));
                    CoverArt.setIcon(getCoverArt(book.getBookId()));
                } catch(ArrayIndexOutOfBoundsException E) {return;}

            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                progressBar.setValue(5);
                progressBar.setVisible(true);
                BookInfoPanel.setVisible(false);
                ResultsPanel.setVisible(false);
                //ResultsList.setListData();
                books  =  bookService.SearchBook(searchBar.getText());
                copies = new ArrayList<>();
                progressBar.setValue(35);
                List<String>  results =  new ArrayList<>();
                progressBar.setValue(50);
                for(int i = 0; i < books.size(); i++) {
                    results.add(i, books.get(i).getBookId().toString() + " - " + books.get(i).getTitle() + " - " + books.get(i).getAuthor());
                    progressBar.setValue(util.Math.map(i, 0, books.size(), 50, 90));
                }
                ResultsList.setListData(results.toArray());
                progressBar.setValue(100);
                progressBar.setVisible(false);
                ResultsPanel.setVisible(true);

            }
        });
        UpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateBokForm updateBook = new UpdateBokForm();
                //todo: add a method that will update the form with the current book.
                FrameUtility.displayNextScreen(Search.this, updateBook, "Update a Book");
            }
        });
        issueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //todo: add a method that will take you to the issue book screen.
            }
        });
        LoansButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //todo: add a method that will take you to the Loans screen.
            }
        });
    }

    public JPanel getMainPanel() {
        return MainPanel;
    }

    public static Icon getCoverArt(long id) {
        ImageIcon icon = new ImageIcon("covers/NotFound.png");
        if(new File("covers/" + id + ".jpg").exists()){
            icon = new ImageIcon("covers/" + id + ".jpg");
        } else {
            // todo: make a service that retrieves cover art from internet
        }
        return icon;
    }

    public static void delay(int milliseconds){
        try {TimeUnit.MILLISECONDS.sleep(milliseconds);}catch(java.lang.InterruptedException E){return;}
    }
}
