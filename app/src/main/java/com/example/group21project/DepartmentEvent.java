package com.example.group21project;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class DepartmentEvent implements EventListItem {
//    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a EEEE, MMMM d, yyyy");
    private String name;
    private String desc;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;
    private int capacity;
    // TODO: maybe separate attending with a different interface or at least don't modify the ArrayList directly
    private ArrayList<String> attending;

    public DepartmentEvent(String name, String desc, LocalDateTime startTime, LocalDateTime endTime, String location,
                           int capacity) {
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
        this.attending = new ArrayList<>();
    }

    public DepartmentEvent() {}

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

    public ArrayList<String> getAttending() {
        return attending;
    }

    @Override
    public String getStartTimeString() {
//        return this.endTime.format(timeFormatter);
        return EventListItem.getFormattedTimeString(startTime);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setAttending(ArrayList<String> attending) {
        this.attending = attending;
    }
}