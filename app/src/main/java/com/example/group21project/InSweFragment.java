package com.example.group21project;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import io.github.muddz.styleabletoast.StyleableToast;

public class InSweFragment extends Fragment {

    private int input = 0;
    private double[] inputs = new double[6];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.in_swe, container, false);

        for (int i = 0; i <= 5; i++) {
            inputs[i] = -1;
        }

        TextInputEditText A67text = view.findViewById(R.id.A67textInputEditText);
        TextInputLayout til = view.findViewById(R.id.A67textInputLayout);

        TextInputEditText A08text = view.findViewById(R.id.A08textInputEditText);
        TextInputLayout til2 = view.findViewById(R.id.A08textInputLayout);

        TextInputEditText A48text = view.findViewById(R.id.A48textInputEditText);
        TextInputLayout til3 = view.findViewById(R.id.A48textInputLayout);

        TextInputEditText A22text = view.findViewById(R.id.A22textInputEditText);
        TextInputLayout til4 = view.findViewById(R.id.A22textInputLayout);

        TextInputEditText A31text = view.findViewById(R.id.A31textInputEditText);
        TextInputLayout til5 = view.findViewById(R.id.A31textInputLayout);

        TextInputEditText A37text = view.findViewById(R.id.A37textInputEditText);
        TextInputLayout til6 = view.findViewById(R.id.A37textInputLayout);

        //TextInputEditText A08text = findViewById(R.id.A08textInputEditText);
        double inputting = setupTextInputValidation(A67text, til, 0);
        double inputting2 = setupTextInputValidation(A08text, til2, 1);
        double inputting3 = setupTextInputValidation(A48text, til3, 2);
        double inputting4 = setupTextInputValidation(A22text, til4, 3);
        double inputting5 = setupTextInputValidation(A31text, til5, 4);
        double inputting6 = setupTextInputValidation(A37text, til6, 5);


        Button calculateButton = view.findViewById(R.id.gradesButton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateGrade(A67text, A08text, A48text, A22text, A31text, A37text);
            }
        });
        return view;
    }

    public void calculateGrade(TextInputEditText A67text, TextInputEditText A08text, TextInputEditText A48text, TextInputEditText A22text, TextInputEditText A31text, TextInputEditText A37text) {

        boolean allInputsNotEmpty = true;

        // Check each TextInputEditText for emptiness
        if (A67text.getText().toString().trim().isEmpty() ||
                A08text.getText().toString().trim().isEmpty() ||
                A48text.getText().toString().trim().isEmpty() ||
                A22text.getText().toString().trim().isEmpty() ||
                A31text.getText().toString().trim().isEmpty() ||
                A37text.getText().toString().trim().isEmpty()) {

            allInputsNotEmpty = false;
        }

        if (allInputsNotEmpty) {
            double[] gpaValues = new double[inputs.length];
            double sum = 0;
            boolean pass = true;

            for (int i = 0; i < inputs.length; i++) {
                if (inputs[i] >= 85) {
                    gpaValues[i] = 4.0;
                } else if (inputs[i] >= 80) {
                    gpaValues[i] = 3.7;
                } else if (inputs[i] >= 77) {
                    gpaValues[i] = 3.3;
                } else if (inputs[i] >= 73) {
                    gpaValues[i] = 3.0;
                } else if (inputs[i] >= 70) {
                    gpaValues[i] = 2.7;
                } else if (inputs[i] >= 67) {
                    gpaValues[i] = 2.3;
                } else if (inputs[i] >= 63) {
                    gpaValues[i] = 2.0;
                } else if (inputs[i] >= 60) {
                    gpaValues[i] = 1.7;
                } else if (inputs[i] >= 57) {
                    gpaValues[i] = 1.3;
                } else if (inputs[i] >= 53) {
                    gpaValues[i] = 1.0;
                } else if (inputs[i] >= 50) {
                    gpaValues[i] = 0.7;
                } else {
                    gpaValues[i] = 0.0;
                }
            }

            for (int i = 0; i < inputs.length; i++) {

                if (i != 2) {
                    sum += gpaValues[i];
                }

                if (gpaValues[i] < 0.7) {
                    pass = false;
                }
            }

            if (sum / 5.0 >= 2.5 && gpaValues[2] >= 3.0 && ((gpaValues[0] >= 1.7 && gpaValues[3] >= 1.7) || (gpaValues[0] >= 1.7 && gpaValues[5] >= 1.7) || (gpaValues[3] >= 1.7 && gpaValues[5] >= 1.7)) && pass == true) {
                StyleableToast.makeText(requireContext(), "You have met the requirements for this POSt!", Toast.LENGTH_LONG, R.style.passtoast).show();
            } else if (sum / 5.0 < 2.5) {
                StyleableToast.makeText(requireContext(), "Unfortunately, you do not qualify for this POSt. Your GPA for these courses (excluding A08) must be greater than 2.5.", Toast.LENGTH_SHORT, R.style.failtoast).show();
            } else if (gpaValues[2] < 3.0) {
                StyleableToast.makeText(requireContext(), "You must have a grade of at least 73 in CSC A48", Toast.LENGTH_SHORT, R.style.failtoast).show();
            } else if (((gpaValues[0] < 1.7 && gpaValues[3] < 1.7) || (gpaValues[0] < 1.7 && gpaValues[5] < 1.7) || (gpaValues[3] < 1.7 && gpaValues[5] < 1.7))) {
                StyleableToast.makeText(requireContext(), "You must have a grade of at least 60 in two of CSC/MAT A67, MAT A22, MAT A37", Toast.LENGTH_SHORT, R.style.failtoast).show();
            } else if (pass == false) {
                StyleableToast.makeText(requireContext(), "You must have passed all courses required for this POSt", Toast.LENGTH_SHORT, R.style.failtoast).show();
            }

        } else {
            StyleableToast.makeText(requireContext(), "Please fill in all fields with valid grades", Toast.LENGTH_SHORT, R.style.failtoast).show();
        }


    }


    public double setupTextInputValidation(TextInputEditText editText, TextInputLayout til, int index) {

        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editText.getText().toString().trim().isEmpty() == false) {

                    if (Integer.parseInt(editText.getText().toString()) > 100) {
                        til.setErrorEnabled(true);
                        til.setError("Please enter a grade from 0 to 100");
                        til.setErrorTextAppearance(R.style.ErrorAppearance);
                    } else if (Integer.parseInt(editText.getText().toString()) <= 100) {
                        til.setErrorEnabled(false);
                        inputs[index] = Integer.parseInt(editText.getText().toString());
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


