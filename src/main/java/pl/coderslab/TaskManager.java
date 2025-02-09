package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {

    public static void main(String[] args) {

        boolean runTaskManager = true;

        String taskFile = "tasks2.csv";
        String[][] tasks = uploadTasks(taskFile);
        System.out.println(ConsoleColors.BLUE + "Welcome to Task Manager");

        Scanner scanner = new Scanner(System.in);

        while (runTaskManager) {
            System.out.println(ConsoleColors.BLUE + "Please select an option:");
            System.out.println(ConsoleColors.RESET + "add\nremove\nlist\nexit");
            String option = scanner.nextLine();

            if (option.equals("add")) {
                tasks = addTask(tasks);
                saveTasks(tasks, taskFile);
            } else if (option.equals("remove")) {
                tasks = removeTask(tasks);
                saveTasks(tasks, taskFile);
            } else if (option.equals("list")) {
                listTasks(tasks);
            } else if (option.equals("exit")) {
                System.out.println(ConsoleColors.RED + "Bye");
                runTaskManager = false;
            } else {
                System.out.println(ConsoleColors.RED + "Invalid option");
            }
        }

    }


    public static String[][] uploadTasks(String fileName) {
        File file = new File(fileName);

        String[][] tasks = new String[0][3];

        try {
            Scanner scan = new Scanner(file);
            int i = 0;
            while (scan.hasNextLine()) {
                tasks = Arrays.copyOf(tasks, tasks.length + 1);
                tasks[i] = scan.nextLine().split(",");
                i++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        return tasks;
    }

    public static void listTasks(String[][] tasks) {
        for (int i = 0; i < tasks.length; i++) {
            System.out.print(i + " : ");
            for (int j = 0; j < tasks[i].length; j++) {
                System.out.print(tasks[i][j] + " ");
            }
            System.out.println();
        }
    }


    public static String[][] addTask(String[][] tasks) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please add task description.");
        String description = scanner.nextLine();

        System.out.println("Please add task due date.");
        //check the date format
        String dueDate = scanner.nextLine();
        while (! isValidDate(dueDate)) {
            System.out.println("Invalid due date format. Must be yyyy-mm-dd.");
            dueDate = scanner.nextLine();
        }

        System.out.println("Is your task important? true/false");
        // check if equals true or false
        String isImportant = scanner.nextLine();
        while (!((isImportant.equals("true")) || (isImportant.equals("false")))) {
            System.out.println("Enter true or false.");
            isImportant = scanner.nextLine();
        }

        //scanner.close();

        tasks = Arrays.copyOf(tasks, tasks.length + 1);
        String[] newTask = {description, dueDate, isImportant};
        tasks[tasks.length - 1] = newTask;

        System.out.println("Task added successfully.");

        return tasks;
        }

    public static String[][] removeTask (String[][] tasks){

        System.out.println("Please select a number to remove.");

        Scanner scanner = new Scanner(System.in);
        String taskNumber = scanner.nextLine();

        try {
            int taskNumberInt = Integer.parseInt(taskNumber);
            String[] taskToRemove = tasks[taskNumberInt];

            System.out.println("Are you sure you want to remove task " + taskNumber + "? (yes/no)");
            String confirmation = scanner.nextLine();
            while (!((confirmation.equals("yes")) || (confirmation.equals("no")))) {
                System.out.println("Enter yes or no.");
                confirmation = scanner.nextLine();
            }

            if (confirmation.equals("yes")) {
                tasks = ArrayUtils.remove(tasks, taskNumberInt);
                System.out.println("Task " + taskNumber + "was successfully removed.");
            } else {
                System.out.println("Task " + taskNumber + " was NOT removed.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Please enter an integer number.");
        }
        catch (IndexOutOfBoundsException e1) {
            System.out.println("Select a number between 0 and " + (tasks.length - 1) + ".");
        }

        //scanner.close();

        return tasks;

    }


    public static boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }


    public static void saveTasks(String[][] tasks, String fileName) {
        try (PrintWriter printWriter = new PrintWriter(fileName)) {

            for (int i = 0; i < tasks.length; i++) {
                printWriter.println(String.join(",", tasks[i]));
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Error - file not saved.");
        }
    }


    }
