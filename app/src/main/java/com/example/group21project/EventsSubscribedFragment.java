package com.example.group21project;

import android.app.Dialog;

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
    boolean eventSatisfiesFilter(DepartmentEvent event, String userId) {
        return LocalDateTime.now().isBefore(event.getEndTime()) && event.userHasRsvped(userId);
    }
}
