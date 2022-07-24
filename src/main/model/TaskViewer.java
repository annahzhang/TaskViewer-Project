package model;

import java.util.ArrayList;
import java.util.List;

// Represents the overall object that can view all commitments and tasks
public class TaskViewer {
    private List<Commitment> commitmentList;
    private List<Task> taskList;

    // EFFECTS: creates a TaskViewer object that has an empty list of commitments and an empty list of tasks
    public TaskViewer() {
        this.commitmentList = new ArrayList<>();
        this.taskList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: if unique commitment, adds commitment to commitment list, add the task list to total task list
    //          and return true
    //          otherwise, return false
    public boolean addCommitment(Commitment commitment) {
        if (this.commitmentList.contains(commitment)) {
            return false;
        } else {
            this.commitmentList.add(commitment);
            this.taskList.addAll(commitment.getTaskList());
            return true;
        }
    }

    // EFFECTS: returns list of commitments in TaskViewer
    public List<Commitment> getListOfCommitment() {
        return this.commitmentList;
    }

    // MODIFIES: this
    // EFFECTS: returns list of tasks
    public List<Task> getListofAllTasks() {
        return this.taskList;
    }
}
