package com.example.group21project;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventsUpcomingFragment extends EventsFragment {
    private String buttonName = "RSVP!";
    private Button PopUpButton;
    private Dialog mDialog;

    public EventsUpcomingFragment() {}

    @Override
    public int getEventFragmentId() {
        return R.layout.fragment_events_upcoming;
    }

    @Override
    List<EventListItem> getEventListItems() {
        ArrayList<EventListItem> eventListItems = new ArrayList<>();
        eventListItems.add(new DepartmentEvent("A Super Cool Event", "This is a super cool event!",
                LocalDateTime.of(2023, 11, 22, 12, 0),
                LocalDateTime.of(2023, 11, 22, 14, 30),
                "IC 130", buttonName,200));
        eventListItems.add(new DepartmentEvent("Not So Cool Event", "This event is mid.",
                LocalDateTime.of(2023, 11, 22, 10, 10),
                LocalDateTime.of(2023, 11, 23, 15, 45),
                "SW 128", buttonName,10));

        return eventListItems;
    }

    @Override
    protected int getEventListRecyclerViewId() {
        return R.id.upcomingEventsView;
    }

    @Override
    int getPopUpFragment() {
        return 0;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}