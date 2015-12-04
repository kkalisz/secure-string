package pl.kalisz.kamil.secure.methods;

/**
 * Created by T420 on 2015-12-03.
 */
public class BlowFishSecureMethodTest extends SecureMethodTest<BlowFishSecureMethod>{

    @Override
    public BlowFishSecureMethod createMethod() {
        return new BlowFishSecureMethod();
    }
}