package com.example.group21project;

import androidx.annotation.NonNull;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.TextView;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button new_button;
    private Button new_button_minor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        TextView mTitle = toolbar.findViewById(R.id.toolbar_title);

        setSupportActionBar(toolbar);
        mTitle.setText(toolbar.getTitle());

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        button = (Button) findViewById(R.id.buttonFirst);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openSpecialistPage();
            }
        });
        new_button = (Button) findViewById(R.id.button2);
        new_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openMajorPage();
            }
        });

        new_button_minor = (Button) findViewById(R.id.button3);
        new_button_minor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openMinorPage();
            }
        });
    }

    public void openSpecialistPage() {
        Intent intent = new Intent(this, SpecialistPage.class);
        startActivity(intent);
    }

    public void openMajorPage() {
        Intent intent = new Intent(this, MajorPage.class);
        startActivity(intent);
    }

    public void openMinorPage() {
        Intent intent = new Intent(this, MinorPage.class);
        startActivity(intent);
    }
}

