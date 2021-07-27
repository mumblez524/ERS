package com.test;

import com.servlets.employee.UpdateInfoController;
import org.junit.Test;

import static org.junit.Assert.*;

public class UpdateInfoControllerTest {

    UpdateInfoController uic = new UpdateInfoController();

    @Test
    public void updateName() {
        assertTrue(uic.updateName("Slim", 2));
    }

    @Test
    public void updatePassword() {
        assertTrue(uic.updatePassword("no", 2));
    }

    @Test
    public void updateCity() {
        assertTrue(uic.updateCity("Austin", 2));
    }

    @Test
    public void updateState() {
        assertTrue(uic.updatePassword("Texas", 2));
    }
}