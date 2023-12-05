package com.example.group21project;

public interface LoginView {
    void showProgress();
    void hideProgress();
    void navigateToAdmin();
    void navigateToStudent();
    void showAuthenticationFailed(String text);
    String getEmail();
    String getPassword();
}
