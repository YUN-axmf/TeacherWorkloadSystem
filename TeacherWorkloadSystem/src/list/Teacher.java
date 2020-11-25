package list;

import database.TaskOperation;

/**
 * 教师类
 *
 * 成员变量：教工号、密码、姓名、性别、职称、教学任务、教学任务数、总教学课时、数据库任务操作对象
 *
 * 方法：
 * 输出教师信息、计算总教学课时
 */
public class Teacher
{
    // 测试方法，仅供调试代码使用
    public static void main(String[] args)
    {

    }

    private int number;
    private String password;
    private String name;
    private String sex;
    private String professionalTitle;
    private TeachingTask[] tasks = new TeachingTask[5];
    private int taskNum;
    private double totalHour;
    private TaskOperation taskOperation = new TaskOperation();

    public Teacher()
    {

    }

    public Teacher(int number, String name, String sex, String professionalTitle, TeachingTask[] tasks)
    {
        this.number = number;
        this.name = name;
        this.sex = sex;
        this.professionalTitle = professionalTitle;
        this.tasks = tasks;
        this.taskNum = tasks.length;
    }

    public int getNumber()
    {
        return number;
    }

    public String getPassword()
    {
        return password;
    }

    public String getName()
    {
        return name;
    }

    public String getSex()
    {
        return sex;
    }

    public String getProfessionalTitle()
    {
        return professionalTitle;
    }

    public TeachingTask[] getTasks()
    {
        return tasks;
    }

    public int getTaskNum()
    {
        return taskOperation.getTasksNum(number);
    }

    public double getTotalHour()
    {
        return taskOperation.getTotalHour(number);
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public void setProfessionalTitle(String professionalTitle)
    {
        this.professionalTitle = professionalTitle;
    }

    public void setTasks(TeachingTask[] tasks)
    {
        this.tasks = tasks;
    }

    public void setTaskNum(int taskNum)
    {
        this.taskNum = taskNum;
    }

    public void setTotalHour(double totalHour)
    {
        this.totalHour = totalHour;
    }

    @Override
    public String toString()
    {
        return "教师号:" + number + " 姓名:" + name + " 性别:" + sex + " 职称:"
                + professionalTitle + "教学任务数:" + taskNum + " 授课总课时:" + totalHour;
    }

    // 计算总教学课时
    public double computeTotalHour()
    {
        for (int i = 0; i < tasks.length; i++)
        {
            totalHour += tasks[i].getTaskHour();
        }
        return totalHour;
    }


    /*// 输出教师信息
    public void printTeacherInformation()
    {
        System.out.println("\t" + toString());
        *//*System.out.println("\t授课任务: ");
        for (int i = 0; i < tasks.length; i++)
        {
            System.out.println("\t\t" + tasks[i].toString());
        }*//*
    }*/

    // 增加任务
    public void addTask(TeachingTask teachingTask)
    {
        taskNum++;
        tasks[taskNum] = teachingTask;
    }

    public Object getValue(int index)
    {
        Object value = null;
        if (index == 0)
            value = getNumber();
        if (index == 1)
            value = getName();
        if (index == 2)
            value = getSex();
        if (index == 3)
            value = getProfessionalTitle();
        if (index == 4)
            value = getTaskNum();
        if (index == 5)
            value = getTotalHour();
        return value;
    }

    public String getInfo(int index)
    {
        String information = null;
        if (index == 0)
            information = "教工号";
        if (index == 1)
            information = "姓名";
        if (index == 2)
            information = "性别";
        if (index == 3)
            information = "职称";
        if (index == 4)
            information = "教学任务数";
        if (index == 5)
            information = "教学总课时";
        return information;
    }
}