import java.io.FileWriter;
import java.time.LocalDate;
import java.io.IOException;
import java.util.List;

public class NewEntry extends Entry {
    public NewEntry(LocalDate date, String name, String place, String description) {
        super(date, name, place, description);
    }

    @Override
    public void writeToFile() throws Exception{
        List<String> previousEntries = readFromFile("main/entries.txt");
        previousEntries.add(getDate() + ";;" + getName() + ";;" + getPlace() + ";;" + getDescription());
        //Method found from https://www.w3schools.com/java/java_files_create.asp
        try {
            FileWriter myWriter = new FileWriter("main/entries.txt");
            for (String entry : previousEntries) {
                myWriter.write('\n' + entry);
            }
            myWriter.close();
            System.out.println("Entry added! ヽ(>∀<☆)ノ");
        } catch (IOException e) {
            System.out.println("Error!");
            e.printStackTrace();
        }
    }
}
