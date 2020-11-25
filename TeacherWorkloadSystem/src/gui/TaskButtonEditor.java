package gui;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.*;

public class TaskButtonEditor extends AbstractCellEditor implements TableCellEditor
{
    private AdminFrame owner = null;
    private TeacherFrame teacherOwner = null;
    private TaskButton taskButton;
    private int teacherNum;

    public TaskButtonEditor(AdminFrame owner, int teacherNum)
    {
        if (owner instanceof TeacherFrame)
            teacherOwner = (TeacherFrame) owner;
        else
            this.owner = owner;
        this.teacherNum = teacherNum;
        taskButton = new TaskButton(teacherNum);
        taskButton.addActionListener(new ShowTaskAction());
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
    {
        taskButton.setRow(row);
        taskButton.setColumn(column);
        return taskButton;
    }

    @Override
    public Object getCellEditorValue()
    {
        return null;
    }

    private class ShowTaskAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (owner != null)
            {
                owner.setTaskRow(taskButton.getRow());
                owner.dispose();
                new TaskFrame(owner.getTeacherNum());
            }
            else
            {
                teacherOwner.setTaskRow(taskButton.getRow());
                teacherOwner.dispose();
                new TaskFrame(teacherOwner.getTeacherNum()).setReadOnly();
            }

        }
    }
}