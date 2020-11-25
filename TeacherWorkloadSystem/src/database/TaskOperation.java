package database;

import list.TeachingTask;

import java.sql.*;

public class TaskOperation
{
    private static String driver = "com.mysql.jdbc.Driver",
            url = "jdbc:mysql://127.0.0.1:3306/system",
            databaseUser = "root",
            databasePassword = "947499";

    /**
     * 从数据库获取教师任务数
     */
    public int getTasksNum(int teacherNum)
    {
        int count = 0;
        try
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, databaseUser, databasePassword);
            Statement cmd = conn.createStatement();
            String sql = "SELECT COUNT(*) FROM tasks WHERE teacherNum= '" + teacherNum + "'";
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
     * 从数据库获取总教学课时
     */
    public double getTotalHour(int teacherNum)
    {
        double hour = 0;
        try
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, databaseUser, databasePassword);
            Statement cmd = conn.createStatement();
            String sql = "SELECT * FROM tasks WHERE teacherNum= '" + teacherNum + "'";
            ResultSet rs = cmd.executeQuery(sql);
            while (rs.next())
            {
                hour += rs.getDouble(7);
            }
            conn.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return hour;
    }

    /**
     * 从数据库计算单个任务教学课时
     */
    public double computeTaskHour(int teacherNum, String course)
    {
        double hour = 0;
        try
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, databaseUser, databasePassword);
            Statement cmd = conn.createStatement();
            String sql = "SELECT * FROM tasks WHERE teacherNum='" + teacherNum +
                    "' AND course='" + course + "'" ;
            ResultSet rs = cmd.executeQuery(sql);
            if (rs.next())
            {
                if (rs.getInt(4) == 2)
                    hour = 1.5 * (rs.getDouble(5) + rs.getDouble(6));
                else if (rs.getInt(4) == 3)
                    hour = 2 * (rs.getDouble(5) + rs.getDouble(6));
                else if (rs.getInt(4) >= 4)
                    hour = 2.5 * (rs.getDouble(5) + rs.getDouble(6));
                String updateSql = "UPDATE tasks SET taskHour='" + hour + "' WHERE teacherNum='" + teacherNum +
                        "' AND course='" + course + "'";
                SQLOperation.executeUpdate(updateSql);
            }
            conn.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return hour;
    }

    /**
     * 从数据库导入教师任务信息
     */
    public TeachingTask[] importTask(int teacherNum)
    {
        TeachingTask[] teachingTasks = new TeachingTask[getTasksNum(teacherNum)];
        for (int i = 0; i < teachingTasks.length; i++)
        {
            teachingTasks[i] = new TeachingTask();
        }
        try
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, databaseUser, databasePassword);
            Statement cmd = conn.createStatement();
            String getInfoSql = "SELECT * FROM tasks WHERE teacherNum= '" + teacherNum + "'";
            ResultSet getInfoRs = cmd.executeQuery(getInfoSql);
            int index = 0;
            while (getInfoRs.next())
            {
                teachingTasks[index].setCourse(getInfoRs.getString(2));
                teachingTasks[index].setClassesInfo(getInfoRs.getString(3));
                teachingTasks[index].setClassNum(getInfoRs.getInt(4));
                teachingTasks[index].setTheoryCourseHour(getInfoRs.getDouble(5));
                teachingTasks[index].setExperimentCourseHour(getInfoRs.getDouble(6));
                teachingTasks[index].setTaskHour(getInfoRs.getDouble(7));
                index++;
            }
            conn.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return teachingTasks;
    }

    /**
     * 插入任务信息
     */
    public int addTask(int teacherNum, String course, String classes, int classNum, double theoryCourseHour, double experimentCourseHour, double taskHour)
    {
        int r = 0;
        String sql = "INSERT INTO tasks VALUES('" + teacherNum + "','" + course + "','"
                + classes + "','" + classNum + "','" + theoryCourseHour + "','"
                + experimentCourseHour + "','" + taskHour + "')";
        r = SQLOperation.executeUpdate(sql);
        return r;
    }

    /**
     * 插入任务信息
     */
    public int addTask(TeachingTask teachingTask)
    {
        int r = 0;
        String sql = "INSERT INTO tasks VALUES('" + teachingTask.getTeacherNum() + "','"
                + teachingTask.getCourse() + "','" + teachingTask.getClassesInfo() +
                "','" + teachingTask.getClassNum() + "','" + teachingTask.getTheoryCourseHour()
                + "','" + teachingTask.getExperimentCourseHour() + "','" +
                teachingTask.getTaskHour() + "')";
        r = SQLOperation.executeUpdate(sql);
        return r;
    }

    /**
     * 删除任务信息
     */
    public int removeTask(int teacherNum, String course)
    {
        int r = 0;
        String sql = "DELETE FROM tasks WHERE teacherNum='" + teacherNum +
                "' AND course='" + course + "'";
        r = SQLOperation.executeUpdate(sql);
        return r;
    }

    /**
     * 修改任务信息
     */
    public int modifyTask(int teacherNum, String course, String classes, int classNum, double theoryCourseHour, double experimentCourseHour, double taskHour)
    {
        int r = 0;
        String sql = "UPDATE tasks SET classes='" + classes + "',classNum='" + classNum +
                "',theoryCourseHour='" + theoryCourseHour + "',experimentCourseHour='" + experimentCourseHour +
                "',taskHour='" + taskHour + "' WHERE teacherNum='" + teacherNum +
                "' AND course='" + course + "'";
        r = SQLOperation.executeUpdate(sql);
        return r;
    }

    /**
     * 由教师号和课程名称获取数据
     */
    public TeachingTask getTask(int teacherNum, String course)
    {
        TeachingTask teacherTask = new TeachingTask();
        try
        {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, databaseUser, databasePassword);
            Statement cmd = conn.createStatement();
            String Sql = "SELECT * FROM tasks WHERE teacherNum='" + teacherNum +
                    "' AND course='" + course + "'" ;
            ResultSet rs = cmd.executeQuery(Sql);
            while (rs.next())
            {
                teacherTask.setTeacherNum(rs.getInt(1));
                teacherTask.setCourse(rs.getString(2));
                teacherTask.setClassesInfo(rs.getString(3));
                teacherTask.setClassNum(rs.getInt(4));
                teacherTask.setTheoryCourseHour(rs.getDouble(5));
                teacherTask.setExperimentCourseHour(rs.getDouble(6));
                teacherTask.setTaskHour(rs.getDouble(7));
            }
            conn.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return teacherTask;
    }

    /**
     * 判断course数据在数据库中是否存在
     */
    public boolean isCourseExisting(int teacherNum, String course) throws SQLException
    {
        String sql = "SELECT * FROM tasks WHERE teacherNum='" + teacherNum +
                "' AND course='" + course + "'" ;
        ResultSet rs = SQLOperation.executeQuery(sql);
        return rs.next();
    }
}