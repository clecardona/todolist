package com.clecardona.menu;

import com.clecardona.tasks.Task;
import com.clecardona.tools.Comparator;
import com.clecardona.tools.UserInputChecker;

import java.io.*;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class Menu implements Serializable {

    @Serial
    private static final long serialVersionUID = 0L;

 ///////////////////fields
    private LinkedList<Task> listOfTasks;
    private final Scanner scanner;

    /**
     * Constructor with no args
     */
    public Menu() {

        this.listOfTasks = new LinkedList<>();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Constructor that takes a LinkedList as arg.
     */
    public Menu(LinkedList<Task> list) {

        this.listOfTasks = list;
        this.scanner = new Scanner(System.in);
    }

    ///////////////////methods

////MAIN MENU

////displays
    /**
     * Prints out the opening message for the user
     */
    public void sayWelcome() {
        int taskDone = countDone(listOfTasks);
        int taskToDo = listOfTasks.size() - taskDone;

        System.out.println();
        System.out.println("Welcome to [todolist]                       -                                       2021 - © Clement Cardona\n" +
                "You have (" + taskToDo + ") tasks todo and (" + taskDone + ") tasks are done !");

    }

    /**
     * Prints out a goodbye message
     */
    public void sayGoodbye() {
        System.out.println();
        System.out.println("Goodbye and Thank you for using [todolist]");
    }

    /**
     * Prints out the main menu
     */
    public void displayMenu() {
        System.out.println();
        System.out.println(
                """
                        Main Menu :
                         > (1) Show and sort tasks list\s
                         > (2) Add new Task
                         > (3) Edit Task (update, mark as done, remove)
                         < (4) Save and Quit""");
        System.out.println("Pick an option: ");

    }


////others
    /**
     * Returns the the number of task "done"
     */
    public int countDone(LinkedList<Task> listOfTasks) {
        int count = 0;
        for (Task task : listOfTasks) {
            if (task.getStatusBoolean()) {
                count++;
            }
        }
        return count;
    }


    /**
     * Process a command from main menu by scanning user's input
     */
    public boolean processCommand() {

        boolean askedToQuit = false;


        try {

            int userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1 -> printDisplayTaskMenu(0);
                case 2 -> {
                    addNewTask(addNewTaskGatherer());
                    System.out.println("The task has been added successfully ");
                }
                case 3 -> editTask(0);
                case 4 -> {
                    saveToFile();
                    askedToQuit = true;
                }
                default -> System.out.println("\n Enter a valid number");
            }
        } catch (InputMismatchException i) {
            System.out.println("\n Enter a valid number");
        }
        return askedToQuit;

    }

////SORTING MENU
    /**
     * Display all tasks  display sorting menu, process sorting by title,project or due date.
     *
     * @param depth : limit of number of recursions allowed.  (recursive method)
     */
    public void printDisplayTaskMenu(int depth) {
                System.out.println("""
                   > (1) Sort By Due Date (earlier first)
                   > (2) Sort By Project (A-Z)
                   > (3) Sort By Status (done - todo)
                   < (4) Back to MAIN menu
                    Pick an option :                    
                """);

        if (listOfTasks.size() == 0) {
            System.out.println("\n No task found");
        }

        if (depth >= 10) { //recursion call will be allowed max 10 times
            return;
        }

        try {
            int userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1 -> {
                    sortedListToString(sortBy('d'),'d');
                    depth++;
                    printDisplayTaskMenu(depth);
                }
                case 2 -> {
                    sortedListToString(sortBy('p'),'p');
                    depth++;
                    printDisplayTaskMenu(depth);
                }
                case 3 -> {
                    sortedListToString(sortBy('s'),'s');
                    depth++;
                    printDisplayTaskMenu(depth);
                }
                    case 4 -> System.out.println();
                    default -> System.out.println("\n Enter valid number");
                }
        } catch (InputMismatchException i) {
            System.out.println("\n Enter valid number");
            depth++;
            printDisplayTaskMenu(depth);
        }
    }

    /**
     * Sort the task by selected mode
     * @param mode: the mode selected ('d':due date , 'p':project , 's':status )
     */
    public LinkedList<Task> sortBy(char mode) {
        LinkedList<Task> sortedListOfTasks = listOfTasks;
        switch (mode) {

            case 'p' -> {
                sortedListOfTasks.sort(new Comparator('p'));
            }
            case 'd' -> {

                sortedListOfTasks.sort(new Comparator('d'));
            }
            case 's' -> {

                sortedListOfTasks.sort(new Comparator('s'));
            }
        }
        return sortedListOfTasks;
    }

    /**
     * Prints the correct data corresponding to the sorted mode selected
     * @param list: the sorted list to print
     * @param mode: the mode selected ('d':due date , 'p':project , 's':status )
     */
    public void sortedListToString(LinkedList<Task> list, char mode) {
        System.out.println();
        String space =" ";// used to format the displayed result
        int i=1;

        for (Task task : list) {

            switch (mode) {
                case 'p' -> {

                    String align = space.repeat(40-task.getProject().length());//formatting
                    System.out.println(i+". Project: "+task.getProject() + align + task.toString());
                    i++;
                }
                case 'd' -> {
                    String align = space.repeat(40-task.getDueDate().length());//formatting
                    System.out.println(i+". Due date: "+task.getDueDate() + align + task.toString());
                    i++;
                }
                case 's' -> {
                    String align = space.repeat(40-task.getStatusString().length());//formatting
                    System.out.println(i+". Status: "+task.getStatusString() + align + task.toString());
                    i++;
                }
            }

        }
        System.out.println();
    }


