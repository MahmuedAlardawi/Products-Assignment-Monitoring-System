/*
Name: Mahmued Ahmed Alardawi
Email: mmalardawi@stu.kau.edu.sa
ID: 2135209
Section: F1
Assignment: Products Assignment Monitoring System
Date: 09/16/2022
Course: CPCS-204
*/

public class Employee {
    // Data
    private int EmpID;
    private String Fname;
    private String Lname;
    private String product;
    private String phone;
    private int age;
    private int center;
    private Employee next;

    // Constructor 1
    public Employee() {};

    // Constructor 2
    public Employee(int empID, String fname, String lname) {
        EmpID = empID;
        Fname = fname;
        Lname = lname;
    }

    // Constructor 3
    public Employee(int empID, String fname, String Lname, String product, Employee next) {
        EmpID = empID;
        Fname = fname;
        this.Lname = Lname;
        this.product = product;
        this.next = next;
    }

    // Constructor 4
    public Employee(int empID, String fname, String Lname, String product, int center, Employee next) {
        EmpID = empID;
        Fname = fname;
        this.Lname = Lname;
        this.product = product;
        this.center = center;
        this.next = next;
    }

    // Constructor 5
    public Employee(int empID, String fname, String Lname, String product, String phone, int age, int center) {
        EmpID = empID;
        Fname = fname;
        this.Lname = Lname;
        this.product = product;
        this.phone = phone;
        this.age = age;
        this.center = center;
    }

    // Getters and setters
    public int getEmpID() {
        return EmpID;
    }
    public void setEmpID(int empID) {
        EmpID = empID;
    }
    public String getFname() {
        return Fname;
    }
    public void setFname(String fname) {
        Fname = fname;
    }
    public String getLname() {
        return Lname;
    }
    public void setLname(String lname) {
        this.Lname = lname;
    }
    public String getProduct() {
        return product;
    }
    public void setProduct(String product) {
        this.product = product;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getCenter() {
        return center;
    }
    public void setCenter(int center) {
        this.center = center;
    }
    public Employee getNext() {
        return next;
    }
    public void setNext(Employee next) {
        this.next = next;
    }

    // toString
    @Override
    public String toString() {
        return "Employee{" +
                "EmpID=" + EmpID +
                ", Fname='" + Fname + '\'' +
                ", lName='" + Lname + '\'' +
                ", product='" + product + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                ", center=" + center +
                ", next=" + next +
                '}';
    }
}
