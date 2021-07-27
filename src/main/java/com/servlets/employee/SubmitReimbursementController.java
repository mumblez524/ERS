package com.servlets.employee;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.business.Employee;
import com.business.Reimbursement;
import com.database.ConnectionUtil;


public class SubmitReimbursementController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SubmitReimbursementController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("submitRequest.html").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        HttpSession session = request.getSession();

        Employee employee = (Employee) session.getAttribute("employee");

        double amount = Double.parseDouble(request.getParameter("amount"));
        String reason = request.getParameter("reason");

        Reimbursement newReim = new Reimbursement(1, employee.getEmployeeID(), employee.getEmployeeName(), amount, reason, "PENDING", 0);

        System.out.println(newReim);

        newReimbursement(newReim);

        try {
            if(employee.getJobTitle().equals("MANAGER")) {
                response.sendRedirect("managerHomepage.html");
            }
            else {
                response.sendRedirect("employeeHomepage.html");
            }
        } catch (Exception e) {
            System.out.println("Failed to connect to Homepage");
            e.printStackTrace();
        }
    }

    public boolean newReimbursement(Reimbursement reimbursement) {
        String QUERY = "INSERT INTO reimbursements VALUES (?, ?, ?, ?, ?, 'PENDING', null)";
        try (Connection conn = ConnectionUtil.getConnection();
             java.sql.PreparedStatement statement = conn.prepareStatement(QUERY))
        {
            statement.setInt(1, reimbursement.getRequestID());
            statement.setInt(2, reimbursement.getEmployeeID());
            statement.setString(3, reimbursement.getEmployeeName());
            statement.setDouble(4, reimbursement.getAmount());
            statement.setString(5, reimbursement.getReason());
            statement.execute();

            System.out.println("Request added");
            return true;
        } catch (SQLException e) {
            System.out.println("Request failed");
        }
        return false;
    }
}