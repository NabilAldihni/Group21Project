package com.example.group21project;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class RatingEventListViewAdapter extends RecyclerView.Adapter<RatingEventListViewHolder> {
    private final Context eventListContext;
    private final List<EventListItem> eventListItems;
    private ReviewListViewAdapter reviewListAdapter;
    private FirebaseFirestore database;

    public RatingEventListViewAdapter(Context eventListContext, List<EventListItem> eventListItems) {
        this.eventListContext = eventListContext;
        this.eventListItems = eventListItems;
        this.database = FirebaseFirestore.getInstance();
    }

    @NonNull
    @Override
    public RatingEventListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(eventListContext).inflate(R.layout.event_list_item, parent, false);
        return new RatingEventListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RatingEventListViewHolder holder, int position) {
        DepartmentEvent eventListItem = (DepartmentEvent) eventListItems.get(position);
        TextView eventNameView = holder.getEventNameView();
        TextView eventStartTimeView = holder.getEventStartTimeView();
        TextView eventLocationView = holder.getEventLocationView();
        Button eventButton = holder.getEventButton();
        Dialog mDialog = holder.getDialog();

        eventNameView.setText(eventListItem.getName());
        eventStartTimeView.setText(eventListItem.getStartTimeString());
        eventLocationView.setText(eventListItem.getLocation());
        eventButton.setText("View Feedback");

        eventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.setContentView(R.layout.fragment_review_list_popup);
                TextView eventNameView = mDialog.findViewById(R.id.reviewPopupEventName);
                TextView reviewCountView = mDialog.findViewById(R.id.reviewPopupCountText);
                TextView reviewAverageView = mDialog.findViewById(R.id.reviewPopupAverageText);

                eventNameView.setText(eventListItem.getName());
                reviewCountView.setText("Number of reviews: 0");
                reviewAverageView.setText("Average rating: 0.0 / 5.0");
                mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                WindowManager.LayoutParams dialogLayoutParams = new WindowManager.LayoutParams();
                dialogLayoutParams.copyFrom(mDialog.getWindow().getAttributes());
                dialogLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                mDialog.show();
                mDialog.getWindow().setAttributes(dialogLayoutParams);

                RecyclerView reviewListView = mDialog.findViewById(R.id.reviewListView);
                reviewListView.setLayoutManager(new LinearLayoutManager(mDialog.getContext()));

                List<DepartmentEventReview> reviews = new ArrayList<>();
                eventListItem.getDocumentQuery(database).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (!task.isSuccessful()) {
                            Log.d("EventsFirebaseDebug", "Error getting Firestore documents for ratings: ",
                                    task.getException());
                            return;
                        }

                        DocumentSnapshot eventSnap = task.getResult().getDocuments().get(0);
                        DepartmentEvent firebaseEvent = DepartmentEventFirebaseAdapter.parseFirebaseCompatibleEvent(
                                eventSnap.toObject(DepartmentEventFirebaseAdapter.class));
                        if (firebaseEvent.getReviewIds() == null)
                            return;

                        database.collection("Reviews").get().addOnCompleteListener(
                                new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if (!task.isSuccessful()) {
                                            Log.d("RatingsFirebaseDebug",
                                                    "Error getting Firestore documents for ratings: ",
                                                    task.getException());
                                            return;
                                        }

                                        for (QueryDocumentSnapshot ratingSnap : task.getResult()) {
                                            if (firebaseEvent.hasReview(ratingSnap.getId()))
                                                reviews.add(ratingSnap.toObject(DepartmentEventReview.class));
                                        }

                                        reviewCountView.setText("Number of reviews: " + reviews.size());
                                        reviewAverageView.setText("Average rating: " + computeAverage(reviews) + " / 5.0");
                                        reviewListAdapter.notifyDataSetChanged();
                                    }
                        });
                    }
                });

                reviewListAdapter = new ReviewListViewAdapter(mDialog.getContext(), reviews);
                reviewListView.setAdapter(reviewListAdapter);
                reviewListAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventListItems.size();
    }

    private double computeAverage(List<DepartmentEventReview> reviews) {
        int numReviews = reviews.size();
        if (numReviews == 0)
            return 0;

        double sum = 0;
        for (DepartmentEventReview review : reviews)
            sum += review.getRating();
        return sum / numReviews;
    }
}