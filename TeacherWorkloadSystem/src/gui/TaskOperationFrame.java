package gui;

import database.TaskOperation;
import database.TeacherOperation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 增加任务、修改任务窗口
 */
public class TaskOperationFrame extends JFrame
{
    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 300;
    protected JLabel courseLabel;
    protected JLabel classLabel;
    protected JLabel theoryCourseHourLabel;
    protected JLabel experimentCourseHourLabel;
    protected JTextField courseTextField;
    protected JTextField classTextField;
    protected JTextField theoryCourseHourTextField;
    protected JTextField experimentCourseHourTextField;
    protected JButton returnButton;
    protected TaskOperation taskOperation = new TaskOperation();

    public TaskOperationFrame()
    {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLocationRelativeTo(null);                //居中显示
        setLayout(null);
        setResizable(false);

        courseLabel = new JLabel("课程");
        classLabel = new JLabel("授课班级");
        theoryCourseHourLabel = new JLabel("理论课时");
        experimentCourseHourLabel = new JLabel("实验课时");

        courseTextField = new JTextField();
        classTextField = new JTextField();
        theoryCourseHourTextField = new JTextField();
        experimentCourseHourTextField = new JTextField();

        courseLabel.setBounds(80, 30, 100, 50);
        classLabel.setBounds(80, 60, 100, 50);
        theoryCourseHourLabel.setBounds(80,90, 100, 50);
        experimentCourseHourLabel.setBounds(80,120, 100, 50);

        courseTextField.setBounds(200, 45, 150, 25);
        classTextField.setBounds(200, 75, 150, 25);
        theoryCourseHourTextField.setBounds(200, 105, 150, 25);
        experimentCourseHourTextField.setBounds(200, 135, 150, 25);

        returnButton = new JButton("返回");
        returnButton.setBounds(220, 200, 90, 35);
        add(returnButton);
        returnButton.addActionListener(new ReturnAction());

        add(courseLabel);
        add(classLabel);
        add(theoryCourseHourLabel);
        add(experimentCourseHourLabel);

        add(courseTextField);
        add(classTextField);
        add(theoryCourseHourTextField);
        add(experimentCourseHourTextField);

        setVisible(true);
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

    private class ReturnAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            TaskOperationFrame.this.dispose();
        }
    }
}