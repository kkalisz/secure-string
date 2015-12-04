package pl.kalisz.kamil.secure.methods;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.IOException;
import java.security.SecureRandom;

/**
 * Method for storing byte array in secure way tat uses AES 128 implementation for encoding/decoding values
 */
public class AesSecureMethod implements SecureMethod {

    private static final String ALGORITHM = "AES/CBC/PKCS5PADDING";
    private static final int KEY_SIZE = 128;
    private static final String TYPE = "AES";

    private Cipher encodeCipher;

    private Cipher decodeCipher;

    private byte[] encoded;

    public AesSecureMethod() {
        try {
            SecureRandom secureRandom = new SecureRandom();
            KeyGenerator keyGenerator = KeyGenerator.getInstance(TYPE);
            keyGenerator.init(secureRandom);
            keyGenerator.init(KEY_SIZE);
            SecretKey secretKey = keyGenerator.generateKey();
            encodeCipher = Cipher.getInstance(ALGORITHM);
            byte[] iv = new byte[encodeCipher.getBlockSize()];

            IvParameterSpec ivParams = new IvParameterSpec(iv);
            encodeCipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParams);
            decodeCipher = Cipher.getInstance(ALGORITHM);
            decodeCipher.init(Cipher.DECRYPT_MODE, secretKey,ivParams);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void encode(byte[] valueToEncrypt) {
        try {
            byte [] basedValue = Base64.encodeBytesToBytes(valueToEncrypt);
            encoded = encodeCipher.doFinal(basedValue);
        } catch (BadPaddingException e) {
            throw new IllegalStateException(e);
        } catch (IllegalBlockSizeException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public byte[] decode() {
        try {
            return Base64.decode(decodeCipher.doFinal(encoded));
        } catch (BadPaddingException e) {
            throw new IllegalStateException(e);
        } catch (IllegalBlockSizeException e) {
            throw new IllegalStateException(e);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
