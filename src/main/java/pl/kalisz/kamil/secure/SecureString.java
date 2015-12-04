package pl.kalisz.kamil.secure;

import pl.kalisz.kamil.secure.methods.AesSecureMethod;
import pl.kalisz.kamil.secure.methods.BlowFishSecureMethod;
import pl.kalisz.kamil.secure.methods.DesSecureMethod;
import pl.kalisz.kamil.secure.methods.SecureMethod;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Implementation of class that can store char sequence in secure way
 */
public class SecureString implements Serializable {

    private static final String CHARSET_NAME = "UTF-8";

    private static SecureMethod createSecureMethod() throws IllegalAccessException, InstantiationException {
        SecureRandom secureRandom = new SecureRandom();
        List<Class<? extends SecureMethod>> SECURE_METHODS = new ArrayList<Class<? extends SecureMethod>>();
        SECURE_METHODS.add(AesSecureMethod.class);
        SECURE_METHODS.add(BlowFishSecureMethod.class);
        SECURE_METHODS.add(DesSecureMethod.class);
        return SECURE_METHODS.get(Math.abs(secureRandom.nextInt(SECURE_METHODS.size() - 1))).newInstance();
    }

    private SecureMethod secureMethod;

    public SecureString(byte[] valueToEncrypt) {
        if (valueToEncrypt == null) {
            return;
        }
        try {
            secureMethod = createSecureMethod();
            secureMethod.encode(valueToEncrypt);
            Arrays.fill(valueToEncrypt, (byte) 0);

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public SecureString(char[] valueToEncrypt) {
        this(toBytes(valueToEncrypt));
    }

    private static byte[] toBytes(char[] chars) {
        if (chars == null) {
            return null;
        }
        CharBuffer charBuffer = CharBuffer.wrap(chars);
        ByteBuffer byteBuffer = Charset.forName(CHARSET_NAME).encode(charBuffer);
        byte[] bytes = Arrays.copyOfRange(byteBuffer.array(),
                byteBuffer.position(), byteBuffer.limit());
        Arrays.fill(charBuffer.array(), '\u0000'); // clear sensitive data
        Arrays.fill(byteBuffer.array(), (byte) 0); // clear sensitive data
        return bytes;
    }

    public SecureString(String valueToEncrypt) {
        this(toBytes(valueToEncrypt));
    }

    private static byte[] toBytes(String valueToEncrypt) {
        if (valueToEncrypt == null) {
            return null;
        }
        try {
            byte[] result = valueToEncrypt.getBytes(CHARSET_NAME);
            Field valueField = String.class.getDeclaredField("value");
            valueField.setAccessible(true);
            char[] arrayToClean = (char[]) valueField.get(valueToEncrypt);
            Arrays.fill(arrayToClean, '\u0000');
            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public byte[] getValue()
    {
        if(secureMethod != null) {
            return secureMethod.decode();
        }
        return null;
    }
}
