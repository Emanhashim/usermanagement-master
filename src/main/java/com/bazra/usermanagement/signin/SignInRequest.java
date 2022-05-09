package com.bazra.usermanagement.signin;

/**
 * SignIn Request
 * 
 * @author Bemnet
 * @version 4/2022
 *
 */
public class SignInRequest {
    private String username;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
