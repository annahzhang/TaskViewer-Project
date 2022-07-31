package persistence;

import model.Commitment;
import model.Task;
import model.TaskViewer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// code based on JsonWriterTest from JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public class JsonWriterTest extends JsonTest {

    @Test
    public void testWriterInvalidFile() {
        try {
            TaskViewer taskviewer = new TaskViewer("Annah");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {}
    }

    @Test
    public void testWriterEmptyTaskViewer() {
        try {
            TaskViewer taskViewer = new TaskViewer("Annah");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTaskViewer.json");
            writer.open();
            writer.write(taskViewer);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTaskViewer.json");
            taskViewer = reader.read();
            assertEquals("Annah", taskViewer.getName());
            assertEquals(0, taskViewer.getListOfCommitment().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriterGeneralTaskViewer() {
        try {
            TaskViewer taskViewer = new TaskViewer("Annah");
            Commitment commitment1 = new Commitment("CPSC 210", "class");
            Commitment commitment2 = new Commitment("Hospital Volunteer", "volunteer");
            Task task1 = new Task("Lab 1", "lab");
            Task task2 = new Task("Phase 1 Project", "project");
            Task task3 = new Task("Volunteer Orientation", "homework");
            Task task4 = new Task("Session 1", "work");
            Calendar calendar1 = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();
            Calendar calendar3 = Calendar.getInstance();
            Calendar calendar4 = Calendar.getInstance();
            calendar1.set(2022, 8, 5);
            calendar2.set(2022, 10, 2);
            calendar3.set(2022, 9, 10);
            calendar4.set(2023, 1, 13);
            task1.setDate(calendar1);
            task2.setDate(calendar2);
            task3.setDate(calendar3);
            task4.setDate(calendar4);

            commitment1.addTask(task1);
            commitment1.addTask(task2);
            commitment2.addTask(task3);
            commitment2.addTask(task4);
            taskViewer.addCommitment(commitment1);
            taskViewer.addCommitment(commitment2);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralTaskViewer.json");
            writer.open();
            writer.write(taskViewer);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralTaskViewer.json");
            taskViewer = reader.read();
            assertEquals("Annah", taskViewer.getName());
            List<Commitment> commitmentList = taskViewer.getListOfCommitment();
            assertEquals(2, commitmentList.size());
            checkCommitment("CPSC 210", "class", commitmentList.get(0));
            checkCommitment("Hospital Volunteer", "volunteer", commitmentList.get(1));
            List<Task> taskList1 = commitmentList.get(0).getTaskList();
            List<Task> taskList2 = commitmentList.get(1).getTaskList();
            checkTask("Lab 1", "lab", "9/5/2022", false, taskList1.get(0));
            checkTask("Phase 1 Project", "project", "11/2/2022", false, taskList1.get(1));
            checkTask("Volunteer Orientation", "homework", "10/10/2022", false, taskList2.get(0));
            checkTask("Session 1", "work", "2/13/2023", false, taskList2.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
