package com.picpay.view;

public class AuthorizationResponse {
    private boolean authorized;
    private String message;

    public boolean isAuthorized() {
        return authorized;
    }

    public String getMessage() {
        return message;
    }
}

