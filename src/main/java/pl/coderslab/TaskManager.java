package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.time.LocalDate;

public class TaskManager {

    public static void main(String[] args) {
        String[][] tasks = uploadTasks("tasks.csv");

        removeTask(tasks);



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

        scanner.close();

        tasks = Arrays.copyOf(tasks, tasks.length + 1);
        String[] newTask = {description, dueDate, isImportant};
        tasks[tasks.length - 1] = newTask;

        return tasks;
        }

    public static String[][] removeTask (String[][] tasks){

        System.out.println("Please select a number to remove.");
        Scanner scanner = new Scanner(System.in);
        String taskNumber = scanner.nextLine();


        try {
            tasks = ArrayUtils.remove(tasks, Integer.parseInt(taskNumber));
            // add confirmation?
            System.out.println("Task was successfully removed.");
        } catch (IndexOutOfBoundsException e2) {
            System.out.println("Select a number between 0 and " + (tasks.length - 1) + ".");
        } catch (NumberFormatException e1) {
            System.out.println("Task number must be integer.");
        }

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


    }
