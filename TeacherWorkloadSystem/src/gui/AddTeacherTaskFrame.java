package gui;

import database.TaskOperation;
import list.TeachingTask;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTeacherTaskFrame extends JFrame
{
    private AdminFrame owner;
    private int teacherNum;
    private int taskNum;
    private JButton addTaskButton;
    private TaskOperation taskOperation = new TaskOperation();
    private TeachingTask[] teachingTasks;
    private AddTaskPanel[] taskPanels;

    public AddTeacherTaskFrame(AdminFrame owner, int teacherNum, int taskNum)
    {
        setResizable(false);
        setLayout(null);

        setTitle("增加教师任务页面");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.owner = owner;
        this.teacherNum = teacherNum;
        this.taskNum = taskNum;
        teachingTasks = new TeachingTask[taskNum];
        taskPanels = new AddTaskPanel[taskNum];

        taskPanels[0] = new AddTaskPanel(teacherNum);

        addTaskButton = new JButton("增加任务");

        switch(taskNum)
        {
            case 1:
                addOneTask();
                break;
            case 2:
                addTwoTasks();
                break;
            case 3:
                addThreeTasks();
                break;
            case 4:
                addFourTasks();
                break;
            case 5:
                addFiveTasks();
                break;
            default:
        }

        // 设置任务教师号
        for (int i = 0; i < teachingTasks.length; i++)
        {
            teachingTasks[i] = new TeachingTask();
            teachingTasks[i].setTeacherNum(teacherNum);
        }

        add(taskPanels[0]);
        add(addTaskButton);

        setLocationRelativeTo(null);                //居中显示得在setSize后面执行
        setVisible(true);
    }

    public void addOneTask()
    {
        setSize(400, 300);

        addTaskButton.setBounds(150, 200, 100, 35);
        addTaskButton.addActionListener(new AddOneTaskAction());

        taskPanels[0].setLocation(50, 50);
    }

    public void addTwoTasks()
    {
        setSize(700, 300);

        addTaskButton.setBounds(300, 200, 100, 35);
        addTaskButton.addActionListener(new AddTwoTasksAction());

        addTaskPanel();
        //taskPanels[1] = new AddTaskPanel(teacherNum);

        taskPanels[0].setLocation(50, 50);
        taskPanels[1].setLocation(350, 50);

        JLabel firstTaskLabel = new JLabel("任务1");
        JLabel secondTaskLabel = new JLabel("任务2");

        firstTaskLabel.setBounds(170, 15, 100, 50);
        secondTaskLabel.setBounds(470, 15, 100, 50);

        add(taskPanels[1]);
        add(firstTaskLabel);
        add(secondTaskLabel);
    }

    public void addThreeTasks()
    {
        setSize(1000, 300);

        addTaskButton.setBounds(450, 200, 100, 35);
        addTaskButton.addActionListener(new AddThreeTasksAction());

        addTaskPanel();
        //taskPanels[1] = new AddTaskPanel(teacherNum);
        //taskPanels[2] = new AddTaskPanel(teacherNum);

        taskPanels[0].setLocation(50, 50);
        taskPanels[1].setLocation(350, 50);
        taskPanels[2].setLocation(650, 50);

        JLabel firstTaskLabel = new JLabel("任务1");
        JLabel secondTaskLabel = new JLabel("任务2");
        JLabel thirdTaskLabel = new JLabel("任务3");

        firstTaskLabel.setBounds(170, 15, 100, 50);
        secondTaskLabel.setBounds(470, 15, 100, 50);
        thirdTaskLabel.setBounds(770, 15, 100, 50);

        add(taskPanels[1]);
        add(taskPanels[2]);
        add(firstTaskLabel);
        add(secondTaskLabel);
        add(thirdTaskLabel);
    }

    public void addFourTasks()
    {
        setSize(700, 500);

        addTaskButton.setBounds(300, 400, 100, 35);
        addTaskButton.addActionListener(new AddFourTasksAction());

        addTaskPanel();
        //taskPanels[1] = new AddTaskPanel(teacherNum);
        //taskPanels[2] = new AddTaskPanel(teacherNum);
        //taskPanels[3] = new AddTaskPanel(teacherNum);

        taskPanels[0].setLocation(50, 50);
        taskPanels[1].setLocation(350, 50);
        taskPanels[2].setLocation(50, 240);
        taskPanels[3].setLocation(350, 240);

        JLabel firstTaskLabel = new JLabel("任务1");
        JLabel secondTaskLabel = new JLabel("任务2");
        JLabel thirdTaskLabel = new JLabel("任务3");
        JLabel forthTaskLabel = new JLabel("任务4");


        firstTaskLabel.setBounds(170, 15, 100, 50);
        secondTaskLabel.setBounds(470, 15, 100, 50);
        thirdTaskLabel.setBounds(170, 205, 100, 50);
        forthTaskLabel.setBounds(470, 205, 100, 50);


        add(taskPanels[1]);
        add(taskPanels[2]);
        add(taskPanels[3]);

        add(firstTaskLabel);
        add(secondTaskLabel);
        add(thirdTaskLabel);
        add(forthTaskLabel);
    }

    public void addFiveTasks()
    {
        setSize(1000, 500);

        addTaskButton.setBounds(450, 400, 100, 35);
        addTaskButton.addActionListener(new AddFiveTasksAction());

        addTaskPanel();
        //taskPanels[1] = new AddTaskPanel(teacherNum);
        //taskPanels[2] = new AddTaskPanel(teacherNum);
        //taskPanels[3] = new AddTaskPanel(teacherNum);
        //taskPanels[4] = new AddTaskPanel(teacherNum);

        taskPanels[0].setLocation(200, 50);
        taskPanels[1].setLocation(500, 50);
        taskPanels[2].setLocation(50, 240);
        taskPanels[3].setLocation(350, 240);
        taskPanels[4].setLocation(650, 240);

        JLabel firstTaskLabel = new JLabel("任务1");
        JLabel secondTaskLabel = new JLabel("任务2");
        JLabel thirdTaskLabel = new JLabel("任务3");
        JLabel forthTaskLabel = new JLabel("任务4");
        JLabel fifthTaskLabel = new JLabel("任务5");


        firstTaskLabel.setBounds(320, 15, 100, 50);
        secondTaskLabel.setBounds(620, 15, 100, 50);
        thirdTaskLabel.setBounds(170, 205, 100, 50);
        forthTaskLabel.setBounds(470, 205, 100, 50);
        fifthTaskLabel.setBounds(770, 205, 100, 50);


        add(taskPanels[1]);
        add(taskPanels[2]);
        add(taskPanels[3]);
        add(taskPanels[4]);

        add(firstTaskLabel);
        add(secondTaskLabel);
        add(thirdTaskLabel);
        add(forthTaskLabel);
        add(fifthTaskLabel);
    }

    public void addTaskPanel()
    {
        for (int i = 1; i < taskNum; i++)
        {
            taskPanels[i] = new AddTaskPanel(teacherNum);
        }
    }

    public double computeTaskHour(int classNum, double theoryCourseHour, double experimentCourseHour)
    {
        if (classNum == 2)
            return 1.5 * (theoryCourseHour + experimentCourseHour);
        else if (classNum == 3)
            return 2 * (theoryCourseHour + experimentCourseHour);
        else
            return 2.5 * (theoryCourseHour + experimentCourseHour);
    }

    /**
     * 存储任务信息至任务数组中
     */
    public void setTaskInfo(int index)
    {
        teachingTasks[index].setCourse(taskPanels[index].courseTextField.getText());
        teachingTasks[index].setClassesInfo(taskPanels[index].classTextField.getText());
        teachingTasks[index].setTheoryCourseHour(Double.parseDouble(taskPanels[index].theoryCourseHourTextField.getText()));
        teachingTasks[index].setExperimentCourseHour(Double.parseDouble(taskPanels[index].experimentCourseHourTextField.getText()));
        teachingTasks[index].setTaskHour(computeTaskHour(teachingTasks[index].getClassNum(),
                teachingTasks[index].getTheoryCourseHour(),
                teachingTasks[index].getExperimentCourseHour()));
    }

    private class AddOneTaskAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setTaskInfo(0);
            int flag = JOptionPane.showConfirmDialog(null, "确认插入信息？", "确认", JOptionPane.YES_NO_OPTION);
            if (flag == JOptionPane.YES_OPTION)
            {
                taskOperation.addTask(teachingTasks[0]);
                owner.updateTable();
            }
            else return;
            dispose();
        }
    }

    private class AddTwoTasksAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setTaskInfo(0);
            setTaskInfo(1);
            int flag = JOptionPane.showConfirmDialog(null, "确认插入信息？", "确认", JOptionPane.YES_NO_OPTION);
            if (flag == JOptionPane.YES_OPTION)
            {
                taskOperation.addTask(teachingTasks[0]);
                taskOperation.addTask(teachingTasks[1]);
                owner.updateTable();
            }
            else return;
            dispose();
        }
    }

    private class AddThreeTasksAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setTaskInfo(0);
            setTaskInfo(1);
            setTaskInfo(2);
            int flag = JOptionPane.showConfirmDialog(null, "确认插入信息？", "确认", JOptionPane.YES_NO_OPTION);
            if (flag == JOptionPane.YES_OPTION)
            {
                taskOperation.addTask(teachingTasks[0]);
                taskOperation.addTask(teachingTasks[1]);
                taskOperation.addTask(teachingTasks[2]);
                owner.updateTable();
            }
            else return;
            dispose();
        }
    }

    private class AddFourTasksAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setTaskInfo(0);
            setTaskInfo(1);
            setTaskInfo(2);
            setTaskInfo(3);
            int flag = JOptionPane.showConfirmDialog(null, "确认插入信息？", "确认", JOptionPane.YES_NO_OPTION);
            if (flag == JOptionPane.YES_OPTION)
            {
                taskOperation.addTask(teachingTasks[0]);
                taskOperation.addTask(teachingTasks[1]);
                taskOperation.addTask(teachingTasks[2]);
                taskOperation.addTask(teachingTasks[3]);
                owner.updateTable();
            }
            else return;
            dispose();
        }
    }

    private class AddFiveTasksAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setTaskInfo(0);
            setTaskInfo(1);
            setTaskInfo(2);
            setTaskInfo(3);
            setTaskInfo(4);
            int flag = JOptionPane.showConfirmDialog(null, "确认插入信息？", "确认", JOptionPane.YES_NO_OPTION);
            if (flag == JOptionPane.YES_OPTION)
            {
                taskOperation.addTask(teachingTasks[0]);
                taskOperation.addTask(teachingTasks[1]);
                taskOperation.addTask(teachingTasks[2]);
                taskOperation.addTask(teachingTasks[3]);
                taskOperation.addTask(teachingTasks[4]);
                owner.updateTable();
            }
            else return;
            dispose();
        }
    }
}