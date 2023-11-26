package com.example.group21project;

import static org.junit.Assert.assertThrows;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

public class DepartmentEventTest {
    private LocalDateTime time;
    @Before
    public void setup() {
        time = LocalDateTime.of(2023, 11, 1, 14, 10);
    }

    @Test
    public void invalidCapacity() {
        assertThrows(IllegalArgumentException.class,
                () -> new DepartmentEvent("name", "desc", time, time, "location", -1));
    }

    @Test
    public void invalidTimes() {
        LocalDateTime endTime = time.minusMinutes(1);
        assertThrows(IllegalArgumentException.class,
                () -> new DepartmentEvent("name", "desc", time, endTime, "location", 0));
    }
}
