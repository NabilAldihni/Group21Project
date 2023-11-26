package com.example.group21project;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EventListViewHolder extends RecyclerView.ViewHolder {
    private final TextView eventNameView;
    private final TextView eventStartTimeView;
    private final TextView eventLocationView;
    private final Button eventButton;

    public EventListViewHolder(@NonNull View itemView) {
        super(itemView);
        eventNameView = itemView.findViewById(R.id.eventListItemName);
        eventStartTimeView = itemView.findViewById(R.id.eventListItemStartTime);
        eventLocationView = itemView.findViewById(R.id.eventListItemLocation);
        eventButton = itemView.findViewById(R.id.eventListItemButton);
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

    public Button getEventActionButton() {
        return eventButton;
    }
}