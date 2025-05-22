package model;

public class Idea {
    protected String activity;
    protected String category;
    protected String description;
    protected boolean favorite;

    public Idea(String activity, String category, String description) {
        this.activity = activity;
        this.category = category;
        this.description = description;
        this.favorite = false; //By default, the idea is not a favourite.
    }

    public String getActivity() {
        return activity;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String toString() {
        return "Activity: " + activity +
                "\nDescription\n" + description;
    }
}
