package com.example.group21project;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainFragment extends Fragment {
    private Button button;
    private Button newButton;
    private Button newButtonMinor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_main, container, false);

        Toolbar toolbar = view.findViewById(R.id.my_toolbar);
        TextView mTitle = toolbar.findViewById(R.id.toolbar_title);

        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
        mTitle.setText(toolbar.getTitle());

        ((AppCompatActivity) requireActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        button = view.findViewById(R.id.buttonFirst);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSpecialistPage();
            }
        });

        newButton = view.findViewById(R.id.button2);
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorPage();
            }
        });

        newButtonMinor = view.findViewById(R.id.button3);
        newButtonMinor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMinorPage();
            }
        });

        return view;

    }


    public void openSpecialistPage() {

        Fragment someFragment = new SpecialistPageFragment();
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }



    public void openMajorPage() {
        Fragment someFragment = new MajorPageFragment();
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    public void openMinorPage() {
        Fragment someFragment = new MinorPageFragment();
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}