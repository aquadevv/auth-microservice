package ru.journalplus.journalplus.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.journalplus.journalplus.IntegrationTestBase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class PasswordEncryptionServiceTest extends IntegrationTestBase {

    @Autowired
    private PasswordEncryptionService passwordEncryptionService;

    @Test
    void testEncryptPassword() {
        String rawPassword = "password";
        String encryptedPassword = passwordEncryptionService.encrypt(rawPassword);
        assertNotEquals(rawPassword, encryptedPassword);

    }

    @Test
    void testDecryptPassword() {
        String rawPassword = "password";
        String encryptedPassword = passwordEncryptionService.encrypt(rawPassword);
        String decryptedPassword = passwordEncryptionService.decrypt(encryptedPassword);
        assertEquals(rawPassword, decryptedPassword);

    }

}
