package com.example.group21project;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
        import static org.junit.Assert.assertTrue;

class LoginPresenterTest {

    @Mock
    private LoginModel loginModel;
    @Mock
    private LoginView loginView;

    @Test
    @RunWith(MockitoJUnitRunner.class)
    public void loginPresenter_correctDetailsLogin_ReturnsTrue() {
        loginModel = new LoginModel();
        LoginPresenter loginPresenter = new LoginPresenter(loginView, loginModel);
        loginPresenter.onLoginClicked("name@email.com", "pass");
    }
}
