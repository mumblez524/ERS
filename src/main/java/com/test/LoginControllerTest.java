package com.test;

import com.business.Address;
import com.business.Employee;
import com.servlets.LoginController;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginControllerTest {

    Employee e = new Employee(3, "h1", "Sally", "MANAGER", 30, 75000, new Address("Nashville", "Tennessee"));
    LoginController login = new LoginController();

    @Test
    public void findByEmployeeID() {
        assertEquals(e.getEmployeeID(), login.findByEmployeeID(3).getEmployeeID());
        assertEquals(e.getEmployeePassword(), login.findByEmployeeID(3).getEmployeePassword());
        assertEquals(e.getEmployeeName(), login.findByEmployeeID(3).getEmployeeName());
        assertEquals(e.getJobTitle(), login.findByEmployeeID(3).getJobTitle());
    }

    @Test
    public void validate() throws ClassNotFoundException {
        assertEquals(true, login.validate(e.getEmployeeID(), e.getEmployeePassword()));
        assertNotEquals(true, login.validate(5, "yo"));
    }
}