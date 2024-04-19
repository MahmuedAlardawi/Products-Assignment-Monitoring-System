/*
Name: Mahmued Ahmed Alardawi
Email: mmalardawi@stu.kau.edu.sa
ID: 2135209
Section: F1
Assignment: Products Assignment Monitoring System
Date: 09/16/2022
Course: CPCS-204
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class MainProgram {
    public static void main(String[] args) throws FileNotFoundException {
        File Commands = new File("Commands.txt");
        File initialInformation = new File("initialInformation.txt");
        PrintWriter finalOutput = new PrintWriter("finalOutput.txt");
        Center[] centers = STARTUP(Input(Commands), Input(initialInformation), finalOutput);

        DISPLAY_ALL_CENTERS(Input(Commands), finalOutput, centers);
        DISPLAY_PRODUCTS_FOR_EMPLOYEE(Input(Commands), finalOutput, centers);
        NUM_OF_EMPLOYEES(Input(Commands), finalOutput, centers);
        DISPLAY_BASED_ON_PRODUCT(Input(Commands), finalOutput, centers);
        CHANGE_TO_NULL_PRODUCT(Input(Commands), finalOutput, centers);
        SWAP_BETWEEN_EMPLOYEES(Input(Commands), finalOutput, centers);
        DISPLAY_ALL_CENTERS(Input(Commands), finalOutput, centers);
        QUIT(Input(Commands), finalOutput);

        finalOutput.close();
    }
    //
    public static Scanner Input(File file) throws FileNotFoundException {return new Scanner(file);}
    //
    public static Center[] STARTUP(Scanner Commands, Scanner input, PrintWriter output) {
        Center[] centers = null;

        while (Commands.hasNext()) {
            if (Commands.next().equals("STARTUP")) {
                output.printf("%73s %n", "Welcome to Shopping Center Employee Management System");
                output.printf("%73s %n", "-----------------------------------------------------");
                int numberOfEmployees = input.nextInt();
                int numberOfCenters = input.nextInt();
                int numberOfProducts = input.nextInt();

                centers = new Center[numberOfCenters];

                // Adding centers
                for (int c = 0; c < numberOfCenters; c++) {
                    centers[c] = new Center(input.next());
                }

                String[] products = new String[numberOfProducts];

                // Adding products
                for (int p = 0; p < numberOfProducts; p++) {
                    products[p] = input.next();
                }

                // Adding employees
                for (int c = 0; c < numberOfCenters; c++) {
                    for (int p = 0; p < numberOfProducts; p++) {
                        centers[c].addEmployee(input.nextInt(), input.next(), input.next(), products[p]);
                    }
                }

                // Adding centers ID
                for (int c = 0; c < numberOfCenters; c++) {
                    centers[c].setCenterID(input.nextInt());
                    input.next();
                }

                // Adding center ID to each employee
                for (int c = 0; c < numberOfCenters; c++) {
                    for (int p = 0; p < numberOfProducts; p++) {
                        if (p == 0) {
                            centers[c].getHead().setCenter(centers[c].getCenterID());
                        } else {
                            Employee helpPtr = centers[c].getHead().getNext();
                            while (helpPtr != null) {
                                helpPtr.setCenter(centers[c].getCenterID());
                                helpPtr = helpPtr.getNext();
                            }
                        }
                    }
                }
            }
        }
        return centers;
    }
    //
    public static void DISPLAY_ALL_CENTERS(Scanner Commands, PrintWriter output, Center[] centers) {
        output.println();
        while (Commands.hasNext()) {
            if (Commands.next().equals("DISPLAY_ALL_CENTERS")) {
                output.printf("%66s %n %n", "Information of Employees in Each Center");

                for (Center center : centers) {
                    output.printf("%20s", "");
                    output.print("Employees in " + center.getCenterName() + " Center\n");
                    output.print("\tID");
                    output.printf("%13s", "Name");
                    output.printf("%31s", "Product");
                    output.println();

                    Employee helpPtrt = center.getHead();
                    for (int node = 0; node < center.numberOfEmployees(); node++) {
                        String empID = String.format("%03d", helpPtrt.getEmpID());
                        output.printf("\t%-11s", empID);
                        output.printf("%-28s", helpPtrt.getFname() + " " + helpPtrt.getLname());
                        output.print(helpPtrt.getProduct());
                        output.println();
                        helpPtrt = helpPtrt.getNext();
                    }
                    output.printf("%-9s", "");
                    output.println("----------------------------------------------");

                }
                break;
            }
        }
    }
    //
    public static void DISPLAY_PRODUCTS_FOR_EMPLOYEE(Scanner Commands, PrintWriter output, Center[] centers) {
        output.println();
        while (Commands.hasNext()) {
            if (Commands.next().equals("DISPLAY_PRODUCTS_FOR_EMPLOYEE")) {
                int empID = Commands.nextInt();

                for (int c = 0; c < centers.length; c++) {
                    if (centers[c].searchEmployeeByID(empID)) {
                        Employee helpPtrt = centers[c].getHead();
                        for (int node = 0; node < centers[c].numberOfEmployees(); node++) {
                            if (empID == helpPtrt.getEmpID()) {
                                String empIDWithZeros = String.format("%03d", helpPtrt.getEmpID());
                                output.println("\t\"Employee: " + helpPtrt.getFname() + " " + helpPtrt.getLname() + ", " +
                                        empIDWithZeros + ", is assigned to " + helpPtrt.getProduct() + ", in " +
                                        centers[c].getCenterName() + " center \"");
                            }
                            helpPtrt = helpPtrt.getNext();
                        }
                        break;
                    }
                    else if (c == centers.length - 1) {
                        output.println("\t\"No employee of this number is found \"");
                    }
                }
            }
        }
        output.println("\n-------------------------------------------------------------------------------");
    }
    //
    public static void NUM_OF_EMPLOYEES(Scanner Commands, PrintWriter output, Center[] centers) {
        output.println();
        while (Commands.hasNext()) {
            if (Commands.next().equals("NUM_OF_EMPLOYEES")) {
                String centerName = Commands.next();

                for (Center center : centers) {
                    if (center.getCenterName().equals(centerName)) {
                        output.println("\tNumber of employees in " + center.getCenterName() + " center: " +
                                center.numberOfEmployees());
                    }
                }
            }
        }
        output.println("\n-------------------------------------------------------------------------------");
    }
    //
    public static void DISPLAY_BASED_ON_PRODUCT(Scanner Commands, PrintWriter output, Center[] centers) {
        output.println();
        while (Commands.hasNext()) {
            if (Commands.next().equals("DISPLAY_BASED_ON_PRODUCT")) {
                String product = Commands.next();

                output.printf("%20s", "");
                output.print("Employees for product " + product + "\n");
                output.print("\tID");
                output.printf("%13s", "Name");
                output.printf("%31s", "Center");
                output.println();

                for (Center center : centers) {
                    Employee helpPtrt = center.getHead();
                    for (int node = 0; node < center.numberOfEmployees(); node++) {
                        if (helpPtrt.getProduct().equals(product)) {
                            String empID = String.format("%03d", helpPtrt.getEmpID());
                            output.printf("\t%-11s", empID);
                            output.printf("%-29s", helpPtrt.getFname() + " " + helpPtrt.getLname());
                            output.print(center.getCenterName());
                            output.println();
                        }
                        helpPtrt = helpPtrt.getNext();
                    }
                }
                output.println();
            }
        }
        output.println("===============================================================================");
    }
    //
    public static void CHANGE_TO_NULL_PRODUCT(Scanner Commands, PrintWriter output, Center[] centers) {
        output.println();
        while (Commands.hasNext()) {
            if (Commands.next().equals("CHANGE_TO_NULL_PRODUCT")) {
                int empID = Commands.nextInt();

                output.printf("%20s", "");
                output.println("Change Assigned Product to Null");

                for (int c = 0; c < centers.length; c++) {
                    if (centers[c].searchEmployeeByID(empID)) {
                        Employee helpPtrt = centers[c].getHead();
                        for (int node = 0; node < centers[c].numberOfEmployees(); node++) {
                            if (empID == helpPtrt.getEmpID()) {
                                helpPtrt.setProduct(null);
                                String empIDWithZeros = String.format("%03d", helpPtrt.getEmpID());
                                output.println("\tProducts for " + helpPtrt.getFname() + " " + helpPtrt.getLname()
                                        + " : " + empIDWithZeros + " has been changed to " + helpPtrt.getProduct());
                                break;
                            }
                            helpPtrt = helpPtrt.getNext();
                        }
                        break;
                    }
                    else if (c == centers.length - 1) {
                        output.println("\t\"No employee of this number is found \"");
                    }
                }
                output.println();
            }
        }
        output.println("===============================================================================");
    }
    //
    public static void SWAP_BETWEEN_EMPLOYEES(Scanner Commands, PrintWriter output, Center[] centers) {
        output.println();
        while (Commands.hasNext()) {
            if (Commands.next().equals("SWAP_BETWEEN_EMPLOYEES")) {
                int empID1 = Commands.nextInt();
                int empID2 = Commands.nextInt();

                output.printf("%20s", "");
                output.println("Swap Centers Between Two Employees");

                Employee e1 = null;
                Employee e2 = null;

                String centerName1 = null;
                String centerName2 = null;

                for (Center center : centers) {
                    if (center.searchEmployeeByID(empID1)) {
                        e1 = center.searchEmployeeByID_withReturn(empID1);
                        centerName1 = center.getCenterName();
                    }
                    else if (center.searchEmployeeByID(empID2)) {
                        e2 = center.searchEmployeeByID_withReturn(empID2);
                        centerName2 = center.getCenterName();
                    }
                }

                String empID1WithZeros = String.format("%03d", empID1);
                String empID2WithZeros = String.format("%03d", empID2);
                if (e1 == null) {
                    output.println("\tThe employee with ID " + empID1WithZeros + " is not in the system");
                    output.println();
                    continue;
                }
                else if (e2 == null) {
                    output.println("\tThe employee with ID " + empID2WithZeros + " is not in the system");
                    output.println();
                    continue;
                }

                output.println("\t" + e1.getFname() + " " + e1.getLname() + " in " + centerName1 +
                        " center is SWAPPED WITH " + e2.getFname() + " " + e2.getLname() + " in " +
                        centerName2 + " center");

                Employee e22 = new Employee(e2.getEmpID(), e2.getFname(), e2.getLname());

                for (Center center : centers) {
                    if (center.searchEmployeeByID(empID1)) {
                        center.replaceEmployee(empID1, e22);
                    }
                    else if (center.searchEmployeeByID(empID2)) {
                        center.replaceEmployee(empID2, e1);
                    }
                }
                                output.println();
            }
        }
        output.println("===============================================================================");
    }
    //
    public static void QUIT(Scanner Commands, PrintWriter output) {
        output.println();
        while (Commands.hasNext()) {
            if (Commands.next().equals("SWAP_BETWEEN_EMPLOYEES")) {
                output.println("\t============================");
                output.println("\t\tBest Wishes");
                output.println("\t============================");
            }
        }
    }
}
