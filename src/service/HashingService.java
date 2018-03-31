package service;/*
 * @(#)service.HashingService.java 1.0 Mar 02, 2018
 */

/**
 * <code>service.HashingService<code> class is
 * <p>
 * <pre>
 * <strong>History</strong>    Name              Date            Description
 * <strong>History</strong>    --------------------------------------------------------------------
 * <strong>History</strong>   Pratyush Giri    3/2/18
 * </pre>
 *
 * @author Pratyush Giri
 * @since Mar 02, 2018
 */
public interface HashingService {

    /**
     * Creates a hash based on algorithm and provided byted
     * @param algorithm algorithm
     * @param text text to Hash
     * @return the hash
     * @throws Exception
     */
    String getHash(String algorithm, byte[] text) throws Exception;

    /**
     * Creates a hash based on algorithm and provided bytes and salt
     * @param algorithm algo name
     * @param text text to hash
     * @param salt Salt
     * @return the Hash
     * @throws Exception
     */
    String getHash(String algorithm, byte[] text, byte[] salt) throws Exception;

    /**
     * Creates a hash and a random Salt based on algorithm and provided bytes
     * @param algorithm algo name
     * @param text tst to hash
     * @return salt (the first one, hash the second one.
     * @throws Exception
     */
    String[] getHashAndSalt(String algorithm, byte[] text) throws Exception;


    boolean isValid(String algorithm, byte[] plainPassword, byte[] salt, byte[] hashedPassword);

}