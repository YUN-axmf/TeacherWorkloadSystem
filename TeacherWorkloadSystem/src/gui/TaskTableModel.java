package gui;

import list.TeachingTask;

import javax.swing.table.AbstractTableModel;

/**
 * 任务表格模板类
 */
public class TaskTableModel extends AbstractTableModel
{
    private int row;
    private static final int COLUMN = 6;
    private TeachingTask[] teachingTasks;

    public TaskTableModel(TeachingTask[] teachingTasks)
    {
        row = teachingTasks.length;
        this.teachingTasks = teachingTasks;
    }

    @Override
    public int getRowCount()
    {
        return row;
    }

    @Override
    public int getColumnCount()
    {
        return COLUMN;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Object value = teachingTasks[rowIndex].getValue(columnIndex);
        return value;
    }

    @Override
    public String getColumnName(int column)
    {
        return teachingTasks[0].getName(column);
    }
}