/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.SQLException;

import Entity.Location;
import java.util.ArrayList;

/**
 *
 * @author Matthew
 */
public interface LocationDAO {
    
    Location createLocation(Connection connection, Location location) throws SQLException;
    
    ArrayList<Location> retrieveLocation(Connection connection, Long locationID, String locationState, String city) throws SQLException;
    
    Location retrieveLocation(Connection connection, String city, String locationState) throws SQLException;
    Location retrieveLocation(Connection connection, Long locationID) throws SQLException;
    
    ArrayList<Location> retrieveAllLocations(Connection connection) throws SQLException;
    
    boolean deleteLocation(Connection connection, String location) throws SQLException;
}
