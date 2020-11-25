package gui;

import database.AdminOperation;
import database.TeacherOperation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 登录页面类
 */
public class LoginFrame extends JFrame
{
    public static void main(String[] args)
    {
    	JFrame frame = new LoginFrame();
    }

    public static final int DEFAULT_WIDTH = 600;
    public static final int DEFAULT_HEIGHT = 400;
    private JTextField textField;
    private JPasswordField passwordField;
    private JLabel userLabel;
    private JLabel passwordLabel;
    private JButton loginButton;
    private JButton exitButton;
    private JPanel radioButtonPanel;
    private ButtonGroup group;
    private LoginDialog loginDialog;
    private ExitDialog exitDialog;
    private JRadioButton teacherRadioButton;
    private JRadioButton adminRadioButton;
    private AdminOperation adminImport = new AdminOperation();
    private TeacherOperation teacherImport = new TeacherOperation();

    public LoginFrame()
    {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLocationRelativeTo(null);                //居中显示
        setLayout(null);
        setResizable(false);

        setTitle("教师工作量管理系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textField = new JTextField(10);
        passwordField = new JPasswordField(10);
        textField.setBounds(220,90,250,30);
        passwordField.setBounds(220,170,250,30);

        userLabel = new JLabel("用户名", SwingConstants.RIGHT);
        passwordLabel = new JLabel("密    码", SwingConstants.RIGHT);
        userLabel.setBounds(80, 80, 100, 50);
        passwordLabel.setBounds(80, 160, 100, 50);

        loginButton = new JButton("登录");
        exitButton = new JButton("退出");

        loginButton.setBounds(200, 280, 80, 30);
        exitButton.setBounds(350, 280, 80, 30);

        radioButtonPanel = new JPanel();
        group = new ButtonGroup();
        radioButtonPanel.setBounds(200, 220, 200, 50);


        adminRadioButton = new JRadioButton("管理员");
        group.add(adminRadioButton);
        radioButtonPanel.add(adminRadioButton);

        teacherRadioButton = new JRadioButton("教师");
        group.add(teacherRadioButton);
        radioButtonPanel.add(teacherRadioButton);

        // 为按钮添加事件
        loginButton.addActionListener(new LoginAction());
        exitButton.addActionListener(new ExitAction());

        add(userLabel);
        add(passwordLabel);
        add(textField);
        add(passwordField);
        add(loginButton);
        add(exitButton);
        add(radioButtonPanel);

        setVisible(true);
    }

    private class LoginAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (loginDialog == null)
                loginDialog = new LoginDialog(LoginFrame.this);
            loginDialog.setVisible(true);
        }
    }

    private class ExitAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (exitDialog == null)
                exitDialog = new ExitDialog(LoginFrame.this);
            exitDialog.setVisible(true);
        }
    }

    public JRadioButton getTeacherRadioButton()
    {
        return teacherRadioButton;
    }

    public JRadioButton getAdminRadioButton()
    {
        return adminRadioButton;
    }

    public AdminOperation getAdminImport()
    {
        return adminImport;
    }

    public TeacherOperation getTeacherImport()
    {
        return teacherImport;
    }

    public JTextField getTextField()
    {
        return textField;
    }

    public JPasswordField getPasswordField()
    {
        return passwordField;
    }
}