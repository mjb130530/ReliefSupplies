/*
 * CS4389
 * Data and Application Security
 * Final Project
 * Project Group 4
 * Group Members: Matt Butler, Johnny Edgett, Abdul Wahab
 */
package DAO;

import java.sql.Connection;
import java.sql.SQLException;

import Entity.Employee;
import java.util.ArrayList;

/**
 *
 * @author Matthew
 */
public interface EmployeeDAO {
    
    Employee createEmployee(Connection connection, Employee employee) throws SQLException;
    
    //Using an ArrayList<> here in case two or more employees have the same name
    //Can either place whole name together or throw both in or SSN (So second use both first and last or SSN)
    //If you want to return all employees with the same first or last name just leave the one you don't want to use null
    //Can use this to return all employees with null for lastNames and null for firstNames
    ArrayList<Employee> retrieveEmployee(Connection connection, String employeeName) throws SQLException;
    ArrayList<Employee> retrieveEmployee(Connection connection, String employeeFirst, String employeeLast, String employeeSSN) throws SQLException;
    ArrayList<Employee> retrieveEmployee(Connection connection, Long locationID) throws SQLException;
    ArrayList<Employee> retrieveEmployee(Connection connection, Long locationID, Long employeeID) throws SQLException;
    
    boolean deleteEmployee(Connection connection, String employeeName) throws SQLException;
    
    
}
