package com.kids.crm.service;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class Encryption {
    private static final String PASSWORD = "pef(*EWffe^&@fdFG3f";

    public static String encrypt(String txt) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(PASSWORD);                     // we HAVE TO set a password
        encryptor.setAlgorithm("PBEWithMD5AndTripleDES");    // optionally set the algorithm

        return encryptor.encrypt(txt);
    }

    public static String decrypt(String encryptedText) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(PASSWORD);                     // we HAVE TO set a password
        encryptor.setAlgorithm("PBEWithMD5AndTripleDES");

        return encryptor.decrypt(encryptedText);
    }
}
