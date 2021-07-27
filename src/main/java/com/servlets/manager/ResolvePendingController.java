package com.servlets.manager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.business.Employee;
import com.database.ConnectionUtil;

public class ResolvePendingController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ResolvePendingController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("rendingRequests.html").forward(request, response);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //retrieve all reimbursements

        HttpSession session = req.getSession();

        Employee e = (Employee) session.getAttribute("employee");
        int requestID = Integer.parseInt(req.getParameter("requestID"));
        String decision = req.getParameter("decision");

        updateStatus(e.getEmployeeID(), decision, requestID);

        try {
            RequestDispatcher rd = req.getRequestDispatcher("pendingRequests.html");
            rd.forward(req, resp);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean updateStatus(int managerID, String decision, int requestID){
        Connection connection = ConnectionUtil.getConnection();
        try{
            PreparedStatement statement = connection.prepareStatement("UPDATE reimbursements SET managerID = ?, status = ? WHERE requestID = ?");

            statement.setInt(1, managerID);
            System.out.println("managerID: " + managerID);
            statement.setString(2, decision);
            System.out.println("decision: " + decision);
            statement.setInt(3, requestID);
            System.out.println("requesterID: " + requestID);
            ResultSet rs = statement.executeQuery();

            System.out.println("before rs.next()");
            if(rs.next()){
                System.out.println("Updated request status successfully");
            }
            return true;
        } catch(SQLException e){
            System.out.println("Failed to update request status");
        }
        return false;
    }
}
