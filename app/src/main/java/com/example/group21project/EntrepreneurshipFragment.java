package com.example.group21project;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class EntrepreneurshipFragment extends Fragment {

    private Button yes_button;
    private Button no_button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.entrepreneurship_courses, container, false);

        yes_button = view.findViewById(R.id.yesbutton);
        yes_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCorrectCoursesEntrepreneurship();
            }
        });

        no_button = view.findViewById(R.id.nobutton);
        no_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIncorrectCourses();
            }
        });

        return view;
    }

    public void openCorrectCoursesEntrepreneurship() {
        Fragment someFragment = new CorrectCoursesEntrepreneurshipFragment();
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void openIncorrectCourses() {
        Fragment someFragment = new IncorrectCoursesFragment();
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
