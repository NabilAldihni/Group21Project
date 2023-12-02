package com.example.group21project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class EventsParentFragment extends Fragment implements NavigationBarView.OnItemSelectedListener {
    // TODO: initialize these inside onCreate
    private final EventsUpcomingFragment upcomingEventsFrag = new EventsUpcomingFragment();
    private final EventsSubscribedFragment subscribedEventsFrag = new EventsSubscribedFragment();
    private final EventsAttendedFragment attendedEventsFrag = new EventsAttendedFragment();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);

        BottomNavigationView eventsBottomNavView = view.findViewById(R.id.eventsBottomNavigation);
        eventsBottomNavView.setOnItemSelectedListener(this);
        eventsBottomNavView.setSelectedItemId(R.id.eventsNavUpcoming);

        return view;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        EventsChildFragment nextFragment = null;
        if (itemId == R.id.eventsNavUpcoming)
            nextFragment = upcomingEventsFrag;
        else if (itemId == R.id.eventsNavSubscribed)
            nextFragment = subscribedEventsFrag;
        else if (itemId == R.id.eventsNavAttended)
            nextFragment = attendedEventsFrag;

        if (nextFragment != null) {
            getParentFragmentManager().beginTransaction().replace(R.id.eventsFrame, nextFragment).commit();
            return true;
        }
        return false;
    }
}