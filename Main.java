public class Main {

    /**
     *  This class is the main class of the "Todolist" application.
     *       *
     * @author  Clement Cardona
     * @version 2016.02.29
     */


    public static void main(String[]args) {


        start();


    }

    /**
     * Main routine
     *stops looping when "Save and Quit " is selected
     */
    public static void start()
        {

            Menu menu = new Menu();

            menu.loadFromFile();

            menu.sayWelcome();

            // Enter the main command loop. Here we repeatedly read commands and
            // execute them until the session is over ( ie Save and Quit is selected)


            boolean askedToQuit = false;
            while (! askedToQuit) {

                menu.displayMenu();
                askedToQuit = menu.processCommand(); // perform action requested  + return false only if "Save and Quit" is selected

            }
            menu.sayGoodbye();
            menu.quit();
        }

    }


