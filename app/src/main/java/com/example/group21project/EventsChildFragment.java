package com.example.group21project;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public abstract class EventsChildFragment extends Fragment {
    protected List<EventListItem> eventListItems;
    protected EventListViewAdapter eventListAdapter;
    protected final FirebaseFirestore database;
    private final FirebaseAuth auth;

    abstract int getEventFragmentId();
    abstract int getEventListRecyclerViewId();
    abstract boolean eventSatisfiesFilter(DepartmentEvent event, String userId);

    public EventsChildFragment() {
        eventListItems = new ArrayList<>();
        database = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(getEventFragmentId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fetchEvents();

        RecyclerView eventListRecyclerView = view.findViewById(getEventListRecyclerViewId());
        eventListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        eventListAdapter = new EventListViewAdapter(getContext(), eventListItems, this);
        eventListRecyclerView.setAdapter(eventListAdapter);
        eventListAdapter.notifyDataSetChanged();
    }

    public int getPopupFragmentId() {
        return R.layout.fragment_popup_rsvp;
    }

    public int getPopupFragmentTextId() {
        return R.id.eventRSVPPopupText;
    }

    public int getPopupFragmentSubmitId() {
        return R.id.popUpSubmitButton;
    }

    public String getEventActionButtonText() {
        return "More Info";
    }

    public String getPopupButtonText(DepartmentEvent event, String userId) {
        if (event.userHasRsvped(userId))
            return "Un-RSVP";
        else
            return "RSVP";
    }

    protected void fetchEvents() {
        // TODO: once merged with the login and user session stuff, figure out how to get only upcoming events
        eventListItems.clear();
        database.collection("Events").orderBy("startTime").get().addOnCompleteListener(
                new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String userId = auth.getUid();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                DepartmentEvent event = DepartmentEventFirebaseAdapter.parseFirebaseCompatibleEvent(
                                        document.toObject(DepartmentEventFirebaseAdapter.class));
                                if (eventSatisfiesFilter(event, userId))
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

    public void eventListItemButtonOnClick(DepartmentEvent event, Dialog mDialog) {
        String userId = auth.getUid();
        Button submitButton = mDialog.findViewById(getPopupFragmentSubmitId());
        String buttonText = submitButton.getText().toString();

        if (event.userHasRsvped(userId)) {
            event.unRsvpUser(userId);
            buttonText = "RSVP";
            Toast.makeText(getContext(), "Un-RSVP successful", Toast.LENGTH_SHORT).show();
        }
        else if (event.hasCapacity()) {
            event.rsvpUser(userId);
            buttonText = "Un-RSVP";
            Toast.makeText(getContext(), "RSVP successful", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getContext(), "RSVP unsuccessful. The event is full", Toast.LENGTH_SHORT).show();
            return;
        }

        submitButton.setText(buttonText);
        updateEventRsvpList(event);
    }

    private void updateEventRsvpList(DepartmentEvent event) {
        database.collection("Events")
                .whereEqualTo("capacity", event.getCapacity())
                .whereEqualTo("location", event.getLocation())
                .whereEqualTo("name", event.getName())
                .whereEqualTo("startTime", event.getStartTime().toString())
                .whereEqualTo("endTime", event.getEndTime().toString())
                .whereEqualTo("desc", event.getDesc())
                .limit(1)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Log.d("EventsFirebaseDebug", "Document ID found for event");
                        if (task.isSuccessful()) {
                            DocumentReference eventDoc = task.getResult().getDocuments().get(0).getReference();
                            eventDoc.update("attending", event.getAttending());
                        } else {
                            Log.d("EventsFirebaseDebug", "Error getting Firestore documents for events: ",
                                    task.getException());
                        }
                    }
                });
    }
}