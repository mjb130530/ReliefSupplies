/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.LocationDAO;
import Entity.Location;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Matthew
 */
public class LocationDAOImpl implements LocationDAO{

    @Override
    public Location createLocation(Connection connection, Location location) throws SQLException {
        PreparedStatement ps = null;
        try{
            String insertSQL = "insert into locations "
                    + "(locationID, locationState, county, city, street, zipcode, cityTaxes, stateTaxes, rent, electricity, water)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            ps = connection.prepareStatement(insertSQL);
            ps.setString(1, location.getLocationID().toString());
            ps.setString(2, location.getLocationState());
            ps.setString(3, location.getCounty());
            ps.setString(4, location.getCity());
            ps.setString(5, location.getStreet());
            ps.setString(6, location.getZipcode());
            ps.setString(7, location.getCityTaxes().toString());
            ps.setString(8, location.getStateTaxes().toString());
            ps.setString(9, location.getRent().toString());
            ps.setString(10, location.getElectricity().toString());
            ps.setString(11, location.getWater().toString());
            
            ps.executeUpdate();
//            
//            ResultSet fullLocation = ps.getGeneratedKeys();
//            fullLocation.next();
//            int genKey = fullLocation.getInt(1);
//            location.setLocationID((long) genKey);
//            
            return location;
        }
        catch(Exception ex){
            System.err.println("Class: LocationDAOImpl. Method: createLocation");
//            if(ps != null && !ps.isClosed()){
//                ps.close();
//            }
//            if(connection != null && !connection.isClosed()){
//                connection.close();
//            }
            return null;
        }
    }

    @Override
    //May need to create another method if all locations need returned
    //Or can change this to ArrayList<Location>
    public ArrayList<Location> retrieveLocation(Connection connection, Long locationID, String locationState, String city) throws SQLException {
        PreparedStatement ps = null;
        Long locateID = locationID;
        String locateState = locationState;
        String locateCity = city;
        
        try{
            //If a locationID is given
            if(locateID != null && locateState == null && locateCity == null){
                String retrieveLocation = "SELECT * FROM Locations WHERE Locations.locationID = ?;";
                ps = connection.prepareStatement(retrieveLocation);
                ps.setString(1, locationID.toString());
            }
            //If a state is given
            else if(locateID == null && locateState != null && locateCity == null){
                String retrieveLocation = "SELECT * FROM Locations WHERE Locations.locationState = ?;";
                ps = connection.prepareStatement(retrieveLocation);
                ps.setString(1, locationState.toString());
            }
            //If a city is given
            else if(locateID == null && locateState == null && locateCity != null){
                String retrieveLocation = "SELECT * FROM Locations WHERE Locations.city = ?;";
                ps = connection.prepareStatement(retrieveLocation);
                ps.setString(1, city.toString());
            }
            //Returns all.  If no locationID or state or city is given
            //Not sure about this one.  The security of preparedstatements is a nonfactor.
            else if(locateID == null && locateState == null && locateCity == null){
                String retrieveLocation = "SELECT * FROM Locations;";
                ps = connection.prepareStatement(retrieveLocation);
            }

            ResultSet rs = ps.executeQuery();
            //Is this needed? Is it not caught by the catch statement
            if(!rs.next()){
                return null;
            }
            
            ArrayList<Location> locationList = new ArrayList<>();
            while(rs.next()){
                Location location = new Location();
                location.setLocationID(Long.valueOf(rs.getString("locationID")));
                location.setLocationState(rs.getString("locationState"));
                location.setCounty(rs.getString("county"));
                location.setCity(rs.getString("city"));
                location.setStreet(rs.getString("street"));
                location.setZipcode(rs.getString("zipcode"));
                location.setCityTaxes(Long.valueOf(rs.getString("cityTaxes")));
                location.setStateTaxes(Long.valueOf(rs.getString("statesTaxes")));
                location.setRent(Long.valueOf(rs.getString("rent")));
                location.setElectricity(Long.valueOf(rs.getString("electricity")));
                location.setWater(Long.valueOf(rs.getString("water")));
                locationList.add(location);
            }

            return locationList;
        }
        catch(Exception ex){
            System.err.println("Class: LocationDAOImpl. Method: retrieveLocation");
            if(ps != null && !ps.isClosed()){
                ps.close();
            }
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
            return null;
        }
    }


