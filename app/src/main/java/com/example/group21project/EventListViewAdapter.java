package com.example.group21project;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class EventListViewAdapter extends RecyclerView.Adapter<EventListViewHolder> {
    private final Context eventListContext;
    private final List<EventListItem> eventListItems;
    private final EventsChildFragment eventsFragment;
    private final FirebaseAuth auth;

    public EventListViewAdapter(Context eventListContext, List<EventListItem> eventListItems,
                                EventsChildFragment eventsFragment) {
        this.eventListContext = eventListContext;
        this.eventListItems = eventListItems;
        this.eventsFragment = eventsFragment;
        this.auth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public EventListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(eventListContext).inflate(R.layout.event_list_item, parent, false);
        return new EventListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventListViewHolder holder, int position) {
        EventListItem event = eventListItems.get(position);
        TextView eventNameView = holder.getEventNameView();
        TextView eventStartTimeView = holder.getEventStartTimeView();
        TextView eventLocationView = holder.getEventLocationView();
        Button eventButton = holder.getEventButton();
        Dialog mDialog = holder.getDialog();

        eventNameView.setText(event.getName());
        eventStartTimeView.setText(event.getStartTimeString());
        eventLocationView.setText(event.getLocation());
        eventButton.setText(eventsFragment.getEventActionButtonText());

        eventButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                mDialog.setContentView(eventsFragment.getPopupFragmentId());
                // TODO: replace with multiple TextViews and icons with proper alignment
                TextView eventInfoText = mDialog.findViewById(eventsFragment.getPopupFragmentTextId());
                eventInfoText.setText("\n\n\n\n\uD83C\uDFAB Event: " + event.getName() + "\n"
                        + "\uD83D\uDCCD Location: " + event.getLocation() + "\n"
                        + "\uD83D\uDCC5 Start Time: " + event.getStartTimeString() + "\n"
                        + "\uD83D\uDCC5 End Time: " + event.getEndTimeString() + "\n\n"
                        + event.getDesc());

                mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                mDialog.show();

                String userId = auth.getUid();
                Button submitButton = mDialog.findViewById(eventsFragment.getPopupFragmentSubmitId());
                submitButton.setText(eventsFragment.getPopupButtonText((DepartmentEvent) event, userId));
                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        eventsFragment.eventListItemButtonOnClick((DepartmentEvent) event, mDialog);
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventListItems.size();
    }
}