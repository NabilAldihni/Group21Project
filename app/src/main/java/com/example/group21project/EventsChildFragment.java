package com.example.group21project;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public abstract class EventsChildFragment extends Fragment {
    protected List<EventListItem> eventListItems;
    protected EventListViewAdapter eventListAdapter;
    private final FirebaseFirestore database;

    abstract int getEventFragmentId();
    abstract void getEventListItems();   // TODO: implement data retrieval from the server
    abstract int getEventListRecyclerViewId();
    abstract int getPopupFragmentId();
    abstract int getPopupFragmentTextId();
    abstract int getPopupFragmentSubmitId();
    // TODO: implement filters in subclasses after merging and decide on what event counts as "upcoming" and "attended"
    // with respect to the start/end times and whether the user RSVP'd
    abstract boolean eventSatisfiesFilter(DepartmentEvent event);

    public EventsChildFragment() {
        eventListItems = new ArrayList<>();
        database = FirebaseFirestore.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(getEventFragmentId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getEventListItems();

        RecyclerView eventListRecyclerView = view.findViewById(getEventListRecyclerViewId());
        eventListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        eventListAdapter = new EventListViewAdapter(getContext(), eventListItems,
                getPopupFragmentId(), getPopupFragmentTextId(), getPopupFragmentSubmitId());
        eventListRecyclerView.setAdapter(eventListAdapter);
        eventListAdapter.notifyDataSetChanged();
    }

    protected void fetchUpcomingEvents() {
        // TODO: once merged with the login and user session stuff, figure out how to get only upcoming events
        eventListItems.clear();
        database.collection("Events").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        DepartmentEvent event = DepartmentEventFirebaseAdapter.parseFirebaseCompatibleEvent(
                                document.toObject(DepartmentEventFirebaseAdapter.class));
                        if (eventSatisfiesFilter(event))
                            eventListItems.add(event);
                    }

                    eventListAdapter.notifyDataSetChanged();
                } else {
                    Log.d("EventsFirebaseDebug", "Error getting Firestore documents for events: ",
                            task.getException());
                }
            }
        });
    }
}