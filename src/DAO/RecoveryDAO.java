package DAO;

import java.sql.Connection;
import java.sql.SQLException;

public interface RecoveryDAO {

    String getUser(Connection connection, String email, String phone) throws SQLException;
    void resetPassword(Connection connection, String username, String phone, String newpass) throws SQLException;
    
}
