/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;

/**
 *
 * @author Matthew
 */
public class Employee {
    private Long employeeID;
    private Long locationID;
    private String employeeFirst;
    private String employeeLast;
    private String employeeSSN;
    private String employeeType;
    private String dateOfHire;

    
    public Long getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Long employeeID) {
        this.employeeID = employeeID;
    }
    
    public Long getLocationID() {
        return locationID;
    }

    public void setLocationID(Long locationID) {
        this.locationID = locationID;
    }

    public String getEmployeeFirst() {
        return employeeFirst;
    }

    public void setEmployeeFirst(String employeeFirst) {
        this.employeeFirst = employeeFirst;
    }

    public String getEmployeeLast() {
        return employeeLast;
    }

    public void setEmployeeLast(String employeeLast) {
        this.employeeLast = employeeLast;
    }

    public String getEmployeeSSN() {
        return employeeSSN;
    }

    public void setEmployeeSSN(String employeeSSN) {
        this.employeeSSN = employeeSSN;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public String getDateOfHire() {
        return dateOfHire;
    }

    public void setDateOfHire(String dateOfHire) {
        this.dateOfHire = dateOfHire;
    }
    
    
    
}
