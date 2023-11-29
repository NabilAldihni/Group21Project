package com.example.group21project;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventsUpcomingFragment extends EventsFragment {
    private final DatabaseReference upcomingEventsRef;

    public EventsUpcomingFragment() {
        upcomingEventsRef = eventsDatabase.getReference("UpcomingEvents");
    }

    @Override
    public int getEventFragmentId() {
        return R.layout.fragment_events_upcoming;
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

    @Override
    protected int getEventListRecyclerViewId() {
        return R.id.upcomingEventsView;
    }

    private void fetchUpcomingEvents() {
        eventListItems = new ArrayList<>();
        upcomingEventsRef.orderByChild("startTime").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                DepartmentEvent event = snapshot.getValue(DepartmentEvent.class);
                Log.d("EventFragmentFirebaseDebug", "Event " + event + " read from database");
                eventListItems.add(event);
            }

            // TODO: do something for other data changes e.g. a toast telling the user to refresh to see changes
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {}

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {}

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {}

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("EventFragmentFirebaseDebug", "Event reading error from database");
                Toast.makeText(getContext(), "Failed to load event(s)", Toast.LENGTH_SHORT).show();
            }
        });
    }
}