    //This needs both city and state
    //Using this 
    @Override
    //May need to create another method if all locations need returned
    //Or can change this to ArrayList<Location>
    public Location retrieveLocation(Connection connection, String city, String locationState) throws SQLException {
        PreparedStatement ps = null;
        try{
            String retrieveLocation = "SELECT * FROM Locations WHERE Locations.city = ? AND Locations.locationState = ?;";
            ps = connection.prepareStatement(retrieveLocation);
            ps.setString(1, city);
            ps.setString(2, locationState);
            
            ResultSet rs = ps.executeQuery();
            
            //Is this needed? Is it not caught by the catch statement
            if(!rs.next()){
                return null;
            }
            Location location = new Location();
            location.setLocationID(Long.valueOf(rs.getString("locationID")));
            location.setLocationState(rs.getString("locationState"));
            location.setCounty(rs.getString("county"));
            location.setCity(rs.getString("city"));
            location.setStreet(rs.getString("street"));
            location.setZipcode(rs.getString("zipcode"));
            location.setCityTaxes(Long.valueOf(rs.getString("cityTaxes")));
            location.setStateTaxes(Long.valueOf(rs.getString("statesTaxes")));
            location.setRent(Long.valueOf(rs.getString("rent")));
            location.setElectricity(Long.valueOf(rs.getString("electricity")));
            location.setWater(Long.valueOf(rs.getString("water")));
            return location;
        }
        catch(Exception ex){
            System.err.println("Class: LocationDAOImpl. Method: retrieveLocation");
            if(ps != null && !ps.isClosed()){
                ps.close();
            }
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
            return null;
        }
    }
    
    @Override
    //May need to create another method if all locations need returned
    //Or can change this to ArrayList<Location>
    public Location retrieveLocation(Connection connection, Long locationID) throws SQLException {
        System.err.println("Made it here:" + locationID + ".");
        PreparedStatement ps = null;
        try{
            String retrieveLocation = "SELECT * FROM locations WHERE locations.locationID = ?;";
            ps = connection.prepareStatement(retrieveLocation);
            ps.setString(1, locationID.toString());
            
            ResultSet rs = ps.executeQuery();
            
            //Is this needed? Is it not caught by the catch statement
            if(!rs.next()){
                System.out.println("beeboopbeboop");
                return null;
            }
            System.err.println("is it making it here?");
            Location location = new Location();
            //location.setLocationID(Long.valueOf(rs.getString("locationID")));
            location.setLocationID(rs.getLong("locationID"));
            location.setLocationState(rs.getString("locationState"));
            location.setCounty(rs.getString("county"));
            location.setCity(rs.getString("city"));
            location.setStreet(rs.getString("street"));
            location.setZipcode(rs.getString("zipcode"));
            location.setCityTaxes(rs.getLong("cityTaxes"));
            location.setStateTaxes(rs.getLong("statesTaxes"));
            location.setRent(rs.getLong("rent"));
            location.setElectricity(rs.getLong("electricity"));
            location.setWater(rs.getLong("water"));
            return location;
        }
        catch(Exception ex){
            System.err.println("Class: LocationDAOImpl. Method: retrieveLocation(Connection connection, Long locationID)");
            if(ps != null && !ps.isClosed()){
                ps.close();
            }
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
            return null;
        }
    }
    
    @Override
    //This is to get all the locations to be used in the dropdown boxes
    public ArrayList<Location> retrieveAllLocations(Connection connection) throws SQLException {
        PreparedStatement ps = null;

        try{
            String retrieveLocation = "SELECT * FROM Locations;";
            ps = connection.prepareStatement(retrieveLocation);
            
            ResultSet rs = ps.executeQuery();
            //Is this needed? Is it not caught by the catch statement
            if(!rs.next()){
                return null;
            }
            
            ArrayList<Location> locationList = new ArrayList<>();
            while(rs.next()){
                Location location = new Location();
                location.setLocationID(Long.valueOf(rs.getString("locationID")));
                location.setLocationState(rs.getString("locationState"));
                location.setCounty(rs.getString("county"));
                location.setCity(rs.getString("city"));
                location.setStreet(rs.getString("street"));
                location.setZipcode(rs.getString("zipcode"));
                location.setCityTaxes(Long.valueOf(rs.getString("cityTaxes")));
                location.setStateTaxes(Long.valueOf(rs.getString("stateTaxes")));
                location.setRent(Long.valueOf(rs.getString("rent")));
                location.setElectricity(Long.valueOf(rs.getString("electricity")));
                location.setWater(Long.valueOf(rs.getString("water")));
                locationList.add(location);
            }

            return locationList;
        }
        catch(Exception ex){
            System.err.println("Class: LocationDAOImpl. Method: retrieveLocation");
            if(ps != null && !ps.isClosed()){
                ps.close();
            }
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
            return null;
        }
    }
    
    
    @Override
    //For now to delete a location is based off of city
    //Can add other ways to delete locations.
    public boolean deleteLocation(Connection connection, String locationCity) throws SQLException {
        PreparedStatement ps = null;
        try{
            String deleteSQL = "DELETE FROM Locations WHERE Locations.city = ?;";
            ps = connection.prepareStatement(deleteSQL);
            ps.setString(1, locationCity);
            ps.executeQuery();
            
            return true;
        }
        catch(Exception ex){
            System.err.println("Class: LocationDAOImpl. Method: deleteLocation");
            if(ps != null && !ps.isClosed()){
                ps.close();
            }
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
            return false;
        }        
    }
    
}
