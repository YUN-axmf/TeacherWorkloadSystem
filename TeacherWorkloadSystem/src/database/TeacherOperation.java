package database;

import list.Teacher;
import list.TeacherList;

import java.sql.*;

public class TeacherOperation
{
    private static String driver = "com.mysql.jdbc.Driver",
            url = "jdbc:mysql://127.0.0.1:3306/system",
            databaseUser = "root",
            databasePassword = "947499";

    /**
     * 验证教师登录身份是否正确
     */
    public boolean validate(String number, String password)
    {
        boolean isRight = false;
        String sql = "SELECT password FROM teachers WHERE number='" + number + "'";
        ResultSet rs = SQLOperation.executeQuery(sql);
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

    /**
     * 从数据库获取教师数量
     */
    public int getTeachersCount()
    {
        int count = 0;
        try
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, databaseUser, databasePassword);
            Statement cmd = conn.createStatement();
            String sql = "SELECT COUNT(*) FROM teachers";
            ResultSet rs = cmd.executeQuery(sql);
            if (rs.next())
            {
                count = rs.getInt(1);
            }
            conn.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return count;
    }

    /**
     * 从数据库导入教师信息存储到教师表中
     */
    public TeacherList importInformation()
    {
        TeacherList teacherList = new TeacherList(100);
        teacherList.setCurLen(getTeachersCount());
        try
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, databaseUser, databasePassword);
            Statement cmd = conn.createStatement();
            String getInfoSql = "SELECT * FROM teachers";
            ResultSet getInfoRs = cmd.executeQuery(getInfoSql);
            int index = 0;
            while (getInfoRs.next())
            {
                teacherList.getTeachers()[index].setNumber(getInfoRs.getInt(1));
                teacherList.getTeachers()[index].setPassword(getInfoRs.getString(2));
                teacherList.getTeachers()[index].setName(getInfoRs.getString(3));
                teacherList.getTeachers()[index].setSex(getInfoRs.getString(4));
                teacherList.getTeachers()[index].setProfessionalTitle(getInfoRs.getString(5));
                index++;
            }
            conn.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return teacherList;
    }

    /**
     * 插入教师信息
     */
    public int addTeacher(int number, String password, String name, String sex, String professionalTitle)
    {
        int r = 0;
        String sql = "INSERT INTO teachers VALUES('" + number + "','" + password + "','" + name + "','"
        + sex + "','" + professionalTitle + "')";
        r = SQLOperation.executeUpdate(sql);
        return r;
    }

    /**
     * 删除教师信息
     */
    public int removeTeacher(int number)
    {
        int r = 0;
        String sql = "DELETE FROM teachers WHERE number='" + number + "'";
        removeTeacherTask(number);
        r = SQLOperation.executeUpdate(sql);
        return r;
    }

    /**
     * 删除教师的教学任务
     */
    public int removeTeacherTask(int teacherNum)
    {
        int r = 0;
        String sql = "DELETE FROM tasks WHERE teacherNum='" + teacherNum + "'";
        r = SQLOperation.executeUpdate(sql);
        return r;
    }

    /**
     * 修改教师信息
     */
    public int modifyTeacher(int number, String password, String name, String sex, String professionalTitle)
    {
        int r = 0;
        String sql = "UPDATE teachers SET password='" + password + "',name='" + name +
                "',sex='" + sex + "',professionalTitle='" + professionalTitle + "' WHERE number='" + number + "'";
        r = SQLOperation.executeUpdate(sql);
        return r;
    }

    /**
     * 由教师号获取数据
     */
    public Teacher getTeacher(int number) throws ClassNotFoundException, SQLException
    {
        Teacher teacher = new Teacher();
        try
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, databaseUser, databasePassword);
            Statement cmd = conn.createStatement();
            String Sql = "SELECT * FROM teachers WHERE number='" + number + "'";
            ResultSet rs = cmd.executeQuery(Sql);
            while (rs.next())
            {
                teacher.setNumber(rs.getInt(1));
                teacher.setPassword(rs.getString(2));
                teacher.setName(rs.getString(3));
                teacher.setSex(rs.getString(4));
                teacher.setProfessionalTitle(rs.getString(5));
            }
            conn.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return teacher;
    }

    /**
     * 判断number数据在数据库中是否存在
     */
    public boolean isNumberExisting(int number) throws SQLException
    {
        String sql = "SELECT * FROM teachers WHERE number='" + number + "'";
        ResultSet rs = SQLOperation.executeQuery(sql);
        return rs.next();
    }
}