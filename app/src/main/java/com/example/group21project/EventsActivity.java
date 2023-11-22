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

        BottomNavigationView eventsBottomNavView = findViewById(R.id.eventsBottomNavigation);
        eventsBottomNavView.setOnItemSelectedListener(this);
        eventsBottomNavView.setSelectedItemId(R.id.eventsNavUpcoming);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Change the fragment if a menu button is selected
        int itemId = item.getItemId();
        if (itemId == R.id.eventsNavUpcoming) {
            getSupportFragmentManager().beginTransaction().replace(R.id.eventsFrame, upcomingEventsFrag).commit();
            return true;
        }
        else if (itemId == R.id.eventsNavSubscribed) {
            getSupportFragmentManager().beginTransaction().replace(R.id.eventsFrame, subscribedEventsFrag).commit();
            return true;
        }
        else if (itemId == R.id.eventsNavAttended) {
            getSupportFragmentManager().beginTransaction().replace(R.id.eventsFrame, attendedEventsFrag).commit();
            return true;
        }
        return false;
    }
}