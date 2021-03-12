package com.clecardona.tools;

import com.clecardona.menu.Menu;
import com.clecardona.tasks.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class UserInputCheckerTest {

    @Test
    void getNonEmptyString() { //todo create test
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
}