package persistence;

// code based on JsonReaderTest from JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

import model.Commitment;
import model.Task;
import model.TaskViewer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            TaskViewer taskViewer = reader.read();
            fail("IOException expected");
        } catch (IOException e) {}
    }

    @Test
    public void testReaderEmptyTaskViewer() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTaskViewer.json");
        try {
            TaskViewer taskViewer = reader.read();
            assertEquals("Annah", taskViewer.getName());
            assertEquals(0, taskViewer.getListOfCommitment().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testReaderGeneralTaskViewer() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralTaskViewer.json");
        try {
            TaskViewer taskViewer = reader.read();
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
