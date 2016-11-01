/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
