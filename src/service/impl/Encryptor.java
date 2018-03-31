/*
 * @(#)Encryptor.java 1.0 Mar 10, 2018
 */
package service.impl;

import org.bouncycastle.util.encoders.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * <code>Encryptor</code> class is
 * <p>
 * <p>
 * <pre>
 * <strong>History</strong>    Name              Date            Description
 * <strong>History</strong>    --------------------------------------------------------------------
 * <strong>History</strong>   Pratyush Giri    3/10/18
 * </pre>
 *
 * @author Pratyush Giri
 * @since Mar 10, 2018
 */

public class Encryptor {

    public static String encrypt(String key, String initVector, String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());

            return new String(Base64.encode(encrypted));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }


    public static String decrypt(String key, String initVector, String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.decode(encrypted));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        String key = "Bar12345Bar12345"; // 128 bit key
        String initVector = "RandomInitVector"; // 16 bytes IV

        String original = "Ben";
        String original2 = "Adya";
        String original3 = "Rinisha";


        String encrtypted = encrypt(key, initVector, original);
        System.out.println("Encrypted ("+original+") : "+ encrtypted);

        System.out.println("Encrypted ("+original2+") : "+ encrypt(key, initVector, original2));
        System.out.println("Encrypted ("+original3+") : "+ encrypt(key, initVector, original3));


        String decrypted = decrypt(key, initVector, encrypt(key, initVector, original));

        System.out.println("Decrypted ("+encrtypted+") : "+ decrypted);

    }
}




