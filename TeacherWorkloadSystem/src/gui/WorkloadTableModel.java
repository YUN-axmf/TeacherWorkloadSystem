package gui;

import list.TeacherList;

import javax.swing.table.AbstractTableModel;

/**
 * 工作量表格模板类
 */
public class WorkloadTableModel extends AbstractTableModel
{
    private int row;
    private static final int COLUMN = 7;
    private TeacherList teacherList;

    public WorkloadTableModel(TeacherList teacherList)
    {
        row = teacherList.length();
        this.teacherList = teacherList;
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
        Object value = teacherList.getTeachers()[rowIndex].getValue(columnIndex);
        return value;
    }

    @Override
    public String getColumnName(int column)
    {
        return teacherList.getTeachers()[0].getInfo(column);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        if (columnIndex == 6)
            return true;
        else
            return false;
    }
}