/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rekah4
 */
public class Database {
    String port;
    String driver;
    String host;
    String username;
    String password;
    String database;
    
    Connection conn;
    Statement st;
    
    public Database(String host,String port,String database,String username,String password){
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
        
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String db_url = "jdbc:mysql://"+host+":"+port+"/"+database;
        
        try {
            conn = DriverManager.getConnection(db_url,username,password);
            st = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public Statement getStatement(){
        return st;
    }
    public void Close(){
        try {
            st.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
