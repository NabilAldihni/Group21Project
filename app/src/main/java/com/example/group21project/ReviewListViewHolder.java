package com.example.group21project;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReviewListViewHolder extends RecyclerView.ViewHolder {
    private final TextView reviewerEmailView;
    private final TextView ratingTextView;
    private final TextView reviewTimeView;
    private final TextView feedbackView;

    public ReviewListViewHolder(@NonNull View itemView) {
        super(itemView);
        reviewerEmailView = itemView.findViewById(R.id.reviewerEmailText);
        ratingTextView = itemView.findViewById(R.id.ratingText);
        reviewTimeView = itemView.findViewById(R.id.reviewTimeText);
        feedbackView = itemView.findViewById(R.id.feedbackText);
    }

    public TextView getReviewerEmailView() {
        return reviewerEmailView;
    }

    public TextView getRatingView() {
        return ratingTextView;
    }

    public TextView getReviewTimeView() {
        return reviewTimeView;
    }

    public TextView getFeedbackView() {
        return feedbackView;
    }
}