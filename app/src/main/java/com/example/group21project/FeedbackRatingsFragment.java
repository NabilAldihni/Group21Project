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
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FeedbackRatingsFragment extends Fragment {
    private List<EventListItem> eventListItems;
    private RatingEventListViewAdapter eventListAdapter;
    private final FirebaseFirestore database;

    public FeedbackRatingsFragment() {
        eventListItems = new ArrayList<>();
        database = FirebaseFirestore.getInstance();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feedback_ratings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fetchEvents();

        RecyclerView eventListRecyclerView = view.findViewById(R.id.feedbackEventListView);
        eventListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        eventListAdapter = new RatingEventListViewAdapter(getContext(), eventListItems);
        eventListRecyclerView.setAdapter(eventListAdapter);
        eventListAdapter.notifyDataSetChanged();
    }

    private void fetchEvents() {
        eventListItems.clear();
        database.collection("Events").orderBy("startTime", Query.Direction.DESCENDING).get().addOnCompleteListener(
                new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (!task.isSuccessful()) {
                            Log.d("EventsFirebaseDebug", "Error getting Firestore documents for events: ",
                                    task.getException());
                            return;
                        }

                        for (QueryDocumentSnapshot document : task.getResult()) {
                            DepartmentEvent event = DepartmentEventFirebaseAdapter.parseFirebaseCompatibleEvent(
                                    document.toObject(DepartmentEventFirebaseAdapter.class));
                            eventListItems.add(event);
                        }

                        eventListAdapter.notifyDataSetChanged();
                    }
                });
    }
}