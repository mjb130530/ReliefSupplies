/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
