package com.example.group21project;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class DepartmentEventFirebaseAdapter implements EventListItem {
    private String name;
    private String desc;
    private String startTime;
    private String endTime;
    private String location;
    private int capacity;
    private ArrayList<String> attending;

    public DepartmentEventFirebaseAdapter(String name, String desc, LocalDateTime startTime, LocalDateTime endTime,
                                          String location, int capacity) {
        if (capacity < 0)
            throw new IllegalArgumentException("Event capacity must be non-negative");
        else if (startTime.isAfter(endTime))
            throw new IllegalArgumentException("Event start time cannot be after end time");
        this.name = name;
        this.desc = desc;
        this.startTime = startTime.toString();
        this.endTime = endTime.toString();
        this.location = location;
        this.capacity = capacity;
        this.attending = new ArrayList<>();
    }

    public DepartmentEventFirebaseAdapter() {}  // Needed for retrieval from database

    public static DepartmentEvent parseFirebaseCompatibleEvent(DepartmentEventFirebaseAdapter compatibleEvent) {
        LocalDateTime eventStartTime = LocalDateTime.parse(compatibleEvent.getStartTime());
        LocalDateTime eventEndTime = LocalDateTime.parse(compatibleEvent.getEndTime());
        return new DepartmentEvent(compatibleEvent.getName(), compatibleEvent.getDesc(), eventStartTime, eventEndTime,
                compatibleEvent.getLocation(), compatibleEvent.getCapacity(), compatibleEvent.getAttending());
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
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
        LocalDateTime eventStartTime = LocalDateTime.parse(startTime);
        return EventListItem.getFormattedTimeString(eventStartTime);
    }

    @Override
    public String getEndTimeString() {
        LocalDateTime eventEndTime = LocalDateTime.parse(endTime);
        return EventListItem.getFormattedTimeString(eventEndTime);
    }
}
