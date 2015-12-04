package pl.kalisz.kamil.secure.methods;


public class AesSecureMethodTest extends SecureMethodTest<AesSecureMethod> {
    @Override
    public AesSecureMethod createMethod()
    {
        return new AesSecureMethod();
    }
}
