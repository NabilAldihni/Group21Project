package com.example.group21project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SpecialistPage extends AppCompatActivity {
    private Button button;

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
    }

    public void openSWE() {
        Intent intent = new Intent(this, SWE.class);
        startActivity(intent);
    }


}