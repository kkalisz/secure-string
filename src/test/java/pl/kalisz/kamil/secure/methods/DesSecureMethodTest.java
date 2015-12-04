package pl.kalisz.kamil.secure.methods;

public class DesSecureMethodTest extends SecureMethodTest<DesSecureMethod> {

    @Override
    public DesSecureMethod createMethod() {
        return new DesSecureMethod();
    }
}