package list;

import database.TaskOperation;

import java.util.Arrays;

/**
 * 教学任务类
 *
 * 成员变量：
 * 任教课程、授课班级、授课班级数、理论课时、实验课时、任务总课时、任务教师教师号
 *
 * 方法：
 * 计算任务总课时
 */
public class TeachingTask
{
    // 测试方法，仅供调试代码使用
    public static void main(String[] args)
    {

    }

    private String course;
    private String[] classes;
    private String classesInfo;
    private int classNum;
    private double theoryCourseHour;
    private double experimentCourseHour;
    private double taskHour;
    private int teacherNum;
    private TaskOperation taskOperation = new TaskOperation();

    public TeachingTask()
    {

    }

    public TeachingTask(String course, String[] classes, double theoryCourseHour, double experimentCourseHour)
    {
        this.course = course;
        this.classes = classes;
        this.classNum = classes.length;
        this.theoryCourseHour = theoryCourseHour;
        this.experimentCourseHour = experimentCourseHour;
        this.classesInfo = Arrays.toString(classes);
        computeTaskTime();
    }

    /**
     * 由班级信息获取班级数目
     */
    public int computeClassNum()        // 回头再改改 如果有空的话。。
    {
        int number = 0;
        for (int i = 0; i < classesInfo.trim().toCharArray().length; i++)
        {
            if (classesInfo.trim().toCharArray()[i] == ' ')
                number++;
        }
        return ++number;
    }

    public String getCourse()
    {
        return course;
    }

    public String[] getClasses()
    {
        return classes;
    }

    public int getClassNum()
    {
        setClassNum(computeClassNum());
        return classNum;
    }

    public double getTheoryCourseHour()
    {
        return theoryCourseHour;
    }

    public double getExperimentCourseHour()
    {
        return experimentCourseHour;
    }

    public double getTaskHour()
    {
        //computeTaskTime();
        return taskHour;
    }

    public int getTeacherNum()
    {
        return teacherNum;
    }

    public String getClassesInfo()
    {
        return classesInfo.trim();
    }

    public void setCourse(String course)
    {
        this.course = course;
    }

    public void setClasses(String[] classes)
    {
        this.classes = classes;
    }

    public void setClassNum(int classNum)
    {
        this.classNum = classNum;
    }

    public void setTheoryCourseHour(double theoryCourseHour)
    {
        this.theoryCourseHour = theoryCourseHour;
    }

    public void setExperimentCourseHour(double experimentCourseHour)
    {
        this.experimentCourseHour = experimentCourseHour;
    }

    public void setTaskHour(double taskHour)
    {
        this.taskHour = taskHour;
    }

    public void setTeacherNum(int teacherNum)
    {
        this.teacherNum = teacherNum;
    }

    public void setClassesInfo(String classesInfo)
    {
        this.classesInfo = classesInfo;
    }

    @Override
    public String toString()
    {
        return "课程名称:" + course + " 授课班级:" + classesInfo
                + " 授课班级数: " + classNum + " 理论课时:" + theoryCourseHour
                + " 实验课时:" + experimentCourseHour + " 任务总课时:" + taskHour;
    }

    // 计算任务总课时
    public void computeTaskTime()
    {
        taskHour = taskOperation.computeTaskHour(teacherNum, course);
    }

    public Object getValue(int index)
    {
        Object value = null;
        if (index == 0)
            value = getCourse();
        if (index == 1)
            value = getClassesInfo();
        if (index == 2)
            value = getClassNum();
        if (index == 3)
            value = getTheoryCourseHour();
        if (index == 4)
            value = getExperimentCourseHour();
        if (index == 5)
            value = getTaskHour();
        return value;
    }

    public String getName(int index)
    {
        String name = null;
        if (index == 0)
            name = "课程名";
        if (index == 1)
            name = "授课班级";
        if (index == 2)
            name = "班级数";
        if (index == 3)
            name = "理论课时";
        if (index == 4)
            name = "实验课时";
        if (index == 5)
            name = "任务总课时";
        return name;
    }
}