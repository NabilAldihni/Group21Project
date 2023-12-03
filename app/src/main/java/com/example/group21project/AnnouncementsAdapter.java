package com.example.group21project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AnnouncementsAdapter extends RecyclerView.Adapter<AnnouncementsAdapter.ViewHolder> {

    private final List<Announcement> announcementsList;

    public AnnouncementsAdapter(List<Announcement> announcementsList) {
        this.announcementsList = announcementsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.announcement_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Announcement announcement = announcementsList.get(position);
        holder.titleTextView.setText(announcement.getTitle());
        holder.descriptionTextView.setText(announcement.getDescription());
    }

    @Override
    public int getItemCount() {
        return announcementsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView descriptionTextView;
        TextView titleTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.announcement_title_text);
            descriptionTextView = itemView.findViewById(R.id.announcement_description_text);
        }
    }
}
