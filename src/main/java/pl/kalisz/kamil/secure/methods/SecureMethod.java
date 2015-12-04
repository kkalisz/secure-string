package pl.kalisz.kamil.secure.methods;

import java.io.Serializable;

/**
 * interface that provides functionality to store byte array in secure way
 */
public interface SecureMethod extends Serializable
{
    /**
     * method perform encoding and store value in secure way
     * @param valueToEncrypt value that should be encrypted and stored
     */
    void encode(byte [] valueToEncrypt);

    /**
     * method should decode previously stored value
     * @return decoded value or null if no value was stored
     */
    byte [] decode();
}
