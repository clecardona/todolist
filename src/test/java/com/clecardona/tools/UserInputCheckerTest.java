package com.clecardona.tools;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserInputCheckerTest {

    @BeforeAll
    static void init() {
        System.out.println("Class UserInputChecker tests .. ");

    }


    @Test
    void testIsAValidDateWithCorrectDatesEntered() {
        String date1 = "2020-01-01";
        String date2 = "2004-02-29";

        assertTrue(UserInputChecker.isAValidDate(date1));
        assertTrue(UserInputChecker.isAValidDate(date2));
    }

    @Test
    void testIsAValidDateWithDateWithoutZeros() {
        String date = "2020-1-1";
        assertFalse(UserInputChecker.isAValidDate(date));
    }

    @Test
    void testIsAValidDateWithSomeDatesThatDoesNotExists() {

        String date1 = "2020-15-30";
        String date2 = "2020-02-30";
        String date3 = "2020-04-31";
        String date4 = "2005-02-29";

        assertFalse(UserInputChecker.isAValidDate(date1));
        assertFalse(UserInputChecker.isAValidDate(date2));
        assertFalse(UserInputChecker.isAValidDate(date3));
        assertFalse(UserInputChecker.isAValidDate(date4));
    }

    @Test
    void testIsValidStringWithANonEmptyString() {
        String str = "edwdd ";
        boolean result = UserInputChecker.isAValidString(str);

        assertTrue(result);
    }

    @Test
    void testIsValidStringWithAnEmptyString() {
        String str = "";
        assertFalse(UserInputChecker.isAValidString(str));
    }

    @Test
    void testIsValidStringWithAWayTooLongString() {
        String str = "chefjkjdlsfgöäk ökkglfkhg'öjm jkjklö klkljkljkjkbg hhjedfrdsfdfygaioieråewirk ";
        assertFalse(UserInputChecker.isAValidString(str));
    }
}