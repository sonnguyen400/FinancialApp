package com.sonnguyen.individual.nhs.security.core;

public interface PasswordEncryptProvider {
    String encryptPassword(String password);
    String decryptPassword(String encryptedPassword);
}
