package com.mrstricty;


import java.sql.*;

public class MySQLHandler {
    Connection connection;

    public MySQLHandler() {
        String url = "RDS INSTANCE NAME";
        String port = "####";
        String dbUser = "######";
        String dbPassword = "######";
        String dbName = "TABLE NAME";

        System.out.println("Checking for JDBC Driver");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("JDBC Driver found");
        }
        catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException encountered");
            return;
        }

        connection = null;
        System.out.println("Initiating server connection");

        try {
            connection = DriverManager.
                    getConnection("jdbc:mysql://" + url + ":"
                            + port + "/"
                            + dbName,
                            dbUser, dbPassword);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception err) {
            System.out.println(err);
        }

        if(connection != null) {
            System.out.println("Server connection established");
        }
        else {
            System.out.println("Failed to make connection to server");
        }
    }

    public ResultSet fullTableQuery() {
        ResultSet rs = null;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String sql = "SELECT * FROM Quotes";
            rs = statement.executeQuery(sql);
        }
        catch (SQLException e) {
            System.err.println("Exception thrown while forming full table query");
        }
        finally {
            try {
                rs.close();
            }
            catch (Exception err) {
                //Ignored
            }
            try {
                statement.close();
            }
            catch (Exception err) {
                //Ignored
            }
            try {
                connection.close();
            }
            catch (Exception err) {
                //Ignored
            }
        }
        return rs;
    }

    public String randomQuoteQuery() {
        ResultSet result = null;
        String resultToString = "";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String sqlQuery = "SELECT * from Quotes order by rand() limit 1";
            result = statement.executeQuery(sqlQuery);
            while(result.next()) {
                resultToString = result.getString("Quote");
            }
        }
        catch (SQLException e) {
            System.err.println("SQLException thrown in random query");
            System.out.println(e);
        }
        finally {
            try {
                result.close();
            }
            catch (Exception err) {
                //Ignored
            }
            try {
                statement.close();
            }
            catch (Exception err) {
                //Ignored
            }
            try {
                connection.close();
            }
            catch (Exception err) {
                //Ignored
            }
        }

        return resultToString;
        }
}
