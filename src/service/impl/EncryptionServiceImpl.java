package service.impl;

import service.EncryptionService;

import java.sql.Statement;

public class EncryptionServiceImpl implements EncryptionService {

    private static final String KEY =  "Bar12345Bar12345"; // 128 bit key
    private static final String INIT_VECTOR = "RandomInitVector"; // 16 bytes IV

    @Override
    public String[] encrypt(String textToEncrypt) {
        String encryptedValue = Encryptor.encrypt(KEY, INIT_VECTOR, textToEncrypt);
        String[] returnValue = new String[2];
        returnValue[0] = encryptedValue;
        returnValue[1] = KEY;
        return returnValue;
    }

    @Override
    public String decrypt(String textToDecrypt, String key) {
        String decrypted = Encryptor.decrypt(key, INIT_VECTOR, textToDecrypt);
        return decrypted;
    }
}
