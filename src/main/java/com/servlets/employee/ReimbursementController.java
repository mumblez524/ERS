package com.servlets.employee;

import com.business.Address;
import com.business.Employee;
import com.business.Reimbursement;
import com.database.ConnectionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ReimbursementController extends HttpServlet{
    //setup the servlet with the context
    //or create new instances of the supporting objects

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //retrieve all reimbursements
        resp.setStatus(200);
        resp.setContentType("application/json");

        HttpSession session = req.getSession();
        Employee employee = (Employee) session.getAttribute("employee");

        ArrayList<Reimbursement> reimList = getMyRequests(employee.getEmployeeID());

        for (Reimbursement r : reimList){
            System.out.println(r);
        }

        resp.getWriter()
                .write(
                        new ObjectMapper()
                                .writeValueAsString(reimList));
    }

    public ArrayList<Reimbursement> getMyRequests(int id) {
        ArrayList<Reimbursement> list = new ArrayList<Reimbursement>();

        String QUERY = "SELECT requestID, employeeID, employeeName, amount, reason, status, managerID FROM reimbursements WHERE employeeID = ?";
        Connection con = ConnectionUtil.getConnection();
        try {
            PreparedStatement prepared = con.prepareStatement(QUERY);
            prepared.setInt(1, id);
            ResultSet rs = prepared.executeQuery();

            while (rs.next()) {

                int requestID = rs.getInt("requestID");
                int employeeID = rs.getInt("employeeID");
                String employeeName = rs.getString("employeeName");
                double amount = rs.getInt("amount");
                String reason = rs.getString("reason");
                String status = rs.getString("status");
                int managerID = rs.getInt("managerID");

                list.add(new Reimbursement(requestID, employeeID, employeeName, amount, reason, status, managerID));
            }
            System.out.println("List created successfully");
            return list;
        } catch (SQLException e) {
            System.out.println("Failed to create list");
        }
        return list;
    }
}