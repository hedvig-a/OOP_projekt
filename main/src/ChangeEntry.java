import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChangeEntry extends Entry{
    public ChangeEntry(LocalDate date, String name, String place, String description) {
        super(date, name, place, description);
    }

    @Override
    public void writeToFile() throws Exception{
        List<Entry> entries = readFromFile("main/entries.txt");
        try {
            FileWriter myWriter = new FileWriter("main/entries.txt");
            myWriter.write(getDate() + ";;" + getName() + ";;" + getPlace() + ";;" + getDescription());
            myWriter.close();
            System.out.println("Entry changed! ヽ(>∀<☆)ノ");
        } catch (IOException e) {
            System.out.println("Error!");
            e.printStackTrace();
        }
    }

    public List<Entry> readFromFile(String fileName) throws Exception{
        List<Entry> entries = new ArrayList<>();
        File f = new File(fileName);
        try (Scanner fail = new Scanner(f, "UTF-8")) {
            while (fail.hasNextLine()) {
                String line = fail.nextLine();
                String[] lineSplit = line.split(";;");
                entries.add(new ChangeEntry(LocalDate.parse(lineSplit[0]), lineSplit[1], lineSplit[2], lineSplit[3]));
            }
        }
        return entries;
    }
}
