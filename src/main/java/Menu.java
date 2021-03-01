import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class Menu implements Commands, Serializable {

    //fields
    LinkedList<Task> taskLibrary1;

    //Constructor

    public Menu() {
        this.taskLibrary1 = new LinkedList<>();
    }

    //methods

    /**
     * Print out the opening message for the player.
     */

    public void sayWelcome() {
        int taskDone = countDone(taskLibrary1);
        int taskToDo = taskLibrary1.size() - countDone(taskLibrary1);

        System.out.println();
        System.out.println("Welcome to [ToDoLy]                       -                                       2021 - © Clement Cardona\n" +
                "You have (" + taskToDo + ") tasks todo and (" + taskDone + ") tasks are done !");

    }

    public int countDone(LinkedList<Task> ll) {
        int count = 0;
        for (Task task : ll) {
            if (task.getStatus().equals("done")) {
                count++;
            }
        }
        return count;
    }

    public void sayGoodbye() {
        System.out.println();
        System.out.println("Goodbye and Thank you for using [ToDoLy]");
    }

    public void displayMenu() {
        System.out.println();
        System.out.println(
                """
                        Main Menu :
                        >>> (1) Show tasks list (by date or project)\s
                        >>> (2) Add new Task
                        >>> (3) Edit Task (update, mark as done, remove)
                        >>> (4) Save and Quit""");
        System.out.println("Pick an option: ");

    }

    public boolean processCommand() {

        Scanner scanner = new Scanner(System.in);
        int userChoice = scanner.nextInt();

        boolean askedToQuit = false;

        switch (userChoice) {
            case 1 -> displayTasks();
            case 2 -> addNewTask();
            case 3 -> editTask();
            case 4 -> {
                saveToFile();
                askedToQuit = true;
            }

            default -> System.out.println("Enter a valid number");
        }

        return askedToQuit;

    }


    //methods implemented from Commands interface

    /**
     * Display the tasks stored done and not done
     */
    @Override
    public void displayTasks() {
        System.out.println("    Pick an option :");
        System.out.println("    >>> (1) Sort By Date");
        System.out.println("    >>> (2) Sort By Project");
        System.out.println("    >>> (3) Sort By Status");
        System.out.println("    >>> (4) Back to MAIN menu");

        Scanner scanner = new Scanner(System.in);
        int userChoice = scanner.nextInt();

        /////////////////////
        switch (userChoice) {
            case 1 -> {
                sortTasksByDueDate();
                displayTasks();
            }
            case 2 -> {
                sortTasksByProject();
                displayTasks();
            }
            case 3 -> {
                sortTasksByStatus();
                displayTasks();
            }
            case 4 -> System.out.println();
            default -> System.out.println("Enter valid number");
        }

    }

    public void sortTasksByDueDate() {

        taskLibrary1.sort(new TaskComparator('p'));
        for (Task task : taskLibrary1) {
            System.out.println("Due date : " + task.getDueDate() + "          "
                    + "             -   Title:  " + task.getTitle()
                    + "   -   Project :  " + task.getProject()
                    + "   -   Status : " + task.getStatus());
        }


    }

    public void sortTasksByProject() {
        taskLibrary1.sort(new TaskComparator('d'));
        for (Task task : taskLibrary1) {
            System.out.println("Project : " + task.getProject() + "          "
                    + "   -   Title : " + task.getTitle()
                    + "   -   Due date: " + task.getDueDate()
                    + "   -   Status : " + task.getStatus());
        }
    }

    public void sortTasksByStatus() {
        taskLibrary1.sort(new TaskComparator('s'));
        for (Task task : taskLibrary1) {
            System.out.println("Status : " + task.getStatus() + "          "
                    + "   -   Title : " + task.getTitle()
                    + "   -   Project : " + task.getProject()
                    + "   -   Due date: " + task.getDueDate());

        }
    }


    /**
     * add a new task to the list
     * isDone is set to  false by default
     */
    @Override
    public void addNewTask() {
        Scanner scanner = new Scanner(System.in); //initiate a scanner to get user input

        System.out.println("Enter Title:  ");
        String title = scanner.nextLine();
        String newTitle = UserInputChecker.getValidString(title, "Title");

        System.out.println("Enter Project related :  ");
        String project = scanner.nextLine();
        String newProject = UserInputChecker.getValidString(project, "Project");

        System.out.println("Enter date (YYYY-MM-DD) :");
        String date = scanner.nextLine();
        String newDueDate = UserInputChecker.getValidDate(date);


        taskLibrary1.add(new Task(newTitle, newProject, newDueDate)); // add the task to the taskFolder arraylist

        System.out.println("The task has been added successfully ");//TODO - associate with a boolean + a "if true"?

    }


    @Override
    public void editTask() {
        System.out.println("Select the task you want to edit");

        int index = 0;
        for (index = 0; index < taskLibrary1.size(); index++) {
            int choice = index + 1;
            System.out.println("  (" + choice + ")  " + "Title : " + taskLibrary1.get(index).getTitle() + "          "
                    + "   -   Status : " + taskLibrary1.get(index).getStatus());

        }
        System.out.println("  (" + (index + 1) + ")  Back to MAIN menu ");

        Scanner scanner = new Scanner(System.in);
        int taskSelected = scanner.nextInt();

        if (taskSelected != index + 1) {

            System.out.println();
            System.out.println("    >>> (1) Update  ");
            System.out.println("    >>> (2) Mark as done");
            System.out.println("    >>> (3) Remove");
            System.out.println("    >>> (4) Back to EDIT menu");
            System.out.println("    >>> (5) Back to MAIN menu");

            int actionSelected = scanner.nextInt();
            switch (actionSelected) {
                case 1 -> {
                    Scanner sc = new Scanner(System.in);

                    System.out.println("[Enter] to keep the actual value");
                    System.out.println("Update Title:");
                    String title = sc.nextLine();

                    if (!title.equals("")) {
                        String newTitle = UserInputChecker.getValidString(title, "Title");
                        taskLibrary1.get(taskSelected - 1).setTitle(newTitle);
                    }


                    System.out.println("[Enter] to keep the actual value");
                    System.out.println("Update Title:");
                    String project = sc.nextLine();

                    if (!project.equals("")) {
                        String newProject = UserInputChecker.getValidString(project, "Project");
                        taskLibrary1.get(taskSelected - 1).setProject(newProject);
                    }

                    System.out.println("[Enter] to keep the actual value");
                    System.out.println("Update Due Date :");
                    String date = sc.nextLine();

                    if (!date.equals("")) {
                        String newDueDate = UserInputChecker.getValidDate(date);
                        taskLibrary1.get(taskSelected - 1).setDueDate(newDueDate);
                    }


                }//update task
                case 2 -> {
                    taskLibrary1.get(taskSelected - 1).setStatus(true);
                    System.out.println("task ( " + taskLibrary1.get(taskSelected - 1).getTitle() + " ) marked as done");
                } // mark as done
                case 3 -> {
                    System.out.println("task ( " + taskLibrary1.get(taskSelected - 1).getTitle() + " ) successfully removed");
                    taskLibrary1.remove(taskSelected - 1);

                }//remove task
                case 4 -> editTask();//back to EDIT menu
                case 5 -> System.out.println();//back to MAIN menu
                default -> System.out.println("Enter valid number");
            }
        }


    }

    @Override
    public void saveToFile() {
        try {
            FileOutputStream fileOut = new FileOutputStream("./src/main/resources/taskFile.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(taskLibrary1);
            out.close();
            fileOut.close();
            System.out.println("... saved as taskFile.txt");
        } catch (IOException i) {
            i.printStackTrace();
        }


    }

    @Override
    public void loadFromFile() {

        File path1 = new File("./src/main/resources/taskFile.txt");
        if (path1.exists()) {
            try {
                FileInputStream fileIn = new FileInputStream("./src/main/resources/taskFile.txt");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                taskLibrary1 = (LinkedList<Task>) in.readObject();

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
     * exit the program
     */
    @Override
    public void quit() {
        System.exit(0);

    }


}
