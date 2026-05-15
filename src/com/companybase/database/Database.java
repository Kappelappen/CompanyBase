package com.companybase.database;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class Database {

    private boolean initialized = false;

    public void doCheckDb() {

        File dbFile = getConnectorDatabaseFile();
        File dbDir = dbFile.getParentFile();
        
        if (!dbDir.exists()) {
            if (!dbDir.mkdirs()) {
                System.err.println("Kunde inte skapa katalog: " + dbDir.getAbsolutePath());
                return;
            }
        }

        // Ladda drivrutin
        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("SQLite JDBC-drivrutin laddad!");
        } catch (Exception e) {
            System.err.println("Kunde inte ladda JDBC-drivrutin!");
            e.printStackTrace();
            return;
        }

        // Skapa eller öppna databasen
        Connection conn = null;
        try {
            conn = Connector.getConnection(); // <-- Viktigt: SAMMA DB SOM APPEN ANVÄNDER

            if (dbFile.exists()) {
                System.out.println("Databasen finns redan: " + dbFile.getAbsolutePath());
            } else {
                System.out.println("Databasen skapades: " + dbFile.getAbsolutePath());
            }

            initTables(conn);

        } catch (SQLException e) {
            System.err.println("Fel vid anslutning till databas: " + e.getMessage());
        } finally {
            close(conn);
        }
    }

    /**
     * HÄMTAR Connector:s databasfil så att båda använder samma SQLite-databas.
     */
    private File getConnectorDatabaseFile() {
        try {
            java.lang.reflect.Field dbFileField = Connector.class.getDeclaredField("dbFile");
            dbFileField.setAccessible(true);
            return (File) dbFileField.get(null);
        } catch (Exception e) {
            throw new RuntimeException("Kan inte läsa Connector.dbFile!", e);
        }
    }

    private void initTables(Connection conn) {
        if (initialized) return;

        Statement stmt = null;
        ResultSet rs = null;

        try {
        	
            stmt = conn.createStatement();
            // Kontrollera om customer-tabellen finns

            rs = stmt.executeQuery(
                "SELECT * FROM sqlite_master " + 
                "WHERE type='table' AND name='companies';"
            );

            if (rs.next()) {
            
            	System.out.println("Tabeller finns redan. Hoppar över skapande.");
            
            } else {
            	
                executeSqlFromClasspath(conn, "sql/main/create.sql");
                executeSqlFromClasspath(conn, "sql/main/insert.sql");
                                   
            }

            initialized = true;

        } catch (SQLException e) {
            System.err.println("Fel vid kontroll av tabeller: " + e.getMessage());
        } finally {
            close(rs);
            close(stmt);
        }
    }

    private void executeSqlFromClasspath(Connection conn, String path) {

        InputStream is = getClass().getClassLoader().getResourceAsStream(path);
        if (is == null) {
            System.err.println("SQL-fil saknas: " + path);
            return;
        }

        BufferedReader reader = null;
        Statement stmt = null;

        try {
            reader = new BufferedReader(new InputStreamReader(is));
            stmt = conn.createStatement();

            StringBuilder sql = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {

                line = line.trim();
                if (line.length() == 0) continue;
                if (line.startsWith("--")) continue;

                sql.append(line).append(" ");

                if (line.endsWith(";")) {
                    String finalSql = sql.toString().trim();
                    finalSql = finalSql.substring(0, finalSql.length() - 1);

                    try {
                        stmt.execute(finalSql);
                    } catch (SQLException e) {
                        if (e.getMessage().contains("already exists")) {
                            System.out.println("Tabell finns redan, hoppar: " + finalSql);
                        } else {
                            System.err.println("SQL-fel: " + finalSql);
                            System.err.println(e.getMessage());
                        }
                    }

                    sql.setLength(0);
                }
            }

            System.out.println("SQL från " + path + " exekverades korrekt.");

        } catch (Exception e) {
            System.err.println("Fel vid exekvering av SQL-fil: " + e.getMessage());
        } finally {
            close(stmt);
            // reader och stream stängs av sig själv när programmet exit:ar, men du kan stänga om du vill.
        }
    }

    private void close(Connection c) {
        try { if (c != null) c.close(); } catch (Exception ignored) {}
    }

    private void close(Statement s) {
        try { if (s != null) s.close(); } catch (Exception ignored) {}
    }

    private void close(ResultSet r) {
        try { if (r != null) r.close(); } catch (Exception ignored) {}
    }
}