import java.util.Scanner;

public class Checker {


/////// static methods
    public static String getValidDate(String date){

        String regex = "^((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$"; // regex verification

        while (!date.matches(regex)) {
            System.out.println("Enter valid date (YYYY-MM-DD) :");
            Scanner scanner = new Scanner(System.in);
            date = scanner.next();
            if (date.matches(regex)) {
               break;
            }
                   }
    return  date;
    }
}
