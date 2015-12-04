package pl.kalisz.kamil.secure.utils;


import org.junit.Assert;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Utility that checks no nested objects of type String or char [] have specified value.
 */
public class ValueValidatorUtils {

    static int MAX_DEPTH = 5;
    public static void assertNoValue(String valueToFind, Object objectToSearchIn) {
        try {
            assertInternal(valueToFind, objectToSearchIn,0);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private static void assertInternal(String valueToFind, Object objectToSearchIn, int depth) throws IllegalAccessException {
        if (objectToSearchIn == null) {
            return;
        }
        if(depth > MAX_DEPTH)
        {
            return;
        }

        Class objectClass = objectToSearchIn.getClass();
        while (objectClass != null) {
            Field[] declaredFields = objectClass.getDeclaredFields();
            for (Field field : declaredFields)
            {
                field.setAccessible(true);

                if(field.get(objectToSearchIn) == null)
                {
                    continue;
                }
                if (field.getType() == String.class) {
                    String objectToValidate = (String) field.get(objectToSearchIn);
                    if (objectToValidate.equals(objectToSearchIn)) {
                        Assert.fail(String.format("Value %s found in object %s", field.getName()));
                    }
                } else if (field.getType() == byte[].class) {
                    field.setAccessible(true);

                    Assert.assertFalse(Arrays.equals(valueToFind.getBytes(), (byte[]) field.get(objectToSearchIn)));
                } else if (field.getType().isPrimitive()) {
                    // nothing
                } else if(field.getType() != objectToSearchIn.getClass()) {
                    assertInternal(valueToFind, field.get(objectToSearchIn),depth+1);
                }
            }
            objectClass = objectClass.getSuperclass();
        }
    }
}
