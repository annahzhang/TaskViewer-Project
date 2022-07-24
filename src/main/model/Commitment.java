package model;

import java.util.ArrayList;
import java.util.List;

// Represents a long term commitment that has tasks
public class Commitment {
    private String name;
    private String category;
    private List<Task> taskList;

    // EFFECTS: creates a Commitment object that has a name, category, and empty list of Tasks
    public Commitment(String name, String category) {
        this.name = name;
        this.category = category;
        this.taskList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: if unique task, adds given Task to the List<Task> and returns true
    //          otherwise, returns false
    public boolean addTask(Task task) {
        if (this.taskList.contains(task)) {
            return false;
        } else {
            this.taskList.add(task);
            return true;
        }
    }

    // EFFECTS: returns the name of the commitment
    public String getName() {
        return this.name;
    }

    // EFFECTS: returns the category ("class", "work", "volunteer") it is in
    public String getCategory() {
        return this.category;
    }

    // EFFECTS: returns list of tasks within a commitment
    public List<Task> getTaskList() {
        return this.taskList;
    }
}
