import java.util.Scanner;

public class Checker {


/////// static methods
    public static String getValidString(String str,String typeOfData) {

    while (str.length() == 0) {
        System.out.println("Invalid.Please Enter a "+ typeOfData +":");
        Scanner scanner = new Scanner(System.in);
        str = scanner.nextLine();
        if (str.length() != 0) {
            break;
        }
    }
    return str;
}

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

