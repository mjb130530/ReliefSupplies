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

public interface RecoveryDAO {

    String getUser(Connection connection, String email, String phone) throws SQLException;
    void resetPassword(Connection connection, String username, String phone, String newpass) throws SQLException;
    
}
