/*
 * @(#)Program.java 1.0 Oct 16, 2017
 */
package main;

import ui.LoginScreen;
import util.FrameUtility;

import javax.swing.*;

import static util.FrameUtility.SCREEN_SIZE;

/**
 * <code>Program</code> class is
 * <p>
 * <p>
 * <pre>
 * <strong>History</strong>    Name              Date            Description
 * <strong>History</strong>    --------------------------------------------------------------------
 * <strong>History</strong>   Pratyush Giri    10/16/17
 * </pre>
 *
 * @author Pratyush Giri
 * @since Oct 16, 2017
 */
public class Program {

    public static JFrame FRAME = FrameUtility.FRAME;

    public static void main(String[] args) {
        FRAME.setSize(SCREEN_SIZE);
        FRAME.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        FRAME.setContentPane(new LoginScreen().getMainPanel());
        FRAME.setVisible(true);
    }
}



