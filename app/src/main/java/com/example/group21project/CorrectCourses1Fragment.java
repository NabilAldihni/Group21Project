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

public class CorrectCourses1Fragment extends Fragment {

    private Button in_button;
    private Button out_button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.correct_courses1, container, false);

        in_button = view.findViewById(R.id.in);
        in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInSwe();
            }
        });

        out_button = view.findViewById(R.id.out);
        out_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOutSwe();
            }
        });

        return view;
    }

    public void openInSwe() {
        Fragment someFragment = new InSweFragment();
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void openOutSwe() {
        Fragment someFragment = new OutSweFragment();
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
