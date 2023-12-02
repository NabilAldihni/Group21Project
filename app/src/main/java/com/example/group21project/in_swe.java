package com.example.group21project;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class in_swe extends AppCompatActivity {
    int input = 0;
    int[] inputs = new int[6];




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        for (int i = 0; i <= 5; i++) {
            inputs[i] = -1;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_swe);
        TextInputEditText A67text = findViewById(R.id.A67textInputEditText);
        TextInputLayout til = findViewById(R.id.A67textInputLayout);

        TextInputEditText A08text = findViewById(R.id.A08textInputEditText);
        TextInputLayout til2 = findViewById(R.id.A08textInputLayout);

        TextInputEditText A48text = findViewById(R.id.A48textInputEditText);
        TextInputLayout til3 = findViewById(R.id.A48textInputLayout);

        TextInputEditText A22text = findViewById(R.id.A22textInputEditText);
        TextInputLayout til4 = findViewById(R.id.A22textInputLayout);

        TextInputEditText A31text = findViewById(R.id.A31textInputEditText);
        TextInputLayout til5 = findViewById(R.id.A31textInputLayout);

        TextInputEditText A37text = findViewById(R.id.A37textInputEditText);
        TextInputLayout til6 = findViewById(R.id.A37textInputLayout);

        //TextInputEditText A08text = findViewById(R.id.A08textInputEditText);
        int inputting = setupTextInputValidation(A67text, til, 0);
        int inputting2 = setupTextInputValidation(A08text, til2, 1);
        int inputting3 = setupTextInputValidation(A48text, til3, 2);
        int inputting4 = setupTextInputValidation(A22text, til4, 3);
        int inputting5 = setupTextInputValidation(A31text, til5, 4);
        int inputting6 = setupTextInputValidation(A37text, til6, 5);

        Log.d("GradesDebug", "Grade A67: " + inputs[0]);
        Log.d("GradesDebug", "Grade A08: " + inputting2);
        Log.d("GradesDebug", "Grade A48: " + inputting3);
        Log.d("GradesDebug", "Grade A22: " + inputting4);
        Log.d("GradesDebug", "Grade A31: " + inputting5);
        Log.d("GradesDebug", "Grade A37: " + inputting6);

        double average = (inputting + inputting2 + inputting3 + inputting4 + inputting5 + inputting6) / 6.0;

        // Check if the average is greater than 70
        if (inputs[0] > 70) {
            Log.d("AverageDebug", "The average is greater than 70!"); // Add this line for debugging
            Toast.makeText(this, "The average is greater than 70!", Toast.LENGTH_SHORT).show();
        }
    }

    public int setupTextInputValidation(TextInputEditText editText, TextInputLayout til, int index) {

        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable editable) {

                if(editText.getText().toString().trim().isEmpty() == false) {

                    if(Integer.parseInt(editText.getText().toString()) > 100 ) {
                        til.setErrorEnabled(true);
                        til.setError("Please enter a grade from 0 to 100");
                        til.setErrorTextAppearance(R.style.ErrorAppearance);
                    }

                    else if(Integer.parseInt(editText.getText().toString()) <= 100 ) {
                        til.setErrorEnabled(false);
                        inputs[index] = Integer.parseInt(editText.getText().toString());




                        if (inputs[0] >= 0 && inputs[1] >= 0 && inputs[2] >= 0 && inputs[3] >= 0 && inputs[4] >= 0 && inputs[5] >= 0) {
                            int sum = 0;

                            for (int i = 0; i <= 5; i++) {
                                sum += inputs[i];
                            }

                            if (sum / 6.0 >= 70) {
                                Toast.makeText(in_swe.this, "The average is greater than 70!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                } else {
                    til.setErrorEnabled(true);
                    til.setError("Please enter a grade from 0 to 100");
                    til.setErrorTextAppearance(R.style.ErrorAppearance);
                }
            }
        });
        return inputs[index];
    }
}
