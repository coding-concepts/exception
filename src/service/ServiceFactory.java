/*
 * @(#)ServiceFactory.java 1.0 Sep 28, 2017
 */
package service;

import domain.BookCopy;
import domain.repository.BookCopyRepository;
import domain.repository.BookCopyRepositoryImpl;
import domain.repository.UserRepository;
import domain.repository.UserRepositoryImpl;
import exception.ValidationException;
import service.impl.UserServiceImpl;

import java.util.List;

/**
 * <code>ServiceFactory</code> class is  Factory class to give out Services.
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

public class ServiceFactory {
    public static  UserService getUserService() {
        return new UserServiceImpl();
    }


    public static UserRepository getUserRepository() {
        return new UserRepositoryImpl();
    }

    public static BookCopyRepository getBookCopyRepository(){
        return new BookCopyRepositoryImpl();
    }
}



