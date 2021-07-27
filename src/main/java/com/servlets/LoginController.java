package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.business.Address;
import com.database.ConnectionUtil;
import com.business.Employee;

public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("index.html").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter pw = response.getWriter();
        response.setContentType("text/html");

        int id = Integer.parseInt(request.getParameter("employeeID"));
        String pass = request.getParameter("password");

        HttpSession session = request.getSession();

        try {
            if (validate(id, pass)) {

                Employee employee = findByEmployeeID(id);

                session.setAttribute("employee", employee);

                if(employee.getJobTitle().equals("MANAGER")) {
                    RequestDispatcher rd = request.getRequestDispatcher("managerHomepage.html");
                    rd.forward(request, response);
                }
                else {
                    RequestDispatcher rd = request.getRequestDispatcher("employeeHomepage.html");
                    rd.forward(request, response);
                }
            }
            else {
                pw.println("Invalid ID and/or password");
                RequestDispatcher rd = request.getRequestDispatcher("index.html");
                rd.include(request, response);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        pw.close();
    }

    public Employee findByEmployeeID(int id) {
        Employee employee = new Employee();

        String QUERY = "SELECT employeeID, employeePassword, employeeName, jobTitle, department, yearlySalary, city, usState FROM employees WHERE employeeID = ?";

        Connection con = ConnectionUtil.getConnection();
        try {
            PreparedStatement prepared = con.prepareStatement(QUERY);
            prepared.setInt(1, id);
            ResultSet rs = prepared.executeQuery();

            while(rs.next()) {
                int employeeID = rs.getInt("employeeID");
                employee.setEmployeeID(employeeID);
                String pass = rs.getString("employeePassword");
                employee.setEmployeePassword(pass);
                String name = rs.getString("employeeName");
                employee.setEmployeeName(name);
                String job = rs.getString("jobTitle");
                employee.setJobTitle(job);
                int department = rs.getInt("department");
                employee.setDepartment(department);
                double salary = rs.getDouble("yearlySalary");
                employee.setYearlySalary(salary);
                String city = rs.getString("city");
                String state = rs.getString("usState");
                employee.setAddress(new Address(city, state));
            }
            System.out.println("Employee found");
            System.out.println(employee);
            return employee;
        } catch(SQLException e) {
            System.out.println("Failed to get employee");
        }
        return employee;
    }

    public boolean validate(int id, String pass) throws ClassNotFoundException {
        Connection connection = ConnectionUtil.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees WHERE employeeID = ? AND employeePassword = ?");
            statement.setInt(1, id);
            statement.setString(2, pass);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Failed to get employee");
        }

        return false;
    }

}