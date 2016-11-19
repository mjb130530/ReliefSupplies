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

import Entity.Supplies;
import java.util.ArrayList;

/**
 *
 * @author Matthew
 */
public interface SuppliesDAO {
    
    Supplies createSupplies(Connection connection, Supplies supplies) throws SQLException;
    
    ArrayList<Supplies> retrieveSupplies(Connection connection, Long locationID) throws SQLException;
    
    boolean deleteSupplies(Connection connection, Supplies supplies) throws SQLException;
    
    //Probably need an editSupplies
    
}
