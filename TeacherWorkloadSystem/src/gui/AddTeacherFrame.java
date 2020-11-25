package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddTeacherFrame extends TeacherOperationFrame
{
    private JButton insertButton;
    private AdminFrame owner;

    public AddTeacherFrame(AdminFrame owner)
    {
        super();

        this.owner = owner;

        setTitle("增加教师信息");

        remove(totalHourLabel);
        remove(totalHourTextField);

        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT - 50);

        insertButton = new JButton("插入任务");
        insertButton.setBounds(90, 260, 100, 40);
        insertButton.addActionListener(new InsertAction());
        add(insertButton);

        returnButton.setBounds(230, 260, 100, 40);
    }

    private class InsertAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String number = numberTextField.getText();
            String taskNum = taskNumTextField.getText();
            String password = passwordTextField.getText();
            String name = nameTextField.getText();
            String sex = sexTextField.getText();
            String professionalTitle = professionalTitleTextField.getText();
            boolean isBlank = number.equals("") || taskNum.equals("") || password.equals("") || name.equals("") ||
                    sex.equals("") || professionalTitle.equals("");
            boolean isCorrectFormat = correctFormat(number) && correctFormat(taskNum);
            if (isBlank || !isCorrectFormat)
            {
                JOptionPane.showMessageDialog(null, "请输入正确格式的数据",
                        "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try
            {
                if (teacherOperation.isNumberExisting(Integer.parseInt(number)))
                {
                    int flag = JOptionPane.showConfirmDialog(null,
                            "确认插入信息？", "确认", JOptionPane.YES_NO_OPTION);
                    if (flag == JOptionPane.NO_OPTION)
                        return;
                    else
                    {
                        JOptionPane.showMessageDialog(null,
                                "教工号已存在！", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                    return;
                }
                else
                {
                    int flag = JOptionPane.showConfirmDialog(null,
                            "确认插入信息？", "确认", JOptionPane.YES_NO_OPTION);
                    if (flag == JOptionPane.YES_OPTION)
                    {
                        if (Integer.parseInt(taskNum) < 1 || Integer.parseInt(taskNum) > 5)
                        {
                            JOptionPane.showMessageDialog(null, "任务数需在1到5之间！", "错误", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        else
                        {
                            new AddTeacherTaskFrame(owner, Integer.parseInt(number), Integer.parseInt(taskNum));
                            teacherOperation.addTeacher(Integer.parseInt(number), password, name, sex, professionalTitle);
                            owner.updateTable();
                        }
                    }
                    else
                        return;
                }
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
            dispose();
        }

        /**
         * 判断输入字符串是否为整数类型
         */
        public boolean correctFormat(String str)
        {
            for (int i = 0; i < str.length(); i++)
                if (!Character.isDigit(str.charAt(i)))
                    return false;
            return true;
        }
    }
}