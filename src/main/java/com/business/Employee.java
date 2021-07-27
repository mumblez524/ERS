package com.business;

public class Employee {

    private Integer employeeID;
    private String employeePassword;
    private String employeeName;
    private String jobTitle;
    private Integer department;
    private double yearlySalary;
    private Address address;

    public Employee() { }


    public Employee(int employeeID, String employeePassword, String employeeName,  String jobTitle, int department, double yearlySalary, Address address) {
        super();
        this.employeeID = employeeID;
        this.employeePassword = employeePassword;
        this.employeeName = employeeName;
        this.yearlySalary = yearlySalary;
        this.address = address;
        this.jobTitle = jobTitle;
        this.department = department;
    }


    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public double getYearlySalary() {
        return yearlySalary;
    }

    public void setYearlySalary(double yearlySalary) {
        this.yearlySalary = yearlySalary;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return "Employee [employeeID=" + employeeID + ", employeePassword=" + employeePassword + ", employeeName="
                + employeeName + ", jobTitle=" + jobTitle + ", department=" + department + ", yearlySalary="
                + yearlySalary + ", address=" + address + "]";
    }


}