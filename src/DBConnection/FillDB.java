/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConnection;

import DAO.DonorDAO;
import DAO.EmployeeDAO;
import DAO.LocationDAO;
import DAO.SuppliesDAO;
import DAOImpl.DonorDAOImpl;
import DAOImpl.EmployeeDAOImpl;
import DAOImpl.LocationDAOImpl;
import DAOImpl.SuppliesDAOImpl;
import Entity.Donor;
import Entity.Employee;
import Entity.Location;
import Entity.Supplies;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import javax.sql.DataSource;

/**
 *
 * @author Matthew
 */
public class FillDB {
    
    private File donorsFile;
    private File employeesFile;
    private File locationsFile;
    private File suppliesFile;
    
    private void initialize(){
        donorsFile = new File("CSVData/donors.csv");
        employeesFile = new File("CSVData/employees.csv");
        locationsFile = new File("CSVData/locations.csv");
        suppliesFile = new File("CSVData/supplies.csv");
    }
    
    public static void main(String[] args){
        try{
            DataSource dataSource = DBConnection.getDataSource();
            Connection connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            
            FillDB fillDB = new FillDB();
            fillDB.initialize();
            
            Map<Long, Location> locationMap = fillDB.buildLocation();
            fillDB.insertLocations(connection, locationMap);
            
            Map<Long, Supplies> suppliesMap = fillDB.buildSupplies();
            fillDB.insertSupplies(connection, suppliesMap);
            
            Map<Long, Donor> donorMap = fillDB.buildDonor();
            fillDB.insertDonors(connection, donorMap);
            
            Map<Long, Employee> employeeMap = fillDB.buildEmployee();
            fillDB.insertEmployees(connection, employeeMap);
            
            connection.commit();
            System.out.println("Finished filling tables");
        }
        catch(Exception ex){
            ex.printStackTrace();
            System.err.println("Failed to fill tables");
        }
    }

    //-----------------------------------------------
    //---------------Build Methods------------------
    //-----------------------------------------------
    private Map<Long, Donor> buildDonor() throws Exception{
        Map<Long, Donor> donorMap = new HashMap<>();
        FileReader fr = new FileReader(donorsFile);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while((line = br.readLine()) != null){
            Object item[] = parseDonors(line);
            donorMap.put((Long) item[0], (Donor) item[1]);
        }
        br.close();
        return donorMap;
    }
    
    private Map<Long, Employee> buildEmployee() throws Exception{
        Map<Long, Employee> employeenMap = new HashMap<>();
        FileReader fr = new FileReader(employeesFile);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while((line = br.readLine()) != null){
            Object item[] = parseEmployees(line);
            employeenMap.put((Long) item[0], (Employee) item[1]);
        }
        br.close();
        return employeenMap;
    }
    
    private Map<Long, Location> buildLocation() throws Exception{
        Map<Long, Location> locationMap = new HashMap<>();
        FileReader fr = new FileReader(locationsFile);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while((line = br.readLine()) != null){
            Object item[] = parseLocations(line);
            locationMap.put((Long) item[0], (Location) item[1]);
        }
        br.close();
        return locationMap;
    }
    
    private Map<Long, Supplies> buildSupplies() throws Exception{
        Map<Long, Supplies> suppliesMap = new HashMap<>();
        FileReader fr = new FileReader(suppliesFile);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while((line = br.readLine()) != null){
            Object item[] = parseSupplies(line);
            suppliesMap.put((Long) item[0], (Supplies) item[1]);
        }
        br.close();
        return suppliesMap;
    }
    
    //-----------------------------------------------
    //---------------Insert Methods------------------
    //-----------------------------------------------
    private void insertDonors(Connection connection, Map<Long, Donor> donorMap) throws Exception{
        DonorDAO donorDAO = new DonorDAOImpl();
        for(Donor donor: donorMap.values()){
            donorDAO.createDonor(connection, donor);
        }
    }
    
    private void insertEmployees(Connection connection, Map<Long, Employee> employeeMap) throws Exception{
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        for(Employee employee: employeeMap.values()){
            employeeDAO.createEmployee(connection, employee);
        }
    }
    
    private void insertLocations(Connection connection, Map<Long, Location> locationMap) throws Exception{
        System.out.println("---------------------got into insertLocations()-------------");
        LocationDAO locationDAO = new LocationDAOImpl();
        for(Location location: locationMap.values()){
            locationDAO.createLocation(connection, location);
        }
    }
    
    private void insertSupplies(Connection connection, Map<Long, Supplies> suppliesMap) throws Exception{
        SuppliesDAO suppliesDAO = new SuppliesDAOImpl();
        for(Supplies supplies: suppliesMap.values()){
            suppliesDAO.createSupplies(connection, supplies);
        }
    }
    
    
    //-----------------------------------------------
    //----------------Parse Methods------------------
    //-----------------------------------------------
    private Object[] parseDonors(String line){
        StringTokenizer st = new StringTokenizer(line, ",");
        Donor donor = new Donor();
        Long id = Long.parseLong(st.nextToken());
        //donor.setLocationID(Long.valueOf(st.nextToken()));
        donor.setLocationID(Long.parseLong(st.nextToken()));
        donor.setDonorFirst(st.nextToken());
        donor.setDonorLast(st.nextToken());
        donor.setDonationDate(st.nextToken());
        donor.setDonationType(st.nextToken());
        donor.setDonationValue(st.nextToken());
        donor.setDonationDescription(st.nextToken());
        Object[] result = {id, donor};
        return result;
    }
    
    private Object[] parseEmployees(String line){
        StringTokenizer st = new StringTokenizer(line, ",");
        Employee employee = new Employee();
        Long id = Long.parseLong(st.nextToken());
        //employee.setLocationID(Long.valueOf(st.nextToken()));
        employee.setLocationID(Long.parseLong(st.nextToken()));
        employee.setEmployeeFirst(st.nextToken());
        employee.setEmployeeLast(st.nextToken());
        employee.setEmployeeSSN(st.nextToken());
        employee.setEmployeeType(st.nextToken());
        employee.setDateOfHire(st.nextToken());
        Object[] result = {id, employee};
        return result;
    }
        
    private Object[] parseLocations(String line){
        StringTokenizer st = new StringTokenizer(line, ",");
        Location location = new Location();
        Long id = Long.parseLong(st.nextToken());
        location.setLocationID(Long.parseLong(st.nextToken()));
        location.setLocationState(st.nextToken());
        location.setCounty(st.nextToken());
        location.setCity(st.nextToken());
        location.setStreet(st.nextToken());
        location.setZipcode(st.nextToken());
        location.setCityTaxes(Long.valueOf(st.nextToken()));
        location.setStateTaxes(Long.valueOf(st.nextToken()));
        location.setRent(Long.valueOf(st.nextToken()));
        location.setElectricity(Long.valueOf(st.nextToken()));
        location.setWater(Long.valueOf(st.nextToken()));
        Object[] result = {id, location};
        return result;
    }
            
    private Object[] parseSupplies(String line){
        StringTokenizer st = new StringTokenizer(line, ",");
        Supplies supplies = new Supplies();
        Long id = Long.parseLong(st.nextToken());
        supplies.setLocationID(Long.parseLong(st.nextToken()));
        supplies.setSupplyName(st.nextToken());
        supplies.setSupplyType(st.nextToken());
        supplies.setSupplyQuantity(st.nextToken());
        supplies.setSupplyDescription(st.nextToken());
        Object[] result = {id, supplies};
        return result;
    }
    
}
