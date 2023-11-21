package com.example.group21project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

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
                // text is stored in complaintTextInput.getText()

                Snackbar snackbar = Snackbar.make(v, "Your complaint has been submitted", Snackbar.LENGTH_LONG);
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
}