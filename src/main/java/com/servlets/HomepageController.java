package com.servlets;

import com.business.Employee;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class HomepageController extends HttpServlet {

    //setup the servlet with the context
    //or create new instances of the supporting objects

    //@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //retrieve all reimbursements
        resp.setStatus(200);
        resp.setContentType("text/html");

        HttpSession session = req.getSession();

        Employee employee = (Employee) session.getAttribute("employee");

        if(employee.getJobTitle().equals("MANAGER")) {
            RequestDispatcher rd = req.getRequestDispatcher("managerHomepage.html");
            rd.forward(req, resp);
        }
        else {
            RequestDispatcher rd = req.getRequestDispatcher("employeeHomepage.html");
            rd.forward(req, resp);
        }
    }
}