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

import DAO.DonorDAO;
import Entity.Donor;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Matthew
 */
public class DonorDAOImpl implements DonorDAO{

    @Override
    public Donor createDonor(Connection connection, Donor donor) throws SQLException {
        PreparedStatement ps = null;
        try{
            String insertSQL = "INSERT INTO donors (locationID, donorFirst, donorLast, donationDate, donationType, donationValue, donationDescription) VALUES (?, ?, ?, ?, ?, ?, ?);";
            ps = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, donor.getLocationID().toString());
            ps.setString(2, donor.getDonorFirst());
            ps.setString(3, donor.getDonorLast());
            ps.setString(4, donor.getDonationDate());
            ps.setString(5, donor.getDonationType());
            ps.setString(6, donor.getDonationValue());
            ps.setString(7, donor.getDonationDescription());
            
            ps.executeUpdate();
            
            ResultSet fullDonor = ps.getGeneratedKeys();
            fullDonor.next();
            int genKey = fullDonor.getInt(1);
            donor.setDonorID((long) genKey);
            
            return donor;
        }
        catch(Exception ex){
            System.err.println("Class: DonorDAOImpl. Method: createDonor");
            //ex.printStackTrace();
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
    //Might change this to an ArrayList<Donor>
    //To account for multiple donations made by same person
    //also when returning donations matching a name without a specific person.
    public ArrayList<Donor> retrieveDonor(Connection connection, String donorName) throws SQLException {
        PreparedStatement ps = null;
        String firstName = null;
        String lastName = null;
        String fullName = donorName;
        String delims = "[ ]+";
        String[] tokens = fullName.split(delims);
        firstName = tokens[1];
        //Should place an if statement here to catch if only one name was sent through
        //Would be a big if else statement with the current try catch being used only if neither of the names are null
        lastName = tokens[2];
        try{
            String retrieveDonor = "SELECT * FROM Donors WHERE Donors.donorFirst = ? AND Donors.donorLast = ?;";
            ps = connection.prepareCall(retrieveDonor);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            
            ResultSet rs = ps.executeQuery();
            
            if(!rs.isBeforeFirst()){
                return null;
            }
            
            ArrayList<Donor> donorList = new ArrayList<>();
            while(rs.next()){
                Donor donor = new Donor();
                donor.setDonorID(rs.getLong("donorID"));
                donor.setLocationID(rs.getLong("locationID"));
                donor.setDonorFirst(rs.getString("donorFirst"));
                donor.setDonorLast(rs.getString("donorLast"));
                donor.setDonationDate(rs.getString("donationDate"));
                donor.setDonationType(rs.getString("donationType"));
                donor.setDonationDescription(rs.getString("donationDescription"));
                donorList.add(donor);
            }
            return donorList;
        }
        catch(Exception ex){
            System.err.println("Class: DonorDAOImpl. Method: retrieveDonor(Connection connection, String donorName)");
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
    //Might change this to an ArrayList<Donor>
    //To account for multiple donations made by same person
    //also when returning donations matching a name without a specific person.
    public ArrayList<Donor> retrieveDonor(Connection connection, String donorFirst, String donorLast) throws SQLException {
        PreparedStatement ps = null;
        try{
            if(!donorFirst.equals("") && !donorLast.equals("")){
                String retrieveDonor = "SELECT * FROM Donors WHERE Donors.donorFirst = ? AND Donors.donorLast = ?;";
                ps = connection.prepareCall(retrieveDonor);
                ps.setString(1, donorFirst);
                ps.setString(2, donorLast);
            }
            if(!donorFirst.equals("") && donorLast.equals("")){
                String retrieveDonor = "SELECT * FROM Donors WHERE Donors.donorFirst = ?;";
                ps = connection.prepareCall(retrieveDonor);
                ps.setString(1, donorFirst);
            }
            if(donorFirst.equals("") && !donorLast.equals("")){
                String retrieveDonor = "SELECT * FROM Donors WHERE Donors.donorLast = ?;";
                ps = connection.prepareCall(retrieveDonor);
                ps.setString(1, donorLast);
            }
            if(donorFirst.equals("") && donorLast.equals("")){
                String retrieveDonor = "SELECT * FROM Donors;";
                ps = connection.prepareCall(retrieveDonor);
            }
            
            ResultSet rs = ps.executeQuery();
            
            if(!rs.isBeforeFirst()){
                return null;
            }
            
            ArrayList<Donor> donorList = new ArrayList<>();
            while(rs.next()){
                Donor donor = new Donor();
                donor.setDonorID(rs.getLong("donorID"));
                donor.setLocationID(rs.getLong("locationID"));
                donor.setDonorFirst(rs.getString("donorFirst"));
                donor.setDonorLast(rs.getString("donorLast"));
                donor.setDonationDate(rs.getString("donationDate"));
                donor.setDonationType(rs.getString("donationType"));
                donor.setDonationValue(rs.getString("donationValue"));
                donor.setDonationDescription(rs.getString("donationDescription"));
                donorList.add(donor);
            }
            return donorList;
        }
        catch(Exception ex){
            System.err.println("Class: DonorDAOImpl. Method: retrieveDonor(Connection connection, String donorFirst, String donorLast)");
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
    //Might change this to an ArrayList<Donor>
    //To account for multiple donations made by same person
    //also when returning donations matching a name without a specific person.
    public ArrayList<Donor> retrieveDonor(Connection connection, Long locationID, String donorFirst, String donorLast) throws SQLException {
        PreparedStatement ps = null;
//        if(locationID.equals("0"))
//            locationID = "";
        try{
            if(locationID.equals("") && !donorFirst.equals("") && !donorLast.equals("")){
                String retrieveDonor = "SELECT * FROM Donors WHERE Donors.donorFirst = ? AND Donors.donorLast = ?;";
                ps = connection.prepareCall(retrieveDonor);
                ps.setString(1, donorFirst);
                ps.setString(2, donorLast);
            }
            if(locationID.equals("") && !donorFirst.equals("") && donorLast.equals("")){
                String retrieveDonor = "SELECT * FROM Donors WHERE Donors.donorFirst = ?;";
                ps = connection.prepareCall(retrieveDonor);
                ps.setString(1, donorFirst);
            }
            if(locationID.equals("") && donorFirst.equals("") && !donorLast.equals("")){
                String retrieveDonor = "SELECT * FROM Donors WHERE Donors.donorLast = ?;";
                ps = connection.prepareCall(retrieveDonor);
                ps.setString(1, donorLast);
            }
            if(locationID.equals("") && donorFirst.equals("") && donorLast.equals("")){
                String retrieveDonor = "SELECT * FROM Donors;";
                ps = connection.prepareCall(retrieveDonor);
            }
            if(!locationID.equals("") && !donorFirst.equals("") && !donorLast.equals("")){
                String retrieveDonor = "SELECT * FROM Donors WHERE Donors.locationID = ? AND Donors.donorFirst = ? AND Donors.donorLast = ?;";
                ps = connection.prepareCall(retrieveDonor);
                ps.setString(1, locationID.toString());
                ps.setString(2, donorFirst);
                ps.setString(3, donorLast);
            }
            if(!locationID.equals("") && !donorFirst.equals("") && donorLast.equals("")){
                String retrieveDonor = "SELECT * FROM Donors WHERE Donors.locationID = ? AND Donors.donorFirst = ?;";
                ps = connection.prepareCall(retrieveDonor);
                ps.setString(1, locationID.toString());
                ps.setString(2, donorFirst);
            }
            if(!locationID.equals("") && donorFirst.equals("") && !donorLast.equals("")){
                String retrieveDonor = "SELECT * FROM Donors WHERE Donors.locationID = ? AND Donors.donorLast = ?;";
                ps = connection.prepareCall(retrieveDonor);
                ps.setString(1, locationID.toString());
                ps.setString(2, donorLast);
            }
            if(!locationID.equals("") && donorFirst.equals("") && donorLast.equals("")){
                String retrieveDonor = "SELECT * FROM Donors WHERE Donors.locationID = ?;";
                ps = connection.prepareCall(retrieveDonor);
                ps.setString(1, locationID.toString());
            }
            
            ResultSet rs = ps.executeQuery();
            
            if(!rs.isBeforeFirst()){
                return null;
            }
            
            ArrayList<Donor> donorList = new ArrayList<>();
            while(rs.next()){
                Donor donor = new Donor();
                donor.setDonorID(rs.getLong("donorID"));
                donor.setLocationID(rs.getLong("locationID"));
                donor.setDonorFirst(rs.getString("donorFirst"));
                donor.setDonorLast(rs.getString("donorLast"));
                donor.setDonationDate(rs.getString("donationDate"));
                donor.setDonationType(rs.getString("donationType"));
                donor.setDonationType(rs.getString("donationValue"));
                donor.setDonationDescription(rs.getString("donationDescription"));
                donorList.add(donor);
            }
            return donorList;
        }
        catch(Exception ex){
            System.err.println("Class: DonorDAOImpl. Method: retrieveDonor(Connection connection, String donorFirst, String donorLast)");
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
    public boolean deleteDonor(Connection connection, String donorName) throws SQLException {
        PreparedStatement ps = null;
        String firstName = null;
        String lastName = null;
        String fullName = donorName;
        String delims = "[ ]+";
        String[] tokens = fullName.split(delims);
        firstName = tokens[1];
        //Should place an if statement here to catch if only one name was sent through
        lastName = tokens[2];
        try{
            String deleteDonor = "DELETE FROM Donor WHERE Donor.donorFirst = ? AND Donor.donorLast = ?;";
            ps = connection.prepareStatement(deleteDonor);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.executeQuery();
            return true;
            
        }
        catch(Exception ex){
            System.err.println("Class: DonorDAOImpl. Method: deleteDonor(Connection connection, String donorName)");
            if(ps != null && !ps.isClosed()){
                ps.close();
            }
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
            return false;
        }
    }
    
    
    @Override
    public boolean deleteDonor(Connection connection, String donorFirst, String donorLast) throws SQLException {
        PreparedStatement ps = null;
        try{
            String deleteDonor = "DELETE FROM Donor WHERE Donor.donorFirst = ? AND Donor.donorLast = ?;";
            ps = connection.prepareStatement(deleteDonor);
            ps.setString(1, donorFirst);
            ps.setString(2, donorLast);
            ps.executeQuery();
            return true;
        }
        catch(Exception ex){
            System.err.println("Class: DonorDAOImpl. Method: deleteDonor(Connection connection, String donorFirst, String donorLast)");
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
