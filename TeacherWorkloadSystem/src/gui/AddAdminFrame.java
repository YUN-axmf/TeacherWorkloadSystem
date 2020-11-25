package gui;

import database.AdminOperation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddAdminFrame extends JFrame
{
    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 300;
    private JLabel idLabel;
    private JLabel passwordLabel;
    private JTextField idTextField;
    private JTextField passwordTextField;
    private JButton addButton;
    private JButton returnButton;
    private AdminFrame owner;
    private AdminOperation adminOperation = new AdminOperation();

    public AddAdminFrame(AdminFrame owner)
    {
        this.owner = owner;

        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLocationRelativeTo(null);                //居中显示
        setLayout(null);
        setResizable(false);

        setTitle("增加管理员信息");

        idLabel = new JLabel("用户名");
        passwordLabel = new JLabel("密码");

        idTextField = new JTextField();
        passwordTextField = new JTextField();

        addButton = new JButton("增加管理员");
        returnButton = new JButton("返回上一级");

        idLabel.setBounds(80, 80, 100, 50);
        passwordLabel.setBounds(80, 120 ,100 ,50);
        idTextField.setBounds(160, 90 ,150 ,30);
        passwordTextField.setBounds(160, 130 ,150 ,30);
        addButton.setBounds(80, 200, 100, 40);
        returnButton.setBounds(210, 200, 100, 40);

        add(idLabel);
        add(passwordLabel);
        add(idTextField);
        add(passwordTextField);

        addButton.addActionListener(new AddAction());
        returnButton.addActionListener(new ReturnAction());
        add(addButton);
        add(returnButton);

        setVisible(true);
    }

    private class AddAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String id = idTextField.getText();
            String password = passwordTextField.getText();
            if (id.equals("") || password.equals(""))
            {
                JOptionPane.showMessageDialog(null,
                        "请输入正确的数据！", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try
            {
                if (adminOperation.isIdExisting(id))
                {
                    int flag = JOptionPane.showConfirmDialog(null,
                            "确认插入信息？", "确认", JOptionPane.YES_NO_OPTION);
                    if (flag == JOptionPane.NO_OPTION);
                    else
                    {
                        JOptionPane.showMessageDialog(null,
                                "该账户已存在！", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                    return;
                }
                else
                {
                    int flag = JOptionPane.showConfirmDialog(null,
                            "确认增加管理员信息？", "确认", JOptionPane.YES_NO_OPTION);
                    if (flag == JOptionPane.YES_OPTION)
                    {
                        JOptionPane.showMessageDialog(null, "增加成功!");
                        adminOperation.addAdmin(id, password);
                        owner.updateTable();
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
    }

    private class ReturnAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            dispose();
        }
    }
}