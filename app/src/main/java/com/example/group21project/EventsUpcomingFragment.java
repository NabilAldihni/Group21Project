package com.example.group21project;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.time.LocalDateTime;

public class EventsUpcomingFragment extends EventsChildFragment {
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
    boolean eventSatisfiesFilter(DepartmentEvent event, String userId) {
        return LocalDateTime.now().isBefore(event.getStartTime());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}