package ui.buttons;

import model.Commitment;
import model.TaskViewer;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// code based on Tool in SimpleDrawingPlayer
// https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Complete.git

public class LoadFileButton extends Button {
    private static final String JSON_STORE = "./data/taskViewer.json";
    private JsonReader jsonReader;

    public LoadFileButton(TaskViewer taskViewer, JComponent parent) {
        super(taskViewer, parent);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS:  constructs a load file button which is then added to the JComponent (parent)
    //           which is passed in as a parameter
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Load File");
        addToParent(parent);
    }

    // MODIFIES: this
    // EFFECTS:  constructs a new listener object which is added to the JButton
    @Override
    protected void addListener() {
        button.addActionListener(new LoadFileClickHandler());
    }

    // EFFECTS: loads task viewer to console
    private void loadTaskViewer() {
        try {
            TaskViewer tempTaskViewer = jsonReader.read();
            copyTaskViewer(tempTaskViewer);
            System.out.println(taskViewer.getName() + "'s Task Viewer is loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Cannot find file " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: takes given task viewer and copies it into original task viewer
    private void copyTaskViewer(TaskViewer tempTaskViewer) {
        taskViewer.setName(tempTaskViewer.getName());
        for (Commitment c : tempTaskViewer.getListOfCommitment()) {
            taskViewer.addCommitment(c);
        }
    }

    private class LoadFileClickHandler implements ActionListener {

        // EFFECTS: sets active tool to the load file tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            loadTaskViewer();
        }
    }
}