package com.example.group21project;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface EventListItem {
    String getName();
    String getStartTimeString();
    String getLocation();

    // TODO: fix redundancy between this and the DepartmentEvent time formatting
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a EEEE, MMMM d, yyyy");
    static String getFormattedTimeString(LocalDateTime time) {
        return time.format(timeFormatter);
    }
}
