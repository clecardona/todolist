import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Menu implements Commands {

    //fields



    //constructor


    //methods implemented
    /**
     * Print out the opening message for the player.
     */
    public void sayWelcome()
    {
        System.out.println();
        System.out.println("Welcome to ToDoLy :                                                    2020 - © Clement Cardona\n" +
                "You have X tasks todo and Y tasks are done !");

    }

    public void sayGoodbye()
    {
        System.out.println();
        System.out.println("Thank you for using ToDoLy  \n" +
                "Goodbye");

    }
    public void displayMenu()
    {
        System.out.println(
                "Options :\n" +
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
                askedToQuit=false;
                break;

            case 2:
                addNewTask();
                askedToQuit=false;
                break;

            case 3:
                editTask();
                askedToQuit=false;
                break;

            case 4:
                save();
                quit();// return askedToQuit = true;
                askedToQuit=true;
                break;

            default:System.out.println("Enter a valid number");
        }


       return askedToQuit;

    }


    //methods implemented

    @Override
    public String displayTasks() {
        System.out.println("Here is a list of the tasks : \n A , B, C ");
        return "TASKS LIST";
    }

    @Override
    public void addNewTask() {
    Scanner scanner = new Scanner(System.in); //initiate a scanner to get user input

        System.out.println("Enter Title:  ");
        String newTitle = scanner.next();

        System.out.println("Enter Project related :  ");
        String newProject = scanner.next();

        System.out.println("End date YYYYMMDD :  ");
        long newDueDate = scanner.nextInt();

        TaskLibrary taskLibrary1 = new TaskLibrary(); // instantiate a taskLibrary object

        taskLibrary1.addOneTask(new Task(newTitle, newProject, newDueDate)); // add the task to the taskFolder arraylist


    System.out.println("The task has been added successfully ");//TODO - associate with a boolean + a "if true"
    taskLibrary1.print(); //just to test  TODO - reorient to "Show Tasks list"
    }




    @Override
    public void editTask() {
        System.out.println("The task has been edited successfully");
    }

    @Override
    public void save() {
        System.out.println("The modifications has been saved to file successfully");
    }

    @Override
    public void quit() {
        System.out.println("Quiting ");

    }




}
