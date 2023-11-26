package com.example.group21project;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class EventsActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
    private final EventsUpcomingFragment upcomingEventsFrag = new EventsUpcomingFragment();
    private final EventsSubscribedFragment subscribedEventsFrag = new EventsSubscribedFragment();
    private final EventsAttendedFragment attendedEventsFrag = new EventsAttendedFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        // Navigation setup
        BottomNavigationView eventsBottomNavView = findViewById(R.id.eventsBottomNavigation);
        eventsBottomNavView.setOnItemSelectedListener(this);
        eventsBottomNavView.setSelectedItemId(R.id.eventsNavUpcoming);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Change the fragment if a menu button is selected
        int itemId = item.getItemId();
        EventsFragment nextFragment = null;
        if (itemId == R.id.eventsNavUpcoming)
            nextFragment = upcomingEventsFrag;
        else if (itemId == R.id.eventsNavSubscribed)
            nextFragment = subscribedEventsFrag;
        else if (itemId == R.id.eventsNavAttended)
            nextFragment = attendedEventsFrag;

        if (nextFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.eventsFrame, nextFragment).commit();
            return true;
        }
        return false;
    }
}