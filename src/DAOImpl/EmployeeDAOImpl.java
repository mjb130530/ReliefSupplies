/*
 * CS4389
 * Data and Application Security
 * Final Project
 * Project Group 4
 * Group Members: Matt Butler, Johnny Edgett, Abdul Wahab
 */
package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import DAO.EmployeeDAO;
import Entity.Employee;
import WorkFromHere.LoggingIntoSystem;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matthew
 */
public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public Employee createEmployee(Connection connection, Employee employee) throws SQLException {
        PreparedStatement ps = null;
        try {

            String insertSQL = "INSERT INTO employees (locationID, employeeFirst, employeeLast, employeeSSN, employeeType, dateOfHire) VALUES (?, ?, ?, ?, ?, ?);";
            ps = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, employee.getLocationID().toString());
            ps.setString(2, employee.getEmployeeFirst());
            ps.setString(3, employee.getEmployeeLast());
            ps.setString(4, getHash(employee.getEmployeeSSN()));
            ps.setString(5, employee.getEmployeeType());
            ps.setString(6, employee.getDateOfHire());

            ps.executeUpdate();

            ResultSet fullEmployee = ps.getGeneratedKeys();
            fullEmployee.next();
            int genKey = fullEmployee.getInt(1);
            employee.setEmployeeID((long) genKey);

            return employee;
        } catch (Exception ex) {
            System.err.println("Class: EmployeeDAOImpl. Method: createEmployee");
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
            return employee;
        }

    }

    @Override
    public ArrayList<Employee> retrieveEmployee(Connection connection, String employeeName) throws SQLException {
        PreparedStatement ps = null;
        String firstName = null;
        String lastName = null;
        String fullName = employeeName;
        String delims = "[ ]+";
        String[] tokens = fullName.split(delims);
        firstName = tokens[1];

        //----------------------------------------------------------------------------
        //Should place an if statement here to catch if only one name was sent through
        //Only placing one name in this will cause EOL
        //----------------------------------------------------------------------------
        lastName = tokens[2];
        try {
            String retrieveEmployee = "SELECT * FROM Employees WHERE Employees.employeeFirst = ? AND Employees.employeeLast = ?;";
            ps = connection.prepareCall(retrieveEmployee);
            ps.setString(1, firstName);
            ps.setString(2, lastName);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                return null;
            }

            ArrayList<Employee> employeeList = new ArrayList<>();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployeeID(rs.getLong("employeeID"));
                employee.setLocationID(rs.getLong("locationID"));
                employee.setEmployeeFirst(rs.getString("employeeFirst"));
                employee.setEmployeeLast(rs.getString("employeeLast"));
                employee.setEmployeeSSN(rs.getString("employeeSSN"));
                employee.setEmployeeType(rs.getString("employeeType"));
                employee.setDateOfHire(rs.getString("dateOfHire"));
                employeeList.add(employee);
            }
            return employeeList;
        } catch (Exception ex) {
            System.err.println("Class: EmployeeDAOImpl. Method: retrieveEmployee(Connection connection, String employeeName)");
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
            return null;
        }
    }

    @Override
    public ArrayList<Employee> retrieveEmployee(Connection connection, String employeeFirst, String employeeLast, String employeeSSN) throws SQLException {
        PreparedStatement ps = null;
        try {
            //Returns all matches of FIRSTNAME = employeeFirst
            if (!employeeFirst.equals("") && employeeLast.equals("") && employeeSSN.equals("")) {
                String retrieveEmployee = "SELECT * FROM Employees WHERE Employees.employeeFirst = ?;";
                ps = connection.prepareCall(retrieveEmployee);
                ps.setString(1, employeeFirst);
            } else if (employeeFirst.equals("") && !employeeLast.equals("") && employeeSSN.equals("")) {
                String retrieveEmployee = "SELECT * FROM Employees WHERE Employees.employeeLast = ?;";
                ps = connection.prepareCall(retrieveEmployee);
                ps.setString(1, employeeLast);
            } else if (employeeFirst.equals("") && employeeLast.equals("") && !employeeSSN.equals("")) {
                String retrieveEmployee = "SELECT * FROM Employees WHERE Employees.employeeSSN = ?;";
                ps = connection.prepareCall(retrieveEmployee);
                ps.setString(1, getHash(employeeSSN));
            } else if (!employeeFirst.equals("") && !employeeLast.equals("") && !employeeSSN.equals("")) {
                String retrieveEmployee = "SELECT * FROM Employees WHERE Employees.employeeFirst = ? AND Employees.employeeLast = ?"
                        + "AND Employees.employeeSSN = ?;";
                ps = connection.prepareCall(retrieveEmployee);
                ps.setString(1, employeeFirst);
                ps.setString(2, employeeLast);
                ps.setString(3, getHash(employeeSSN));
            } else if (!employeeFirst.equals("") && !employeeLast.equals("") && employeeSSN.equals("")) {
                String retrieveEmployee = "SELECT * FROM Employees WHERE Employees.employeeFirst = ? AND Employees.employeeLast = ?;";
                ps = connection.prepareCall(retrieveEmployee);
                ps.setString(1, employeeFirst);
                ps.setString(2, employeeLast);
            } //This returns all employees
            else if (employeeFirst.equals("") && employeeLast.equals("") && employeeSSN.equals("")) {
                String retrieveEmployee = "SELECT * FROM Employees;";
                ps = connection.prepareCall(retrieveEmployee);
            } else if (!employeeFirst.equals("") && employeeLast.equals("") && !employeeSSN.equals("")) {
                String retrieveEmployee = "SELECT * FROM Employees WHERE Employees.employeeFirst = ? AND Employees.employeeSSN = ?;";
                ps = connection.prepareCall(retrieveEmployee);
                ps.setString(1, employeeFirst);
                ps.setString(2, getHash(employeeSSN));
            } else if (employeeFirst.equals("") && !employeeLast.equals("") && !employeeSSN.equals("")) {
                String retrieveEmployee = "SELECT * FROM Employees WHERE Employees.employeeLast = ? AND Employees.employeeSSN = ?;";
                ps = connection.prepareCall(retrieveEmployee);
                ps.setString(1, employeeLast);
                ps.setString(2, getHash(employeeSSN));
            }

            ResultSet rs = ps.executeQuery();

            if (!rs.isBeforeFirst()) {
                return null;
            }

            ArrayList<Employee> employeeList = new ArrayList<>();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployeeID(rs.getLong("employeeID"));
                employee.setLocationID(rs.getLong("locationID"));
                employee.setEmployeeFirst(rs.getString("employeeFirst"));
                employee.setEmployeeLast(rs.getString("employeeLast"));
                employee.setEmployeeSSN(rs.getString("employeeSSN"));
                employee.setEmployeeType(rs.getString("employeeType"));
                employee.setDateOfHire(rs.getString("dateOfHire"));
                employeeList.add(employee);
            }
            return employeeList;
        } catch (SQLException ex) {
            System.err.println("Class: EmployeeDAOImpl. Method: retrieveEmployee(Connection connection, String employeeFirst, String employeeLast, String employeeSSN)");
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
            return null;
        }
    }

    @Override
    public ArrayList<Employee> retrieveEmployee(Connection connection, Long locationID) throws SQLException {
        PreparedStatement ps = null;
        try {
            String retrieveEmployee = "SELECT * FROM Employees WHERE Employees.locationID = ?;";
            ps = connection.prepareCall(retrieveEmployee);
            ps.setString(1, locationID.toString());

            ResultSet rs = ps.executeQuery();

            if (!rs.isBeforeFirst()) {
                return null;
            }

            ArrayList<Employee> employeeList = new ArrayList<>();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployeeID(rs.getLong("employeeID"));
                employee.setLocationID(rs.getLong("locationID"));
                employee.setEmployeeFirst(rs.getString("employeeFirst"));
                employee.setEmployeeLast(rs.getString("employeeLast"));
                employee.setEmployeeSSN(rs.getString("employeeSSN"));
                employee.setEmployeeType(rs.getString("employeeType"));
                employee.setDateOfHire(rs.getString("dateOfHire"));
                employeeList.add(employee);
            }
            return employeeList;
        } catch (Exception ex) {
            System.err.println("Class: EmployeeDAOImpl. Method: retrieveEmployee(Connection connection, Long locationID)");
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
            return null;
        }
    }

    @Override
    public ArrayList<Employee> retrieveEmployee(Connection connection, Long locationID, Long employeeID) throws SQLException {
        PreparedStatement ps = null;
        try {
            if (!locationID.equals("Any") && !employeeID.equals("")) {
                String retrieveEmployee = "SELECT * FROM Employees WHERE Employees.locationID = ? AND Employees.employeeID = ?;";
                ps = connection.prepareCall(retrieveEmployee);
                ps.setString(1, locationID.toString());
                ps.setString(2, employeeID.toString());
            } else if (locationID.equals("Any") && !employeeID.equals("")) {
                String retrieveEmployee = "SELECT * FROM Employees WHERE  Employees.employeeID = ?;";
                ps = connection.prepareCall(retrieveEmployee);
                ps.setString(1, employeeID.toString());
            }

            ResultSet rs = ps.executeQuery();

            if (!rs.isBeforeFirst()) {
                return null;
            }

            ArrayList<Employee> employeeList = new ArrayList<>();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployeeID(rs.getLong("employeeID"));
                employee.setLocationID(rs.getLong("locationID"));
                employee.setEmployeeFirst(rs.getString("employeeFirst"));
                employee.setEmployeeLast(rs.getString("employeeLast"));
                employee.setEmployeeSSN(rs.getString("employeeSSN"));
                employee.setEmployeeType(rs.getString("employeeType"));
                employee.setDateOfHire(rs.getString("dateOfHire"));
                employeeList.add(employee);
            }   
            return employeeList;

        } catch (Exception ex) {
            System.err.println("Class: EmployeeDAOImpl. Method: retrieveEmployee(Connection connection, Long locationID, Long employeeID)");
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
            return null;
        }
    }

    @Override
    public boolean deleteEmployee(Connection connection, String employeeName) throws SQLException {
        PreparedStatement ps = null;
        String firstName = null;
        String lastName = null;
        String fullName = employeeName;
        String delims = "[ ]+";
        String[] tokens = fullName.split(delims);
        firstName = tokens[1];
        //Should place an if statement here to catch if only one name was sent through
        lastName = tokens[2];
        try {
            String deleteEmployee = "DELETE FROM Employees WHERE Employees.employeeFirst = ? AND Employees.employeeLast = ?;";
            ps = connection.prepareStatement(deleteEmployee);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.executeQuery();
            return true;
        } catch (Exception ex) {
            System.err.println("Class: EmployeeDAOImpl. Method: deleteEmployee");
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
            return false;
        }

    }

    public String getHash(String employeeSSN) {
        System.out.println("HERE");
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(employeeSSN.getBytes());
            byte[] newPass = digest.digest();
            for (byte bytes : newPass) {
                sb.append(String.format("%02x", bytes & 0xff));
            }
            employeeSSN = sb.toString();
        } catch (Exception ex) {
            System.err.println("Class: LoggingIntoSystem Method: jButton1ActionPerformed");
            Logger.getLogger(LoggingIntoSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(employeeSSN);
        return employeeSSN;
    }
}
