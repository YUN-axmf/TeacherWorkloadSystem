package gui;

import list.Teacher;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ModifyTeacherFrame extends TeacherOperationFrame
{
    private JButton modifyButton;
    private JButton showTaskButton;
    private Teacher teacher;
    private AdminFrame owner;

    public ModifyTeacherFrame(AdminFrame owner, int number) throws SQLException, ClassNotFoundException
    {
        super();

        this.owner = owner;
        teacher = teacherOperation.getTeacher(number);

        setTitle("修改教师信息");

        modifyButton = new JButton("修改数据");
        modifyButton.setBounds(160, 300, 100, 40);
        modifyButton.addActionListener(new ModifyAction());
        add(modifyButton);

        showTaskButton = new JButton("修改任务");
        showTaskButton.setBounds(50, 300, 100, 40);
        showTaskButton.addActionListener(new ShowTaskAction());
        add(showTaskButton);

        returnButton.setBounds(270, 300, 100, 40);

        numberTextField.setText(String.valueOf(number));
        passwordTextField.setText(teacher.getPassword());
        nameTextField.setText(teacher.getName());
        sexTextField.setText(teacher.getSex());
        professionalTitleTextField.setText(teacher.getProfessionalTitle());
        taskNumTextField.setText(String.valueOf(teacher.getTaskNum()));
        totalHourTextField.setText(String.valueOf(teacher.getTotalHour()));

        numberTextField.setEditable(false);
        taskNumTextField.setEditable(false);
        totalHourTextField.setEditable(false);
    }

    public void modifyUpdateData() throws SQLException, ClassNotFoundException
    {
        dispose();
        new ModifyTeacherFrame(owner, teacher.getNumber());
    }

    private class ModifyAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            int number = Integer.parseInt(numberTextField.getText());
            String password = passwordTextField.getText();
            String name = nameTextField.getText();
            String sex = sexTextField.getText();
            String professionalTitle = professionalTitleTextField.getText();
            String taskNum = taskNumTextField.getText();
            String totalHour = totalHourTextField.getText();

            int flag = JOptionPane.showConfirmDialog(null,
                    "确认修改信息？", "确认", JOptionPane.YES_NO_OPTION);
            if (flag == JOptionPane.YES_OPTION)
            {
                teacherOperation.modifyTeacher(number, password, name, sex, professionalTitle);
                owner.updateTable();
            }
            else
                return;
            dispose();
        }
    }

    private class ShowTaskAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                new TaskFrame(teacher.getNumber()).setModifyProperty(ModifyTeacherFrame.this);
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
            catch (ClassNotFoundException ex)
            {
                ex.printStackTrace();
            }
        }
    }
}