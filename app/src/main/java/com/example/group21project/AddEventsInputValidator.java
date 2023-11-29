package com.example.group21project;

import java.time.LocalDateTime;

public class AddEventsInputValidator {
    static boolean validateEventInput(String eventName, LocalDateTime startTime, LocalDateTime endTime,
                                       String eventLocation, String eventCapacity) {
        if (stringIsBlank(eventName))
            return false;
        else if (startTime.isAfter(endTime))
            return false;
        else if (stringIsBlank(eventLocation))
            return false;
        else if (!stringIsNumeric(eventCapacity))
            return false;
        return true;
    }

    private static boolean stringIsBlank(String string) {
        return string.trim().isEmpty();
    }

    private static boolean stringIsNumeric(String string) {
        return string.matches("\\d+");
    }
}