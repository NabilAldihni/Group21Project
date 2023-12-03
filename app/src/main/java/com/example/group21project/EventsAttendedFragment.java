package com.example.group21project;

import android.app.Dialog;
import android.util.Log;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class EventsAttendedFragment extends EventsChildFragment {
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
    public int getPopupFragmentId() {
        return R.layout.fragment_popup;
    }

    public int getPopupFragmentTextId() {
        return R.id.eventAttendedPopupText;
    }

    public int getPopupFragmentSubmitId() {
        return R.id.popUpSubmitButton;
    }

    @Override
    public String getEventActionButtonText() {
        return "Leave a Review";
    }

    @Override
    public String getPopupButtonText(DepartmentEvent event, String userId) {
        return "Submit";
    }

    @Override
    boolean eventSatisfiesFilter(DepartmentEvent event, String userId) {
        return LocalDateTime.now().isAfter(event.getEndTime()) && event.userHasRsvped(userId);
    }

    @Override
    public void eventListItemButtonOnClick(DepartmentEvent event, Dialog mDialog) {
        RatingBar starsRating;
        EditText reviewTextInput;
        try {
            starsRating = (RatingBar) mDialog.findViewById(R.id.starsRatingBarEventsAttended);
            reviewTextInput = mDialog.findViewById(R.id.textReviewInputEventsAttended);

            Log.d("STARS", "num of stars: " + starsRating.getRating());
            if (0.5 <= starsRating.getNumStars() && starsRating.getNumStars() <= 5 && reviewTextInput.getText().toString().length() >= 1) {
                Log.d("EDIT TEXT", "review " + reviewTextInput.getText().toString());
                Map<String, Object> data = new HashMap<>();
                data.put("eventName", event.getName());
                data.put("location", event.getLocation());
                data.put("numStars", starsRating.getNumStars());
                data.put("reviewTextInput", reviewTextInput.getText().toString());
                data.put("startTime", event.getStartTimeString());

                database.collection("Reviews").add(data);

                Toast reviewConfirmation = Toast.makeText(getContext(), "Review submitted", Toast.LENGTH_SHORT);
                reviewConfirmation.show();
                mDialog.hide();

            }
            else {
                Toast invalid = Toast.makeText(getContext(), "Please enter a review", Toast.LENGTH_SHORT);
                invalid.show();
            }
        }
        catch (Exception e) {
            Log.d("EXCEPTION", "EXCEPTION");
        }
    }
}