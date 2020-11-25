package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * 教师窗口类
 * 只可查看数据，不可对数据进行修改
 */
public class TeacherFrame extends AdminFrame
{
    public TeacherFrame()
    {
        super();

        setTitle("教师页面");

        remove(buttonPanel);

        JButton searchButton = new JButton("查找数据");
        searchButton.addActionListener(new SearchAction());

        JButton exitButton = new JButton("退出登录");
        exitButton.addActionListener(new ExitAction());

        buttonPanel = new JPanel();
        buttonPanel.add(searchButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        workloadTable.getColumnModel().getColumn(6).setCellEditor(new TaskButtonEditor(TeacherFrame.this, getTeacherNum()));
        workloadTable.getColumnModel().getColumn(6).setCellRenderer(new TaskButtonRender());

    }

    private class SearchAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String info = JOptionPane.showInputDialog("请输入要查找的教工号");
            if (!correctFormat(info) || info.equals(""))
            {
                JOptionPane.showMessageDialog(null, "请输入正确的教工号",
                        "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int number = Integer.parseInt(info);
            try
            {
                if (teacherOperation.isNumberExisting(number))
                {
                    new SearchTeacherFrame(TeacherFrame.this, number);
                }
                else
                {
                    JOptionPane.showMessageDialog(null,
                            "教工号不存在！", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
            catch (SQLException | ClassNotFoundException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    private class ExitAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            int flag = JOptionPane.showConfirmDialog(null,
                    "确认退出登录？", "确认", JOptionPane.YES_NO_OPTION);
            if (flag == JOptionPane.NO_OPTION)
                return;
            else
            {
                dispose();
                new LoginFrame();
            }
        }
    }
}