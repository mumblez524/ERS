package com.servlets.employee;

import com.business.Address;
import com.business.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class EmployeeController extends HttpServlet {

    //setup the servlet with the context
    //or create new instances of the supporting objects

    //@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //retrieve all reimbursements
        resp.setStatus(200);
        resp.setContentType("application/json");

        HttpSession session = req.getSession();

        Employee temp = (Employee) session.getAttribute("employee");
        Employee e = new Employee(temp.getEmployeeID(),
                temp.getEmployeePassword(),
                temp.getEmployeeName(),
                temp.getJobTitle(),
                temp.getDepartment(),
                temp.getYearlySalary(),
                new Address(temp.getAddress().getCity(),
                        temp.getAddress().getState()));

        resp.getWriter()
                .write(
                        new ObjectMapper()
                                .writeValueAsString(
                                        new ArrayList(
                                                Arrays.asList(e)
                                        )));
    }
}
