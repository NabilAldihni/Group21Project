package com.example.group21project;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {
    @Mock
    private LoginModel loginModel;
    @Mock
    private LoginView loginView;
    private final String VALID_EMAIL = "student@gmail.com";
    private final String VALID_PASSWORD = "Student1";
    private final String INVALID_EMAIL = "a@b.com";

    private LoginPresenter getPresenterForLoginInfo(String email, String password) {
        when(loginView.getEmail()).thenReturn(email);
        when(loginView.getPassword()).thenReturn(password);
        LoginPresenter loginPresenter = new LoginPresenter(loginView, loginModel);
        loginPresenter.onLoginClicked();
        return loginPresenter;
    }

    @Test
    public void loginCorrectDetailsPleaseEnterTextNotDisplayed() {
        LoginPresenter loginPresenter = getPresenterForLoginInfo(VALID_EMAIL, VALID_PASSWORD);
        verify(loginView, never()).showAuthenticationFailed(loginPresenter.getEmailPasswordNotEnteredText());
    }

    @Test
    public void loginCorrectDetailsAuthFailedTextNotDisplayed() {
        LoginPresenter loginPresenter = getPresenterForLoginInfo(VALID_EMAIL, VALID_PASSWORD);
        verify(loginView, never()).showAuthenticationFailed(loginPresenter.getAuthFailedText());
    }

    @Test
    public void emailPasswordBothEmpty() {
        LoginPresenter loginPresenter = getPresenterForLoginInfo("", "");
        verify(loginView, times(1)).showAuthenticationFailed(loginPresenter.getEmailPasswordNotEnteredText());
    }

    @Test
    public void emailEmptyPasswordNonempty() {
        LoginPresenter loginPresenter = getPresenterForLoginInfo("", "nonemptyPassword");
        verify(loginView, times(1)).showAuthenticationFailed(loginPresenter.getEmailPasswordNotEnteredText());
    }

    @Test
    public void passwordEmptyEmailNonempty() {
        LoginPresenter loginPresenter = getPresenterForLoginInfo("nonemptyEmail", "");
        verify(loginView, times(1)).showAuthenticationFailed(loginPresenter.getEmailPasswordNotEnteredText());
    }

    // TODO: find way to make the test wait until the authentication with Firebase is done and the text is displayed
//    @Test
//    public void emailInDatabasePasswordIncorrect() {
//        String incorrectPassword = VALID_PASSWORD + "suffixToMakePasswordIncorrect";
//        LoginPresenter loginPresenter = getPresenterForLoginInfo(VALID_EMAIL, incorrectPassword);
//        verify(loginView, times(1)).showAuthenticationFailed(loginPresenter.getAuthFailedText());
//    }

    // TODO: find way to make the test wait until the authentication with Firebase is done and the text is displayed
//    @Test
//    public void passwordInDatabaseEmailIncorrect() {
//        LoginPresenter loginPresenter = getPresenterForLoginInfo(INVALID_EMAIL, VALID_PASSWORD);
//        verify(loginView, times(1)).showAuthenticationFailed(loginPresenter.getAuthFailedText());
//    }
}