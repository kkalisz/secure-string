package pl.kalisz.kamil.secure;

import org.junit.Assert;
import org.junit.Test;
import pl.kalisz.kamil.secure.utils.ValueValidatorUtils;

import static org.junit.Assert.*;

public class SecureStringTest
{
    private static final String VALUE_TO_ENCRYPT = "Some secret value";

    private static final String OTHER_VALUE_TO_ENCRYPT = "Hey Look at me";


    @Test
    public void whenCreateSecureStringFromStringValueCanBeDecoded()
    {
        String valueToEncrypt = new StringBuffer(VALUE_TO_ENCRYPT).toString();
        SecureString secureString = new SecureString(valueToEncrypt);

        ValueValidatorUtils.assertNoValue(VALUE_TO_ENCRYPT,valueToEncrypt);
        byte [] decodedValue = secureString.getValue();
        String expected = new String(decodedValue);

        Assert.assertEquals(expected,VALUE_TO_ENCRYPT);
    }

    @Test
    public void whenCreateSecureStringFromCharArrayValueCanBeDecoded()
    {
        char[] valueToEncrypt = new StringBuffer(VALUE_TO_ENCRYPT).toString().toCharArray();
        SecureString secureString = new SecureString(valueToEncrypt);

        ValueValidatorUtils.assertNoValue(VALUE_TO_ENCRYPT,valueToEncrypt);
        byte [] decodedValue = secureString.getValue();
        String expected = new String(decodedValue);

        Assert.assertEquals(expected,VALUE_TO_ENCRYPT);
    }

    @Test
    public void whenCreateSecureStringFromByteArrayValueCanBeDecoded()
    {
        byte[] valueToEncrypt = new StringBuffer(VALUE_TO_ENCRYPT).toString().getBytes();
        SecureString secureString = new SecureString(valueToEncrypt);

        ValueValidatorUtils.assertNoValue(VALUE_TO_ENCRYPT,valueToEncrypt);
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

    @Test
    public void whenCreateSecureStringFromStringAndAddNextStringValueDecodedValueIsMergeOfTwoValues()
    {
        String valueToEncrypt = new StringBuffer(VALUE_TO_ENCRYPT).toString();
        SecureString secureString = new SecureString(valueToEncrypt);

        ValueValidatorUtils.assertNoValue(VALUE_TO_ENCRYPT,valueToEncrypt);

        String valueToAdd = new StringBuffer(OTHER_VALUE_TO_ENCRYPT).toString();
        secureString.addValue(valueToAdd);

        ValueValidatorUtils.assertNoValue(OTHER_VALUE_TO_ENCRYPT,valueToAdd);

        byte [] decodedValue = secureString.getValue();
        String decodedString = new String(decodedValue);

        String expectedValue = VALUE_TO_ENCRYPT+OTHER_VALUE_TO_ENCRYPT;
        Assert.assertEquals(expectedValue,decodedString);
    }

    @Test
    public void whenCreateSecureStringFromStringAndAddNextCharArrayValueDecodedValueIsMergeOfTwoValues()
    {
        char [] valueToEncrypt = new StringBuffer(VALUE_TO_ENCRYPT).toString().toCharArray();
        SecureString secureString = new SecureString(valueToEncrypt);

        ValueValidatorUtils.assertNoValue(VALUE_TO_ENCRYPT,valueToEncrypt);

        String valueToAdd = new StringBuffer(OTHER_VALUE_TO_ENCRYPT).toString();
        secureString.addValue(valueToAdd);

        ValueValidatorUtils.assertNoValue(OTHER_VALUE_TO_ENCRYPT,valueToAdd);

        byte [] decodedValue = secureString.getValue();
        String decodedString = new String(decodedValue);

        String expectedValue = VALUE_TO_ENCRYPT+OTHER_VALUE_TO_ENCRYPT;
        Assert.assertEquals(expectedValue,decodedString);
    }

    @Test
    public void whenCreateSecureStringFromStringAndAddNextByteArrayValueDecodedValueIsMergeOfTwoValues()
    {
        byte [] valueToEncrypt = new StringBuffer(VALUE_TO_ENCRYPT).toString().getBytes();
        SecureString secureString = new SecureString(valueToEncrypt);

        ValueValidatorUtils.assertNoValue(VALUE_TO_ENCRYPT,valueToEncrypt);

        String valueToAdd = new StringBuffer(OTHER_VALUE_TO_ENCRYPT).toString();
        secureString.addValue(valueToAdd);

        ValueValidatorUtils.assertNoValue(OTHER_VALUE_TO_ENCRYPT,valueToAdd);

        byte [] decodedValue = secureString.getValue();
        String decodedString = new String(decodedValue);

        String expectedValue = VALUE_TO_ENCRYPT+OTHER_VALUE_TO_ENCRYPT;
        Assert.assertEquals(expectedValue,decodedString);
    }

    @Test
    public void whenCreateSecureStringFromNullAndAddNextStringValueDecodedValueIsMergeOfTwoValues()
    {
        SecureString secureString = new SecureString((byte [])null);

        String valueToAdd = new StringBuffer(OTHER_VALUE_TO_ENCRYPT).toString();
        secureString.addValue(valueToAdd);

        ValueValidatorUtils.assertNoValue(OTHER_VALUE_TO_ENCRYPT,valueToAdd);

        byte [] decodedValue = secureString.getValue();
        String decodedString = new String(decodedValue);

        String expectedValue = OTHER_VALUE_TO_ENCRYPT;
        Assert.assertEquals(expectedValue,decodedString);
    }

    @Test
    public void whenCreateSecureStringFromNullAndAddNullValueDecodedValueIsMergeOfTwoValues()
    {
        SecureString secureString = new SecureString((String)null);

        secureString.addValue((String)null);

        byte [] decodedValue = secureString.getValue();

        Assert.assertNull(decodedValue);
    }



}