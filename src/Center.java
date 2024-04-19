/*
Name: Mahmued Ahmed Alardawi
Email: mmalardawi@stu.kau.edu.sa
ID: 2135209
Section: F1
Assignment: Products Assignment Monitoring System
Date: 09/16/2022
Course: CPCS-204
*/

import java.util.*;

public class Center {
    // Data
    private String centerName;
    private int centerID;
    private Employee head;

    // Constructor 1
    public Center() {}

    // Constructor 2
    public Center(String centerName) {
        this.centerName = centerName;
    }

    // Constructor 3
    public Center(String centerName, int centerID) {
        this.centerName = centerName;
        this.centerID = centerID;
    }

    // Constructor 4
    public Center(String centerName, int centerID, Employee head) {
        this.centerName = centerName;
        this.centerID = centerID;
        this.head = head;
    }

    // Getters and setters
    public String getCenterName() {
        return centerName;
    }
    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }
    public int getCenterID() {
        return centerID;
    }
    public void setCenterID(int centerID) {
        this.centerID = centerID;
    }
    public Employee getHead() {
        return head;
    }
    public void setHead(Employee head) {
        this.head = head;
    }

    // toString
    @Override
    public String toString() {
        return "Center{" +
                "centerName='" + centerName + '\'' +
                ", centerID=" + centerID +
                ", head=" + head +
                '}';
    }

    // Check if the linked list (Employee) is empty
    public boolean isEmpty() {
        return head == null;
    }

    // Add employee
    public void addEmployee(int empID, String fname, String lName, String product) {
        if (!isEmpty()) {
            Employee helpPtr = head;
            while (helpPtr.getNext() != null) {
                helpPtr = helpPtr.getNext();
            }
            helpPtr.setNext(new Employee(empID, fname, lName,product, helpPtr.getNext()));
        }
        else  {head = new Employee(empID, fname, lName,product, null);}
    }
    public void addEmployee(int empID, String fname, String lName, String product, int center) {
        if (!isEmpty()) {
            Employee helpPtr = head;
            while (helpPtr.getNext() != null) {
                helpPtr = helpPtr.getNext();
            }
            helpPtr.setNext(new Employee(empID, fname, lName, product, center, helpPtr.getNext()));
        }
        else  {head = new Employee(empID, fname, lName, product, center, null);}
    }

    // Search employee by id
    public boolean searchEmployeeByID(int empID) {
        if (!isEmpty()) {
            Employee helpPtr = head;
            while (helpPtr != null) {
                if (helpPtr.getEmpID() == empID) {
                    return true;
                }
                helpPtr = helpPtr.getNext();
            }
        }
        return false;
    }
    public Employee searchEmployeeByID_withReturn(int empID) {
        if (!isEmpty()) {
            Employee helpPtr = head;
            while (helpPtr != null) {
                if (helpPtr.getEmpID() == empID) {
                    return helpPtr;
                }
                helpPtr = helpPtr.getNext();
            }
        }
        return null;
    }

    // Swap employee
    public void swapEmployees(int empID1, int EmpID2) {}

    // Delete employee
    public void deleteEmployeeByID(int empID) {
        if (!isEmpty()) {
            if (head.getEmpID() == empID) {
                head = head.getNext();
            }
            else {Employee helpPtr = head;
                while (helpPtr.getNext() != null) {
                    if (helpPtr.getNext().getEmpID() == empID) {
                        helpPtr.setNext(helpPtr.getNext().getNext());
                    }
                    helpPtr = helpPtr.getNext();
                }
            }
        }
    }

    // Number of nodes
    public int numberOfEmployees() {
        int count = 0;
        if (!isEmpty()) {
            Employee helpPtr = head;
            while (helpPtr != null) {
                count++;
                helpPtr = helpPtr.getNext();
            }
        }
        return count;
    }

    // Replace employee
    public void replaceEmployee(int empIDOld, Employee newEmployee) {
        if (!isEmpty()) {
            Employee oldEmployee = searchEmployeeByID_withReturn(empIDOld);

            if (oldEmployee != null && newEmployee != null) {
                Employee helpPtr = head;
                while (helpPtr != null) {
                    if (helpPtr == oldEmployee) {
                        helpPtr.setEmpID(newEmployee.getEmpID());
                        helpPtr.setFname(newEmployee.getFname());
                        helpPtr.setLname(newEmployee.getLname());
                    }
                    helpPtr = helpPtr.getNext();
                }
            }
        }
    }
//
}
