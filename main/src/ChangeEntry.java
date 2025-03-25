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
        List<String> entries = readFromFile("main/entries.txt");
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
}
