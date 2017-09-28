/*
 * @(#)LongDocumentFilter.java 1.0 Sep 28, 2017
 */
package ui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import java.awt.*;

/**
 * <code>LongDocumentFilter</code> class is
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
public class LongDocumentFilter extends DocumentFilter {

    private boolean isValidInteger(DocumentFilter.FilterBypass fp, String string) throws BadLocationException {
        int len = string.length();
        boolean isValidInteger = true;
        Document doc = fp.getDocument();

        if (doc.getLength()>10){
            return false;
        }
        for (int i = 0; i < len; i++) {
            if (!Character.isDigit(string.charAt(i))) {
                isValidInteger = false;
                break;
            }
        }
        return isValidInteger;
    }

    @Override public void insertString(DocumentFilter.FilterBypass fp, int offset, String string, AttributeSet aset) throws BadLocationException {
        if (isValidInteger(fp, string)) {
            super.insertString(fp, offset, string, aset);
        } else {
            Toolkit.getDefaultToolkit().beep();
        }
    }

    @Override public void replace(DocumentFilter.FilterBypass fp, int offset, int length, String string, AttributeSet aset) throws BadLocationException {

        if (isValidInteger(fp, string)) {
            super.replace(fp, offset, length, string, aset);
        } else {
            Toolkit.getDefaultToolkit().beep();
        }
    }
}

