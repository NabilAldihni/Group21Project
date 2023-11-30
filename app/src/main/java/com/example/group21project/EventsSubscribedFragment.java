package com.example.group21project;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventsSubscribedFragment extends EventsFragment {
    public EventsSubscribedFragment() {}

    @Override
    public int getEventFragmentId() {
        return R.layout.fragment_events_subscribed;
    }

    @Override
    List<EventListItem> getEventListItems() {
        ArrayList<EventListItem> eventListItems = new ArrayList<>();
        eventListItems.add(new DepartmentEvent("A Subscribed Event", "Check this event out! It was subscribed to",
                LocalDateTime.of(2023, 11, 22, 11, 0),
                LocalDateTime.of(2023, 11, 30, 14, 30),
                "SW 319", 135));
        eventListItems.add(new DepartmentEvent("Hackathon", "Check this event out! It was subscribed to",
                LocalDateTime.of(2023, 11, 22, 11, 0),
                LocalDateTime.of(2023, 11, 30, 14, 30),
                "SW 319", 135));
        eventListItems.add(new DepartmentEvent(
                "What happens if the title is super long? We will now find out if the app explodes",
                "Check this event out! It was subscribed to",
                LocalDateTime.of(2023, 11, 26, 23, 0),
                LocalDateTime.of(2023, 11, 27, 1, 30),
                "HLB 101", 235));
        return eventListItems;
    }

    @Override
    protected int getEventListRecyclerViewId() {
        return R.id.subscribedEventsView;
    }

}
