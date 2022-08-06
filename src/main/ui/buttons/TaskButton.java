package ui.buttons;

import model.Commitment;
import model.TaskViewer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public abstract class TaskButton extends Button {
    protected String selectedStringCommitment;
    protected JList commitmentList;
    protected JFrame commitmentListFrame;
    protected JButton okButton;
    protected Commitment selectedCommitment;

    public TaskButton(TaskViewer taskViewer, JComponent parent) {
        super(taskViewer, parent);
    }

    // EFFECTS: creates button to activate tool
    protected abstract void createButton(JComponent parent);

    // EFFECTS: adds a listener for this tool
    protected abstract void addListener();

    // MODIFIES: commitmentList
    // EFFECTS: displays list of commitments
    protected void getCommitmentListSelectionPane() {
        commitmentListFrame = new JFrame("Commitments");
        commitmentListFrame.setLayout(new FlowLayout());
        DefaultListModel<String> commitmentNameList = new DefaultListModel<>();

        for (Commitment c : taskViewer.getListOfCommitment()) {
            commitmentNameList.addElement(c.getName());
        }

        commitmentList = new JList(commitmentNameList);
        commitmentList.addListSelectionListener(new CommitmentListSelectionListener());

        commitmentListFrame.add(commitmentList);
        commitmentListFrame.setSize(200, 200);
        commitmentListFrame.setVisible(true);
    }

    // EFFECTS: adds listener for ok button
    protected abstract void addOkListener();

    protected Commitment convertStringToCommitment(String commitment) {
        for (Commitment c : taskViewer.getListOfCommitment()) {
            if (c.getName().equals(commitment)) {
                return c;
            }
        }
        return null;
    }

    private class CommitmentListSelectionListener implements ListSelectionListener {
        // MODIFIES: selectedCommitment
        // EFFECTS: sets value of selected commitment to the value that is selected
        @Override
        public void valueChanged(ListSelectionEvent e) {
            selectedStringCommitment = (String) commitmentList.getSelectedValue();
            selectedCommitment = convertStringToCommitment(selectedStringCommitment);
        }
    }
}
