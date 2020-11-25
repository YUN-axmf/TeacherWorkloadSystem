package database;

import java.sql.*;

public class SQLOperation
{
    private static String driver = "com.mysql.jdbc.Driver",
        url = "jdbc:mysql://127.0.0.1:3306/system",
        databaseUser = "root",
        databasePassword = "947499";

    private static Connection conn = null;

    static
    {
        try
        {
            Class.forName(driver);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public static void closeConnection()
    {
        try
        {
            if(conn != null && !conn.isClosed())
                conn.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * 执行UPDATE INSERT DELETE语句
     */
    public static int executeUpdate(String sql)
    {
        int r = 0;
        try
        {
            conn = DriverManager.getConnection(url, databaseUser, databasePassword);
            Statement cmd = conn.createStatement();
            r = cmd.executeUpdate(sql);
            conn.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return r;
    }

    /**
     * 执行SELECT语句
     */
    public static ResultSet executeQuery(String sql)
    {
        ResultSet rs = null;
        try
        {
            conn = DriverManager.getConnection(url, databaseUser, databasePassword);
            Statement cmd = conn.createStatement();
            rs = cmd.executeQuery(sql);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return rs;
    }
}