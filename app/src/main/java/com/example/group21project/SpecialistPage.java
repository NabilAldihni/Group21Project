package com.example.group21project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SpecialistPage extends AppCompatActivity {
    private Button button;
    private Button button1;
    private Button button2;
    private Button button3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.specialist_page);


        button = (Button) findViewById(R.id.buttonFirst);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openSWE();
            }
        });

        button1 = (Button) findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openSWE();
            }
        });

        button2 = (Button) findViewById(R.id.button);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openInformationSystems();
            }
        });

        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openEntrepreneurhsip();
            }
        });
    }

    public void openSWE() {
        Intent intent = new Intent(this, SWE.class);
        startActivity(intent);
    }

    public void openInformationSystems() {
        Intent intent = new Intent(this, InformationSystems.class);
        startActivity(intent);
    }

    public void openEntrepreneurhsip() {
        Intent intent = new Intent(this, Entrepreneurship.class);
        startActivity(intent);
    }



}