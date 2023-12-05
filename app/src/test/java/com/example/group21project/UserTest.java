package com.example.group21project;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
    User admin_user = new User("sudo", "sudo@gmail.com", true);
    User regular_user = new User("aly", "aly@gmail.com", false);


    @Test
    public void test1() {
        //testing setAdmin
        assertTrue(admin_user.getAdmin());
    }
    @Test
    public void test2() {
        assertFalse(regular_user.getAdmin());
    }

    @Test
    public void test3() {
        assertTrue(admin_user.getEmail().equals("sudo@gmail.com"));
    }

    @Test
    public void test4() {
        assertFalse(regular_user.getEmail().equals("sudo@gmail.com"));
    }
    @Test
    public void test5() {
        assertTrue(admin_user.getUsername().equals("sudo"));
    }

    @Test
    public void test6() {
        assertFalse(regular_user.getUsername().equals("aly"));
    }

    @Test
    public void test7() {
        regular_user.setAdmin(true);
        assertTrue(regular_user.getAdmin());
    }

    @Test
    public void test8() {
        admin_user.setAdmin(false);
        assertFalse(admin_user.getAdmin());
    }


}
