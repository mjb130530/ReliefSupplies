/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.SQLException;

import Entity.Donor;
import java.util.ArrayList;

/**
 *
 * @author Matthew
 */
public interface DonorDAO {
    
    Donor createDonor(Connection connection, Donor donor) throws SQLException;
    
    ArrayList<Donor> retrieveDonor(Connection connection, String donorName) throws SQLException;
    ArrayList<Donor> retrieveDonor(Connection connection, String donorFirst, String donorLast) throws SQLException;
    ArrayList<Donor> retrieveDonor(Connection connection, Long locationID, String donorFirst, String donorLast) throws SQLException;
    
    boolean deleteDonor(Connection connection, String donorName) throws SQLException;
    boolean deleteDonor(Connection connection, String donorFirst, String donorLast) throws SQLException;
    
}
