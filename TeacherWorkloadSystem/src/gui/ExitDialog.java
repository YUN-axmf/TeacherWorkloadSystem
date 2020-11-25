package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 退出对话框类
 */
public class ExitDialog extends JDialog
{
    public ExitDialog(JFrame owner)
    {
        super(owner, "退出确认", true);

        JLabel message = new JLabel("确认退出？", JLabel.CENTER);
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
            JOptionPane.showMessageDialog(null, "感谢您的使用！");
            System.exit(0);
        }
    }

    private class cancelAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            ExitDialog.this.dispose();
        }
    }
}