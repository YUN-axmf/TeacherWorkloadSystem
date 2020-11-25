package database;

import list.AdminAccount;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminOperation
{
    /**
     * 验证管理员登录身份是否正确
     */
    public boolean validate(String id, String password)
    {
        boolean isRight = false;
        String sql ="SELECT password FROM admins WHERE id='" + id + "'";
        ResultSet rs= SQLOperation.executeQuery(sql);
        try
        {
            if(rs != null && rs.next())
            {
                String databasePassword = rs.getString(1);
                if(databasePassword.equals(password))
                {
                    isRight = true;
                }
            }
            SQLOperation.closeConnection();
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return isRight;
    }

    public int addAdmin(String id, String password)
     {
        int r = 0;
        String sql="INSERT INTO admins values('" + id + "','" + password + "')";
        r = SQLOperation.executeUpdate(sql);
        return r;
    }

    /**
     * 判断id数据在数据库中是否存在
     */
    public boolean isIdExisting(String id) throws SQLException
    {
        String sql = "SELECT * FROM admins WHERE id='" + id + "'";
        ResultSet rs = SQLOperation.executeQuery(sql);
        return rs.next();
    }
}