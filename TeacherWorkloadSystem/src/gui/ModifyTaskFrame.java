package gui;

import list.TeachingTask;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyTaskFrame extends TaskOperationFrame
{
    private JButton modifyTaskButton;
    private TeachingTask teachingTask;
    private TaskFrame owner;
    private int teacherNum;
    private String course;

    public ModifyTaskFrame(TaskFrame owner, int teacherNum, String course)
    {
        super();

        this.owner = owner;
        this.teacherNum = teacherNum;
        this.course = course;

        teachingTask = taskOperation.getTask(teacherNum, course);

        setTitle("修改任务信息");

        modifyTaskButton = new JButton("修改数据");
        modifyTaskButton.setBounds(100, 200, 90, 35);
        add(modifyTaskButton);
        modifyTaskButton.addActionListener(new ModifyTaskAction());

        courseTextField.setText(teachingTask.getCourse());
        classTextField.setText(teachingTask.getClassesInfo());
        theoryCourseHourTextField.setText(String.valueOf(teachingTask.getTheoryCourseHour()));
        experimentCourseHourTextField.setText(String.valueOf(teachingTask.getExperimentCourseHour()));

        courseTextField.setEditable(false);
    }

    private class ModifyTaskAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            boolean isBlank = courseTextField.getText().equals("") || classTextField.getText().equals("") || theoryCourseHourTextField.getText().equals("") || experimentCourseHourTextField.getText().equals("");
            if (isBlank || !correctFormat(theoryCourseHourTextField.getText()) || !correctFormat(experimentCourseHourTextField.getText()))
            {
                JOptionPane.showMessageDialog(null, "请输入正确格式的数据", "错误", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                String classesInfo = classTextField.getText();
                int classNum = computeClassNum(classesInfo);
                double theoryCourseHour = Double.parseDouble(theoryCourseHourTextField.getText());
                double experimentCourseHour = Double.parseDouble(experimentCourseHourTextField.getText());
                double taskHour = computeTaskHour(classNum, theoryCourseHour, experimentCourseHour);

                int flag = JOptionPane.showConfirmDialog(null,
                        "确认修改任务信息？", "确认", JOptionPane.YES_NO_OPTION);
                if (flag == JOptionPane.YES_OPTION)
                {
                    taskOperation.modifyTask(teacherNum, course, classesInfo, classNum, theoryCourseHour, experimentCourseHour, taskHour);
                    owner.updateTable();
                }
                else
                    return;
                dispose();
            }
        }

        public int computeClassNum(String classesInfo)
        {
            int number = 0;
            for (int i = 0; i < classesInfo.trim().toCharArray().length; i++)
            {
                if (classesInfo.trim().toCharArray()[i] == ' ')
                    number++;
            }
            return ++number;
        }

        /**
         * 判断输入字符串是否为double类型
         */
        public boolean correctFormat(String str)
        {
            for (int i = 0; i < str.length(); i++)
            {
                if (str.charAt(i) == '.');
                else if (!Character.isDigit(str.charAt(i)))
                    return false;
            }
            return true;
        }
    }
}