package com.example.group21project;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface EventListItem {
    String getName();
    String getDesc();
    String getStartTimeString();
    String getEndTimeString();
    String getLocation();

    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a EEEE, MMMM d, yyyy");
    static String getFormattedTimeString(LocalDateTime time) {
        return time.format(timeFormatter);
    }
}