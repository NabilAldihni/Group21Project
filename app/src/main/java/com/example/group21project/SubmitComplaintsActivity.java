package com.example.group21project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SubmitComplaintsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_complaints);

        Button submitBtn = (Button) findViewById(R.id.submitComplaintBtn);
        TextInputEditText complaintTextInput = (TextInputEditText) findViewById(R.id.complaintsTextInput);

        submitBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // Upload complaint to Firebase
                FirebaseFirestore db = FirebaseFirestore.getInstance();

                Map<String, Object> complaint = new HashMap<>();
                complaint.put("description", complaintTextInput.getText().toString());

                // Add a new document with a generated ID
                db.collection("complaints")
                        .add(complaint)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Snackbar snackbar = Snackbar.make(v, "Your complaint has been submitted", Snackbar.LENGTH_LONG);
                                snackbar.setAction("Dismiss", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        snackbar.dismiss();
                                    }
                                });
                                snackbar.show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Snackbar snackbar = Snackbar.make(v, "Something went wrong, please try again", Snackbar.LENGTH_LONG);
                                snackbar.setAction("Dismiss", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        snackbar.dismiss();
                                    }
                                });
                                snackbar.show();
                            }
                        });


            }
        });
    }
}