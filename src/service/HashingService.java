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
    @Deprecated
    String[] getHashAndSalt(String algorithm, byte[] text) throws Exception;

    /**
     * Creates a hash and a random Salt based on algorithm and provided bytes
     * @param text tst to hash
     * @return salt (the first one, hash the second one.
     * @throws Exception
     */
    String[] getHashAndSalt(String text) throws Exception;

    /***
     * The following validates the login information that is given in the login screen
     * @param algorithm - SHA256 algorithm used in getHashAndSalt
     * @param plainPassword - plain password that the user types into the UI
     * @param salt - random salt used in hashing of password
     * @param hashedPassword - hashed password from database
     * @return
     */
    boolean isValid(String algorithm, String plainPassword, String salt, String  hashedPassword);

}