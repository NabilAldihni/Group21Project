package com.example.group21project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EventListViewAdapter extends RecyclerView.Adapter<EventListViewHolder> {
    private final Context eventListContext;
    private final List<EventListItem> eventListItems;
    private final int popupFragmentId;
    private final int popupFragmentTextId;
    private final int popupFragmentSubmitId;



    public EventListViewAdapter(Context eventListContext, List<EventListItem> eventListItems, int popupFragmentId,
                                int popupFragmentTextId, int popupFragmentSubmitId) {
        this.eventListContext = eventListContext;
        this.eventListItems = eventListItems;
        this.popupFragmentId = popupFragmentId;
        this.popupFragmentTextId = popupFragmentTextId;
        this.popupFragmentSubmitId = popupFragmentSubmitId;

    }

    @NonNull
    @Override
    public EventListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(eventListContext).inflate(R.layout.event_list_item, parent, false);
        return new EventListViewHolder(view, popupFragmentId, popupFragmentTextId, popupFragmentSubmitId);
    }

    @Override
    public void onBindViewHolder(@NonNull EventListViewHolder holder, int position) {
        EventListItem event = eventListItems.get(position);
        holder.getEventNameView().setText(event.getName());
        holder.getEventStartTimeView().setText(event.getStartTimeString());
        holder.getEventLocationView().setText(event.getLocation());
    }

    @Override
    public int getItemCount() {
        return eventListItems.size();
    }
}