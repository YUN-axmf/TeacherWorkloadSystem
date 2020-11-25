package gui;

import javax.swing.*;

public class TaskButton extends JButton
{
    private int row;
    private int column;
    private int teacherNum;

    public TaskButton(int teacherNum)
    {
        this.teacherNum = teacherNum;
    }

    public int getRow()
    {
        return row;
    }

    public void setRow(int row)
    {
        this.row = row;
    }

    public int getColumn()
    {
        return column;
    }

    public void setColumn(int column)
    {
        this.column = column;
    }

    public int getTeacherNum()
    {
        return teacherNum;
    }
}