package ui.buttons;

import model.Commitment;
import model.TaskViewer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// code based on Tool in SimpleDrawingPlayer
// https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Complete.git

public class ShowCommitmentButton extends Button {

    public ShowCommitmentButton(TaskViewer taskViewer, JComponent parent) {
        super(taskViewer, parent);
    }

    // MODIFIES: this
    // EFFECTS:  constructs a show commitment button which is then added to the JComponent (parent)
    //           which is passed in as a parameter
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Show Commitment");
        addToParent(parent);
    }

    // MODIFIES: this
    // EFFECTS:  constructs a new listener object which is added to the JButton
    @Override
    protected void addListener() {
        button.addActionListener(new ShowCommitmentClickHandler());
    }

    // EFFECTS: makes new window to display list of commitments
    public void showCommitments() {
        JFrame commitmentListFrame = new JFrame("Commitments");
        DefaultListModel<String> commitmentNameList = new DefaultListModel<>();

        for (Commitment c : taskViewer.getListOfCommitment()) {
            commitmentNameList.addElement(c.getName());
        }

        JList commitmentList = new JList(commitmentNameList);

        commitmentListFrame.add(commitmentList);
        commitmentListFrame.setSize(200, 200);
        commitmentListFrame.setVisible(true);
    }

    private class ShowCommitmentClickHandler implements ActionListener {

        // EFFECTS: sets active tool to the show commitment tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            showCommitments();
        }
    }


}
