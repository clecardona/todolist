package com.clecardona;

import java.util.Scanner;

public class UserInputChecker {

// static methods

    /**
     * Method that ensure that a correct string is provided ( not empty ) . Loops with while until correct input is provided.
     *
     * @param str        : input of the user ( String )
     * @param typeOfData ( "Title", "Project" ... )
     * @return a non-empty String
     */
    public static String getValidString(String str, String typeOfData) {

        while (str.length() == 0) {
            System.out.println("Invalid. Please Enter a " + typeOfData + ":");
            Scanner scanner = new Scanner(System.in);
            str = scanner.nextLine();
            if (str.length() != 0) {
                break;
            }
        }
        return str;
    }

    /**
     * Method that ensure that a corrected formatted date is provided by user. Loops with while until correct input is provided.
     *
     * @param date : input of the user
     * @return : correct and existing  formatted date
     */
    public static String getValidDate(String date) {

        String regex = "^((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$"; // regex verification

        while (!date.matches(regex)) {
            System.out.println("Enter valid date (YYYY-MM-DD) :");
            Scanner scanner = new Scanner(System.in);
            date = scanner.nextLine();
            if (date.matches(regex)) {
                break;
            }
        }
        return date;
    }


}

