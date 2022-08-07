package ui.buttons;

import model.Task;
import model.TaskViewer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

// code based on Tool in SimpleDrawingPlayer
// https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Complete.git

public class AddTaskButton extends TaskButton {
    private JTextField nameField;
    private JTextField typeField;
    private JTextField yearField;
    private JTextField monthField;
    private JTextField dayField;

    public AddTaskButton(TaskViewer taskViewer, JComponent parent) {
        super(taskViewer, parent);
    }

    // MODIFIES: this
    // EFFECTS:  constructs an add task button which is then added to the JComponent (parent)
    //           which is passed in as a parameter
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Add Task");
        addToParent(parent);
    }

    // MODIFIES: this
    // EFFECTS:  constructs a new listener object which is added to the JButton
    @Override
    protected void addListener() {
        button.addActionListener(new AddTaskClickHandler());
    }

    // MODIFIES: this
    // EFFECTS: constructs a new listener object which is added to the ok JButton
    @Override
    protected void addOkListener() {
        okButton.addActionListener(new AddOkButtonClickHandler());
    }

    private class AddTaskClickHandler implements ActionListener {
        // MODIFIES: this
        // EFFECTS: sets active tool to the add task tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            getCommitmentListSelectionPane();
            okButton = new JButton("OK");
            commitmentListFrame.add(okButton);
            addOkListener();
        }
    }

    // EFFECTS: sets up text input window
    private int setUpOptionPane() {
        nameField = new JTextField(10);
        typeField = new JTextField(10);
        yearField = new JTextField(5);
        monthField = new JTextField(5);
        dayField = new JTextField(5);

        JPanel addTaskPanel = new JPanel();
        addTaskPanel.add(new JLabel("Task Name: "));
        addTaskPanel.add(nameField);
        addTaskPanel.add(new JLabel("Task Type: "));
        addTaskPanel.add(typeField);
        addTaskPanel.add(new JLabel("Year Due: "));
        addTaskPanel.add(yearField);
        addTaskPanel.add(new JLabel("Month Due: "));
        addTaskPanel.add(monthField);
        addTaskPanel.add(new JLabel("Day Due: "));
        addTaskPanel.add(dayField);

        int result = JOptionPane.showConfirmDialog(null, addTaskPanel,
                "Please fill out necessary information", JOptionPane.OK_CANCEL_OPTION);
        return result;
    }

    // EFFECTS: converts the entered dates to a calendar
    private Calendar setDueDateForTask() {
        int year = Integer.parseInt(yearField.getText());
        int month = Integer.parseInt(monthField.getText());
        int day = Integer.parseInt(dayField.getText());
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar;
    }

    private class AddOkButtonClickHandler implements ActionListener {
        // EFFECTS: sets active tool to the add task tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            int result = setUpOptionPane();
            if (result == JOptionPane.OK_OPTION) {
                Task task = new Task(nameField.getText(), typeField.getText());
                Calendar calendar = setDueDateForTask();
                task.setDate(calendar);
                selectedCommitment.addTask(task);
                System.out.println(selectedCommitment.getName());
                System.out.println(task.getTaskName());
                System.out.println(task.getTaskType());
            }
        }
    }
}
