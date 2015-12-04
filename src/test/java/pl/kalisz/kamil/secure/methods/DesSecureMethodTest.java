package pl.kalisz.kamil.secure.methods;

/**
 * Created by T420 on 2015-12-03.
 */
public class DesSecureMethodTest extends SecureMethodTest<DesSecureMethod> {

    @Override
    public DesSecureMethod createMethod() {
        return new DesSecureMethod();
    }
}