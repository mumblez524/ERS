package com.business;

public class Reimbursement {

    private int requestID;
    private int employeeID;
    private String employeeName;
    private double amount;
    private String reason;
    private String status;
    private int managerID;

    public Reimbursement(int requestID, int employeeID, String employeeName, double amount, String reason, String status, int managerID) {
        this.requestID = requestID;
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.amount = amount;
        this.reason = reason;
        this.status = status;
        this.managerID = managerID;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getManagerID() {
        return managerID;
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "requestID=" + requestID +
                ", employeeID=" + employeeID +
                ", employeeName='" + employeeName + '\'' +
                ", amount=" + amount +
                ", reason='" + reason + '\'' +
                ", status='" + status + '\'' +
                ", managerID=" + managerID +
                '}';
    }
}
