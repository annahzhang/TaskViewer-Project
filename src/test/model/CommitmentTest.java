package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class CommitmentTest {
    private Commitment commitmentTest1;
    private Task task1;
    private Task task2;
    private Task task3;
    private Calendar calendar;

    @BeforeEach
    public void setUp() {
        this.calendar = Calendar.getInstance();
        commitmentTest1 = new Commitment("CPSC 210", "class");
        task1 = new Task("Assignment 1", "Homework");
        task2 = new Task("Lab 1", "Lab");
        task3 = new Task("Midterm 1", "Exam");
    }

    @Test
    public void testCommitment() {
        assertEquals("CPSC 210", commitmentTest1.getName());
        assertEquals("class", commitmentTest1.getCategory());
        assertEquals(0, commitmentTest1.getTaskList().size());
    }

    @Test
    public void testAddOneTask() {
        assertTrue(commitmentTest1.addTask(task1));
        assertEquals(1, commitmentTest1.getTaskList().size());
        assertEquals(task1, commitmentTest1.getTaskList().get(0));
    }

    @Test
    public void testAddSameTaskTwice() {
        assertTrue(commitmentTest1.addTask(task1));
        assertFalse(commitmentTest1.addTask(task1));

        assertEquals(1, commitmentTest1.getTaskList().size());
        assertEquals(task1, commitmentTest1.getTaskList().get(0));
    }

    @Test
    public void testAddDifferentTasks() {
        assertTrue(commitmentTest1.addTask(task1));
        assertTrue(commitmentTest1.addTask(task2));
        assertTrue(commitmentTest1.addTask(task3));

        assertEquals(3, commitmentTest1.getTaskList().size());
        assertEquals(task1, commitmentTest1.getTaskList().get(0));
        assertEquals(task2, commitmentTest1.getTaskList().get(1));
        assertEquals(task3, commitmentTest1.getTaskList().get(2));
    }
}