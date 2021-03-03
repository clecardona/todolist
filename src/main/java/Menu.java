import java.io.*;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class Menu implements Commands, Serializable {

    @Serial
    private static final long serialVersionUID = 0L;
    ///////////////////fields
    private LinkedList<Task> taskLibrary1;

    ///////////////////Constructor

    public Menu() {
        this.taskLibrary1 = new LinkedList<>();
    }

    ///////////////////methods

    /**
     * Prints out the opening message for the user
     */
    public void sayWelcome() {
        int taskDone = countDone(taskLibrary1);
        int taskToDo = taskLibrary1.size() - countDone(taskLibrary1);

        System.out.println();
        System.out.println("Welcome to [todolist]                       -                                       2021 - © Clement Cardona\n" +
                "You have (" + taskToDo + ") tasks todo and (" + taskDone + ") tasks are done !");

    }

    /**
     * Returns the the number of task "done"
     */
    public int countDone(LinkedList<Task> ll) {
        int count = 0;
        for (Task task : ll) {
            if (task.getStatus().equals("done")) {
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

        Scanner scanner = new Scanner(System.in);
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
    @Override
    public void displayTasks() {
        System.out.println();
        System.out.println("    >>> (1) Sort By Date");
        System.out.println("    >>> (2) Sort By Project");
        System.out.println("    >>> (3) Sort By Status");
        System.out.println("    >>> (4) Back to MAIN menu");
        System.out.println("    Pick an option :");

        if (taskLibrary1.size() != 0) {

            Scanner scanner = new Scanner(System.in);
            try {
                int userChoice = scanner.nextInt();

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
                    default -> System.out.println("\n Enter valid number");
                }
            } catch (InputMismatchException i) {
                System.out.println("\n Enter valid number");
                displayTasks();
            }
        } else {
            System.out.println("\n No task found");
        }
    }

    // TODO - if possible refactor 3 methods used to sort
    public void sortTasksByDueDate() {

        taskLibrary1.sort(new TaskComparator('p'));
        for (Task task : taskLibrary1) {
            System.out.println("Due date : " + task.getDueDate() + "      "
                    + "             -   Title:  " + task.getTitle()
                    + "   -   Project :  " + task.getProject()
                    + "   -   Status : " + task.getStatus());
        }


    }

    public void sortTasksByProject() {
        taskLibrary1.sort(new TaskComparator('d'));
        for (Task task : taskLibrary1) {
            System.out.println("Project : " + task.getProject() + "      "
                    + "   -   Title : " + task.getTitle()
                    + "   -   Due date: " + task.getDueDate()
                    + "   -   Status : " + task.getStatus());
        }
    }

    public void sortTasksByStatus() {
        taskLibrary1.sort(new TaskComparator('s'));
        for (Task task : taskLibrary1) {
            System.out.println("Status : " + task.getStatus() + "      "
                    + "   -   Title : " + task.getTitle()
                    + "   -   Project : " + task.getProject()
                    + "   -   Due date: " + task.getDueDate());

        }
    }


    /**
     * Add a new task to the list
     * boolean isDone is set to false by default when a task is added
     */
    @Override
    public void addNewTask() {
        Scanner scanner = new Scanner(System.in);

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
        System.out.println("The task has been added successfully ");//TODO - Maybe associated with a boolean + a "if true"?

    }

    /**
     * Display all tasks , display edit menu, process action requested ( update(), markAsDone() , remove() )
     */
    @Override
    public void editTask() {
        System.out.println("Select the task you want to edit");

        if (taskLibrary1.size() == 0) {
            System.out.println("\n No task found");
        }

        int index = 0;
        for (index = 0; index < taskLibrary1.size(); index++) {
            int choice = index + 1;
            System.out.println("  (" + choice + ")  " + "Title : " + taskLibrary1.get(index).getTitle()
                    + " - Status : " + taskLibrary1.get(index).getStatus());

        }
        System.out.println("  (" + (index + 1) + ")  Back to MAIN menu ");

        Scanner scanner = new Scanner(System.in);
        try {
            int indexOfTaskSelected = scanner.nextInt();

            if (indexOfTaskSelected == index + 1) {

            } else if (indexOfTaskSelected > 0 && indexOfTaskSelected < index + 1) {

                System.out.println("  (" + indexOfTaskSelected + ")  " + "Title : " + taskLibrary1.get(indexOfTaskSelected - 1).getTitle()
                        + " - Status : " + taskLibrary1.get(indexOfTaskSelected - 1).getStatus());
                System.out.println();
                System.out.println("    >>> (1) Update  ");
                System.out.println("    >>> (2) Mark as done");
                System.out.println("    >>> (3) Remove");
                System.out.println("    >>> (4) Back to EDIT menu");
                System.out.println("    >>> (5) Back to MAIN menu");


                int actionSelected = scanner.nextInt();
                switch (actionSelected) {
                    case 1 -> update(indexOfTaskSelected);
                    case 2 -> markAsDone(indexOfTaskSelected);
                    case 3 -> removeTask(indexOfTaskSelected);
                    case 4 -> editTask();
                    case 5 -> System.out.println();
                    default -> System.out.println(" Enter valid number\n");
                }
            } else {
                System.out.println(" Enter valid number\n");
                editTask();
            }
        } catch (InputMismatchException i) {
            System.out.println(" Enter valid number\n");
            editTask();
        }

    }

    public void markAsDone(int indexOfTask) {
        taskLibrary1.get(indexOfTask - 1).setStatus(true);
        System.out.println("task ( " + taskLibrary1.get(indexOfTask - 1).getTitle() + " ) marked as done");

    }

    /**
     * update a task by replacing or not (user choice) the fields through Task class setters
     * TODO : refactor update if possible
     */
    public void update(int indexOfTask) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Press [Enter] to keep the actual Title ( " + taskLibrary1.get(indexOfTask - 1).getTitle() + " )");
        System.out.println("Update Title:");
        String title = sc.nextLine();

        if (!title.equals("")) {
            String newTitle = UserInputChecker.getValidString(title, "Title");
            taskLibrary1.get(indexOfTask - 1).setTitle(newTitle);
        }

        System.out.println("Press [Enter] to keep the actual Project ( " + taskLibrary1.get(indexOfTask - 1).getProject() + " )");
        System.out.println("Update Project:");
        String project = sc.nextLine();

        if (!project.equals("")) {
            String newProject = UserInputChecker.getValidString(project, "Project");
            taskLibrary1.get(indexOfTask - 1).setProject(newProject);
        }

        System.out.println("Press [Enter] to keep the actual due Date ( " + taskLibrary1.get(indexOfTask - 1).getDueDate() + " )");
        System.out.println("Update Due Date :");
        String date = sc.nextLine();

        if (!date.equals("")) {
            String newDueDate = UserInputChecker.getValidDate(date);
            taskLibrary1.get(indexOfTask - 1).setDueDate(newDueDate);
        }

    }

    public void removeTask(int indexOfTask) {
        System.out.println("task ( " + taskLibrary1.get(indexOfTask - 1).getTitle() + " ) successfully removed");
        taskLibrary1.remove(indexOfTask - 1);
    }

    /**
     * save to file taskFile.txt using serialization
     */
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

    /**
     * load from file taskFile.txt using serialization
     */
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
     * exits the program
     */
    @Override
    public void quit() {
        System.exit(0);
    }


}
