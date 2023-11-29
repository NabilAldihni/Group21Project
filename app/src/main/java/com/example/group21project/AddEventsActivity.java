package com.example.group21project;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDateTime;

public class AddEventsActivity extends AppCompatActivity {
    private EditText eventNameInput;
    private EditText locationInput;
    private EditText capacityInput;     // TODO: remove unnecessary keys on the numerical keyboard (. , - etc.)
    private EditText descInput;
    private EventDateTimePickerFragment startTimePickerFragment;
    private EventDateTimePickerFragment endTimePickerFragment;
    private Button addEventButton;

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

        Button addEventButton = (Button) findViewById(R.id.addEventButton);
        addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eventName = eventNameInput.getText().toString();
                LocalDateTime startTime = startTimePickerFragment.getPickerTime();
                LocalDateTime endTime = endTimePickerFragment.getPickerTime();
                String location = locationInput.getText().toString();
                String capacity = capacityInput.getText().toString();
                String desc = descInput.getText().toString();

                if (AddEventsInputValidator.validateEventInput(eventName, startTime, endTime, location, capacity)) {
                    // TODO: firebase stuff
                    Log.d("AddEventsDebug", "Event name: " + eventName);
                    Log.d("AddEventsDebug", "Event start time: " + startTime.toString());
                    Log.d("AddEventsDebug", "Event end time: " + endTime.toString());
                    Log.d("AddEventsDebug", "Event location: " + location);
                    Log.d("AddEventsDebug", "Event capacity: " + capacity);
                    Log.d("AddEventsDebug", "Event description: " + desc);
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