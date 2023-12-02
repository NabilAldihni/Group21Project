package com.example.group21project;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.time.LocalDateTime;

public class EventsUpcomingFragment extends EventsFragment {
    public EventsUpcomingFragment() {}

    @Override
    public int getEventFragmentId() {
        return R.layout.fragment_events_upcoming;
    }

    @Override
    protected int getEventListRecyclerViewId() {
        return R.id.upcomingEventsView;
    }

    @Override
    int getPopupFragmentId() {
        return R.layout.fragment_popup_rsvp;
    }

    @Override
    int getPopupFragmentTextId() {
        return R.id.eventRSVPPopupText;
    }

    @Override
    int getPopupFragmentSubmitId() {
        return R.id.popUpSubmitButton;
    }

    @Override
    void getEventListItems() {
//        ArrayList<EventListItem> eventListItems = new ArrayList<>();
//        eventListItems.add(new DepartmentEvent("A Super Cool Event", "This is a super cool event!",
//                LocalDateTime.of(2023, 11, 22, 12, 0),
//                LocalDateTime.of(2023, 11, 22, 14, 30),
//                "IC 130", 200));
//        eventListItems.add(new DepartmentEvent("Not So Cool Event", "This event is mid.",
//                LocalDateTime.of(2023, 11, 22, 10, 10),
//                LocalDateTime.of(2023, 11, 23, 15, 45),
//                "SW 128", 10));
//
//        return eventListItems;
        fetchUpcomingEvents();
        Log.d("EventFragmentFirebaseDebug", "fetchUpcomingEvents() successful");
    }

    @Override
    boolean eventSatisfiesFilter(DepartmentEvent event) {
        return LocalDateTime.now().isBefore(event.getStartTime());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}