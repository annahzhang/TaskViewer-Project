package model;

import org.json.JSONArray;
import org.json.JSONObject;

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
            EventLog.getInstance().logEvent(new Event("added a task named " + task.getTaskName() + " to " + this.name));
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
        EventLog.getInstance().logEvent(new Event("displayed task list for " + this.name));
        return this.taskList;
    }

    // code for last two methods based on Workroom from JsonSerializationDemo
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

    // EFFECTS: returns commitment as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("commitment", this.name);
        json.put("category", this.category);
        json.put("tasks", tasksToJson());
        return json;
    }

    // EFFECTS: returns tasks in commitment as a JSON array
    private JSONArray tasksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Task t: taskList) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}
