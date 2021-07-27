package com.servlets.manager;

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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AllEmployeesController extends HttpServlet {
    //setup the servlet with the context
    //or create new instances of the supporting objects

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //retrieve all reimbursements
        resp.setStatus(200);
        resp.setContentType("application/json");

        ArrayList<Employee> employeeList = getAllEmployees();

        for (Employee e : employeeList){
            System.out.println(e);
        }

        resp.getWriter()
                .write(
                        new ObjectMapper()
                                .writeValueAsString(employeeList));
    }

    public ArrayList<Employee> getAllEmployees() {
        ArrayList<Employee> list = new ArrayList<Employee>();

        String QUERY = "SELECT employeeID, employeePassword, employeeName, jobTitle, department, yearlySalary, city, usState FROM employees WHERE jobTitle = 'EMPLOYEE'";

        try(Connection con = ConnectionUtil.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY)){
            while(rs.next()){
                int id = rs.getInt("employeeID");
                String pass = rs.getString("employeePassword");
                String name = rs.getString("employeeName");
                String job = rs.getString("jobTitle");
                int department = rs.getInt("department");
                double salary = rs.getDouble("yearlySalary");
                String city = rs.getString("city");
                String state = rs.getString("usState");

                list.add(new Employee(id, pass, name, job, department, salary, new Address(city, state)));
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Failed to get all departments");;
        }

        return list;
    }

}
