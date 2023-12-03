package com.example.group21project;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EventListViewHolder extends RecyclerView.ViewHolder {
    private final TextView eventNameView;
    private final TextView eventStartTimeView;
    private final TextView eventLocationView;
    private final Button eventButton;
    private final Dialog mDialog;
    private FirebaseFirestore database;



    public EventListViewHolder(@NonNull View itemView, int popupFragmentId, int popupFragmentTextId, int popupFragmentSubmitId) {
        super(itemView);
        eventNameView = itemView.findViewById(R.id.eventListItemName);
        eventStartTimeView = itemView.findViewById(R.id.eventListItemStartTime);
        eventLocationView = itemView.findViewById(R.id.eventListItemLocation);
        eventButton = itemView.findViewById(R.id.eventListItemButton);
        mDialog = new Dialog(eventButton.getContext());
        database = FirebaseFirestore.getInstance();


        eventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.setContentView(popupFragmentId);
                TextView textViewToChange = mDialog.findViewById(popupFragmentTextId);
                textViewToChange.setText("\n\n\n\nEvent: "+eventNameView.getText().toString() +"\n" +
                        "Start Time: "+eventStartTimeView.getText().toString() + "\n" +
                        "Location: "+eventLocationView.getText().toString());

                mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                mDialog.show();

                final Button submitButton;
                final RatingBar starsRating;
                final EditText reviewTextInput;
                try {
                    submitButton = mDialog.findViewById(popupFragmentSubmitId);
                    starsRating = (RatingBar) mDialog.findViewById(R.id.starsRatingBarEventsAttended);
                    reviewTextInput = mDialog.findViewById(R.id.textReviewInputEventsAttended);

                    submitButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d("TAG", "onClick: " + submitButton.getText().toString());
                            Log.d("TAG", "Event info: " + eventNameView.getText().toString());
                            Log.d("TAG", "Event info: " + eventStartTimeView.getText().toString());
                            Log.d("TAG", "Event info: " + eventLocationView.getText().toString());

                        try {
                            Log.d("STARS", "num of stars: " + starsRating.getRating());
                            if (0.5 <= starsRating.getNumStars() && starsRating.getNumStars() <= 5 && reviewTextInput.getText().toString().length() >= 1) {
                                Log.d("EDIT TEXT", "review " + reviewTextInput.getText().toString());
                                Map<String, Object> data = new HashMap<>();
                                data.put("eventName", eventNameView.getText().toString());
                                data.put("location", eventLocationView.getText().toString());
                                data.put("numStars", starsRating.getNumStars());
                                data.put("reviewTextInput", reviewTextInput.getText().toString());
                                data.put("startTime", eventStartTimeView.getText().toString());

                                database.collection("Reviews").add(data);

                                Toast reviewConfirmation = Toast.makeText(submitButton.getContext(), "Sent!", Toast.LENGTH_SHORT);
                                reviewConfirmation.show();
                                mDialog.hide();

                            } else {
                                Toast invalid = Toast.makeText(submitButton.getContext(), "Please enter a review!", Toast.LENGTH_SHORT);
                                invalid.show();
                            }

                        } catch (Exception e) {
                            Log.d("EXCEPTION", "EXCEPTION");
                        }

                        }
                    });
                }
                catch (Exception e) {
                    Log.d("EXCEPTION", "EXCEPTION");
                }


            }
        });
    }

    public TextView getEventNameView() {
        return eventNameView;
    }

    public TextView getEventStartTimeView() {
        return eventStartTimeView;
    }

    public TextView getEventLocationView() {
        return eventLocationView;
    }
}