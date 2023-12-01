package com.example.group21project;

import java.time.LocalDateTime;

public class EventsAttendedFragment extends EventsFragment {
    public EventsAttendedFragment() {}

    @Override
    public int getEventFragmentId() {
        return R.layout.fragment_events_attended;
    }

    @Override
    protected int getEventListRecyclerViewId() {
        return R.id.attendedEventsView;
    }

    @Override
    int getPopupFragmentId() {
        return R.layout.fragment_popup;
    }

    @Override
    int getPopupFragmentTextId() {
        return R.id.eventAttendedPopupText;
    }

    @Override
    void getEventListItems() {
        eventListItems.clear();
        eventListItems.add(new DepartmentEvent("An Attended Event", "This event was attended already.",
                LocalDateTime.of(2023, 11, 26, 11, 0),
                LocalDateTime.of(2023, 11, 28, 14, 30),
                "SW 319", 135));
        eventListItems.add(new DepartmentEvent("Hackathon", "Check this attended event out!",
                LocalDateTime.of(2023, 11, 29, 11, 0),
                LocalDateTime.of(2023, 11, 30, 14, 30),
                "SW 319", 135));
        eventListItems.add(new DepartmentEvent("What happens if the title is long-ish? Let's find out",
                "Check this event out! It was subscribed to",
                LocalDateTime.of(2023, 11, 29, 17, 0),
                LocalDateTime.of(2023, 11, 29, 19, 30),
                "HLB 101", 235));
    }

    @Override
    boolean eventSatisfiesFilter(DepartmentEvent event) {
        return LocalDateTime.now().isAfter(event.getEndTime());     // TODO: implement && user RSVP'd to the event
    }
}