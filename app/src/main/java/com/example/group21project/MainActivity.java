package com.example.group21project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: replace these makeshift buttons with the navigation bar buttons
        Button eventsButton = (Button) findViewById(R.id.tempEventsButton);
        eventsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, EventsActivity.class));
            }
        });

        Button addEventsButton = (Button) findViewById(R.id.tempEventsAddButton);
        addEventsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddEventsActivity.class));
            }
        });
    }
}