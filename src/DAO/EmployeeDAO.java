/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    
    boolean deleteEmployee(Connection connection, String employeeName) throws SQLException;
    
    
}
