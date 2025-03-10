package DevLewi.crypto_utils;

import org.junit.jupiter.api.Test;
import javax.crypto.SecretKey;
import static org.junit.jupiter.api.Assertions.*;

public class CryptoUtilsTest {

    @Test
    public void testEncryptionCycleWithSecretKey() throws Exception {
        SecretKey key = CryptoUtils.generateKey();
        String original = "Try hack me pals!!!";
        String encrypted = CryptoUtils.encrypt(original, key);
        String decrypted = CryptoUtils.decrypt(encrypted, key);

        assertNotNull(encrypted);
        assertEquals(original, decrypted);
    }

    @Test
    public void testEncryptionCycleWithKeyBytes() throws Exception {
        byte[] keyBytes = CryptoUtils.generateKeyBytes();
        String original = "Another secret message!";
        String encrypted = CryptoUtils.encrypt(original, keyBytes);
        String decrypted = CryptoUtils.decrypt(encrypted, keyBytes);

        assertNotNull(encrypted);
        assertEquals(original, decrypted);
    }

    @Test
    public void testKeySerialization() throws Exception {
        SecretKey originalKey = CryptoUtils.generateKey();
        String keyString = CryptoUtils.keyToString(originalKey);
        SecretKey restoredKey = CryptoUtils.stringToKey(keyString);

        assertEquals(
                CryptoUtils.keyToString(originalKey),
                CryptoUtils.keyToString(restoredKey)
        );
    }

    @Test
    public void testEmptyStringEncryption() throws Exception {
        SecretKey key = CryptoUtils.generateKey();
        assertThrows(IllegalArgumentException.class, () -> {
            CryptoUtils.encrypt("", key);
        });
    }

    @Test
    public void testNullDecryption() throws Exception {
        SecretKey key = CryptoUtils.generateKey();
        assertThrows(IllegalArgumentException.class, () -> {
            CryptoUtils.decrypt(null, key);
        });
    }

    @Test
    public void testInvalidKeyDecryption() throws Exception {
        SecretKey key1 = CryptoUtils.generateKey();
        SecretKey key2 = CryptoUtils.generateKey();
        String original = "Test message";
        String encrypted = CryptoUtils.encrypt(original, key1);

        assertThrows(Exception.class, () -> {
            CryptoUtils.decrypt(encrypted, key2);
        });
    }
}