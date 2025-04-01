import java.io.*;
import java.util.*;

public class IdeaGenerator {
    private final List<Idea> randomIdeas;
    private final List<Idea> savedIdeas;
    private final String savedIdeasFile = "main/saved_ideas.txt";

    public IdeaGenerator() {
        String randomIdeasFile = "main/random_ideas.txt";
        randomIdeas = getIdeasFromFile(randomIdeasFile, false);
        savedIdeas = getIdeasFromFile(savedIdeasFile, true);
    }

    public void addUserIdea() {
        String activity = getInputWithBackOption("Enter a title for your idea/activity or write 'B' to go back: ");
        if (activity == null) {
            return;
        }
        //asks for the category input and makes sure it is either date or hangout.
        String category = checkCategoryInput();
        if (category.equalsIgnoreCase("B")) {
            return;
        }
        String description = getInputWithBackOption("Enter a simple description or write 'B' to go back: ");
        if (description == null) {
            return;
        }

        Idea newIdea;
        if ("date".equalsIgnoreCase(category)) {
            newIdea = new DateIdeaGenerator(activity, category, description);
        } else {
            newIdea = new HangoutIdeaGenerator(activity, category, description);
        }

        try (FileWriter writer = new FileWriter(savedIdeasFile, true)) {
            writer.write(newIdea.getActivity() + ";;" + newIdea.getCategory() + ";;" + newIdea.getDescription() + ";;false\n");
        } catch (IOException e) {
            System.out.println("There was an error while saving your idea. :(");
        }

        System.out.println("New idea added! :)");
    }

    public String checkCategoryInput() {
        while (true) {
            String category = getInputWithBackOption("Enter a category for the random idea (date/hangout) or press 'B' to go back: ");
            if (category == null) {
                return "B";
            }
            if (category.equalsIgnoreCase("date") || category.equalsIgnoreCase("hangout")) {
                return category;
            } else {
                System.out.println("Invalid category, please enter either 'date' or 'hangout'.");
            }
        }
    }

    public String getInputWithBackOption(String prompt){
        Scanner scan = new Scanner(System.in);
        System.out.println(prompt);
        String input = scan.nextLine();
        return input.equalsIgnoreCase("B") ? null : input;
    }

    public void getIdeas(String type, String category) {
        List<Idea> ideas = ("random".equalsIgnoreCase(type)) ? randomIdeas : savedIdeas;
        List<Idea> filteredIdeas = new ArrayList<>();

        for (Idea idea : ideas) {
            if (idea.getCategory().equalsIgnoreCase(category)) {
                filteredIdeas.add(idea);
            }
        }

        if (filteredIdeas.isEmpty()) {
            System.out.println("No ideas found in this category. :(");
            return;
        }

        if (type.equalsIgnoreCase("random")) {
            Random random = new Random();
            Idea randomIdea = filteredIdeas.get(random.nextInt(filteredIdeas.size()));
            System.out.println(randomIdea);

            //check if the user would like to save this idea.
            Scanner scanner = new Scanner(System.in);
            System.out.println("Would you like to save this idea (yes/no)? ");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("yes")) {
                saveIdea(randomIdea);
            }
        } else {
            System.out.println("Saved ideas in the " + category + " category: ");
            for (Idea idea : filteredIdeas) {
                System.out.println(idea);
            }
        }
    }

    private List<Idea> getIdeasFromFile(String filename, boolean isSavedIdeas) {
        List<Idea> ideas = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Idea idea = getIdea(isSavedIdeas, line);
                ideas.add(idea);
                
            }
        } catch (FileNotFoundException e) {
            System.out.println("No ideas found in the file. :(");
        }
        return ideas;
    }

    private static Idea getIdea(boolean isSavedIdeas, String line) {
        String[] parts = line.split(";;");
        String activity = parts[0];
        String category = parts[1];
        String description = parts[2];
        boolean isFavorite = isSavedIdeas && parts.length == 4 && Boolean.parseBoolean(parts[3]);

        Idea idea;
        if ("date".equalsIgnoreCase(category)) {
            idea = new DateIdeaGenerator(activity, category, description);
        } else {
            idea = new HangoutIdeaGenerator(activity, category, description);
        }
        idea.setFavorite(isFavorite);
        return idea;
    }

    private void saveIdea(Idea idea) {
        try (FileWriter write = new FileWriter(savedIdeasFile, true)) {
            write.write(idea.getActivity() + ";;" + idea.getCategory() + ";;" + idea.getDescription() + idea.isFavorite() + "\n");
            savedIdeas.add(idea);
            System.out.println("Idea is now saved! :)");
        } catch (IOException e) {
            System.out.println("There was an error while saving your idea. :(");
        }
    }


    public void markAsFavorite(String activity) {
        for (Idea idea : savedIdeas) {
            if (idea.getActivity().equalsIgnoreCase(activity)) {
                if (!idea.isFavorite()) {
                    idea.setFavorite(true);
                    saveIdeasToFile(savedIdeas);
                    System.out.println("Idea is now set as a favorite! :)");
                } else {
                    System.out.println("This idea is already a favourite! :)");
                }
                return;
            }
        }
        System.out.println("Idea not found in your saved ideas. :(");
    }

    private void saveIdeasToFile(List<Idea> ideas) {
        try (FileWriter writer = new FileWriter("main/saved_ideas.txt")) {
            for (Idea idea : ideas) {
                writer.write(idea.getActivity() + ";;" +idea.getCategory() + ";;" + idea.getDescription() + ";;" + idea.isFavorite() + "\n");
            }
        } catch (IOException e) {
            System.out.println("There was an error while saving the ideas. :(");
        }
    }

    public void viewFavorites() {
        System.out.println("Your favorite ideas: ");
        boolean foundFavorite = false;
        for (Idea idea : savedIdeas) {
            if (idea.isFavorite()) {
                System.out.println(idea);
                foundFavorite = true;
            }
        }
        if (!foundFavorite) {
            System.out.println("You have no favorites yet. :(");
        }
    }

    public void removeIdea(String activity) {
        for (int i = 0; i < savedIdeas.size(); i++) {
            Idea idea = savedIdeas.get(i);
            if (idea.getActivity().equalsIgnoreCase(activity)) {
                savedIdeas.remove(i); // Remove the idea from the ideas list
                saveIdeasToFile(savedIdeas);
                System.out.println("Idea removed successfully");
                return;
            }
        }
        System.out.println("Could not find the idea.");
    }
}
