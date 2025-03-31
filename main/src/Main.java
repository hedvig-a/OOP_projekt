import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hi! Welcome to your relationship diary!");
        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("What would you like to do?" +
                    "\nOptions: add entries - A, change entries - C, find entries - F, quit - Q");
            String choice = scan.nextLine();
            if (choice.equalsIgnoreCase("a")) {
                System.out.println("Enter the date of the occasion (yyyy-mm-dd): ");
                LocalDate.now();
                LocalDate parsedDate;
                try {
                    String date = scan.nextLine();
                    parsedDate = LocalDate.parse(date);
                } catch (DateTimeParseException e) {
                    System.out.println("Date entered incorrectly >_< Try again!");
                    continue;
                }
                System.out.println("Enter the name of the occasion: ");
                String name = scan.nextLine();
                System.out.println("Enter the location of the occasion: ");
                String location = scan.nextLine();
                System.out.println("Write a description for the occasion: ");
                String description = scan.nextLine();
                NewEntry entry = new NewEntry(parsedDate, name, location, description);
                entry.writeToFile();
                continue;
            }
            if (choice.equalsIgnoreCase("q")){
                System.out.println("Bye-bye!");
                break;
            }
            if (choice.equalsIgnoreCase("c")){
                System.out.println("Enter the date of the entry you'd like to change:");
                LocalDate.now();
                LocalDate parsedDate;
                try {
                    String date = scan.nextLine();
                    parsedDate = LocalDate.parse(date);
                } catch (DateTimeParseException e) {
                    System.out.println("Date entered incorrectly >_< Try again!");
                    continue;
                }

                System.out.println("Enter the name of the occasion you'd like to change: ");
                String name = scan.nextLine();
                System.out.println("Enter a new location for the occasion: ");
                String location = scan.nextLine();
                System.out.println("Write a new description for the occasion: ");
                String description = scan.nextLine();
                ChangeEntry entry = new ChangeEntry(parsedDate, name, location, description);
                entry.writeToFile();
                continue;
            }
            if (choice.equalsIgnoreCase("f")){
                System.out.println("Would you like to find all entries that share a date or a specific entry? (all/one)");
                choice = scan.nextLine();
                if (choice.equalsIgnoreCase("all")){
                    System.out.println("Enter the date you wish to see entries from (yyyy-mm-dd): ");
                    LocalDate.now();
                    LocalDate parsedDate;
                    try {
                        String date = scan.nextLine();
                        parsedDate = LocalDate.parse(date);
                    } catch (DateTimeParseException e) {
                        System.out.println("Date entered incorrectly >_< Try again!");
                        continue;
                    }
                    ChangeEntry entry = new ChangeEntry(parsedDate);
                    entry.findFromFileDate();
                }
            }
        }
    }
}