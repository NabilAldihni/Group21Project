package com.example.group21project;

import android.app.Dialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EventListViewHolder extends RecyclerView.ViewHolder {
    private final TextView eventNameView;
    private final TextView eventStartTimeView;
    private final TextView eventLocationView;
    private final Button eventButton;
    private final Dialog mDialog;


    public EventListViewHolder(@NonNull View itemView) {
        super(itemView);
        eventNameView = itemView.findViewById(R.id.eventListItemName);
        eventStartTimeView = itemView.findViewById(R.id.eventListItemStartTime);
        eventLocationView = itemView.findViewById(R.id.eventListItemLocation);
        eventButton = itemView.findViewById(R.id.eventListItemButton);
        mDialog = new Dialog(eventButton.getContext());

        eventButton.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           Log.d("TAG", "onClick: "+eventButton.getText().toString());
           if (eventButton.getText().toString() == "RSVP") {
               mDialog.setContentView(R.layout.fragment_popup_rsvp);
           } else if (eventButton.getText().toString().equals("Event action button")) {
               mDialog.setContentView(R.layout.fragment_popup);
           }
           TextView textViewToChange = (TextView) mDialog.findViewById(R.id.textView2);
           textViewToChange.setText("\n\n\n\nEvent: "+eventNameView.getText().toString() +"\n" +
                   "Start Time: "+eventStartTimeView.getText().toString() + "\n" +
                   "Location: "+eventLocationView.getText().toString());

           mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
           mDialog.show();
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

    public Button getEventActionButton() {
        return eventButton;
    }

    int getPopUpFragment() {
        return 0;
    }
}