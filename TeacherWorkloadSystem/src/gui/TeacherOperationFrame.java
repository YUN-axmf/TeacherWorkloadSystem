package gui;

import database.TeacherOperation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 增加教师、修改教师、查找教师窗口
 */
public class TeacherOperationFrame extends JFrame
{
    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 400;
    protected JLabel numberLabel;
    protected JLabel passwordLabel;
    protected JLabel nameLabel;
    protected JLabel sexLabel;
    protected JLabel professionalTitleLabel;
    protected JLabel taskNumLabel;
    protected JLabel totalHourLabel;
    protected JTextField numberTextField;
    protected JTextField passwordTextField;
    protected JTextField nameTextField;
    protected JTextField sexTextField;
    protected JTextField professionalTitleTextField;
    protected JTextField taskNumTextField;
    protected JTextField totalHourTextField;
    protected JButton returnButton;
    protected TeacherOperation teacherOperation = new TeacherOperation();

    public TeacherOperationFrame()
    {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLocationRelativeTo(null);                //居中显示
        setLayout(null);
        setResizable(false);

        numberLabel = new JLabel("教师号");
        passwordLabel = new JLabel("密码");
        nameLabel = new JLabel("姓名");
        sexLabel = new JLabel("性别");
        professionalTitleLabel = new JLabel("职称");
        taskNumLabel = new JLabel("教学任务数");
        totalHourLabel = new JLabel("教学总课时");

        numberTextField = new JTextField();
        passwordTextField = new JTextField();
        nameTextField = new JTextField();
        sexTextField = new JTextField();
        professionalTitleTextField = new JTextField();
        taskNumTextField = new JTextField();
        totalHourTextField = new JTextField();

        returnButton = new JButton("返回");
        returnButton.addActionListener(new ReturnAction());
        returnButton.setBounds(230, 300, 100, 40);

        numberLabel.setBounds(80, 50, 100, 50);
        passwordLabel.setBounds(80, 80, 100, 50);
        nameLabel.setBounds(80, 110, 100, 50);
        sexLabel.setBounds(80,140, 100, 50);
        professionalTitleLabel.setBounds(80,170, 100, 50);
        taskNumLabel.setBounds(80, 200, 100, 50);
        totalHourLabel.setBounds(80,230, 100, 50);

        numberTextField.setBounds(200, 65, 150, 25);
        passwordTextField.setBounds(200, 95, 150, 25);
        nameTextField.setBounds(200, 125, 150, 25);
        sexTextField.setBounds(200, 155, 150, 25);
        professionalTitleTextField.setBounds(200, 185, 150, 25);
        taskNumTextField.setBounds(200, 215, 150, 25);
        totalHourTextField.setBounds(200, 245, 150, 25);

        add(numberLabel);
        add(passwordLabel);
        add(nameLabel);
        add(sexLabel);
        add(professionalTitleLabel);
        add(taskNumLabel);
        add(totalHourLabel);

        add(numberTextField);
        add(passwordTextField);
        add(nameTextField);
        add(sexTextField);
        add(professionalTitleTextField);
        add(taskNumTextField);
        add(totalHourTextField);

        add(returnButton);

        setVisible(true);
    }

    private class ReturnAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            TeacherOperationFrame.this.dispose();
        }
    }
}