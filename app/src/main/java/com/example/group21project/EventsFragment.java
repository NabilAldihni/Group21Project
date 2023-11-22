package com.example.group21project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public abstract class EventsFragment extends Fragment {
    public abstract int getRFragment();

    // This should only be used in the subclasses
    protected EventsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(getRFragment(), container, false);
    }


}
