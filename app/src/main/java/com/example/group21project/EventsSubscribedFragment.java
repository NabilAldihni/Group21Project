package com.example.group21project;

import java.time.LocalDateTime;

public class EventsSubscribedFragment extends EventsChildFragment {
    public EventsSubscribedFragment() {}

    @Override
    public int getEventFragmentId() {
        return R.layout.fragment_events_subscribed;
    }

    @Override
    protected int getEventListRecyclerViewId() {
        return R.id.subscribedEventsView;
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
    void getEventListItems() {
        eventListItems.clear();
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
    }

    @Override
    boolean eventSatisfiesFilter(DepartmentEvent event) {
        return true;    // TODO: check if user RSVP'd to the event
    }
}
