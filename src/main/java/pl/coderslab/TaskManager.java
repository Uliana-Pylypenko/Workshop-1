package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {

    public static void main(String[] args) {
        String[][] tasks = uploadTasks("tasks.csv");
        listTasks(tasks);

        String[] numbers = {"1", "one"};
        System.out.println(NumberUtils.isParsable(numbers[0]));
        System.out.println(NumberUtils.isParsable(numbers[1]));

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
        String dueDate = scanner.nextLine();
        System.out.println("Is your task important? true/false");
        String isImportant = scanner.nextLine();
        scanner.close();

        tasks = Arrays.copyOf(tasks, tasks.length + 1);
        String[] newTask = {description, dueDate, isImportant};
        tasks[tasks.length-1] = newTask;

        return tasks;
    }

    public static String[][] removeTask(String[][] tasks) {

        System.out.println("Please select a number to remove.");
        Scanner scanner = new Scanner(System.in);
        int taskNumber = scanner.nextInt();
        scanner.close();
        tasks = ArrayUtils.remove(tasks, taskNumber);
        System.out.println("Task was successfully removed.");
        return tasks;
    }


}