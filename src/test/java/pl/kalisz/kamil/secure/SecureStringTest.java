package pl.kalisz.kamil.secure;

import org.junit.Assert;
import org.junit.Test;
import pl.kalisz.kamil.secure.utils.ValueValidatorUtils;

import static org.junit.Assert.*;

/**
 * Created by T420 on 2015-11-20.
 */
public class SecureStringTest
{
    private static final String VALUE_TO_ENCRYPT = "Some secret value";

    @Test
    public void whenCreateSecureStringValueCanBeDecoded()
    {
        SecureString secureString = new SecureString(new StringBuffer(VALUE_TO_ENCRYPT).toString());

        byte [] decodedValue = secureString.getValue();
        String expected = new String(decodedValue);

        Assert.assertEquals(expected,VALUE_TO_ENCRYPT);
    }

    @Test
    public void whenCreateSecureStringNoInternalMemberHasRawValue()
    {
        SecureString secureString = new SecureString(VALUE_TO_ENCRYPT);

        ValueValidatorUtils.assertNoValue(VALUE_TO_ENCRYPT,secureString);
    }

    @Test
    public void whenCreateSecureStringFromNullValueNullValueIsReturned()
    {
        SecureString secureString = new SecureString((String)null);

        byte [] decodedValue = secureString.getValue();

        Assert.assertNull(decodedValue);
    }


}