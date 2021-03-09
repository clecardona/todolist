import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.clecardona.Menu;
import com.clecardona.Task;

import java.util.LinkedList;


public class MenuTest {

    @Test
    public void testCountDone() {
        Menu menu = new Menu();
        LinkedList<Task> ll = new LinkedList<Task>();


        ll.add(new Task("a", "aa", "2020-02-01"));
        ll.add(new Task("b", "bb", "2020-02-02"));
        ll.add(new Task("c", "cc", "2020-02-03"));
        ll.add(new Task("d", "dd", "2020-02-04"));
        ll.add(new Task("e", "ee", "2020-02-05"));

        int count = menu.countDone(ll);
        Assertions.assertEquals(0, count);

        ll.get(2).setStatus(true);
        int nextCount = menu.countDone(ll);
        Assertions.assertEquals(1, nextCount);

    }

    @Test
    public void testMarkAsDone() {
        LinkedList<Task> ll = new LinkedList<Task>();
        Menu menu = new Menu(ll);

        ll.add(new Task("x", "kk", "2020-02-01"));
        ll.add(new Task("a", "aa", "2020-02-02"));
        ll.add(new Task("b", "zz", "2020-02-03"));

        menu.markAsDone(1);

        String expected = "done";

        Assertions.assertEquals(expected, ll.get(0).getStatus());

        menu.markAsDone(2);
        Assertions.assertEquals(expected, ll.get(1).getStatus());

    }

}
