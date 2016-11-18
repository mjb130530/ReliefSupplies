package DAOImpl;

import DAO.RecoveryDAO;
import Entity.Member;
import WorkFromHere.LoggingIntoSystem;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RecoveryDAOImpl implements RecoveryDAO {

    @Override
    public String getUser(Connection connection, String email, String phone) throws SQLException {
        PreparedStatement ps = null;
        try{
            String retrieveUser = "SELECT JAMusername FROM logins WHERE logins.JAMemail = ? AND logins.JAMphone = ?;";
            ps = connection.prepareCall(retrieveUser);
            ps.setString(1, email);
            ps.setString(2, phone);
            
            ResultSet rs = ps.executeQuery();
            
            if(!rs.next()){
                return null;
            }
            
            return rs.getString("JAMusername");
        }
        catch(SQLException ex){
            System.err.println("Class: RecoveryDAOImpl. Method: getUser(Connection connection, String email, String phone)");
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

    @Override
    public void resetPassword(Connection connection, String username, String phone, String newpass) throws SQLException {
        PreparedStatement ps = null;
        try{
            String setPassword = "UPDATE logins SET logins.JAMpassword = ? WHERE logins.JAMusername = ? AND logins.JAMphone = ?;";
            ps = connection.prepareCall(setPassword);
            ps.setString(1, getHash(newpass));
            ps.setString(2, username);
            ps.setString(3, phone);
            
            ps.executeUpdate();
        }
        catch(SQLException ex){
            System.err.println("Class: RecoveryDAOImpl. Method: resetPassword(Connection connection, String username, String phone, String newpass)");
            Logger.getLogger(MemberLoginDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            if(ps != null && !ps.isClosed()){
                ps.close();
            }
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
        }
    }

    public String getHash(String password){
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(password.getBytes());
            byte[] newPass = digest.digest();
            for(byte bytes : newPass){
                sb.append(String.format("%02x", bytes & 0xff));
            }
            password = sb.toString();
        } catch (Exception ex) {
            System.err.println("Class: LoggingIntoSystem Method: jButton1ActionPerformed");
            Logger.getLogger(LoggingIntoSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return password;
    }
}
