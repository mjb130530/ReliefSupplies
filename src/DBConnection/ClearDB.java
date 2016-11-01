/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.sql.DataSource;

/**
 *
 * @author Matthew
 */
public class ClearDB {
    public static void main(String[] args){
       try{
           DataSource dataSource = DBConnection.getDataSource();
           Connection connection = dataSource.getConnection();
           connection.setAutoCommit(false);
           
           PreparedStatement ps1 = null;
           PreparedStatement ps2 = null;
           PreparedStatement ps3 = null;
           PreparedStatement ps4 = null;
           
           String deleteSupplies = "DELETE FROM supplies;";
           String deleteEmployees = "DELETE FROM employees;";
           String deleteDonors = "DELETE FROM donors;";
           String deleteLocations = "DELETE FROM locations;";
           
           ps1 = connection.prepareStatement(deleteSupplies);
           ps2 = connection.prepareStatement(deleteEmployees);
           ps3 = connection.prepareStatement(deleteDonors);
           ps4 = connection.prepareStatement(deleteLocations);
           
           ps1.executeUpdate();
           ps2.executeUpdate();
           ps3.executeUpdate();
           ps4.executeUpdate();
           
           connection.commit();
           System.out.println("Tables are now empty");
       }
       catch(Exception ex){
           System.err.println("Tables were NOT emptied");
           ex.printStackTrace();
       }
       
    }
}
