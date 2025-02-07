package pl.coderslab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {

    public static void main(String[] args) {
        String[][] tasks = uploadTasks("tasks.csv");
        printTasks(tasks);



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

    public static void printTasks(String[][] tasks) {
        for (int i = 0; i < tasks.length; i++) {
            for (int j = 0; j < tasks[i].length; j++) {
                System.out.print(tasks[i][j] + " ");
            }
            System.out.println();
        }
    }


}