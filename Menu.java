import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class Menu implements Commands , Serializable {

    //fields
    LinkedList<Task> taskLibrary1 ; // instantiate a linkedlist object

    //constructor

    public Menu() {
        this.taskLibrary1 = new LinkedList<Task>();
    }

    //methods
    /**
     * Print out the opening message for the player.
     */
    public void sayWelcome() {
        System.out.println();
        System.out.println("Welcome to ToDoLy :                                                    2020 - © Clement Cardona\n" +
                "You have X tasks todo and Y tasks are done !");//TODO- implements X and Y from file reading - and process count of the "isDone boolean"

    }

    public void sayGoodbye() {
        System.out.println();
        System.out.println("Thank you for using ToDoLy  \n" +
                "Goodbye");

    }

    public void displayMenu() {
        System.out.println();
        System.out.println(
                "Menu :\n" +
                ">>> (1) Show tasks list (by date or project) \n" +
                ">>> (2) Add new Task\n" +
                ">>> (3) Edit Task (update, mark as done, remove)\n" +
                ">>> (4) (Save and) Quit" );
        System.out.println("Pick an option: ");

    }

    public boolean processCommand(){

        Scanner scanner = new Scanner(System.in);
        int userChoice = scanner.nextInt();


        boolean askedToQuit = false;

        switch (userChoice) {
            case 1:
                displayTasks();
                 break;

            case 2:
                addNewTask();
                break;

            case 3:
                editTask();
                break;

            case 4:
                saveToFile();
                askedToQuit=true;
                break;

            default:System.out.println("Enter a valid number");
        }

       return askedToQuit;

    }

    //methods implemented from Commands interface

    /**
     * Display the tasks stored done and not done
     *
     */
    @Override
    public void displayTasks() {
        for (int i = 0; i < taskLibrary1.size(); i++) {
            System.out.println(taskLibrary1.get(i).getTitle() + " - " + taskLibrary1.get(i).getProject() + " - due date: "
                    + taskLibrary1.get(i).getDueDate()+ " - Status : " +taskLibrary1.get(i).getStatus());
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
        String newTitle = Checker.getValidString(title,"Title");

        System.out.println("Enter Project related :  ");
        String project = scanner.nextLine();
        String newProject = Checker.getValidString(project,"Project");

        System.out.println("Enter date (YYYY-MM-DD) :");
        String date = scanner.nextLine();
        String newDueDate = Checker.getValidDate(date);


        taskLibrary1.add(new Task(newTitle, newProject, newDueDate)); // add the task to the taskFolder arraylist

        System.out.println("The task has been added successfully ");//TODO - associate with a boolean + a "if true"?

    }


    @Override
    public void editTask() {
        System.out.println("The task has been edited successfully");
    }

    @Override
    public void saveToFile() {
        try {
            FileOutputStream fileOut = new FileOutputStream("taskFile.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(taskLibrary1);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in taskFile.txt");
        } catch (IOException i) {
            i.printStackTrace();
        }


    } // TODO serialization

    @Override
    public void loadFromFile() {

        File path1 = new File ("taskFile.txt");
        if (path1.exists()){
            try {
                FileInputStream fileIn = new FileInputStream("taskFile.txt");
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
        System.out.println("Quitting . . .");
        System.exit(0);

    }

    /**TODO - implement methods
     *
     *
     * markasdone()
     *
     *  remove()
     *
     *
     *
     *
     */


}
