package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// Represents the overall object that can view all commitments and tasks
public class TaskViewer {
    private String name;
    private List<Commitment> commitmentList;

    // EFFECTS: creates a TaskViewer object that has an empty list of commitments and an empty list of tasks
    public TaskViewer(String name) {
        this.name = name;
        this.commitmentList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: sets task viewer name to given string
    public void setName(String name) {
        this.name = name;
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
            return true;
        }
    }

    // EFFECTS: returns list of commitments in TaskViewer
    public List<Commitment> getListOfCommitment() {
        return this.commitmentList;
    }

    // MODIFIES: this
    // EFFECTS: returns list of tasks
    public List<Task> getListOfAllTasks() {
        List<Task> taskList = new ArrayList<>();
        for (Commitment c: commitmentList) {
            taskList.addAll(c.getTaskList());
        }
        return taskList;
    }

    // EFFECTS: returns name saved with task list
    public String getName() {
        return this.name;
    }

    // code for last two methods based on Workroom from JsonSerializationDemo
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

    // EFFECTS: returns TaskViewer as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("owner", this.name);
        json.put("commitments", commitmentsToJson());
        return json;
    }

    // EFFECTS: returns all commitments in task viewer as a json array
    private JSONArray commitmentsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Commitment c : commitmentList) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }

}
