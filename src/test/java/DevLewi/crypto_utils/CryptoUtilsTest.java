package DevLewi.crypto_utils;

import org.junit.jupiter.api.Test;
import javax.crypto.SecretKey;
import static org.junit.jupiter.api.Assertions.*;

public class CryptoUtilsTest {
    @Test
    public void testEncryptionCycle() throws Exception {
        SecretKey key = CryptoUtils.generateKey();
        String original = "Try hack me pals!!!";
        String encrypted = CryptoUtils.encrypt(original, key);
        String decrypted = CryptoUtils.decrypt(encrypted, key);

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
}