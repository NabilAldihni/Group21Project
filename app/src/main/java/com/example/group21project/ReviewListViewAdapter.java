package com.example.group21project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewListViewAdapter extends RecyclerView.Adapter<ReviewListViewHolder> {
    private final Context reviewListContext;
    private final List<DepartmentEventReview> reviews;

    public ReviewListViewAdapter(Context reviewListContext, List<DepartmentEventReview> reviews) {
        this.reviewListContext = reviewListContext;
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public ReviewListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_list_item, parent, false);
        return new ReviewListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewListViewHolder holder, int position) {
        DepartmentEventReview review = reviews.get(position);
        TextView reviewerEmailView = holder.getReviewerEmailView();
        TextView ratingView = holder.getRatingView();
        TextView reviewTimeView = holder.getReviewTimeView();
        TextView feedbackView = holder.getFeedbackView();

        reviewerEmailView.setText(review.getReviewerEmail());
        ratingView.setText(review.getRating() + " / 5");
        String formattedSubmitTime = EventListItem.getFormattedTimeString(LocalDateTime.parse(review.getSubmitTime()));
        reviewTimeView.setText(formattedSubmitTime);
        feedbackView.setText(review.getFeedback());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }
}