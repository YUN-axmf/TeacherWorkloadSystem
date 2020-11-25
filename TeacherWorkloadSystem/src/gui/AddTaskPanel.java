package gui;

import javax.swing.*;
import java.awt.*;

public class AddTaskPanel extends JPanel
{
    JLabel courseLabel;
    JLabel classLabel;
    JLabel theoryCourseHourLabel;
    JLabel experimentCourseHourLabel;
    JTextField courseTextField;
    JTextField classTextField;
    JTextField theoryCourseHourTextField;
    JTextField experimentCourseHourTextField;

    public AddTaskPanel(int teacherNum)
    {
        setSize(300, 140);
        setLayout(null);

        courseLabel = new JLabel("课程");
        classLabel = new JLabel("授课班级");
        theoryCourseHourLabel = new JLabel("理论课时");
        experimentCourseHourLabel = new JLabel("实验课时");
        courseTextField = new JTextField();
        classTextField = new JTextField();
        theoryCourseHourTextField = new JTextField();
        experimentCourseHourTextField = new JTextField();

        courseLabel.setBounds(20, 0, 100, 50);
        classLabel.setBounds(20, 30, 100, 50);
        theoryCourseHourLabel.setBounds(20,60, 100, 50);
        experimentCourseHourLabel.setBounds(20,90, 100, 50);

        courseTextField.setBounds(100, 15, 150, 25);
        classTextField.setBounds(100, 45, 150, 25);
        theoryCourseHourTextField.setBounds(100, 75, 150, 25);
        experimentCourseHourTextField.setBounds(100, 105, 150, 25);

        add(courseLabel);
        add(classLabel);
        add(theoryCourseHourLabel);
        add(experimentCourseHourLabel);
        add(courseTextField);
        add(classTextField);
        add(theoryCourseHourTextField);
        add(experimentCourseHourTextField);
    }
}