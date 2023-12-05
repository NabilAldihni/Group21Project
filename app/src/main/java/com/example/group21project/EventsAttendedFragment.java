package com.example.group21project;

import android.app.Dialog;
import android.util.Log;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

import java.time.LocalDateTime;

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

            if (!AddEventsInputValidator.stringIsBlank(reviewTextInput.getText().toString())) {
                String reviewerEmail = auth.getCurrentUser().getEmail();
                DepartmentEventReview review = new DepartmentEventReview(reviewerEmail, starsRating.getRating(),
                        reviewTextInput.getText().toString(), LocalDateTime.now().toString());
                database.collection("Reviews").add(review).addOnSuccessListener(
                        new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                event.addReview(documentReference.getId());
                                updateEventField(event, "reviewIds", event.getReviewIds());
                            }
                        });

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