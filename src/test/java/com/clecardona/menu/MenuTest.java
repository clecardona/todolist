package com.clecardona.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.clecardona.tasks.Task;

import java.util.LinkedList;


public class MenuTest {


    @Test
    public void testCountDone() {
        Menu menu = new Menu();
        LinkedList<Task> list = new LinkedList<Task>();


        list.add(new Task("a", "aa", "2020-02-01"));
        list.add(new Task("b", "bb", "2020-02-02"));
        list.add(new Task("c", "cc", "2020-02-03"));
        list.add(new Task("d", "dd", "2020-02-04"));
        list.add(new Task("e", "ee", "2020-02-05"));

        int count = menu.countDone(list);
        Assertions.assertEquals(0, count);

        list.get(2).setStatus(true);
        int nextCount = menu.countDone(list);
        Assertions.assertEquals(1, nextCount);

    }

    @Test
    public void testMarkAsDoneTwiceInARow() {

        LinkedList<Task> list = new LinkedList<Task>();
        Menu menu = new Menu(list);

        list.add(new Task("x", "kk", "2020-02-01"));
        list.add(new Task("a", "aa", "2020-02-02"));
        list.add(new Task("b", "zz", "2020-02-03"));

        menu.markAsDone(1);

        String expected = "done";
        Assertions.assertEquals(expected, list.get(1).getStatusString());

        menu.markAsDone(2);
        Assertions.assertEquals(expected, list.get(2).getStatusString());

    }

}
