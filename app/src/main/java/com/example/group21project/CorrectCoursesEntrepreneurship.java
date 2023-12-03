package com.example.group21project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CorrectCoursesEntrepreneurship extends AppCompatActivity {
    private Button in_button;
    private Button out_button;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.correct_courses_entrepreneurship);


        in_button = (Button) findViewById(R.id.in);
        in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                open_InfoSystems();
            }
        });

        out_button = (Button) findViewById(R.id.out);
        out_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                open_out_entrepreneurship();
            }
        });

    }

    public void open_InfoSystems() {
        Intent intent = new Intent(this, InEntrepreneurship.class);
        startActivity(intent);
    }

    public void open_out_entrepreneurship() {
        Intent intent = new Intent(this, OutEntrepreneurship.class);
        startActivity(intent);
    }



}