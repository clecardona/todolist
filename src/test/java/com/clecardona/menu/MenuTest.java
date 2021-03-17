package com.clecardona.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.clecardona.tasks.Task;

import java.util.LinkedList;


public class MenuTest {

    @BeforeAll
    static void init() {
        System.out.println("Class Menu tests .. ");

    }

    @Test
    void testCountDone() {
        LinkedList<Task> list = new LinkedList<Task>();
        Menu menu = new Menu(list);

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
    void testMarkAsDone() {
        LinkedList<Task> list = new LinkedList<Task>();
        Menu menu = new Menu(list);

        list.add(new Task("x", "kk", "2020-02-01"));
        list.add(new Task("a", "aa", "2020-02-02"));
        list.add(new Task("b", "zz", "2020-02-03"));

        menu.markAsDone(1);

        String expected = "done";
        Assertions.assertEquals(expected, list.get(1).getStatusString());
        Assertions.assertTrue(list.get(1).getStatusBoolean());

        menu.markAsDone(2);
        Assertions.assertEquals(expected, list.get(2).getStatusString());
        Assertions.assertTrue(list.get(2).getStatusBoolean());
    }


    @Test
    void testAddNewTaskShouldBeAddAtTheEndOfList() {
        LinkedList<Task> list = new LinkedList<Task>();
        Menu menu = new Menu(list);

        list.add(new Task("a", "aa", "2020-02-01"));
        list.add(new Task("b", "bb", "2020-02-02"));
        list.add(new Task("c", "cc", "2020-02-03"));

        String[] newTask = {"d", "dd", "2020-02-04"};
        menu.addNewTask(newTask); // should be added at the end : index 3

        Assertions.assertEquals("d", list.get(3).getTitle());
        Assertions.assertEquals("dd", list.get(3).getProject());
        Assertions.assertEquals("2020-02-04", list.get(3).getDueDate());
        Assertions.assertFalse(list.get(3).getStatusBoolean());
    }


    @Test
    public void testRemoveATAsk() {

        LinkedList<Task> list = new LinkedList<Task>();
        Menu menu = new Menu(list);

        list.add(new Task("x", "kk", "2020-02-01"));
        list.add(new Task("a", "aa", "2020-02-02"));// - this should be deleted
        list.add(new Task("b", "zz", "2020-02-03"));


        menu.removeTask(1);
        Assertions.assertEquals("zz", list.get(1).getProject());


    }


    @Test
    void updateATaskWithAllDataModified() {
        LinkedList<Task> list = new LinkedList<Task>();
        Menu menu = new Menu(list);

        list.add(new Task("a", "aa", "2020-02-01"));
        list.add(new Task("b", "bb", "2020-02-02"));
        list.add(new Task("c", "cc", "2020-02-03"));// this one
        list.add(new Task("d", "dd", "2020-02-04"));
        list.add(new Task("e", "ee", "2020-02-05"));

        String[] updatedTask = {"run", "marathon", "2021-05-01"};

        menu.update(2, updatedTask);

        Assertions.assertEquals("run", list.get(2).getTitle());
        Assertions.assertEquals("marathon", list.get(2).getProject());
        Assertions.assertEquals("2021-05-01", list.get(2).getDueDate());
    }

    @Test
    void updateATaskWithOnlySomeDataModified() {
        LinkedList<Task> list = new LinkedList<Task>();
        Menu menu = new Menu(list);

        list.add(new Task("a", "aa", "2020-02-01"));
        list.add(new Task("b", "bb", "2020-02-02"));
        list.add(new Task("c", "cc", "2020-02-03"));// this one
        list.add(new Task("d", "dd", "2020-02-04"));
        list.add(new Task("e", "ee", "2020-02-05"));

        String[] updatedTask = {"", "semi marathon", ""};

        menu.update(2, updatedTask);

        Assertions.assertEquals("c", list.get(2).getTitle());
        Assertions.assertEquals("semi marathon", list.get(2).getProject());
        Assertions.assertEquals("2020-02-03", list.get(2).getDueDate());
    }


    @Test
    void testSortByProject() {
        LinkedList<Task> list = new LinkedList<Task>();
        Menu menu = new Menu(list);

        list.add(new Task("d", "dd", "2020-02-04"));
        list.add(new Task("e", "ee", "2020-02-05"));
        list.add(new Task("a", "aa", "2020-02-01"));
        list.add(new Task("b", "bb", "2020-02-02"));// this one will be at index 1 when sorted by project
        list.add(new Task("c", "cc", "2020-02-03"));


        menu.sortBy('p');

        Assertions.assertEquals("b", list.get(1).getTitle());
        Assertions.assertEquals("bb", list.get(1).getProject());
        Assertions.assertEquals("2020-02-02", list.get(1).getDueDate());
    }


}
