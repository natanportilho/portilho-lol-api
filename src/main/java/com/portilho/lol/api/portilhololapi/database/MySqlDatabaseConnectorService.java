package com.portilho.lol.api.portilhololapi.database;

import java.sql.*;

public class MySqlDatabaseConnectorService implements DatabaseConnectorService
{
    private static final String MY_SQL_URL = "jdbc:mysql://localhost/lol_api?user=root&password=hybris";

    @Override
    public void connect()
    {
        try (Connection connect = DriverManager.getConnection(MY_SQL_URL);
             Statement stmt = connect.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM lol_api.`match`"))
        {
            while (rs.next())
            {
                System.out.println(rs.getString(1));
            }
        } catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }
}
