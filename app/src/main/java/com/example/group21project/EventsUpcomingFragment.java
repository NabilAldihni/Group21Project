package com.example.group21project;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventsUpcomingFragment extends EventsFragment {
    private final DatabaseReference database;

    public EventsUpcomingFragment() {
        database = eventsDatabase.getReference("Events");
        eventListItems = new ArrayList<>();
    }

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
    List<EventListItem> getEventListItems() {
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
        return eventListItems;
    }

    private void fetchUpcomingEvents() {
        // TODO: retrieve events from Firebase
        // TODO: once retrieval is sorted out, figure out how to get only events for this screen
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                eventListItems = new ArrayList<>();
                for (DataSnapshot eventSnapshot : snapshot.getChildren()) {
                    DepartmentEvent event = eventSnapshot.getValue(DepartmentEvent.class);
                    eventListItems.add(event);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}