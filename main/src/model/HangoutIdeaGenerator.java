package model;

public class HangoutIdeaGenerator extends Idea {

    public HangoutIdeaGenerator(String activity, String category, String description) {
        super(activity, category, description);
    }

    @Override
    public String toString() {
        return "[Hangout idea]\n" + super.toString();
    }
}
