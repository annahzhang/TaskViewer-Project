package model;

import java.util.Calendar;

// Represents a task that either must be completed by a certain date or occurs on a certain date
public class Task {
    private String name;
    private Calendar calendar;
    private String type;
    private boolean complete;

    // EFFECTS: creates a Task object that has a name, date set as today, is not complete, and type
    public Task(String name, String type) {
        this.name = name;
        this.calendar = Calendar.getInstance();
        this.type = type;
        this.complete = false;
    }

    // MODIFIES: this
    // EFFECTS: if date has not passed, updates the due date of the task to the given date and returns true
    //          otherwise returns false.
    public boolean setDate(Calendar calendar) {
        if (calendar.before(this.calendar)) {
            return false;
        } else {
            this.calendar = calendar;
            return true;
        }
    }

    // MODIFIES: this
    // EFFECTS: updates the task to complete
    public void complete() {
        this.complete = true;
    }

    // EFFECTS: returns the name of the task
    public String getTaskName() {
        return this.name;
    }

    // EFFECTS: returns the due date of the task
    public Calendar getDueDate() {
        return this.calendar;
    }

    // EFFECTS: returns the type of task ("homework", "exam", "lab")
    public String getTaskType() {
        return this.type;
    }

    // EFFECTS: returns the completion status of task
    public boolean isComplete() {
        return this.complete;
    }
}
