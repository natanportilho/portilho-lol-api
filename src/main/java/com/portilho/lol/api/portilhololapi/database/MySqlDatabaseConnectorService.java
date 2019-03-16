package com.portilho.lol.api.portilhololapi.database;

import java.sql.*;

public class MySqlDatabaseConnectorService implements DatabaseConnectorService
{
    @Override
    public void connect()
    {

        try
        {
            Connection connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/lol_api?"
                            + "user=root&password=hybris");



            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM lol_api.`match`");

            while(rs.next())
            {
                System.out.println(rs.getString(1));
            }

            rs.close();
            stmt.close();
            connect.close();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
