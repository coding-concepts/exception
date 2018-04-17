/*
 * @(#)RegistrationForm.java 1.0 Sep 28, 2017
 */
package ui;

import data.RegistrationData;
import exception.ValidationError;
import exception.ValidationException;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import service.ServiceFactory;
import service.UserService;
import util.FrameUtility;
import validator.RegistrationDataValidator;
import validator.Validator;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;



/**
 * <code>RegistrationForm</code> class is
 * <p>
 * <p>
 * <pre>
 * <strong>History</strong>    Name              Date            Description
 * <strong>History</strong>    --------------------------------------------------------------------
 * <strong>History</strong>   Pratyush Giri    9/28/17
 * </pre>
 *
 * @author Pratyush Giri
 * @since Sep 28, 2017
 */
public class RegistrationForm implements IScreen {
    private JPanel mainPanel;

    private JLabel title;

    private JPanel titlePanel;

    private JPanel fieldPanel;

    private JLabel firstName;

    private JTextField txtFirstName;

    private JLabel lastName;

    private JTextField txtLastName;

    private JLabel email;

    private JTextField txtEmail;

    private JLabel password;

    private JPasswordField txtPassword;

    private JLabel confirmPassword;

    private JPasswordField txtConfPassword;

    private JLabel dob;

    private JLabel gender;

    private JComboBox txtGender;

    private JLabel phone;

    private JTextField txtPhone;

    private JPanel btnPanel;

    private JButton btnOk;

    private JButton cancelButton;

    private JDatePickerImpl txtDob;

    private JButton backBtn;

    private UtilDateModel model;

    private JDatePanelImpl datePanel;

    private HashMap<String, JLabel> errorMap = new HashMap<>();


    private static final Color DEFAULT_COLOR = Color.BLACK;

    private static final Color ERROR_COLOR = Color.RED;


    private static final Dimension DIMENSION = new Dimension(200, 30);

    public RegistrationForm()  {
        setupUI();
        populateErrorMap();

        //set up a filter so you cant type characters for phone number
        ((AbstractDocument)txtPhone.getDocument()).setDocumentFilter(
                new LongDocumentFilter());

        //set an action item for the cancel Button.
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        //set an action item for Ok button.
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetColor();
                dealWithOkButton();
            }
        });
        backBtn.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override public void actionPerformed(ActionEvent e) {
                goBackToLoginScreen();
            }
        });
    }

    private void goBackToLoginScreen() {
        clearForm();
        FrameUtility.displayNextScreen(this, new LoginScreen(), "Login Screen");
    }

    /**
     * What to do when ok button is pressed.
     */
    private void dealWithOkButton() {

        RegistrationData data = new RegistrationData();
        data.setFirstName(txtFirstName.getText());
        data.setLastName(txtLastName.getText());
        data.setEmail(txtEmail.getText());
        data.setPassword(new String(txtPassword.getPassword()));
        data.setConfirmPassWord(new String(txtConfPassword.getPassword()));
        data.setGender(getGender((String)txtGender.getSelectedItem()));

        Date dt = (Date)txtDob.getModel().getValue();


        data.setDob((Date) txtDob.getModel().getValue());
        if (!"".equals(txtPhone.getText())) {
            data.setPhone(Long.parseLong(txtPhone.getText()));
        }
        //validate
        try {
            Validator validator  = RegistrationDataValidator.getInstance();
            validator.validate(data);
            UserService userService = ServiceFactory.getUserService();
            userService.registerUser(data);
            JOptionPane.showMessageDialog(mainPanel, "User Registered Successfully.");
            clearForm();

        } catch (ValidationException e) {
            List<ValidationError> errors = e.getErrorList();
            dealWithvalidationErrors(errors);
        }

    }

    private void dealWithvalidationErrors(List<ValidationError> errors) {
        if (errors == null || errors.isEmpty()){
            return;
        }
        StringBuffer sb = new StringBuffer();
        for (ValidationError  error : errors){


            String s = error.getField();

            JLabel comp = errorMap.get(s);


            comp.setForeground(ERROR_COLOR);
            sb.append(error.getFix()+"\n");
        }
        //since this does not talk about the password, then we should
        //make the color of the conf. password to the password.
        confirmPassword.setForeground(password.getForeground());
        //open a dialog
        JOptionPane.showMessageDialog(mainPanel, sb.toString());
    }

    private char getGender(String selectedItem) {
        return ( "Male".equals(selectedItem) ? 'M' :
                    "Female".equals(selectedItem) ? 'F' :
                        "Others".equals(selectedItem) ? 'O' :
                                "Rather Not Say".equals(selectedItem) ? 'N' :' ');
    }

    /**
     * Initialization of creating ui components and set data.
     */
    private void setupUI() {
        txtFirstName.setPreferredSize(DIMENSION);
        txtLastName.setPreferredSize(DIMENSION);
        txtEmail.setPreferredSize(DIMENSION);
        txtPassword.setPreferredSize(DIMENSION);
        txtConfPassword.setPreferredSize(DIMENSION);

        txtGender.setPreferredSize(DIMENSION);
        txtPhone.setPreferredSize(DIMENSION);

    }

    /**
     * Since we used a custom component, this dude is going to be called first.
     * So we need to set the txtDob now.
     */
    public void createUIComponents() {

        model = new UtilDateModel();
        //model.setSelected(true);
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        datePanel = new JDatePanelImpl(model,p);
        txtDob  =  new JDatePickerImpl(datePanel, new DateLabelFormatter());
    }

    /**
     * Clears the form.
     */
    private void clearForm(){
        txtFirstName.setText("");
        txtLastName.setText("");
        txtEmail.setText("");
        txtPassword.setText("");
        txtConfPassword.setText("");
        txtPhone.setText("");
        txtGender.setSelectedIndex(-1);
        txtDob.getJFormattedTextField().setText("");
        resetColor();
    }

    private void resetColor() {
        for (JLabel comp : errorMap.values() ){
            comp.setForeground(DEFAULT_COLOR);
        }
        //since this does not talk about the password, then we should
        //make the color of the conf. password to the password.
        confirmPassword.setForeground(password.getForeground());
    }

    private void populateErrorMap() {
        errorMap.put("first-name", firstName);
        errorMap.put("last-name", lastName);
        errorMap.put("email", email);
        errorMap.put("password", password);
        errorMap.put("phone", phone);
        errorMap.put("gender", gender);
        errorMap.put("date-of-birth", dob);
    }

//    public static void main(String[] args){
//        JFrame f = new JFrame("User Registration");
//        f.setSize(600, 800);
//        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        f.setContentPane(new RegistrationForm(f).mainPanel);
//        f.pack();
//        f.setVisible(true);
//    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}



