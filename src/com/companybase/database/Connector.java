package com.companybase.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.File;

public class Connector {

    private static Connection connection;

    // Dynamisk db-fil, t.ex. under användarens hemkatalog / JCineBase
    private static final File dbFile;
    
    static {
    
    	String userHome = System.getProperty("user.home");
        File dbDir = new File(userHome, "CompanyBase");
        
        if (!dbDir.exists()) dbDir.mkdirs();
        dbFile = new File(dbDir, "CompanyBase.db");
    
    }

    public static Connection getConnection() throws SQLException {
    
    	if (connection == null || connection.isClosed()) {
            String url = "jdbc:sqlite:" + dbFile.getAbsolutePath();
            connection = DriverManager.getConnection(url);
        }
    	
        return connection;
    
    }

    public static File getDatabaseFile() {
        return dbFile;
    }
}