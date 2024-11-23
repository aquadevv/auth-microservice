package ru.journalplus.journalplus.service;


import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncryptionService {
    private final TextEncryptor textEncryptor;


    public PasswordEncryptionService() {
        Dotenv dotenv = Dotenv.load();
        String encryptionKey = dotenv.get("ENCRYPTION_KEY");
        String encryptionSalt = dotenv.get("ENCRYPTION_SOLT");
        assert encryptionKey != null;
        assert encryptionSalt != null;
        this.textEncryptor = Encryptors.text(encryptionKey, encryptionSalt);
    }

    public String encrypt(String rawPassword) {
        return textEncryptor.encrypt(rawPassword);
    }

    public String decrypt(String encryptedPassword) {
        return textEncryptor.decrypt(encryptedPassword);
    }
}
