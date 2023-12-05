package com.example.group21project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PostQuestionaire#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PostQuestionaire extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RadioGroup admissionCatOptions, programOptions;
    private EditText editTextA67, editTextA31, editTextA37, editTextA48, editTextA22, editTextA08, editTextCredits;
    private Button submitButton;
    private TextView textViewResult;

    public PostQuestionaire() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PostQuestionaire.
     */
    // TODO: Rename and change types and number of parameters
    public static PostQuestionaire newInstance(String param1, String param2) {
        PostQuestionaire fragment = new PostQuestionaire();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post_questionaire, container, false);

        // Initialize UI components
        admissionCatOptions = view.findViewById(R.id.admissionCatOptions);
        programOptions = view.findViewById(R.id.programOptions);
        textViewResult = view.findViewById(R.id.resultsText);
        editTextA67 = view.findViewById(R.id.A67);
        editTextA08 = view.findViewById(R.id.A08);
        editTextA22 = view.findViewById(R.id.A22);
        editTextA31 = view.findViewById(R.id.A31);
        editTextA37 = view.findViewById(R.id.A37);
        editTextA48 = view.findViewById(R.id.A48);
        editTextCredits = view.findViewById(R.id.Credits);
        submitButton = view.findViewById(R.id.submitButton);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
                    boolean isInAdmissionCategory = admissionCatOptions.getCheckedRadioButtonId() == R.id.yesOption;
                    String programType = programOptions.getCheckedRadioButtonId() == R.id.specialistMajorOption ? "specialist/major" : "minor";
                    double a67 = Double.parseDouble(editTextA67.getText().toString());
                    double a08 = Double.parseDouble(editTextA08.getText().toString());
                    double a22 = Double.parseDouble(editTextA22.getText().toString());
                    double a31 = Double.parseDouble(editTextA31.getText().toString());
                    double a37 = Double.parseDouble(editTextA37.getText().toString());
                    double a48 = Double.parseDouble(editTextA48.getText().toString());
                    int credits = Integer.parseInt(editTextCredits.getText().toString());
                    PostCalculator postCalculator = new PostCalculator();
                    boolean isEligible = postCalculator.isEligible(a67, a31, a37, a48, a22, a08, credits, isInAdmissionCategory, programType);

                    if(isEligible && programType.equalsIgnoreCase("minor")){
                        textViewResult.setText("congrats you are eligible to do a minor in computer science, but you will be competing against your fellow students so there is no guarantee.");
                    }else if (isEligible && programType.equalsIgnoreCase("specialist/major") && isInAdmissionCategory){
                        textViewResult.setText("congrats you are eligible to do your specialist/major in computer science at UTSC");
                    }else if (isEligible && programType.equalsIgnoreCase("specialist/major") && !isInAdmissionCategory){
                        textViewResult.setText("You are eligible to study a major in computer science but since you are not in the computer science admission category, you will need a competitive average to get in, there is no guarantee");
                    }else if (!isEligible) {
                        textViewResult.setText("Sorry, you are not eligible");
                    }
                } else {
                    // Show error message
                    Toast.makeText(getContext(), "Please fill all fields correctly", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

    private boolean validateInputs() {
        // Check if all EditText fields are not empty
        boolean areAllEditTextsFilled = !editTextA67.getText().toString().isEmpty()
                && !editTextA31.getText().toString().isEmpty()
                && !editTextA37.getText().toString().isEmpty()
                && !editTextA48.getText().toString().isEmpty()
                && !editTextA22.getText().toString().isEmpty()
                && !editTextA08.getText().toString().isEmpty()
                && !editTextCredits.getText().toString().isEmpty();

        // Check if both RadioGroups have a selected option
        boolean isAdmissionCategorySelected = admissionCatOptions.getCheckedRadioButtonId() != -1;
        boolean isProgramTypeSelected = programOptions.getCheckedRadioButtonId() != -1;

        // Return true if all conditions are met
        return areAllEditTextsFilled && isAdmissionCategorySelected && isProgramTypeSelected;
    }


}