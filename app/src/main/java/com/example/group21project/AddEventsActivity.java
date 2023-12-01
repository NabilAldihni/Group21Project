package com.example.group21project;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDateTime;

public class AddEventsActivity extends AppCompatActivity {
    private EditText eventNameInput;
    private EditText locationInput;
    private EditText capacityInput;     // TODO: remove unnecessary keys on the numerical keyboard (. , - etc.)
    private EditText descInput;
    private EventDateTimePickerFragment startTimePickerFragment;
    private EventDateTimePickerFragment endTimePickerFragment;
    private Button addEventButton;

    private FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_events);

        if (savedInstanceState == null) {
            Bundle startTimeSetupBundle = new Bundle();
            startTimeSetupBundle.putString("setTimeButtonText", "Start Time");
            startTimePickerFragment = new EventDateTimePickerFragment();
            startTimePickerFragment.setArguments(startTimeSetupBundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.eventStartTimePickerContainer, startTimePickerFragment)
                    .commit();

            Bundle endTimeSetupBundle = new Bundle();
            endTimeSetupBundle.putString("setTimeButtonText", "End Time");
            endTimePickerFragment = new EventDateTimePickerFragment();
            endTimePickerFragment.setArguments(endTimeSetupBundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.eventEndTimePickerContainer, endTimePickerFragment)
                    .commit();
        }

        eventNameInput = findViewById(R.id.eventNameInput);
        locationInput = findViewById(R.id.eventLocationInput);
        capacityInput = findViewById(R.id.eventCapacityInput);
        descInput = findViewById(R.id.eventDescriptionInput);
        addEventButton = findViewById(R.id.addEventButton);

        database = FirebaseFirestore.getInstance();
        Button addEventButton = (Button) findViewById(R.id.addEventButton);
        addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eventName = eventNameInput.getText().toString();
                LocalDateTime startTime = startTimePickerFragment.getPickerTime();
                LocalDateTime endTime = endTimePickerFragment.getPickerTime();
                String location = locationInput.getText().toString();
                String capacityStr = capacityInput.getText().toString();
                String desc = descInput.getText().toString();

                if (AddEventsInputValidator.validateEventInput(eventName, startTime, endTime, location, capacityStr)) {
                    int capacity = Integer.parseInt(capacityStr);
                    DepartmentEventFirebaseAdapter event = new DepartmentEventFirebaseAdapter(eventName, desc,
                            startTime, endTime, location, capacity);

                    Snackbar snackbar = Snackbar.make(view, "", Snackbar.LENGTH_LONG);
                    snackbar.setAction("Dismiss", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            snackbar.dismiss();
                        }
                    });

                    database.collection("Events").add(event)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    snackbar.setText("The event has been created");
                                    snackbar.show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    snackbar.setText("An error occurred, please try again");
                                    snackbar.show();
                                }
                            });
                }
                else {
                    Toast invalidInputToast = Toast.makeText(AddEventsActivity.this,
                            "Invalid input. Please check that the event information is valid", Toast.LENGTH_SHORT);
                    invalidInputToast.show();
                    Log.d("AddEventsDebug", "Invalid event input");
                }
            }
        });
    }
}