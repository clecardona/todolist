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


    /**
     * Constructor with no args
     */
    public Menu() {
        this.listOfTasks = new LinkedList<>();
    }

    /**
     * Constructor that takes a LinkedList as arg.
     */
    public Menu(LinkedList<Task> ll) {
        this.listOfTasks = ll;
    }

    ///////////////////methods

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
     * Returns the the number of task "done"
     */
    public int countDone(LinkedList<Task> listOfTasks) { //todo or return int[] to calculate both at the time
        int count = 0;
        for (Task task : listOfTasks) {
            if (task.getStatusBoolean()) {
                count++;
            }
        }
        return count;
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
                        >>> (1) Show and sort tasks list\s
                        >>> (2) Add new Task
                        >>> (3) Edit Task (update, mark as done, remove)
                        >>> (4) Save and Quit""");
        System.out.println("Pick an option: ");

    }

    /**
     * Process a command from main menu by scanning user's input
     */
    public boolean processCommand() {

        Scanner scanner = new Scanner(System.in);//todo why not have a validateInt. but this probably is good too
        boolean askedToQuit = false;

        try {

            int userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1 -> displayTasks();
                case 2 -> addNewTask();
                case 3 -> editTask();
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

    /**
     * Display all tasks , display sorting menu, process sorting by title,project or due date.
     */

    public void displayTasks() {
        System.out.println();
        System.out.println("    >>> (1) Sort By Date");
        System.out.println("    >>> (2) Sort By Project");
        System.out.println("    >>> (3) Sort By Status");
        System.out.println("    >>> (4) Back to MAIN menu");
        System.out.println("    Pick an option :");

        if (listOfTasks.size() != 0) {  //todo I usually prefer to have short if first an return after them but that is good too.

            Scanner scanner = new Scanner(System.in);
            try {
                int userChoice = scanner.nextInt();

                switch (userChoice) {
                    case 1 -> {
                        sortTasksByDueDate();
                        displayTasks(); //todo I usually don't like the recursion methods if not needed - use return;
                    }
                    case 2 -> {
                        sortTasksByProject();
                        displayTasks();// todo avoid recursion- use return
                    }
                    case 3 -> {
                        sortTasksByStatus();
                        displayTasks();// todo avoid recursion- use return;
                    }
                    case 4 -> System.out.println();
                    default -> System.out.println("\n Enter valid number");
                }
            } catch (InputMismatchException i) {
                System.out.println("\n Enter valid number");
                displayTasks();// todo avoid recursion
            }
        } else {
            System.out.println("\n No task found");
        }
    }

    // TODO - if possible refactor the 3 methods used to sort
    private void sortTasksByDueDate() {

        listOfTasks.sort(new Comparator('d'));
        for (Task task : listOfTasks) {
            System.out.println("Due date : " + task.getDueDate() + "      "
                    + "             -   Title:  " + task.getTitle()
                    + "   -   Project :  " + task.getProject()
                    + "   -   Status : " + task.getStatusString());
        }


    }

    private void sortTasksByProject() {
        listOfTasks.sort(new Comparator('p'));
        for (Task task : listOfTasks) {
            System.out.println("Project : " + task.getProject() + "      "
                    + "   -   Title : " + task.getTitle()
                    + "   -   Due date: " + task.getDueDate()
                    + "   -   Status : " + task.getStatusString());
        }
    }

    private void sortTasksByStatus() {
        listOfTasks.sort(new Comparator('s'));
        for (Task task : listOfTasks) {
            System.out.println("Status : " + task.getStatusString() + "      "
                    + "   -   Title : " + task.getTitle()
                    + "   -   Project : " + task.getProject()
                    + "   -   Due date: " + task.getDueDate());

        }
    }


    /**
     * Add a new task to the list
     * boolean isDone is set to false by default when a task is added
     */

    public void addNewTask() {
        Scanner scanner = new Scanner(System.in);//todo since you are using this too much, have it as global??

        System.out.println("Enter Title:  ");
        String title = scanner.nextLine();
        String newTitle = UserInputChecker.getNonEmptyString(title, "Title");

        System.out.println("Enter Project related :  ");
        String project = scanner.nextLine();
        String newProject = UserInputChecker.getNonEmptyString(project, "Project");

        System.out.println("Enter date (YYYY-MM-DD) :");//todo you made your regex accept : 1999-1-1
        String date = scanner.nextLine();
        String newDueDate = UserInputChecker.getValidDate(date);


        listOfTasks.add(new Task(newTitle, newProject, newDueDate)); // adds the task to the taskFolder arraylist
        System.out.println("The task has been added successfully ");

    }

    /**
     * Display all tasks , display edit menu, process action requested ( update(), markAsDone() , remove() )
     */

    public void editTask() {
        System.out.println("Select the task you want to edit");

        if (listOfTasks.size() == 0) {
            System.out.println("\n No task found");
        }

        int index = 0;
        for (index = 0; index < listOfTasks.size(); index++) { //todo  I have a feeling that you can merge this with the display method- sortByTitle to create
            System.out.println("  (" + (index + 1) + ")  " + "Title : " + listOfTasks.get(index).getTitle() //todo toString
                    + " - Status : " + listOfTasks.get(index).getStatusString());

        }
        System.out.println("  (" + (index + 1) + ")  Back to MAIN menu ");

        Scanner scanner = new Scanner(System.in);//todo since you are using this too much, have it as global??
        try {
            int indexOfTaskSelected = scanner.nextInt();

            if (indexOfTaskSelected == index + 1) {
//do nothing
            } else if (indexOfTaskSelected > 0 && indexOfTaskSelected < index + 1) {

                System.out.println("  (" + indexOfTaskSelected + ")  " + "Title : " + listOfTasks.get(indexOfTaskSelected - 1).getTitle() //todo toString again
                        + " - Status : " + listOfTasks.get(indexOfTaskSelected - 1).getStatusString());
                System.out.println();
                System.out.println("    >>> (1) Update  ");
                System.out.println("    >>> (2) Mark as done");
                System.out.println("    >>> (3) Remove");
                System.out.println("    >>> (4) Back to EDIT menu");
                System.out.println("    >>> (5) Back to MAIN menu");


                int actionSelected = scanner.nextInt();
                switch (actionSelected) {
                    case 1 -> update(indexOfTaskSelected - 1);
                    case 2 -> markAsDone(indexOfTaskSelected);
                    case 3 -> removeTask(indexOfTaskSelected);
                    case 4 -> editTask();  // todo avoid recursion
                    case 5 -> System.out.println();
                    default -> System.out.println(" Enter valid number\n");
                }
            } else {
                System.out.println(" Enter valid number\n");
                editTask();// todo avoid recursion
            }
        } catch (InputMismatchException i) {
            System.out.println(" Enter valid number\n");
            editTask();
        }

    }

    public void markAsDone(int index) {
        listOfTasks.get(index).setStatus(true);
        System.out.println("task ( " + listOfTasks.get(index).getTitle() + " ) marked as done");

    }

    /**
     * update a task by replacing or not (user choice) the fields through Task class setters
     */
    private void update(int indexOfTask) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Press [Enter] to keep the actual Title ( " + listOfTasks.get(indexOfTask - 1).getTitle() + " )");
        System.out.println("Update Title:");
        String updatedTitle = sc.nextLine();

        if (!updatedTitle.equals("")) {
            listOfTasks.get(indexOfTask - 1).setTitle(updatedTitle);
        }

        System.out.println("Press [Enter] to keep the actual Project ( " + listOfTasks.get(indexOfTask - 1).getProject() + " )");
        System.out.println("Update Project:");
        String updatedProject = sc.nextLine();

        if (!updatedProject.equals("")) {
            listOfTasks.get(indexOfTask - 1).setProject(updatedProject);
        }

        System.out.println("Press [Enter] to keep the actual due Date ( " + listOfTasks.get(indexOfTask - 1).getDueDate() + " )");
        System.out.println("Update Due Date :");
        String updatedDueDate = sc.nextLine();

        if (!updatedDueDate.equals("")) {
            String newDueDate = UserInputChecker.getValidDate(updatedDueDate);
            listOfTasks.get(indexOfTask - 1).setDueDate(newDueDate);
        }

    }

    private void removeTask(int indexOfTask) {
        System.out.println("task ( " + listOfTasks.get(indexOfTask - 1).getTitle() + " ) successfully removed");
        listOfTasks.remove(indexOfTask - 1);
    }

    /**
     * save to file taskFile.txt using serialization
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
     * load from file taskFile.txt using serialization
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

    /**
     * exits the program
     */

    public void quit() {
        System.exit(0);
    }


}
