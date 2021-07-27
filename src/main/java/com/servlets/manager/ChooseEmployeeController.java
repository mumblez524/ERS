package com.servlets.manager;

import com.business.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.servlets.LoginController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ChooseEmployeeController extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //retrieve all reimbursements
        resp.setStatus(200);
        resp.setContentType("application/json");

        int id = Integer.parseInt(req.getParameter("employeeID"));

        LoginController lc = new LoginController();
        Employee e = lc.findByEmployeeID(id);
        System.out.println(e);

        resp.getWriter()
                .write(
                        new ObjectMapper()
                                .writeValueAsString(
                                        new ArrayList(
                                                Arrays.asList(e)
                                        )));
    }
}
