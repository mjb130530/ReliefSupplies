/*
 * CS4389
 * Data and Application Security
 * Final Project
 * Project Group 4
 * Group Members: Matt Butler, Johnny Edgett, Abdul Wahab
 */
package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.MemberLoginDAO;
import Entity.Member;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matthew
 */
public class MemberLoginDAOImpl implements MemberLoginDAO{

    @Override
    public Member retrieveMember(Connection connection, String username, String password) throws SQLException {
        PreparedStatement ps = null;
        try{
            String retrieveDonor = "SELECT * FROM logins WHERE logins.JAMusername = ? AND logins.JAMpassword = ?;";
            ps = connection.prepareCall(retrieveDonor);
            ps.setString(1, username);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            
            if(!rs.next()){
                return null;
            }
            
            Member member = new Member();
            member.setUserID(rs.getLong("JAMuserID"));
            member.setUsername(rs.getString("JAMusername"));
            member.setUserpassword(rs.getString("JAMpassword"));
            member.setPermission(rs.getLong("JAMpermission"));
            
            return member;
        }
        catch(SQLException ex){
            System.err.println("Class: DonorDAOImpl. Method: retrieveMember(Connection connection, String username, String password)");
            Logger.getLogger(MemberLoginDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            if(ps != null && !ps.isClosed()){
                ps.close();
            }
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
            return null;
        }
    }
}
