package ui;

import model.Commitment;
import model.Task;
import model.TaskViewer;

import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

// Task viewer application
public class TaskViewerApp {
    private TaskViewer taskViewer;
    private Scanner input;

    // EFFECTS: initiates the Task viewer application
    public TaskViewerApp() {
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runApp() {
        boolean quit = false;
        String command;
        input = new Scanner(System.in);
        taskViewer = new TaskViewer();

        System.out.println("Welcome to the Task Viewer!");

        while (!quit) {
            initialUserMenu();
            command = input.nextLine();
            if (command.equalsIgnoreCase("q")) {
                quit = true;
            } else {
                while (!(command.equalsIgnoreCase("v") || command.equalsIgnoreCase("a"))) {
                    System.out.println("Input was not valid. Please try again.");
                    command = input.nextLine();
                }
                processInitialCommand(command);
                command = input.nextLine();
                processSecondaryCommand(command);
            }
        }
        System.out.println("Thank you for using the Task Viewer!");
    }

    // EFFECTS: displays initial user menu
    public void initialUserMenu() {
        System.out.println("Choose what you want to do:");
        System.out.println("\tPress \"v\" to view your commitments and tasks.");
        System.out.println("\tPress \"a\" to add a new commitment or task.");
        System.out.println("\tPress \"q\" to quit.");
    }

    // MODIFIES: this
    // EFFECTS: processes initial user command
    public void processInitialCommand(String command) {
        if (command.equalsIgnoreCase("v")) {
            viewerMenu();
        } else if (command.equalsIgnoreCase("a")) {
            addMenu();
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
            specificTaskList = taskViewer.getListofAllTasks();
        } else {
            specificTaskList = taskViewer.getListOfCommitment().get(command - 1).getTaskList();
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
        int year;
        int month;
        int day;
        Commitment specifcCommitment = taskViewer.getListOfCommitment().get(command - 1);

        System.out.println("Enter name of task: ");
        taskName = input.nextLine();
        System.out.println("Enter task type: ");
        taskType = input.nextLine();
        System.out.println("Enter year due: ");
        year = Integer.parseInt(input.nextLine());
        System.out.println("Enter month due (as a number): ");
        month = Integer.parseInt(input.nextLine());
        System.out.println("Enter day due: ");
        day = Integer.parseInt(input.nextLine());

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        Task task = new Task(taskName, taskType);
        task.setDate(calendar);
        specifcCommitment.addTask(task);
        taskViewer.getListofAllTasks().add(task);
        System.out.println("Task added successfully!");
    }

    // EFFECTS: converts the calendar object into a string
    public String dueDateToString(Calendar calendar) {
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        return month + "/" + day + "/" + year;
    }
}