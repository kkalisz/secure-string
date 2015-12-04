package pl.kalisz.kamil.secure.methods;

import javax.crypto.*;
import java.io.IOException;

/**
 * Method for storing byte array in secure way tat uses DES implementation for encoding/decoding values
 */
public class DesSecureMethod implements SecureMethod {

    private static final String BLOWFISH = "DES";

    private Cipher encodeCipher;

    private Cipher decodeCipher;

    private byte[] encoded;

    public DesSecureMethod()
    {
        try {
            KeyGenerator keygenerator = KeyGenerator.getInstance(BLOWFISH);

            SecretKey secretkey = keygenerator.generateKey();

            encodeCipher = Cipher.getInstance(BLOWFISH);
            decodeCipher = Cipher.getInstance(BLOWFISH);

            encodeCipher.init(Cipher.ENCRYPT_MODE, secretkey);
            decodeCipher.init(Cipher.DECRYPT_MODE, secretkey);
        }
        catch (Exception e)
        {
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
