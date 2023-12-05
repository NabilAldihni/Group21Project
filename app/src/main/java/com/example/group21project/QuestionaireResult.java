package com.example.group21project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuestionaireResult#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionaireResult extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView resultsTextView;

    public QuestionaireResult() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuestionaireResult.
     */
    // TODO: Rename and change types and number of parameters
    public static QuestionaireResult newInstance(String param1, String param2) {
        QuestionaireResult fragment = new QuestionaireResult();
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
        View view = inflater.inflate(R.layout.fragment_questionaire_result, container, false);
        resultsTextView = view.findViewById(R.id.resultsTextView);

        displayResults();

        return view;
    }

    private void displayResults(){
        Bundle bundle = getArguments();
        if(bundle != null) {
            double a67 = Double.parseDouble(bundle.getString("A67"));
            double a31 = Double.parseDouble(bundle.getString("A31"));
            double a37 = Double.parseDouble(bundle.getString("A37"));
            double a48 = Double.parseDouble(bundle.getString("A48"));
            double a22 = Double.parseDouble(bundle.getString("A22"));
            double a08 = Double.parseDouble(bundle.getString("A08"));
            int credits = Integer.parseInt(bundle.getString("Credits"));
            boolean isInAdmissionCategory = bundle.getBoolean("isInAdmissionCategory");
            String programType = bundle.getString("programType");

            PostCalculator postCalculator = new PostCalculator();
            boolean isEligible = postCalculator.isEligible(a67, a31, a37, a48, a22, a08, credits, isInAdmissionCategory, programType);

            if(isEligible && programType == "minor"){
                resultsTextView.setText("congrats you are eligible to do a minor in computer science, but you will be competing against your fellow students so there is no guarantee.");
            }else if (isEligible && programType == "major/specialist" && isInAdmissionCategory){
                resultsTextView.setText("congrats you are eligible to do your specialist/major in computer science at UTSC");
            }else if (isEligible && programType == "major/specialist" && !isInAdmissionCategory){
                resultsTextView.setText("You are eligible to study a major in computer science but since you are not in the computer science admission category, you will need a competitive average to get in, there is no guarantee");
            }else if (!isEligible) {
                resultsTextView.setText("Sorry, you are not eligible");
            }
        }else {
            resultsTextView.setText("No data available.");
            return;
        }

    }
}