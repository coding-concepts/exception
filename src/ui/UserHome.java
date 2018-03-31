/*
 * @(#)UserHome.java 1.0 Oct 05, 2017
 */
package ui;

import data.UserProfile;
import util.FrameUtility;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <code>UserHome</code> class is
 * <p>
 * <p>
 * <pre>
 * <strong>History</strong>    Name              Date            Description
 * <strong>History</strong>    --------------------------------------------------------------------
 * <strong>History</strong>   Pratyush Giri    10/5/17
 * </pre>
 *
 * @author Pratyush Giri
 * @since Oct 05, 2017
 */
public class UserHome implements IScreen {
    private JPanel mainPanel;

    private JPanel titlePanel;

    private JLabel welcomeLbl;

    private JLabel userNameLbl;

    private JPanel bodyPanel;

    private JLabel fullNameValue;

    private JLabel emailValue;

    private JLabel dobValue;

    private JLabel phoneValue;

    private JLabel genderValue;

    private JButton btnLgOut;

    private JButton addBookButton;

    private JButton updateBookButton;

    private JButton deleteBookButton;

    private JButton searchButton;

    private JButton loanButton;

    private JButton button3;

    public UserHome(){
        this(LoginScreen.loggedOnUser);


    }

    private void gotoSearchScreen() {
        FrameUtility.displayNextScreen(this, new Search(), "Search for Books");
    }


    public UserHome(UserProfile profile){

        welcomeLbl.setText("Welcome : "+profile.getFirstName());
//        fullNameValue.setText(profile.getFirstName() + " " +profile.getLastName());
//        emailValue.setText(profile.getEmail());
//        dobValue.setText(profile.getDob().toString());
//        phoneValue.setText(profile.getPhone()+"");
//        genderValue.setText(profile.getGender());

        setupListeners();
    }
    private void gotoAddBookForm() {
        FrameUtility.displayNextScreen(this, new AddBookForm(), "Add a Book");
    }

    private void gotoUpdateBookForm() {
        FrameUtility.displayNextScreen(this, new UpdateBokForm(), "Update a Book");
    }
    private void gotoDeleteBookForm() {
        FrameUtility.displayNextScreen(this, new DeleteBookForm(), "Update a Book");
    }

    private void setupListeners() {
        btnLgOut.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override public void actionPerformed(ActionEvent e) {
                logout();
            }
        });

        addBookButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                gotoAddBookForm();
            }
        });

        updateBookButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                gotoUpdateBookForm();
            }
        });
        deleteBookButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                gotoDeleteBookForm();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gotoSearchScreen();
            }
        });
    }



    private void logout() {
        FrameUtility.displayNextScreen(this, new LoginScreen(), "Login Screen");
    }

    @Override public JPanel getMainPanel() {
        return mainPanel;
    }

//    public static void main(String[] args){
//                JFrame f = new JFrame("userdetail");
//                f.setSize(500, 300);
//                f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//                f.setContentPane(new UserHome().mainPanel);
//                f.setLocationRelativeTo(null);
//               // f.pack();
//                f.setVisible(true);
//            }
}



