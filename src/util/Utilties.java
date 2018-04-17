/*
 * @(#)Utilties.java 1.0 Nov 26, 2017
 */
package util;

/**
 * <code>Utilties</code> class is  util class
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
public class Utilties {

    public static boolean isBlank(String s) {
        return ( s==null || s.trim().equals(""));
    }
    public static boolean isNotBlank(String s) {
        return ( s!=null && s.trim().equals(""));
    }

}



