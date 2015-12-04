package pl.kalisz.kamil.secure.methods;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.kalisz.kamil.secure.utils.ValueValidatorUtils;

public abstract class SecureMethodTest<V extends SecureMethod>
{
    private String MY_STRING_VALUE = "MY_STRING_VALUE";

    public abstract V createMethod();

    protected V secureMethod;

    @Before
    public void setUp()
    {
        secureMethod = createMethod();
    }

    //@Test
    public void whenNullValueIsEncryptedNullValueIsDecoded()
    {
        secureMethod.encode(null);

        byte [] decoded = secureMethod.decode();

        Assert.assertNull(decoded);
    }

    @Test
    public void whenEmptyValueIsEncryptedNullValueIsEmpty()
    {
        byte[] emptyChar = new byte[0];

        secureMethod.encode(emptyChar);
        byte [] decoded = secureMethod.decode();

        Assert.assertArrayEquals(emptyChar,decoded);
    }

    @Test
    public void whenEncodeUtfStringValueDecodedValueIsEqual()
    {
        secureMethod.encode(MY_STRING_VALUE.getBytes());

        byte[] decode = secureMethod.decode();
        String decodedValue = new String(decode);

        Assert.assertEquals(decodedValue, MY_STRING_VALUE);
    }

    @Test
    public void whenValueIsEncodedNoStringValueIsNotPresentInAnyField()
    {
        secureMethod.encode(MY_STRING_VALUE.getBytes());

        ValueValidatorUtils.assertNoValue(MY_STRING_VALUE,secureMethod);
    }
}