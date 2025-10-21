package helpers.extensions;

import dto.AuthData;
import dto.LoginResponse;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import tests.BaseTest;

import static steps.ApiSteps.login;

public class LoginExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        AuthData authData = new AuthData("JohnDoe", "John12345!");
        LoginResponse loginResponse = login(authData);

        if (context.getTestInstance().isPresent()) {
            Object testInstance = context.getTestInstance().get();
            if (testInstance instanceof BaseTest) {
                ((BaseTest) testInstance).setLoginResponse(loginResponse);
            }
        }

    }
}
