package ui.buttons;

import model.Task;
import model.TaskViewer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// code based on Tool in SimpleDrawingPlayer
// https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Complete.git

public class ShowTaskButton extends TaskButton {

    public ShowTaskButton(TaskViewer taskViewer, JComponent parent) {
        super(taskViewer, parent);
    }

    // MODIFIES: this
    // EFFECTS:  constructs a show task button which is then added to the JComponent (parent)
    //           which is passed in as a parameter
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Show Task");
        addToParent(parent);
    }

    // MODIFIES: this
    // EFFECTS:  constructs a new listener object which is added to the JButton
    @Override
    protected void addListener() {
        button.addActionListener(new ShowTaskClickHandler());
    }

    @Override
    protected void addOkListener() {
        okButton.addActionListener(new AddOkButtonClickHandler());
    }

    private class ShowTaskClickHandler implements ActionListener {

        // EFFECTS: sets active tool to the show task tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            getCommitmentListSelectionPane();
            okButton = new JButton("OK");
            commitmentListFrame.add(okButton);
            addOkListener();
            // show specific tasks from commitment chosen
        }
    }

    // EFFECTS: makes new window to show task list
    private void showTask() {
        JFrame taskFrame = new JFrame("Tasks for " + selectedStringCommitment);
        DefaultListModel<String> taskNameList = new DefaultListModel<>();

        for (Task t : selectedCommitment.getTaskList()) {
            taskNameList.addElement(t.getTaskName());
        }

        JList taskList = new JList(taskNameList);

        taskFrame.add(taskList);
        taskFrame.setSize(200,200);
        taskFrame.setVisible(true);
    }

    private class AddOkButtonClickHandler implements ActionListener {

        // EFFECTS: sets active tool to the show task tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            showTask();
        }
    }


}