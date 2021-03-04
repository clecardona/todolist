import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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





}
