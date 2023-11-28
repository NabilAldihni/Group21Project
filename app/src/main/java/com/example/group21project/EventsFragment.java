package com.example.group21project;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class EventsFragment extends Fragment {
    private List<EventListItem> eventListItems;


    abstract int getEventFragmentId();
    abstract List<EventListItem> getEventListItems();   // TODO: implement data retrieval from the server
    abstract int getEventListRecyclerViewId();

    abstract int getPopUpFragment();

    public EventsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(getEventFragmentId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        eventListItems = getEventListItems();

        RecyclerView eventListRecyclerView = view.findViewById(getEventListRecyclerViewId());
        eventListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        EventListViewAdapter eventListAdapter = new EventListViewAdapter(getContext(), eventListItems);
        eventListRecyclerView.setAdapter(eventListAdapter);
        eventListAdapter.notifyDataSetChanged();


    }
}