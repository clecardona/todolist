package com.clecardona.tools;

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
    public static String getNonEmptyString(String str, String typeOfData) {

        while (str.length() == 0) {
            System.out.println("Invalid. Please Enter a " + typeOfData + ":");
            Scanner scanner = new Scanner(System.in);
            str = scanner.nextLine();

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

        while (!isAValidDate(date)) {
            System.out.println("Enter valid date (YYYY-MM-DD) :");
            Scanner scanner = new Scanner(System.in);
            date = scanner.nextLine();
        }
        return date;
    }

    public static boolean isAValidDate(String date) {

        String regex = "^((20)\\d\\d)-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";

        //date does not match pattern - return false
        if (!date.matches(regex)) { // does not match pattern -return false
            return false;
        }

        boolean dateExists;

        int year = Integer.parseInt(date.substring(0, 4));//2020
        int month = Integer.parseInt(date.substring(5, 7));//01 -> 1
        int day = Integer.parseInt(date.substring(8)); // 01-> 1

        if (((month == 4) || (month == 6) || (month == 9) || (month == 11)) && day >= 31) {
            dateExists = false;

        } else if (month == 2) {
            dateExists = day < 30 && (day != 29 || year % 4 == 0);

        } else {
            dateExists = true;
        }

        return dateExists;
    }

    // todo validate boolean??
}

