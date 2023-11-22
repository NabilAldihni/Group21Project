package com.example.group21project;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class DepartmentEvent implements EventListItem {
    private String name;
    private String desc;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int capacity;
    private ArrayList<User> attending;

    public DepartmentEvent(String name, String desc, LocalDateTime startTime, LocalDateTime endTime, int capacity) {
        if (capacity < 0)
            throw new IllegalArgumentException("Event capacity must be non-negative");
        else if (startTime.isAfter(endTime))
            throw new IllegalArgumentException("Event start time cannot be after end time");
        this.name = name;
        this.desc = desc;
        this.startTime = startTime;
        this.endTime = endTime;
        this.capacity = capacity;
        this.attending = new ArrayList<User>();
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

    public int getCapacity() {
        return capacity;
    }

    public ArrayList<User> getAttending() {
        return attending;
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

    public void setAttending(ArrayList<User> attending) {
        this.attending = attending;
    }
}
