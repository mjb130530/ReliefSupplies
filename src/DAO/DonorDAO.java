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
    boolean deleteDonor(Connection connection, Long locationID, String donorFirst, String donorLast) throws SQLException;
}
