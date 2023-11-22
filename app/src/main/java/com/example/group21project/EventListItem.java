package com.example.group21project;

import java.time.LocalDateTime;

public interface EventListItem {
    public String getName();
    public String getDesc();
    public LocalDateTime getStartTime();
    public LocalDateTime getEndTime();
}
