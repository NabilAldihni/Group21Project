package com.example.group21project;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;

public class LoginPresenter {
    private LoginView view;
    private LoginModel model;
    private final String EMAIL_PASSWORD_NOT_ENTERED_TEXT = "Please enter email and password";
    private final String AUTH_FAILED_TEXT = "Authentication failed.";


    public LoginPresenter(LoginView view, LoginModel model) {
        this.view = view;
        this.model = model;
    }

    public void onLoginClicked() {
        String email = view.getEmail();
        String password = view.getPassword();
        if(email.isEmpty() || password.isEmpty()) {
            view.showAuthenticationFailed(EMAIL_PASSWORD_NOT_ENTERED_TEXT);
            return;
        }

        view.showProgress();
        model.loginUser(email, password, task -> {
            view.hideProgress();
            if (task.isSuccessful()){
                FirebaseUser user = model.getCurrentUser();
                if (user != null) {
                    model.checkUserType(user, this::handleUserType);
                }
            }else {
                view.showAuthenticationFailed(AUTH_FAILED_TEXT);
            }
        });

    }

    private void handleUserType(Task<DocumentSnapshot> task ) {
        if (task.isSuccessful()) {
            DocumentSnapshot document = task.getResult();
            if (document.exists()) {
                if ((boolean) document.getData().get("admin")) {
                    view.navigateToAdmin();
                }else {
                    view.navigateToStudent();
                }
            }
        }
    }


    public void checkCurrentUser() {
        FirebaseUser currentUser = model.getCurrentUser();
        if(currentUser != null) {
            view.showProgress();
            model.checkUserType(currentUser, this::handleUserType);
        }
    }

    public String getEmailPasswordNotEnteredText() {
        return EMAIL_PASSWORD_NOT_ENTERED_TEXT;
    }

    public String getAuthFailedText() {
        return AUTH_FAILED_TEXT;
    }
}
