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

import DAO.SuppliesDAO;
import Entity.Supplies;
import java.util.ArrayList;

/**
 *
 * @author Matthew
 */
public class SuppliesDAOImpl implements SuppliesDAO{

    @Override
    public Supplies createSupplies(Connection connection, Supplies supplies) throws SQLException {
        PreparedStatement ps = null;
        try{
            String insertSQL = "INSERT INTO supplies (locationID, supplyName, supplyType, supplyQuantity, supplyDescription) VALUES (?, ?, ?, ?, ?);";
            ps = connection.prepareStatement(insertSQL);
            ps.setString(1, supplies.getLocationID().toString());
            ps.setString(2, supplies.getSupplyName());
            ps.setString(3, supplies.getSupplyType());
            ps.setString(4, supplies.getSupplyQuantity());
            ps.setString(5, supplies.getSupplyDescription());
            
            ps.executeUpdate();
            return supplies;
        }
        catch(SQLException se){
            System.err.println("SQLException: " + se.getMessage());
            System.err.println("SQLState: " + se.getSQLState());
            System.err.println("VendorError: " + se.getErrorCode());
            return null;
        }
        catch(Exception ex){
            System.err.println("Class: SuppliesDAOImpl. Method: createSupplies().");
//            if (ps != null && !ps.isClosed()){
//                ps.close();
//            }
//            if (connection != null && !connection.isClosed()){
//                connection.close();
//            }
            return null;
        }
    }

    @Override
    public ArrayList<Supplies> retrieveSupplies(Connection connection, Long locationID) throws SQLException {
        PreparedStatement ps = null;
        try{
            String retrieveSupplies = "SELECT * FROM Supplies WHERE Supplies.locationID = ?;";
            ps = connection.prepareStatement(retrieveSupplies);
            ps.setString(1, locationID.toString());
            
            ResultSet rs = ps.executeQuery();
            //If no supplies are returned for given location
            if(!rs.isBeforeFirst()){
                return null;
            }
            
            //Increment through results and build list of supplies
            ArrayList<Supplies> supplyList = new ArrayList<>();
            while(rs.next()){
                Supplies supplies = new Supplies();
                supplies.setLocationID(rs.getLong("locationID"));
                supplies.setSupplyName(rs.getString("supplyName"));
                supplies.setSupplyType(rs.getString("supplyType"));
                supplies.setSupplyQuantity(rs.getString("supplyQuantity"));
                supplies.setSupplyDescription(rs.getString("supplyDescription"));
                supplyList.add(supplies);
            }
            
            return supplyList;
            
        }
        catch(Exception ex){
            System.err.print("Class: SuppliesDAOImpl. Method: retrieveSupplies().");
            if (ps != null && !ps.isClosed()){
                ps.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
            return null;
        }
    }

    
    @Override
    public boolean deleteSupplies(Connection connection, Supplies supplies) throws SQLException {
        PreparedStatement ps = null;
        try{
            String deleteSQL = "DELETE FROM Supplies WHERE Supplies.supplyName = ?;";
            ps = connection.prepareStatement(deleteSQL);
            ps.setString(1, supplies.getSupplyName());
            
            //This method might be incorrect because it might still work 
            //if the execution fails.
            //Need to test and check for this.
            ps.executeQuery();
            
            return true;
        }
        catch(Exception ex){
            System.err.print("Class: SuppliesDAOImpl. Method: deleteSupplies.");
            if (ps != null && !ps.isClosed()){
                ps.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
            return false;
        }
    }
        
}