////ADD MENU
    /**
     * Get user input for adding a new task
     *
     * @return an array with user input data
     */
    public String[] addNewTaskGatherer() {

        String[] newData = new String[3];

        System.out.println("Enter Title :  ");
        String title = scanner.nextLine();
        newData[0] = UserInputChecker.getValidString(title, "Title");

        System.out.println("Enter Project related :  ");
        String project = scanner.nextLine();
        newData[1] = UserInputChecker.getValidString(project, "Project");

        System.out.println("Enter date (YYYY-MM-DD) :");
        String date = scanner.nextLine();
        newData[2] = UserInputChecker.getValidDate(date);

        return newData;
}

    /**
     * Add a new task to the list.
     * boolean isDone is set to false by default when a task is added
     * @param newData: array returned by addNewTaskGatherer() method.
     */
    public void addNewTask(String[] newData) {
        listOfTasks.add(new Task(newData[0], newData[1], newData[2])); // adds the task to the listOfTasks
    }



////EDIT MENU
    /**
     * Display all tasks , display edit menu, process action requested ( update(), markAsDone() , remove() )
     * @param depth : limit of number of recursions allowed.  (recursive method)
     */
    public void editTask(int depth) {
        System.out.println("Select the task you want to edit");

        if (listOfTasks.size() == 0) {
            System.out.println("\n No task found");
        }

        if (depth >= 10) { //recursion call will be allowed max 10 times
            return;
        }

        int index;
        for (index = 0; index < listOfTasks.size(); index++) {

            System.out.print(" > (" + (index + 1) + ")  ");
            System.out.println(listOfTasks.get(index).toString());
        }
        System.out.println(" < (" + (index + 1) + ")  Back to MAIN menu ");

        try {
            int indexSelected = scanner.nextInt() - 1;

            if (indexSelected == index) {
                //do nothing
            } else if (indexSelected >= 0 && indexSelected < index + 1) {

                System.out.print("Task to edit > (" + (indexSelected + 1) + ")  ");
                System.out.println(listOfTasks.get(indexSelected).toString());

                System.out.println();
                System.out.println("""
                          > (1) Update (rename Title/ rename Project / change Due Date) 
                          > (2) Mark as done
                          > (3) Remove
                          < (4) Back to EDIT menu
                          < (5) Back to MAIN menu
                        """);


                int actionSelected = scanner.nextInt();
                switch (actionSelected) {
                    case 1 -> {
                         update(indexSelected,updateGatherer(indexSelected));
                    }
                    case 2 -> markAsDone(indexSelected);
                    case 3 -> removeTask(indexSelected);
                    case 4 -> {
                        depth++;
                        editTask(depth);
                    }
                    case 5 -> System.out.println();
                    default -> System.out.println(" Enter valid number\n");
                }
            } else {
                System.out.println(" Enter valid number\n");
                depth++;
                editTask(depth);
            }
        } catch (InputMismatchException i) {
            System.out.println(" Enter valid number\n");
            depth++;
            editTask(depth);
        }

    }

    /**
     * Marks as done the task at the index passed
     * @param index : the index of the task to be proceed
     */
    public void markAsDone(int index) {
        listOfTasks.get(index).setStatus(true);
        System.out.println("task ( " + listOfTasks.get(index).getTitle() + " ) marked as done");

    }


