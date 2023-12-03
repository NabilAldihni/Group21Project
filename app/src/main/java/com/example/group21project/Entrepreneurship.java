package com.example.group21project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Entrepreneurship extends AppCompatActivity {
    private Button yes_button;
    private Button no_button;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrepreneurship_courses);


        yes_button = (Button) findViewById(R.id.yesbutton);
        yes_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openCorrectCoursesEntrepreneurship();
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

    public void openCorrectCoursesEntrepreneurship() {
        Intent intent = new Intent(this, CorrectCoursesEntrepreneurship.class);
        startActivity(intent);
    }

    public void openIncorrectCourses() {
        Intent intent = new Intent(this, IncorrectCourses.class);
        startActivity(intent);
    }



}