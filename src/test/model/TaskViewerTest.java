package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for TaskViewer class
public class TaskViewerTest {
    private TaskViewer taskViewerTest1;
    private Commitment commitment1;
    private Commitment commitment2;
    private Task task1;
    private Task task2;
    private Task task3;
    private Task task4;
    private Calendar calendar1;
    private Calendar calendar2;
    private Calendar calendar3;
    private Calendar calendar4;

    @BeforeEach
    public void setUp() {
        taskViewerTest1 = new TaskViewer();
        commitment1 = new Commitment("CPSC 210", "class");
        commitment2 = new Commitment("Isaac Lab", "work");

        task1 = new Task("Assignment 1", "Homework");
        calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DAY_OF_MONTH, 7);
        task1.setDate(calendar1);

        task2 = new Task("Lab 1", "Lab");
        calendar2 = Calendar.getInstance();
        calendar2.add(Calendar.DAY_OF_MONTH, 2);
        task2.setDate(calendar2);

        task3 = new Task("Review Paper Screening", "Homework");
        calendar3 = Calendar.getInstance();
        calendar3.add(Calendar.MONTH, 1);
        task3.setDate(calendar3);

        task4 = new Task("Examlet 2", "Exam");
        calendar4 = Calendar.getInstance();
        calendar4.add(Calendar.DAY_OF_MONTH, 5);
        task4.setDate(calendar4);

        commitment1.addTask(task1);
        commitment1.addTask(task2);
        commitment1.addTask(task3);
        commitment2.addTask(task4);
    }

    @Test
    public void testTaskViewer() {
        assertEquals(0,taskViewerTest1.getListOfCommitment().size());
        assertEquals(0, taskViewerTest1.getListofAllTasks().size());
    }

    @Test
    public void testAddOneCommitment() {
        assertTrue(taskViewerTest1.addCommitment(commitment1));
        assertEquals(commitment1, taskViewerTest1.getListOfCommitment().get(0));
        assertEquals(1, taskViewerTest1.getListOfCommitment().size());

        assertEquals(commitment1.getTaskList(), taskViewerTest1.getListofAllTasks());
        assertEquals(3, taskViewerTest1.getListofAllTasks().size());
    }

    @Test
    public void testAddSameCommitment() {
        assertTrue(taskViewerTest1.addCommitment(commitment1));
        assertFalse(taskViewerTest1.addCommitment(commitment1));

        assertEquals(commitment1, taskViewerTest1.getListOfCommitment().get(0));
        assertEquals(1, taskViewerTest1.getListOfCommitment().size());

        assertEquals(commitment1.getTaskList(), taskViewerTest1.getListofAllTasks());
        assertEquals(3, taskViewerTest1.getListofAllTasks().size());
    }

    @Test
    public void testAddDifferentCommitment() {
        List<Task> totalTaskList = new ArrayList<>();
        totalTaskList.addAll(commitment1.getTaskList());
        totalTaskList.addAll(commitment2.getTaskList());

        assertTrue(taskViewerTest1.addCommitment(commitment1));
        assertTrue(taskViewerTest1.addCommitment(commitment2));

        assertEquals(commitment1, taskViewerTest1.getListOfCommitment().get(0));
        assertEquals(commitment2, taskViewerTest1.getListOfCommitment().get(1));
        assertEquals(2, taskViewerTest1.getListOfCommitment().size());

        assertEquals(totalTaskList, taskViewerTest1.getListofAllTasks());
        assertEquals(4, taskViewerTest1.getListofAllTasks().size());
    }
}
