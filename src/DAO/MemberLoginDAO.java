/*
 * CS4389
 * Data and Application Security
 * Final Project
 * Project Group 4
 * Group Members: Matt Butler, Johnny Edgett, Abdul Wahab
 */
package DAO;

import Entity.Member;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Matthew
 */
public interface MemberLoginDAO {
    
    Member retrieveMember(Connection connection, String username, String password) throws SQLException;
    
}
