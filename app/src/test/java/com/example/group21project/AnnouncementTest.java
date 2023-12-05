package com.example.group21project;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class AnnouncementTest {
    Announcement a1 = new Announcement();

    @Before
    public void setup() {
        String title = "this is a random title";
        a1.setTitle(title);
    }

    @Test
    public void test1() {
        String expectedResponse = "this is a random title";
        assertTrue(expectedResponse.equals(a1.getTitle()));
    }
    @Test
    public void test2() {
        String unexpectedResponse = "this is a different random description";
        assertFalse(unexpectedResponse.equals(a1.getTitle()));
    }
}
