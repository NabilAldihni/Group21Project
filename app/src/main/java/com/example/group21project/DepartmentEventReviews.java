package com.example.group21project;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class DepartmentEventReviews implements EventListItem {
    private String name;
    private String startTime;
    private Integer numStars;
    private String reviewTextInput;
    private String location;

    public DepartmentEventReviews(String name, String startTime,
                                  String location, int numStars, String reviewTextInput) {
        this.name = name;
        this.numStars = numStars;
        this.startTime = startTime;
        this.location = location;
        this.reviewTextInput = reviewTextInput;
    }

//    public DepartmentEventReviews(String name, String startTime,
//                                  String location, Integer numStars, String reviewTextInput) {
//        this(name, startTime, location, numStars, reviewTextInput);
//    }

    public String getName() {
        return name;
    }

    @Override
    public String getStartTimeString() {
        return null;
    }
    public static DepartmentEventReviews parseFirebaseCompatibleEvent(DepartmentEventReviews compatibleEvent) {
        LocalDateTime eventStartTime = LocalDateTime.parse(compatibleEvent.getStartTime());
//        LocalDateTime eventEndTime = LocalDateTime.parse(compatibleEvent.getEndTime());
        return new DepartmentEventReviews(compatibleEvent.getName(), compatibleEvent.startTime,
                compatibleEvent.getLocation(), compatibleEvent.getNumStars(), compatibleEvent.getReviewTextInput());
    }
    public String getStartTime() {
        return startTime;
    }

    public int getNumStars() {
        return numStars;
    }

    public String getReviewTextInput() {
        return reviewTextInput;
    }

    public String getLocation() {
        return location;
    }

//    @Override
//    public String getStartTimeString() {
//        return EventListItem.getFormattedTimeString(startTime);
//    }
}