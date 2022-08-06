package ui.buttons;

import model.Commitment;
import model.TaskViewer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// code based on Tool in SimpleDrawingPlayer
// https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Complete.git

public class AddCommitmentButton extends Button {

    public AddCommitmentButton(TaskViewer taskViewer, JComponent parent) {
        super(taskViewer, parent);
    }

    // MODIFIES: this
    // EFFECTS:  constructs an add commitment button which is then added to the JComponent (parent)
    //           which is passed in as a parameter
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Add Commitment");
        addToParent(parent);
    }

    // MODIFIES: this
    // EFFECTS:  constructs a new listener object which is added to the JButton
    @Override
    protected void addListener() {
        button.addActionListener(new AddCommitmentClickHandler());
    }

    // MODIFIES: taskViewer
    // EFFECTS: makes a commitment based on user input
    private void addCommitment() {
        JTextField nameField = new JTextField(10);
        JTextField categoryField = new JTextField(10);

        JPanel commitmentPanel = new JPanel();
        commitmentPanel.add(new JLabel("Commitment Name: "));
        commitmentPanel.add(nameField);
        commitmentPanel.add(Box.createHorizontalStrut(15));
        commitmentPanel.add(new JLabel("Category: "));
        commitmentPanel.add(categoryField);

        int result = JOptionPane.showConfirmDialog(null, commitmentPanel,
                "Please fill out necessary information", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            Commitment commitment = new Commitment(nameField.getText(), categoryField.getText());
            taskViewer.addCommitment(commitment);
            System.out.println(commitment.getName());
            System.out.println(commitment.getCategory());
        }
    }

    private class AddCommitmentClickHandler implements ActionListener {

        // EFFECTS: sets active tool to the add commitment tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            addCommitment();
        }
    }
}
