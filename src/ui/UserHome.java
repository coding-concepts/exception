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

    private JLabel fullName;

    private JLabel fullNameValue;

    private JLabel email;

    private JLabel emailValue;

    private JLabel dob;

    private JLabel dobValue;

    private JLabel phone;

    private JLabel phoneValue;

    private JLabel gender;

    private JLabel genderValue;

    private JButton btnLgOut;




    public UserHome(UserProfile profile){
        userNameLbl.setText(profile.getFirstName());
        fullNameValue.setText(profile.getFirstName() + " " +profile.getLastName());
        emailValue.setText(profile.getEmail());
        dobValue.setText(profile.getDob().toString());
        phoneValue.setText(profile.getPhone()+"");
        genderValue.setText(profile.getGender());

        setupListeners();
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



