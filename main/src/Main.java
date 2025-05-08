import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        IdeaGenerator ideaGenerator = new IdeaGenerator();

        System.out.println("Hi! Welcome to your relationship diary!");

        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("What would you like to do?" +
                    "\nOptions: manage entries - E, manage ideas - I, quit - Q");
            String choice = scan.nextLine();
            if (choice.equalsIgnoreCase("e")) {
                boolean entryMenu = true;
                while (entryMenu) {
                    System.out.println("What do you wish to do with the entries? \nadd entries - A, change entries - C, find entries - F, back to the main menu - Q");
                    choice = scan.nextLine();

                    if (choice.equalsIgnoreCase("a")) {
                        LocalDate parsedDate = null;
                        while (parsedDate == null) {
                            System.out.println("Enter the date of the occasion (yyyy-mm-dd): ");
                            try {
                                String date = scan.nextLine();
                                parsedDate = LocalDate.parse(date);
                            } catch (DateTimeParseException e) {
                                System.out.println("Date entered incorrectly >_< Try again!");
                            }
                        }
                        System.out.println("Enter the name of the occasion: ");
                        String name = scan.nextLine();
                        System.out.println("Enter the location of the occasion: ");
                        String location = scan.nextLine();
                        System.out.println("Write a description for the occasion: ");
                        String description = scan.nextLine();
                        Entry entry = new Entry(parsedDate, name, location, description);
                        entry.newEntry();

                    } else if (choice.equalsIgnoreCase("q")) {
                        System.out.println("returning to the main menu...");
                        entryMenu = false;

                    } else if (choice.equalsIgnoreCase("c")) {
                        LocalDate parsedDate = null;
                        while (parsedDate == null) {
                            System.out.println("Enter the date of the entry you'd like to change (yyyy-mm-dd):");
                            try {
                                String date = scan.nextLine();
                                parsedDate = LocalDate.parse(date);
                            } catch (DateTimeParseException e) {
                                System.out.println("Date entered incorrectly >_< Try again!");
                            }
                        }

                        System.out.println("Enter the name of the occasion you'd like to change: ");
                        String name = scan.nextLine();
                        System.out.println("Enter a new location for the occasion: ");
                        String location = scan.nextLine();
                        System.out.println("Write a new description for the occasion: ");
                        String description = scan.nextLine();
                        Entry entry = new Entry(parsedDate, name, location, description);
                        entry.changeEntry();

                    } else if (choice.equalsIgnoreCase("f")) { //tõstsin selle checki et kas üldse sellel kuupäeval entrysid on entry classi meetodisse
                        System.out.println("Would you like to find all entries that share a date or a specific entry? (all/one)");
                        choice = scan.nextLine();
                        if (choice.equalsIgnoreCase("all")) {
                            LocalDate parsedDate;
                            System.out.println("Enter the date you wish to see entries from (yyyy-mm-dd): ");
                            try {
                                String date = scan.nextLine();
                                parsedDate = LocalDate.parse(date);
                                Entry entry = new Entry(parsedDate);
                                entry.findFromFileDate();
                            } catch (DateTimeParseException e) {
                                System.out.println("Date entered incorrectly >_< Try again!");
                            }

                        } else if (choice.equalsIgnoreCase("one")) {
                            LocalDate parsedDate;
                            try {
                                System.out.println("Enter the date of the entry you wish to see (yyyy-mm-dd): ");
                                String date = scan.nextLine();
                                parsedDate = LocalDate.parse(date);
                                System.out.println("Enter the name of the entry: ");
                                String inqEntryName = scan.nextLine();
                                Entry entryWithName = new Entry(inqEntryName, parsedDate);
                                entryWithName.findFromFileName();

                            } catch (DateTimeParseException e) {
                                System.out.println("Date entered incorrectly >_< Try again!");
                            }
                        }

                    } else {
                        System.out.println("Invalid option, please try again!");
                    }
                }
            }

            else if (choice.equalsIgnoreCase("i")) {
                boolean ideaMenu = true;
                while (ideaMenu) {
                    System.out.println("What would you like to do with the ideas?" +
                            "\nOptions: add idea - A, get random idea - R, view saved ideas - S, view favorite ideas - F, favorite new idea - Y, remove idea - X, quit - Q");
                    String ideaChoice = scan.nextLine();

                    if (ideaChoice.equalsIgnoreCase("a")) {
                        //Adding a new idea.
                        ideaGenerator.addUserIdea();
                    } else if (ideaChoice.equalsIgnoreCase("r") || ideaChoice.equalsIgnoreCase("s")) {
                        //figuring out if user wants random idea or the saved ideas.
                        String type = ideaChoice.equalsIgnoreCase("r") ? "random" : "saved";
                        //getting a random idea from a category of choice and making sure the category is valid.
                        String input = ideaGenerator.checkCategoryInput();
                        if (input.equalsIgnoreCase("B")) {
                            continue;
                        }
                        ideaGenerator.getIdeas(type, input);
                    } else if (ideaChoice.equalsIgnoreCase("f")) {
                        //getting a list of all the ideas that have been favorited.
                        ideaGenerator.viewFavorites();
                    } else if (ideaChoice.equalsIgnoreCase("y")) {
                        // Marking an idea as a favorite
                        String input = ideaGenerator.getInputWithBackOption("Enter the activity name of the idea you want to favorite or write 'B' to go back: ");
                        if (input == null) {
                            continue;
                        }
                        ideaGenerator.markAsFavorite(input);
                    } else if (ideaChoice.equalsIgnoreCase("x")) {
                        String input = ideaGenerator.getInputWithBackOption("Enter the activity name of the idea you want to remove or write 'B' to go back:: ");
                        if (input == null) {
                            continue;
                        }
                        ideaGenerator.removeIdea(input);
                    } else if (ideaChoice.equalsIgnoreCase("q")) {
                        //closing the idea menu and going to the main manu.
                        System.out.println("returning to the main menu...");
                        ideaMenu = false;
                    } else {
                        // in case they type a choice that is not listed.
                        System.out.println("Invalid option, please try again!");
                    }
                }
            } else if (choice.equalsIgnoreCase("q")){
                System.out.println("Bye-bye!");
                break;
            } else {
                System.out.println("Invalid option, please try again!");
            }
        }
    }
}
