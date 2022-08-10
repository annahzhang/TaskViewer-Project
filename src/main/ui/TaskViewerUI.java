package ui;

import model.Event;
import model.EventLog;
import model.TaskViewer;
import ui.buttons.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;

public class TaskViewerUI extends JFrame {
    public static final int WIDTH = 300;
    public static final int HEIGHT = 150;

    private TaskViewer taskViewer;
    private JPanel panel;

    public TaskViewerUI() {
        super("Task Viewer");
        panel = new JPanel();
        initializeGraphics();
    }

    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where this TaskViewer will operate, and populates the buttons to be used
    //           to manipulate this task viewer
    private void initializeGraphics() {
        setLayout(new FlowLayout());
        setSize(WIDTH,HEIGHT);
        panel.setLayout(new GridLayout(0,2));
        add(panel);

        setTaskViewerName();

        createButtons();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event.getDescription());
                }
                System.exit(0);
            }
        });
        setVisible(true);
    }

    // code based on AlarmControllerUI in AlarmSystem
    // https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git

    // MODIFIES: taskViewer
    // EFFECTS: initializes the task viewer object with given name
    private void setTaskViewerName() {
        String name = JOptionPane.showInputDialog(null, "What is your name?",
                "TaskViewer", JOptionPane.QUESTION_MESSAGE);

        taskViewer = new TaskViewer(name);
    }

    // MODIFIES: this
    // EFFECTS:  a helper method which declares and instantiates all buttons
    private void createButtons() {
        AddCommitmentButton addCommitButton = new AddCommitmentButton(taskViewer, panel);
        AddTaskButton addTaskButton = new AddTaskButton(taskViewer, panel);
        ShowCommitmentButton showCommitButton = new ShowCommitmentButton(taskViewer, panel);
        ShowTaskButton showTaskButton = new ShowTaskButton(taskViewer, panel);
        LoadFileButton loadFileButton = new LoadFileButton(taskViewer, panel);
        SaveFileButton saveFileButton = new SaveFileButton(taskViewer, panel);
    }

    public static void main(String[] args) {
        new TaskViewerUI();
    }
}
