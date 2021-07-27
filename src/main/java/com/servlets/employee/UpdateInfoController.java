package com.servlets.employee;

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


public class UpdateInfoController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateInfoController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("updateInfo.html").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("newName");
        String pass = request.getParameter("newPassword");
        String city = request.getParameter("newCity");
        String state = request.getParameter("newState");

        HttpSession session = request.getSession();

        Employee employee = (Employee) session.getAttribute("employee");

        if(!(employee.getEmployeeName().equals(name) || name.isEmpty())) {
            updateName(name, employee.getEmployeeID());
            employee.setEmployeeName(name);
        }
        if(!(employee.getEmployeePassword().equals(pass) || pass.isEmpty())) {
            updatePassword(pass, employee.getEmployeeID());
            employee.setEmployeePassword(pass);
        }
        if(!(employee.getAddress().getCity().equals(city) || city.isEmpty())) {
            updateCity(city, employee.getEmployeeID());
            employee.getAddress().setCity(city);
        }
        if(!(employee.getAddress().getState().equals(state) || state.isEmpty())) {
            updateState(state, employee.getEmployeeID());
            employee.getAddress().setState(state);
        }

        session.setAttribute("employee", employee);

        employee = (Employee) session.getAttribute("employee");

        System.out.println(employee);

        try {
            RequestDispatcher rd = request.getRequestDispatcher("employee.html");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*
    * JDBC Functions
    */
    public boolean updateName(String newName, int id) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE employees SET employeeName = ? WHERE employeeID = ?");
            statement.setString(1, newName);
            statement.setInt(2, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                System.out.println("Updated name successfully");
            }
            return true;

        } catch(SQLException e) {
        System.out.println("Failed to update name");
        }
        return false;
    }

    public boolean updatePassword(String newPass, int id) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE employees SET employeePassword = ? WHERE employeeID = ?");
            statement.setString(1, newPass);
            statement.setInt(2, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                System.out.println("Updated password successfully");
            }
            return true;
        } catch(SQLException e) {
            System.out.println("Failed to update password");
        }
        return false;
    }

    public boolean updateCity(String newCity, int id) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE employees SET city = ? WHERE employeeID = ?");
            statement.setString(1, newCity);
            statement.setInt(2, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                System.out.println("Updated city successfully");
            }
            return true;
        } catch(SQLException e) {
            System.out.println("Failed to update city");
        }
        return false;
    }

    public boolean updateState(String newState, int id) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE employees SET usState = ? WHERE employeeID = ?");
            statement.setString(1, newState);
            statement.setInt(2, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                System.out.println("Updated state successfully");
            }
            return true;
        } catch(SQLException e) {
            System.out.println("Failed to update state");
        }
        return false;
    }
}