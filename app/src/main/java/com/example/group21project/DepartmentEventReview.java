package com.example.group21project;

public class DepartmentEventReview {
    private String reviewerEmail;
    private double rating;
    private String feedback;
    private String submitTime;

    public DepartmentEventReview(String reviewerEmail, double rating, String feedback, String submitTime) {
        this.reviewerEmail = reviewerEmail;
        this.rating = rating;
        this.feedback = feedback;
        this.submitTime = submitTime;
    }

    public DepartmentEventReview() {}   // Needed for parsing by Firebase

    public String getReviewerEmail() {
        return reviewerEmail;
    }

    public double getRating() {
        return rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public String getSubmitTime() {
        return submitTime;
    }
}
