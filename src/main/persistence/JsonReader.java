package persistence;


// code based on JsonReader from JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

import model.Commitment;
import model.Task;
import model.TaskViewer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.stream.Stream;

// Represents a reader that reads a task viewer from JSON data stored in file
public class JsonReader {
    private String source;

    // constructs a reader that can read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads task viewer from file and returns it;
    // throws IOException if an error occurs reading data from file
    public TaskViewer read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTaskViewer(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses task viewer from JSON ojbect and returns it
    private TaskViewer parseTaskViewer(JSONObject jsonObject) {
        String name = jsonObject.getString("owner");
        TaskViewer taskViewer = new TaskViewer(name);
        addCommitments(taskViewer, jsonObject);
        return taskViewer;
    }

    // MODIFIES: taskViewer
    // EFFECTS: parses commitments from JSON object and adds them to task viewer
    private void addCommitments(TaskViewer taskViewer, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("commitments");
        for (Object json : jsonArray) {
            JSONObject nextCommitment = (JSONObject) json;
            addCommitment(taskViewer, nextCommitment);
        }
    }

    // MODIFIES: taskViewer
    // EFFECTS: parses commitment from JSON object and adds it task viewer
    // and parses tasks from JSON object and adds it to commitment
    private void addCommitment(TaskViewer taskViewer, JSONObject jsonObject) {
        String name = jsonObject.getString("commitment");
        String category = jsonObject.getString("category");
        Commitment commitment = new Commitment(name, category);
        JSONArray jsonArray = jsonObject.getJSONArray("tasks");
        for (Object json : jsonArray) {
            JSONObject nextTask = (JSONObject) json;
            addTask(commitment, nextTask);
        }
        taskViewer.addCommitment(commitment);
    }

    // MODIFIES: commitment, taskViewer
    // EFFECTS: parses task from JSON object and adds it to commitment
    private void addTask(Commitment commitment, JSONObject jsonObject) {
        String name = jsonObject.getString("task");
        String calendar = jsonObject.getString("due date");
        String type = jsonObject.getString("type");
        Boolean complete = jsonObject.getBoolean("complete?");
        Task task = new Task(name, type);
        task.setDate(convertToCalendar(calendar));
        commitment.addTask(task);
    }

    // MODIFIES: task, commitment, taskViewer
    // EFFECTS: converts the calendar string back into calendar object
    private Calendar convertToCalendar(String calendar) {
        int firstSlash = calendar.indexOf("/");
        int secondSlash = calendar.lastIndexOf("/");
        int month = Integer.parseInt(calendar.substring(0, firstSlash));
        int date = Integer.parseInt(calendar.substring(firstSlash + 1, secondSlash));
        int year = Integer.parseInt(calendar.substring(secondSlash + 1));
        Calendar calendarFinal = Calendar.getInstance();
        calendarFinal.set(year, month - 1, date);
        return calendarFinal;
    }
}
