package com.example.group21project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CorrectCourses1 extends AppCompatActivity {
    private Button in_button;
    private Button out_button;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.correct_courses1);


        in_button = (Button) findViewById(R.id.in);
        in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                open_in_Swe();
            }
        });

        out_button = (Button) findViewById(R.id.out);
        out_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                open_out_Swe();
            }
        });

    }

    public void open_in_Swe() {
        Intent intent = new Intent(this,in_swe.class);
        startActivity(intent);
    }

    public void open_out_Swe() {
        Intent intent = new Intent(this, out_swe.class);
        startActivity(intent);
    }



}