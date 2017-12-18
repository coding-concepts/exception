/*
 * @(#)FrameUtility.java 1.0 Oct 16, 2017
 */
package util;

import javafx.stage.Screen;
import ui.IScreen;

import javax.swing.*;
import java.awt.*;

/**
 * <code>FrameUtility</code> class is
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
public class FrameUtility {
    public static Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

    public static JFrame FRAME = new JFrame();

    private IScreen previousScreen;
    private static String previousScreenT;

    private IScreen currentScreen;
    private static String currentScreenT;

    private static FrameUtility fu = new FrameUtility();

    public static void displayNextScreen(IScreen orig, IScreen next, String title) {
        //orig.getMainPanel().setVisible(false);
        FRAME.setContentPane(next.getMainPanel());
        FRAME.setSize(SCREEN_SIZE);
        FRAME.setTitle(title);
        FRAME.revalidate();
        FRAME.setVisible(true);
        fu.previousScreen = fu.currentScreen;
        previousScreenT = currentScreenT;
        fu.currentScreen = next;
        currentScreenT = title;
    }

    public static void displayPreviousScreen() {
        displayNextScreen(fu.currentScreen, fu.previousScreen, previousScreenT);
    }
}



