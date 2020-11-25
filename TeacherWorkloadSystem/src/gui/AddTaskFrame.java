package gui;

import database.TaskOperation;
import list.TeachingTask;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTaskFrame extends TaskOperationFrame
{
    private JButton insertButton;
    private TaskFrame owner;
    private TeachingTask teachingTask = new TeachingTask();
    private TaskOperation taskOperation = new TaskOperation();

    public AddTaskFrame(TaskFrame owner)
    {
        super();

        this.owner = owner;

        setTitle("增加任务信息");

        insertButton = new JButton("插入数据");
        insertButton.setBounds(100, 200, 90, 35);
        add(insertButton);
        insertButton.addActionListener(new InsertAction());
    }

    private class InsertAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            boolean isBlank = courseTextField.getText().equals("") || classTextField.getText().equals("") || theoryCourseHourTextField.getText().equals("") || experimentCourseHourTextField.getText().equals("");
            if (isBlank)
            {
                JOptionPane.showMessageDialog(null, "请输入正确格式的数据", "错误", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                teachingTask.setTeacherNum(owner.getTeacherNum());
                teachingTask.setCourse(courseTextField.getText().trim());
                teachingTask.setClassesInfo(classTextField.getText());
                teachingTask.setTheoryCourseHour(Double.parseDouble(theoryCourseHourTextField.getText()));
                teachingTask.setExperimentCourseHour(Double.parseDouble(experimentCourseHourTextField.getText()));
                teachingTask.setTaskHour(computeTaskHour(teachingTask.getClassNum(), teachingTask.getTheoryCourseHour(),
                        teachingTask.getExperimentCourseHour()));

                int flag = JOptionPane.showConfirmDialog(null, "确认插入信息？", "确认", JOptionPane.YES_NO_OPTION);
                if (flag == JOptionPane.YES_OPTION)
                {
                    if (taskOperation.getTasksNum(owner.getTeacherNum()) >= 5)
                        JOptionPane.showMessageDialog(null, "任务数不得大于5!");
                    else
                    {
                        taskOperation.addTask(teachingTask.getTeacherNum(), teachingTask.getCourse(),
                                teachingTask.getClassesInfo(), teachingTask.computeClassNum(), teachingTask.getTheoryCourseHour(), teachingTask.getExperimentCourseHour(), teachingTask.getTaskHour());
                        owner.updateTable();
                    }
                }
                else return;
                dispose();
            }
        }
    }
}