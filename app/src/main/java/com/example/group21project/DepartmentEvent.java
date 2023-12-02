package com.example.group21project;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class DepartmentEvent implements EventListItem {
    private String name;
    private String desc;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;
    private int capacity;
    // TODO: maybe separate attending with a different interface or at least don't modify the ArrayList directly
    private ArrayList<String> attending;

    public DepartmentEvent(String name, String desc, LocalDateTime startTime, LocalDateTime endTime, String location,
                           int capacity, ArrayList<String> attending) {
        if (capacity < 0)
            throw new IllegalArgumentException("Event capacity must be non-negative");
        else if (startTime.isAfter(endTime))
            throw new IllegalArgumentException("Event start time cannot be after end time");
        this.name = name;
        this.desc = desc;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.capacity = capacity;
        this.attending = attending;
    }

    public DepartmentEvent(String name, String desc, LocalDateTime startTime, LocalDateTime endTime, String location,
                           int capacity) {
        this(name, desc, startTime, endTime, location, capacity, new ArrayList<>());
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String getStartTimeString() {
        return EventListItem.getFormattedTimeString(startTime);
    }
}