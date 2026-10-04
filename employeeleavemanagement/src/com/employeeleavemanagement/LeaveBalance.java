package com.employeeleavemanagement;

public class LeaveBalance {
	private int balanceId;
    private int employeeId;
    private int totalLeaves;
    private int leavesTaken;

    public LeaveBalance() {
    }

    public LeaveBalance(int balanceId, int employeeId, int totalLeaves, int leavesTaken) {
        this.balanceId = balanceId;
        this.employeeId = employeeId;
        this.totalLeaves = totalLeaves;
        this.leavesTaken = leavesTaken;
    }

    public LeaveBalance(int employeeId, int totalLeaves, int leavesTaken) {
        this.employeeId = employeeId;
        this.totalLeaves = totalLeaves;
        this.leavesTaken = leavesTaken;
    }

    public int getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(int balanceId) {
        this.balanceId = balanceId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getTotalLeaves() {
        return totalLeaves;
    }

    public void setTotalLeaves(int totalLeaves) {
        this.totalLeaves = totalLeaves;
    }

    public int getLeavesTaken() {
        return leavesTaken;
    }

    public void setLeavesTaken(int leavesTaken) {
        this.leavesTaken = leavesTaken;
    }
}
