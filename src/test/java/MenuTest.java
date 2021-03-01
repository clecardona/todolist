import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

public class MenuTest {
    @Test
    public void testCountDone() {
        // Arrange
        Menu menu = new Menu();
        LinkedList<Task> ll = new LinkedList<Task>();
        ll.add(new Task("a", "b", "1"));
        ll.add(new Task("a", "b", "1"));
        ll.add(new Task("a", "b", "1"));
        ll.add(new Task("a", "b", "1"));
        ll.add(new Task("a", "b", "1"));

        // Act
        int count = menu.countDone(ll);

        // Assert
        Assertions.assertEquals(0, count);
    }
}
