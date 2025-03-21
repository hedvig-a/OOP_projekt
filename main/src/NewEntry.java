import java.io.FileWriter;
import java.time.LocalDate;
import java.io.IOException;

public class NewEntry extends Entry {
    public NewEntry(LocalDate date, String name, String place, String description) {
        super(date, name, place, description);
    }

    @Override
    public void writeToFile() {
        //Method found from https://www.w3schools.com/java/java_files_create.asp
        try {
            FileWriter myWriter = new FileWriter("main/entries.txt");
            myWriter.write(getDate() + ";;" + getName() + ";;" + getPlace() + ";;" + getDescription());
            myWriter.close();
            System.out.println("Entry added! ヽ(>∀<☆)ノ");
        } catch (IOException e) {
            System.out.println("Error!");
            e.printStackTrace();
        }
    }
}
