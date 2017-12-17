/*
 * @(#)LoginScreen.java 1.0 Oct 05, 2017
 */
package ui;

import data.UserProfile;
import exception.ValidationException;
import service.ServiceFactory;
import service.UserService;
import util.FrameUtility;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <code>LoginScreen</code> class is
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
public class LoginScreen  implements IScreen {
    JPanel mainPanel;

    private JPanel titlePanel;

    private JLabel title;

    private JPanel fieldPanel;

    private JTextField txtUser;

    private JPasswordField txtPassword;

    private JLabel lblUserName;

    private JLabel lblPassword;

    private JButton btnRegister;

    private JButton btnLogin;
    private JButton button1;

    public static UserProfile loggedOnUser = null;


    public LoginScreen() {
        setupUI();
        btnRegister.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override public void actionPerformed(ActionEvent e) {
                showRegistrationScreen();

            }
        });
        btnLogin.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override public void actionPerformed(ActionEvent e) {
                doLogin();
            }
        });
    }

    private void doLogin() {

        UserService userService = ServiceFactory.getUserService();
        try {
            UserProfile profile = userService.validateUser(txtUser.getText(), new String(txtPassword.getPassword()));
            loggedOnUser = profile;
            showUserHome(profile);
        } catch (ValidationException e) {
            JOptionPane.showMessageDialog(mainPanel, e.getMessage());
        }
    }

    private void showUserHome(UserProfile profile) {
        FrameUtility.displayNextScreen(this, new UserHome(profile), "User Profile Screen" );
    }

    private void showRegistrationScreen() {
        FrameUtility.displayNextScreen(this, new RegistrationForm(), "Registration Screen" );
    }

    private void setupUI() {
        mainPanel.setBorder(BorderFactory.createTitledBorder("Login Screen"));
        titlePanel.setBorder(BorderFactory.createTitledBorder(""));
        fieldPanel.setBorder(BorderFactory.createTitledBorder(""));
    }

//    public static void main(String[] args){
//        JFrame f = new JFrame("User Registration");
//        f.setSize(500, 300);
//        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        f.setContentPane(new LoginScreen().mainPanel);
//        f.setLocationRelativeTo(null);
//       // f.pack();
//        f.setVisible(true);
//    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}



