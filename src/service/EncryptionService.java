
package service;

public interface EncryptionService {

    /**
     * Encrypts
     * @param textToEncrypt
     * @return String[], 0 index is encrypted value, second is key.
     */
    String[] encrypt(String textToEncrypt);

    /**
     * Decrypts
      * @param textToDecrypt
     * @return
     */
    String decrypt(String textToDecrypt, String key);
}



