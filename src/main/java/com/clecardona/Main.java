package com.clecardona;

import com.clecardona.menu.Menu;

public class Main {

    /**
     * This class is the main class of the "Todolist" application.
     *
     * @author Clement Cardona
     * @version 2016.02.29
     */


    public static void main(String[] args) {

        run();

    }

    /**
     * Main routine
     * Stops looping when "Save and Quit " is selected ( setting boolean askedToQuit as true )
     */
    public static void run() {

        Menu menu = new Menu();

        menu.loadFromFile();
        menu.sayWelcome();

        // Enter the main command loop. Here we repeatedly read commands and
        // execute them until the session is over ( ie Save and Quit is selected)


        boolean askedToQuit = false;
        while (!askedToQuit) {

            menu.displayMenu();
            askedToQuit = menu.processCommand();// perform action requested  + return false only if "Save and Quit" is selected
            clearScreen();

        }
        menu.sayGoodbye();
        menu.quit();
    }

    public static void clearScreen() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

    }

}


