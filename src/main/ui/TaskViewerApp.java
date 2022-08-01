package ui;

import model.Commitment;
import model.Task;
import model.TaskViewer;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

// Task viewer application
public class TaskViewerApp {
    private static final String JSON_STORE = "./data/taskViewer.json";
    private TaskViewer taskViewer;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private String command;

    // EFFECTS: initiates the Task viewer application
    public TaskViewerApp() {
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runApp() {
        boolean quit = false;

        System.out.println("Welcome to the Task Viewer!");
        System.out.println("Please enter your name: ");
        command = input.nextLine();
        taskViewer = new TaskViewer(command);

        while (!quit) {
            initialUserMenu();
            command = input.nextLine();
            if (command.equalsIgnoreCase("q")) {
                quit = true;
            } else {
                while (!(command.equals("v") || command.equals("a") || command.equals("s") || command.equals("l"))) {
                    System.out.println("Input was not valid. Please try again.");
                    command = input.nextLine();
                }
                processInitialCommand(command);
                if (! (command.equals("l") || command.equals("s"))) {
                    command = input.nextLine();
                    processSecondaryCommand(command);
                }
            }
        }
        System.out.println("Thank you for using the Task Viewer " + taskViewer.getName() + "!");
    }

    // EFFECTS: displays initial user menu
    public void initialUserMenu() {
        System.out.println("Choose what you want to do:");
        System.out.println("\tPress \"v\" to view your commitments and tasks.");
        System.out.println("\tPress \"a\" to add a new commitment or task.");
        System.out.println("\tPress \"s\" to save your entries.");
        System.out.println("\tPress \"l\" to load previously saved entries.");
        System.out.println("\tPress \"q\" to quit.");
    }

    // MODIFIES: this
    // EFFECTS: processes initial user command
    public void processInitialCommand(String command) {
        if (command.equalsIgnoreCase("v")) {
            viewerMenu();
        } else if (command.equalsIgnoreCase("a")) {
            addMenu();
        } else if (command.equalsIgnoreCase("s")) {
            saveTaskViewer();
        } else if (command.equalsIgnoreCase("l")) {
            loadTaskViewer();
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user secondary command
    public void processSecondaryCommand(String command) {
        if (command.equalsIgnoreCase("vc")) {
            viewCommitments();
        } else if (command.equalsIgnoreCase("vt")) {
            commitmentSelectionForViewing();
        } else if (command.equalsIgnoreCase("ac")) {
            addCommitment();
        } else if (command.equalsIgnoreCase("at")) {
            commitmentSelectionForAdding();
        } else {
            System.out.println("Invalid input. Please try again.");
            command = input.nextLine();
            processSecondaryCommand(command);
        }
    }

    // EFFECTS: displays viewer menu
    public void viewerMenu() {
        System.out.println("Press \"vc\" to view commitments.");
        System.out.println("Press \"vt\" to view tasks.");
    }

    // EFFECTS: displays add menu
    public void addMenu() {
        System.out.println("Press \"ac\" to add new commitment.");
        System.out.println("Press \"at\" to add new task.");
    }

    // EFFECTS: view commitment names that are currently stored in taskviewer
    public void viewCommitments() {
        int numberList;

        System.out.println("Commitments: ");

        for (int index = 0; index < taskViewer.getListOfCommitment().size(); index++) {
            numberList = index + 1;
            System.out.println(numberList + ". " + taskViewer.getListOfCommitment().get(index).getName());
        }
    }

    // EFFECTS: determines which task list to view from the user input
    public void commitmentSelectionForViewing() {
        int userInputForCommitment;

        System.out.println("Select commitment task list you would like to view: ");
        viewCommitments();

        System.out.println("If you would like to view all tasks, select 0.");
        userInputForCommitment = Integer.parseInt(input.nextLine());
        while (userInputForCommitment > taskViewer.getListOfCommitment().size()) {
            System.out.println("Input invalid. Please try again.");
            userInputForCommitment = Integer.parseInt(input.nextLine());
        }
        viewTask(userInputForCommitment);
    }

    // MODIFIES: this
    // EFFECTS: adds new commitment to task viewer
    public void addCommitment() {
        String commitmentName;
        String category;
        Commitment newCommitment;

        System.out.println("Enter name for commitment: ");
        commitmentName = input.nextLine();
        System.out.println("Enter the category of commitment (class, work, volunteering, etc.): ");
        category = input.nextLine();

        newCommitment = new Commitment(commitmentName, category);
        taskViewer.addCommitment(newCommitment);
        System.out.println("Commitment added successfully!");
    }

    // MODIFIES: this
    // EFFECTS: determines which task list to add to based on user input
    public void commitmentSelectionForAdding() {
        int userInputForCommitment;

        System.out.println("Select commitment task list you would like to add: ");
        viewCommitments();

        userInputForCommitment = Integer.parseInt(input.nextLine());
        addTask(userInputForCommitment);
    }

    // EFFECTS: displays task list for given commitment or overall task list
    public void viewTask(int command) {
        List<Task> specificTaskList;
        if (command == 0) {
            specificTaskList = taskViewer.getListOfAllTasks();
            System.out.println("All tasks in list: ");
        } else {
            specificTaskList = taskViewer.getListOfCommitment().get(command - 1).getTaskList();
            System.out.println("Tasks in " + taskViewer.getListOfCommitment().get(command - 1).getName() + ": ");
        }
        for (Task task:specificTaskList) {
            System.out.println(task.getTaskName() + " due on " + dueDateToString(task.getDueDate()));
        }
        System.out.println("Tasks viewed successfully!");
    }

    // MODIFIES: this
    // EFFECTS: prompts user to enter information for task and creates task in specific commitment
    public void addTask(int command) {
        String taskName;
        String taskType;
        Commitment specifcCommitment = taskViewer.getListOfCommitment().get(command - 1);

        System.out.println("Enter name of task: ");
        taskName = input.nextLine();
        System.out.println("Enter task type: ");
        taskType = input.nextLine();
        Task task = new Task(taskName, taskType);
        setDueDateForTask(task);
        specifcCommitment.addTask(task);
        taskViewer.getListOfAllTasks().add(task);
        System.out.println("Task added successfully!");
    }

    // EFFECTS: converts the calendar object into a string
    public String dueDateToString(Calendar calendar) {
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        return month + "/" + day + "/" + year;
    }

    // EFFECTS: saves task viewer to file
    public void saveTaskViewer() {
        try {
            jsonWriter.open();
            jsonWriter.write(taskViewer);
            jsonWriter.close();
            System.out.println("Successfully saved " + taskViewer.getName() + "'s Task Viewer to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file " + JSON_STORE);
        }
    }

    // EFFECTS: loads task viewer to console
    public void loadTaskViewer() {
        try {
            taskViewer = jsonReader.read();
            System.out.println(taskViewer.getName() + "'s Task Viewer is loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Cannot find file " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: sets due date for a certain task
    private void setDueDateForTask(Task task) {
        int year;
        int month;
        int day;
        boolean validDate = true;
        do {
            if (!validDate) {
                System.out.println("Invalid date, please enter a date after today's date.");
            }
            System.out.println("Enter year due: ");
            year = Integer.parseInt(input.nextLine());
            System.out.println("Enter month due (as a number): ");
            month = Integer.parseInt(input.nextLine());
            System.out.println("Enter day due: ");
            day = Integer.parseInt(input.nextLine());

            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month - 1, day);
            validDate = task.setDate(calendar);
        } while (!validDate);
    }
}
