/*
 * @(#)UpdateBokForm.java 1.0 Nov 26, 2017
 */
package ui;

import util.FrameUtility;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <code>UpdateBokForm</code> class is
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
public class UpdateBokForm implements IScreen {
    private JPanel mainPanel;

    private JPanel titlePanel;

    private JPanel btlPanel;

    private JButton cancelButton;

    public UpdateBokForm() {
        cancelButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {

            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                gotoHomePage();
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



