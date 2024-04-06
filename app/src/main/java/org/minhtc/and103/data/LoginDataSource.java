package org.minhtc.and103.data;

import org.minhtc.and103.data.model.LoggedInUser;
import org.minhtc.and103.infrastructure.service.AuthService;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
@Deprecated
public class LoginDataSource {
    private AuthService authService;

    public Result<LoggedInUser> login(String username, String password) {

        if (username.equals("admin123") && password.equals("admin123")) {
            LoggedInUser user = new LoggedInUser(java.util.UUID.randomUUID().toString(), "admin");
            return new Result.Success<>(user);
        }
        try {
            // TODO: handle loggedInUser authentication
            LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe");
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}