////update
    /**
     * Help function that display adapted menu for updateGatherer(int index)
     *
     * @param index the same index
     * @param mode  't': title , 'p': project , 'd': due date
     */
    public void updatePrintDisplaySentence(int index, char mode) {
        System.out.print("Press [Enter] to keep the actual value [ ");
        switch (mode) {
            case 't' -> {
                System.out.print(listOfTasks.get(index).getTitle() + " ] :\n New Title : ");
            }
            case 'p' -> {
                System.out.print(listOfTasks.get(index).getProject() + " ] :\n New Project : ");
            }
            case 'd' -> {
                System.out.print(listOfTasks.get(index).getDueDate() + " ] :\n New DueDate : ");
            }
        }

    }

    /**
     * Gather user choice for updating a task
     * @param index the index chosen which will be modified
     * @return an array with all the info , null if non modified
     */
    public String[] updateGatherer(int index) {

        String[] newData = new String[3];

        updatePrintDisplaySentence(index,'t');
        newData[0] = scanner.nextLine();

        updatePrintDisplaySentence(index,'p');
        newData[1] = scanner.nextLine();

        updatePrintDisplaySentence(index,'d');
        newData[2] = scanner.nextLine();


    return newData;
    }

    /**
     * Update a task by replacing the fields through Task class setters.
     * @param index the index chosen which will be modified
     * @param newData an String array returned by updateGatherer(int index) methods that collects user input.
     *                If an empty String is received, data is not updated.
     */
    public void update(int index,String [] newData) {

        if (!(newData[0].isEmpty())) {
            listOfTasks.get(index).setTitle(newData[0]);
        }

        if (!(newData[1].isEmpty())) {
            listOfTasks.get(index).setProject(newData[1]);
        }

        if (!(newData[2].isEmpty())) {
            String newDueDate = UserInputChecker.getValidDate(newData[2]);
            listOfTasks.get(index).setDueDate(newDueDate);
        }

    }


///remove
    public void removeTask(int index) {
        System.out.println("task ( " + listOfTasks.get(index).getTitle() + " ) successfully removed");
        listOfTasks.remove(index);
    }


///save and load ( serialization )
    /**
     * save to file taskFile.txt (path:./src/main/resources/taskFile.txt ) using serialization
     */
    public void saveToFile() {
        try {
            FileOutputStream fileOut = new FileOutputStream("./src/main/resources/taskFile.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(listOfTasks);
            out.close();
            fileOut.close();
            System.out.println("... saved as taskFile.txt");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * load from file taskFile.txt (path:./src/main/resources/taskFile.txt ) using serialization
     */
    public void loadFromFile() {

        File path1 = new File("./src/main/resources/taskFile.txt");
        if (path1.exists()) {
            try {
                FileInputStream fileIn = new FileInputStream("./src/main/resources/taskFile.txt");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                listOfTasks = (LinkedList<Task>) in.readObject();

                in.close();
                fileIn.close();

            } catch (IOException i) {
                i.printStackTrace();

            } catch (ClassNotFoundException c) {
                System.out.println(" class not found");
                c.printStackTrace();

            }

        }
    }


///quit
    /**
     * exits the program
     */
    public void quit() {
        scanner.close();
        System.exit(0);
    }


}
