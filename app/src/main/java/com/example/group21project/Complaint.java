package com.example.group21project;

public class Complaint {
    private String description;

    // Constructor
    public Complaint() {
        // Required for Firebase
    }

    // Getter and setter
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}