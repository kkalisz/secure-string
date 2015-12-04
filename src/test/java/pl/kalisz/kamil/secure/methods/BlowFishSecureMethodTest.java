package pl.kalisz.kamil.secure.methods;

public class BlowFishSecureMethodTest extends SecureMethodTest<BlowFishSecureMethod>{

    @Override
    public BlowFishSecureMethod createMethod() {
        return new BlowFishSecureMethod();
    }
}