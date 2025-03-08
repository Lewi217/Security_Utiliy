package DevLewi.crypto_utils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.util.Arrays;
import java.util.Base64;

public class CryptoUtils {
    private static final String ALGORITHM = "AES/GCM/NoPadding";
    private static final int TAG_LENGTH = 128;
    private static final int IV_LENGTH = 12;
    private static final int KEY_LENGTH = 256;

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static SecretKey generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(KEY_LENGTH);
        return keyGen.generateKey();
    }

    public static String encrypt(String plaintext, SecretKey key) throws Exception {
        byte[] iv = new byte[IV_LENGTH];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(iv);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        GCMParameterSpec parameterSpec = new GCMParameterSpec(TAG_LENGTH, iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec);

        byte[] cipherText = cipher.doFinal(plaintext.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(iv);
        outputStream.write(cipherText);

        return Base64.getEncoder().encodeToString(outputStream.toByteArray());
    }

    public static String decrypt(String encryptedData, SecretKey key) throws Exception {
        byte[] decoded = Base64.getDecoder().decode(encryptedData);

        byte[] iv = Arrays.copyOfRange(decoded, 0, IV_LENGTH);
        byte[] cipherText = Arrays.copyOfRange(decoded, IV_LENGTH, decoded.length);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        GCMParameterSpec parameterSpec = new GCMParameterSpec(TAG_LENGTH, iv);
        cipher.init(Cipher.DECRYPT_MODE, key, parameterSpec);

        return new String(cipher.doFinal(cipherText));
    }

    public static String keyToString(SecretKey key) {
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    public static SecretKey stringToKey(String keyString) {
        byte[] decoded = Base64.getDecoder().decode(keyString);
        return new SecretKeySpec(decoded, "AES");
    }
}