package org.minhtc.and103.data.payload.login;

public class LoginResponse {
    private String userId;
    private String username;

    public LoginResponse(String userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public LoginResponse() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
