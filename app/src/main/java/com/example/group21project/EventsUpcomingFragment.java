package com.example.group21project;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventsUpcomingFragment extends EventsFragment {
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
                "IC 130", 200));
        eventListItems.add(new DepartmentEvent("Not So Cool Event", "This event is mid.",
                LocalDateTime.of(2023, 11, 22, 10, 10),
                LocalDateTime.of(2023, 11, 23, 15, 45),
                "SW 128", 10));

        return eventListItems;
    }

    @Override
    protected int getEventListRecyclerViewId() {
        return R.id.upcomingEventsView;
    }
}