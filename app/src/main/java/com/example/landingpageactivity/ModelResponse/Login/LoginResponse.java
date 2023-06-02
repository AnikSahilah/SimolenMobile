package com.example.landingpageactivity.ModelResponse.Login;

public class LoginResponse {
    private boolean status;
    private String message;
    private UserData data;

    // Getter methods
    public boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public UserData getData() {
        return data;
    }
}

