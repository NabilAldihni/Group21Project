package com.example.group21project;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginModel {
    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;

    public LoginModel() {
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public void loginUser(String email, String password, OnCompleteListener<AuthResult> listener) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(listener);
    }

    public FirebaseUser getCurrentUser(){
        return mAuth.getCurrentUser();
    }

    public void checkUserType(FirebaseUser user, OnCompleteListener<DocumentSnapshot> listener) {
        DocumentReference docRef = firebaseFirestore.collection("Users").document(user.getUid());
        docRef.get().addOnCompleteListener(listener);
    }
}
