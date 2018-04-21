package com.cmcc.dpi;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.SQLException;  

public class DBHelper {
	public static final String url = "jdbc:mysql://192.168.205.247:3306/osgiweb";  
    public static final String name = "com.mysql.jdbc.Driver";  
    public static final String user = "root";  
    public static final String password = "123456";  
  
    public Connection conn = null;  
    public PreparedStatement pst = null;
	public Object setString;  
  
    public DBHelper(String sql) {  
        try {  
            Class.forName(name);  
            conn = DriverManager.getConnection(url, user, password);  
            pst = conn.prepareStatement(sql); 
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    public void close() {  
        try {  
            this.conn.close();  
            this.pst.close();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }

	public void execute(String sql) {

		
	}

}  