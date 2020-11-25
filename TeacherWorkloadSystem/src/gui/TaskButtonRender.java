package gui;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class TaskButtonRender implements TableCellRenderer
{
    private JButton taskButton;

    public TaskButtonRender()
    {
        taskButton = new JButton("查看任务");
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        return taskButton;
    }
}