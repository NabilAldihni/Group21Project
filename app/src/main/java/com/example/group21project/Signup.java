package com.example.group21project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;



public class Signup extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword, editTextId, editTextUsername;
    private CheckBox checkBoxAdmin;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextUsername = findViewById(R.id.editTextUsername);
        checkBoxAdmin = findViewById(R.id.checkBoxAdmin);
        Button buttonSignup = findViewById(R.id.buttonSignUp);
        TextView loginLink = findViewById(R.id.loginLink);

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                createNewUser();
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                startActivity(new Intent(Signup.this, Login.class));
                finish();
            }
        });
    }
    public void createNewUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String username = editTextUsername.getText().toString().trim();
        boolean admin = checkBoxAdmin.isChecked();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(Signup.this, "Please enter all details", Toast.LENGTH_SHORT).show();
            return;
        }

        //Firebase Authentication to create new user
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        String userId = mAuth.getCurrentUser().getUid();
                        User user = new User(username, email, admin);

                        firestore.collection("Users").document(userId).set(user)
                                .addOnSuccessListener(aVoid -> {
                                    Toast.makeText(Signup.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(Signup.this, Login.class);
                                    startActivity(intent);
                                    finish();
                                });
                    } else {
                        if(task.getException() != null) {
                            Log.e("SignupActivity", task.getException().getMessage());
                        }
                        Toast.makeText(Signup.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                    }
                });

    }

}