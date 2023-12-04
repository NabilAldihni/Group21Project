package com.example.group21project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;

public class Login extends AppCompatActivity implements LoginView{

    private EditText editTextEmail, editTextPassword;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseMessaging.getInstance().subscribeToTopic("your_topic");
        mAuth = FirebaseAuth.getInstance();

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        Button buttonLogin = findViewById(R.id.buttonLogin);
        TextView signupLink = findViewById(R.id.signupLink);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);

        presenter = new LoginPresenter(this, new LoginModel());

        buttonLogin.setOnClickListener(v -> presenter.onLoginClicked(
                editTextEmail.getText().toString().trim(),
                editTextPassword.getText().toString().trim()));
        signupLink.setOnClickListener(v -> startActivity(new Intent(Login.this, Signup.class)));

    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void navigateToAdmin() {
        startActivity(new Intent(Login.this, AdminActivity.class));
    }

    @Override
    public void navigateToStudent() {
        startActivity(new Intent(Login.this, StudentActivity.class));
        finish();
    }

    @Override
    public void showAuthenticationFailed(String text) {
        Toast.makeText(Login.this, text, Toast.LENGTH_SHORT).show();
    }
}