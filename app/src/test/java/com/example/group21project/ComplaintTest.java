package com.example.group21project;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

public class ComplaintTest {
    Complaint c1 = new Complaint();

    @Before
    public void setup() {
        String description = "this is a random description";
        c1.setDescription(description);
    }

    @Test
    public void test1() {
        String expectedResponse = "this is a random description";
        assertTrue(expectedResponse.equals(c1.getDescription()));
    }
    @Test
    public void test2() {
        String unexpectedResponse = "this is a different random description";
        assertFalse(unexpectedResponse.equals(c1.getDescription()));
    }
}
