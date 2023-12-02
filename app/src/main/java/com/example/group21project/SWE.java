package com.example.group21project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SWE extends AppCompatActivity {
    private Button yes_button;
    private Button no_button;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swe_page);


        yes_button = (Button) findViewById(R.id.yesbutton);
        yes_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openCorrectCourses1();
            }
        });

        no_button = (Button) findViewById(R.id.nobutton);
        no_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openIncorrectCourses();
            }
        });
    }

    public void openCorrectCourses1() {
        Intent intent = new Intent(this, CorrectCourses1.class);
        startActivity(intent);
    }

    public void openIncorrectCourses() {
        Intent intent = new Intent(this, IncorrectCourses.class);
        startActivity(intent);
    }



}