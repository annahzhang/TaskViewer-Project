package ui.buttons;

import model.TaskViewer;

import javax.swing.*;

public abstract class Button {
    protected JButton button;
    protected TaskViewer taskViewer;

    public Button(TaskViewer taskViewer, JComponent parent) {
        this.taskViewer = taskViewer;
        createButton(parent);
        addToParent(parent);
        addListener();
    }

    // EFFECTS: creates button to activate tool
    protected abstract void createButton(JComponent parent);

    // MODIFIES: parent
    // EFFECTS: adds the given button to the parent component
    public void addToParent(JComponent parent) {
        parent.add(button);
    }

    // EFFECTS: adds a listener for this tool
    protected abstract void addListener();
}
