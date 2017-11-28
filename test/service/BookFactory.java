/*
 * @(#)BookFactory.java 1.0 Nov 27, 2017
 */
package service;

import data.BookData;

import java.util.Random;

/**
 * <code>BookFactory</code> class is  factory class
 * <p>
 * <p>
 * <pre>
 * <strong>History</strong>    Name              Date            Description
 * <strong>History</strong>    --------------------------------------------------------------------
 * <strong>History</strong>   Pratyush Giri    11/27/17
 * </pre>
 *
 * @author Pratyush Giri
 * @since Nov 27, 2017
 */
public class BookFactory {

    public static BookData getBookData(){
        BookData bd = new BookData();
        bd.setAuthor("Author");
        bd.setTitle("Book_"+getRandomInt());
        return bd;
    }

    private static int getRandomInt(){
        Random r = new Random();
        return r.nextInt(1_00_00_00_00_0);
    }

}



