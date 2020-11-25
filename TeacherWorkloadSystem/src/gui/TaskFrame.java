package gui;

import database.TaskOperation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * 任务表格类
 * 显示教师的任务列表
 */
public class TaskFrame extends JFrame
{
    public static final int DEFAULT_WIDTH = 600;
    public static final int DEFAULT_HEIGHT = 400;
    private JTable taskTable;
    private JPanel buttonPanel = new JPanel();
    private TaskOperation taskOperation = new TaskOperation();
    private int teacherNum;
    private JButton addTaskButton;
    private JButton removeTaskButton;
    private JButton modifyTaskButton;
    private JButton returnButton;
    private TeacherOperationFrame owner = null;

    public TaskFrame(int teacherNum)
    {
        this.teacherNum = teacherNum;

        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLocationRelativeTo(null);                //居中显示
        setResizable(false);

        setTitle("任务查看");

        TaskTableModel model = new TaskTableModel(taskOperation.importTask(teacherNum));
        taskTable = new JTable(model);

        add(new JScrollPane(taskTable), BorderLayout.CENTER);

        addTaskButton = new JButton("增加任务");
        removeTaskButton = new JButton("删除任务");
        modifyTaskButton = new JButton("修改任务");
        returnButton = new JButton("返回上一级");

        //添加事件
        addTaskButton.addActionListener(new AddTaskAction());
        removeTaskButton.addActionListener(new RemoveTaskAction());
        modifyTaskButton.addActionListener(new ModifyTaskAction());
        returnButton.addActionListener(new ReturnAdminAction());

        buttonPanel.add(addTaskButton);
        buttonPanel.add(removeTaskButton);
        buttonPanel.add(modifyTaskButton);
        buttonPanel.add(returnButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * 更新表格
     */
    public void updateTable()
    {
        taskTable.setModel(new TaskTableModel(taskOperation.importTask(teacherNum)));
    }

    public int getTeacherNum()
    {
        return teacherNum;
    }

    public void setTeacherNum(int teacherNum)
    {
        this.teacherNum = teacherNum;
    }

    /**
     * 设置表格为只读
     * 即没有增、删、改操作
     * 且返回上一级改为返回教师页面
     */
    public void setReadOnly()
    {
        buttonPanel.remove(addTaskButton);
        buttonPanel.remove(removeTaskButton);
        buttonPanel.remove(modifyTaskButton);
        buttonPanel.remove(returnButton);
        JButton returnTeacherButton = new JButton("返回上一级");
        returnTeacherButton.addActionListener(new ReturnTeacherAction());
        buttonPanel.add(returnTeacherButton);
    }

    /**
     * 设置AdminFrame查找操作时表格属性
     * 查找时可以查看任务 此时的任务表格只读
     * 点击返回上一级时 返回的是查找教师信息页面
     */
    public void setSearchProperty()
    {
        remove(buttonPanel);
        JButton returnOperationButton = new JButton("返回上一级");
        returnOperationButton.addActionListener(new ReturnOperationAction());
        JPanel returnButtonPanel = new JPanel();
        returnButtonPanel.add(returnOperationButton);
        add(returnButtonPanel, BorderLayout.SOUTH);
    }

    /**
     * 设置AdminFrame修改时表格属性
     * 点击返回上一级时 返回的是修改教师信息页面
     * 同时对ModifyTeacherFrame进行更新
     */
    public void setModifyProperty(ModifyTeacherFrame owner) throws SQLException, ClassNotFoundException
    {
        this.owner = owner;
        this.setTitle("修改任务");
        buttonPanel.remove(returnButton);
        JButton returnOperationButton = new JButton("返回上一级");
        returnOperationButton.addActionListener(new ReturnOperationAction());
        buttonPanel.add(returnOperationButton);
    }

    private class AddTaskAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            new AddTaskFrame(TaskFrame.this);
        }
    }

    private class RemoveTaskAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {

            int selectedRow = taskTable.getSelectedRow();
            if(selectedRow == -1)
            {
                JOptionPane.showMessageDialog(TaskFrame.this, "没有选择数据!");
            }
            else if (taskOperation.getTasksNum(teacherNum) <= 1)
            {
                JOptionPane.showMessageDialog(TaskFrame.this, "任务数不得少于1!");
            }
            else
            {
                int flag = JOptionPane.showConfirmDialog(null,
                        "确认删除信息？", "确认", JOptionPane.YES_NO_OPTION);
                if(flag == JOptionPane.YES_OPTION)
                {
                    taskOperation.removeTask(teacherNum, taskTable.getValueAt(selectedRow,0).toString());
                    updateTable();
                }
                else
                    return;
            }
        }
    }

    private class ModifyTaskAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String course = JOptionPane.showInputDialog(null, "请输入要修改的课程名");
            if (course == null)
            {
                return;
            }
            if (course.equals(""))
            {
                JOptionPane.showMessageDialog(null,
                        "请输入课程名！", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try
            {
                if (taskOperation.isCourseExisting(teacherNum, course))
                {
                    new ModifyTaskFrame(TaskFrame.this, teacherNum, course);
                }
                else
                {
                    JOptionPane.showMessageDialog(null,
                            "课程不存在！", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }

        }
    }

    /**
     * 返回管理员窗口
     */
    private class ReturnAdminAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            TaskFrame.this.dispose();
            new AdminFrame();
        }
    }

    /**
     * 返回教师窗口
     */
    private class ReturnTeacherAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            TaskFrame.this.dispose();
            new TeacherFrame();
        }
    }

    /**
     * 返回教师操作窗口
     */
    private class ReturnOperationAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            TaskFrame.this.dispose();
            // 若在修改教师信息的修改任务栏中对任务进行修改 在返回上一级时更新修改教师信息框架
            if (owner instanceof ModifyTeacherFrame)
            {
                try
                {
                    ((ModifyTeacherFrame) owner).modifyUpdateData();
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
}