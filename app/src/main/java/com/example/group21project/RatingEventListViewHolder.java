package com.example.group21project;

import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RatingEventListViewHolder extends RecyclerView.ViewHolder {
    private final TextView eventNameView;
    private final TextView eventStartTimeView;
    private final TextView eventLocationView;
    private final Button eventButton;
    private final Dialog mDialog;

    public RatingEventListViewHolder(@NonNull View itemView) {
        super(itemView);
        eventNameView = itemView.findViewById(R.id.eventListItemName);
        eventStartTimeView = itemView.findViewById(R.id.eventListItemStartTime);
        eventLocationView = itemView.findViewById(R.id.eventListItemLocation);
        eventButton = itemView.findViewById(R.id.eventListItemButton);
        mDialog = new Dialog(eventButton.getContext());
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

    public Button getEventButton() {
        return eventButton;
    }

    public Dialog getDialog() {
        return mDialog;
    }
}
