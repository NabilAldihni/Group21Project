package com.example.group21project;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DepartmentEvent implements EventListItem {
    private final String name;
    private final String desc;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final String location;
    private final int capacity;
    // TODO: maybe separate attending with a different interface or at least don't modify the ArrayList directly
    private final ArrayList<String> attending;

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

    public String getDesc() {
        return desc;
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

    public int getCapacity() {
        return capacity;
    }

    public List<String> getAttending() {
        return attending;
    }

    @Override
    public String getStartTimeString() {
        return EventListItem.getFormattedTimeString(startTime);
    }

    @Override
    public String getEndTimeString() {
        return EventListItem.getFormattedTimeString(endTime);
    }

    public boolean userHasRsvped(String userId) {
        return attending.contains(userId);
    }

    public boolean hasCapacity() {
        return attending.size() < capacity;
    }

    public void rsvpUser(String userId) {
        attending.add(userId);
    }

    public void unRsvpUser(String userId) {
        attending.remove(userId);
    }
}