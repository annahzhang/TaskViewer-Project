package persistence;

import model.Commitment;
import model.Task;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

// code based on JsonTest from JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public class JsonTest {
    protected void checkTask(String name, String type, String dueDate, Boolean complete, Task task) {
        assertEquals(name, task.getTaskName());
        assertEquals(type, task.getTaskType());
        assertEquals(dueDate, task.dueDateToString());
        assertEquals(complete, task.isComplete());
    }

    protected void checkCommitment(String name, String category, Commitment commitment) {
        assertEquals(name, commitment.getName());
        assertEquals(category, commitment.getCategory());
    }
}
