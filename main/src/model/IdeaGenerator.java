package model;

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

    public List<Idea> getFilteredIdeas(String type) {
        return "random".equalsIgnoreCase(type) ? randomIdeas : savedIdeas;
    }

    public boolean addIdea(String activity, String category, String description) {
        if (activity == null || category == null || description == null) return false;

        Idea newIdea;
        if ("date".equalsIgnoreCase(category)) {
            newIdea = new DateIdeaGenerator(activity, category, description);
        } else {
            newIdea = new HangoutIdeaGenerator(activity, category, description);
        }

        try (FileWriter writer = new FileWriter(savedIdeasFile, true)) {
            writer.write(newIdea.getActivity() + ";;" +
                    newIdea.getCategory() + ";;" +
                    newIdea.getDescription() + ";;false\n");
            savedIdeas.add(newIdea);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void saveIdea(Idea idea) {
        try (FileWriter write = new FileWriter(savedIdeasFile, true)) {
            write.write(idea.getActivity() + ";;" +
                    idea.getCategory() + ";;" +
                    idea.getDescription() + ";;" +  // Missing ;; fixed
                    idea.isFavorite() + "\n");
            savedIdeas.add(idea);
            System.out.println("Idea is now saved! :)");
        } catch (IOException e) {
            System.out.println("There was an error while saving your idea. :(");
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
            System.out.println("No ideas found in the file: " + filename);
        }

        return ideas;
    }

    private static Idea getIdea(boolean isSavedIdeas, String line) {
        String[] parts = line.split(";;");
        if (parts.length < 3) return null;

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

    public void markAsFavorite(String activity) {
        for (Idea idea : savedIdeas) {
            if (idea.getActivity().equalsIgnoreCase(activity)) {
                if (!idea.isFavorite()) {
                    idea.setFavorite(true);
                    saveIdeasToFile(savedIdeas);
                }
                return;
            }
        }
    }

    private void saveIdeasToFile(List<Idea> ideas) {
        try (FileWriter writer = new FileWriter(savedIdeasFile)) {
            for (Idea idea : ideas) {
                writer.write(idea.getActivity() + ";;" +
                        idea.getCategory() + ";;" +
                        idea.getDescription() + ";;" +
                        idea.isFavorite() + "\n");
            }
        } catch (IOException e) {
            System.out.println("There was an error while saving the ideas. :(");
        }
    }

    public void removeIdea(String activity) {
        for (int i = 0; i < savedIdeas.size(); i++) {
            Idea idea = savedIdeas.get(i);
            if (idea.getActivity().equalsIgnoreCase(activity)) {
                savedIdeas.remove(i);
                saveIdeasToFile(savedIdeas);
                return;
            }
        }
    }

    public List<Idea> getFavorites() {
        List<Idea> favorites = new ArrayList<>();
        for (Idea idea : savedIdeas) {
            if (idea.isFavorite()) {
                favorites.add(idea);
            }
        }
        return favorites;
    }
}
