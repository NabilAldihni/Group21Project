package com.example.group21project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MinorPage extends AppCompatActivity {

    private Button button;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minor_page);

        button = (Button) findViewById(R.id.buttonFirst);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openMinorCourses();
            }
        });
    }
    public void openMinorCourses() {
        Intent intent = new Intent(this, MinorCourses.class);
        startActivity(intent);
    }

}