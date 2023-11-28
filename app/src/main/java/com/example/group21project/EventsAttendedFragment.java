package com.example.group21project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventsAttendedFragment extends EventsFragment {
    public EventsAttendedFragment() {}
    private String buttonName = "Leave a review!";


    @Override
    public int getEventFragmentId() {
        return R.layout.fragment_events_attended;
    }

    @Override
    List<EventListItem> getEventListItems() {
        ArrayList<EventListItem> eventListItems = new ArrayList<>();
        eventListItems.add(new DepartmentEvent("An Attended Event", "This event was attended already.",
                LocalDateTime.of(2023, 11, 26, 11, 0),
                LocalDateTime.of(2023, 11, 28, 14, 30),
                "SW 319", buttonName,135));
        eventListItems.add(new DepartmentEvent("Hackathon", "Check this attended event out!",
                LocalDateTime.of(2023, 11, 29, 11, 0),
                LocalDateTime.of(2023, 11, 30, 14, 30),
                "SW 319", buttonName,135));
        eventListItems.add(new DepartmentEvent("What happens if the title is long-ish? Let's find out",
                "Check this event out! It was subscribed to",
                LocalDateTime.of(2023, 11, 29, 17, 0),
                LocalDateTime.of(2023, 11, 29, 19, 30),
                "HLB 101", buttonName,235));
        return eventListItems;
    }

    @Override
    protected int getEventListRecyclerViewId() {
        return R.id.attendedEventsView;
    }

    @Override
    int getPopUpFragment() {
        return R.layout.fragment_popup;
    }
}
