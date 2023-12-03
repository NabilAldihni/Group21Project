package com.example.group21project;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class User {

    private String username;
    private String email;
    private boolean admin;

    public User(String username, String email){
        this.username = username;
        this.email = email;
        this.admin = false;
    }

    public User(String username, String email, boolean admin){
        this.username = username;
        this.email = email;
        this.admin = admin;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email; }

    public boolean getAdmin() { return admin; }

    public void setAdmin(boolean admin) { this.admin = admin; }

}
