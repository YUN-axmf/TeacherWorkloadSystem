package gui;

import list.Teacher;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SearchTeacherFrame extends TeacherOperationFrame
{
    private Teacher teacher;
    private JButton showTaskButton;

    public SearchTeacherFrame(AdminFrame owner, int number) throws SQLException, ClassNotFoundException
    {
        super();

        setTitle("查找教师信息");

        teacher = teacherOperation.getTeacher(number);

        showTaskButton = new JButton("查看任务");
        showTaskButton.setBounds(90, 300, 100, 40);
        showTaskButton.addActionListener(new ShowTaskAction());
        add(showTaskButton);

        numberTextField.setText(String.valueOf(number));
        passwordTextField.setText(teacher.getPassword());
        nameTextField.setText(teacher.getName());
        sexTextField.setText(teacher.getSex());
        professionalTitleTextField.setText(teacher.getProfessionalTitle());
        taskNumTextField.setText(String.valueOf(teacher.getTaskNum()));
        totalHourTextField.setText(String.valueOf(teacher.getTotalHour()));

        numberTextField.setEditable(false);
        passwordTextField.setEditable(false);
        nameTextField.setEditable(false);
        sexTextField.setEditable(false);
        professionalTitleTextField.setEditable(false);
        taskNumTextField.setEditable(false);
        totalHourTextField.setEditable(false);
    }

    private class ShowTaskAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            new TaskFrame(teacher.getNumber()).setSearchProperty();
        }
    }
}