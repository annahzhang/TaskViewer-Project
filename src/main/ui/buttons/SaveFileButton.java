package ui.buttons;

import model.TaskViewer;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// code based on Tool in SimpleDrawingPlayer
// https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Complete.git

public class SaveFileButton extends Button {
    private static final String JSON_STORE = "./data/taskViewer.json";
    private JsonWriter jsonWriter;

    public SaveFileButton(TaskViewer taskViewer, JComponent parent) {
        super(taskViewer, parent);
        jsonWriter = new JsonWriter(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS:  constructs a save file button which is then added to the JComponent (parent)
    //           which is passed in as a parameter
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Save File");
        addToParent(parent);
    }

    // MODIFIES: this
    // EFFECTS:  constructs a new listener object which is added to the JButton
    @Override
    protected void addListener() {
        button.addActionListener(new SaveFileClickHandler());
    }

    // EFFECTS: saves task viewer to file
    public void saveTaskViewer() {
        try {
            jsonWriter.open();
            jsonWriter.write(taskViewer);
            jsonWriter.close();
            System.out.println("Successfully saved " + taskViewer.getName() + "'s Task Viewer to " + JSON_STORE);
            displaySuccessPage();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file " + JSON_STORE);
        }
    }

    // EFFECTS: displays an image when file is successfully saved
    private void displaySuccessPage() {
        JFrame successFrame = new JFrame("Success!");
        successFrame.setLayout(new FlowLayout());
        successFrame.setSize(100, 100);

        ImageIcon successImage = new ImageIcon("./data/rose.jpg");
        Image image = successImage.getImage();
        Image newImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        successImage = new ImageIcon(newImage);

        JLabel successStatement = new JLabel("Successfully saved!");
        successFrame.add(successStatement);
        successFrame.add(new JLabel(successImage));
        successFrame.pack();
        successFrame.setVisible(true);
    }

    private class SaveFileClickHandler implements ActionListener {

        // EFFECTS: sets active tool to the load file tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            saveTaskViewer();
        }
    }
}