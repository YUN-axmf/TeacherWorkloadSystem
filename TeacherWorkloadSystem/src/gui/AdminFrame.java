package gui;

import database.TeacherOperation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * 管理员窗口类
 * 可以查看并修改数据
 */
public class AdminFrame extends JFrame
{
    /**
     * 测试方法 仅供调试时使用
     */
    public static void main(String[] args)
    {
        JFrame frame = new AdminFrame();
    }

    public static final int DEFAULT_WIDTH = 700;
    public static final int DEFAULT_HEIGHT = 450;
    protected JPanel buttonPanel = new JPanel();
    protected JTable workloadTable;
    protected TeacherOperation teacherOperation = new TeacherOperation();
    private int taskRow;

    public AdminFrame()
    {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLocationRelativeTo(null);                //居中显示
        setResizable(false);

        setTitle("管理员页面");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        WorkloadTableModel model = new WorkloadTableModel(teacherOperation.importInformation());
        workloadTable = new JTable(model);

        add(new JScrollPane(workloadTable), BorderLayout.CENTER);

        JButton insertButton = new JButton("插入数据");
        JButton removeButton = new JButton("删除数据");
        JButton modifyButton = new JButton("修改数据");
        JButton searchButton = new JButton("查找数据");
        JButton addAdminButton = new JButton("增加管理员");
        JButton exitButton = new JButton("退出登录");

        buttonPanel.add(insertButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(modifyButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(addAdminButton);
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.SOUTH);

        //添加事件
        removeButton.addActionListener(new RemoveAction());
        insertButton.addActionListener(new InsertAction());
        modifyButton.addActionListener(new ModifyAction());
        searchButton.addActionListener(new SearchAction());
        addAdminButton.addActionListener(new AddAdminAction());
        exitButton.addActionListener(new ExitAction());

        addShowTaskButton();

        workloadTable.setRowSelectionAllowed(false);

        setVisible(true);
    }

    /**
     * 增加管理员查看任务按钮
     */
    private void addShowTaskButton()
    {
        workloadTable.getColumnModel().getColumn(6).setCellEditor(new TaskButtonEditor(AdminFrame.this, getTeacherNum()));
        workloadTable.getColumnModel().getColumn(6).setCellRenderer(new TaskButtonRender());
    }

    /**
     * 更新表格
     */
    public void updateTable()
    {
        workloadTable.setModel(new WorkloadTableModel(teacherOperation.importInformation()));
        workloadTable.getColumnModel().getColumn(6).setCellEditor(new TaskButtonEditor(AdminFrame.this, getTeacherNum()));
        workloadTable.getColumnModel().getColumn(6).setCellRenderer(new TaskButtonRender());
    }

    /**
     * 获取所点击的查看任务按钮行的教师号
     */
    public int getTeacherNum()
    {
        return (int)workloadTable.getValueAt(taskRow, 0);
    }

    public void setTaskRow(int taskRow)
    {
        this.taskRow = taskRow;
    }

    public int getTaskRow()
    {
        return taskRow;
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

    private class InsertAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            new AddTeacherFrame(AdminFrame.this);
        }
    }

    private class RemoveAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String info = JOptionPane.showInputDialog("请输入要删除的教工号");
            if (info == null)
                return;
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
                    int flag = JOptionPane.showConfirmDialog(null,
                            "确认删除信息？", "确认", JOptionPane.YES_NO_OPTION);
                    if (flag == JOptionPane.YES_OPTION)
                    {
                        teacherOperation.removeTeacher(number);
                        updateTable();
                    }
                    else
                        return;
                }
                else
                {
                    JOptionPane.showMessageDialog(null,
                            "教工号不存在！", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    private class ModifyAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String info = JOptionPane.showInputDialog("请输入要修改的教工号");
            if (info == null)
                return;
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
                    new ModifyTeacherFrame(AdminFrame.this, number);
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

    private class SearchAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String info = JOptionPane.showInputDialog("请输入要查找的教工号");
            if (info == null)
                return;
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
                    new SearchTeacherFrame(AdminFrame.this, number);
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

    private class AddAdminAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            new AddAdminFrame(AdminFrame.this);
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