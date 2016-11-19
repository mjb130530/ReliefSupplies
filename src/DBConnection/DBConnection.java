/*
 * CS4389
 * Data and Application Security
 * Final Project
 * Project Group 4
 * Group Members: Matt Butler, Johnny Edgett, Abdul Wahab
 */
package DBConnection;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 *
 * @author Matthew
 */
public class DBConnection {
    
    private static DataSource dataSource;
    
    public synchronized static DataSource getDataSource() throws IOException{
        if(dataSource ==null){
            MysqlDataSource mds = null;
            Properties properties = getPropertiesFromClasspath();
            
            String url = properties.getProperty("url");
            if(url == null || url.isEmpty()){
                throw new RuntimeException("url missing");
            }
            String id = properties.getProperty("id");
            if(id == null || id.isEmpty()){
                throw new RuntimeException("id missing");
            }
            String pw = properties.getProperty("password");
            if(pw == null || pw.isEmpty()){
                throw new RuntimeException("password missing");
            }
            
            mds = new MysqlDataSource();
            mds.setURL(url);
            mds.setUser(id);
            mds.setPassword(pw);
            dataSource = mds; 
        }
        return dataSource;
    }
    
    private static final String DBconfigs = "dbconfig.properties";
    
    public static Properties getPropertiesFromClasspath() throws IOException{
        
        Properties properties = new Properties();
        
        InputStream inputStream = DBConnection.class.getResourceAsStream(DBconfigs);
        
        if(inputStream == null){
            throw new RuntimeException("Can't find configuration file");
        }
        
        properties.load(inputStream);
        
        return properties;
        
    }
    
}
