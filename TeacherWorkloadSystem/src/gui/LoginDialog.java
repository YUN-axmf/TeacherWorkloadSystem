package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 登录对话框类
 */
public class LoginDialog extends JDialog
{
    private LoginFrame owner;

    public LoginDialog(JFrame owner)
    {
        super(owner, "登录确认", true);

        this.owner = (LoginFrame) owner;

        JLabel message = new JLabel("确认登录？", JLabel.CENTER);
        message.setFont(new Font("Serif", Font.BOLD, 30));
        add(message);

        JButton confirmButton = new JButton("确认");
        JButton cancelButton = new JButton("取消");
        confirmButton.addActionListener(new confirmAction());
        cancelButton.addActionListener(new cancelAction());

        JPanel panel = new JPanel();
        panel.add(confirmButton);
        panel.add(cancelButton);

        add(panel, BorderLayout.SOUTH);

        setSize(250, 150);
        setLocationRelativeTo(null);
    }

    private class confirmAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            LoginDialog.this.dispose();
            login(owner);
        }
    }

    private class cancelAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            LoginDialog.this.dispose();
        }
    }

    public void login(LoginFrame loginFrame)
    {
        if (loginFrame.getAdminRadioButton().isSelected())
        {
            String id = loginFrame.getTextField().getText();
            String password = loginFrame.getPasswordField().getText();
            if(loginFrame.getAdminImport().validate(id, password))
            {
                JOptionPane.showMessageDialog(this, "登录成功!");
                loginFrame.dispose();
                new AdminFrame();
            }
            else
                {
                JOptionPane.showMessageDialog(this, "用户名或密码输入错误!");
            }

        }
        else if (loginFrame.getTeacherRadioButton().isSelected())
        {
            String number = loginFrame.getTextField().getText();
            String password = loginFrame.getPasswordField().getText();
            if(loginFrame.getTeacherImport().validate(number, password))
            {
                JOptionPane.showMessageDialog(this, "登录成功!");
                loginFrame.dispose();
                new TeacherFrame();
            }
            else
            {
                JOptionPane.showMessageDialog(this, "用户名或密码输入错误!");
            }
        }
        else
            JOptionPane.showMessageDialog(this,
                    "请选择登录身份！", "错误", JOptionPane.WARNING_MESSAGE);
    }
}