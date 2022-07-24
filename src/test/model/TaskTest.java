package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for Task class
public class TaskTest {
    private Task taskTest1;
    private Calendar calendar1;
    private Calendar calendar2;
    private Calendar calendar3;

    @BeforeEach
    public void setUp() {
        calendar1 = Calendar.getInstance();
        taskTest1 = new Task("Assignment 1","Homework");
        calendar2 = Calendar.getInstance();
        calendar2.set(2022, Calendar.NOVEMBER, 1);
        calendar3 = Calendar.getInstance();
        calendar3.set(2020, Calendar.MARCH, 23);
    }

    @Test
    public void testTask() {
        assertEquals("Assignment 1", taskTest1.getTaskName());
        assertEquals(calendar1, taskTest1.getDueDate());
        assertEquals("Homework", taskTest1.getTaskType());
        assertFalse(taskTest1.isComplete());
    }

    @Test
    public void testSetCalendarValid() {
        assertTrue(taskTest1.setDate(calendar2));
        assertEquals(calendar2, taskTest1.getDueDate());
    }

    @Test
    public void testSetCalendarInvalid() {
        assertFalse(taskTest1.setDate(calendar3));
    }

    @Test
    public void testComplete() {
        taskTest1.complete();
        assertTrue(taskTest1.isComplete());
    }
}
