import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Entry {
    private LocalDate date;
    private String name;
    private String place;
    private String description;

    public Entry(LocalDate date, String name, String place, String description) {
        this.date = date;
        this.name = name;
        this.place = place;
        this.description = description;
    }

    public Entry(String name, LocalDate date) {
        this.name = name;
        this.date = date;
    }

    public Entry(LocalDate date) {
        this.date = date;
    }

    public void writeToFile(List<String> previousEntries) throws Exception {
        //Method found from https://www.w3schools.com/java/java_files_create.asp
        try (FileWriter myWriter = new FileWriter("main/entries.txt")) {
            for (String entry : previousEntries) {
                if (previousEntries.indexOf(entry) == 0) {
                    myWriter.write(entry);
                } else {
                    myWriter.write('\n' + entry);
                }
            }
        }
    }

    public void changeEntry() throws Exception {
        List<String> entries = readFromFile("main/entries.txt");
        if (entries.isEmpty()) {
            System.out.println("No entries to change! Try adding an entry instead ^_^");
            return;
        }
        for (int i = 0; i < entries.size(); i++) {
            String line = entries.get(i);
            String[] lineSplit = line.split(";;");
            if (LocalDate.parse(lineSplit[0]).equals(getDate()) && lineSplit[1].equals(getName())) {
                entries.set(i, toString());
            }
        }
        writeToFile(entries);
    }

    public void newEntry() throws Exception {
        List<String> previousEntries = readFromFile("main/entries.txt");
        previousEntries.add(toString());
        writeToFile(previousEntries);
        System.out.println("Entry added! ヽ(>∀<☆)ノ");
    }

    public void findFromFileName() throws Exception {
        File f = new File("main/entries.txt");
        boolean entryFound = false;
        String output = "";
        try (Scanner fail = new Scanner(f, "UTF-8")) {
            while (fail.hasNextLine()) {
                String line = fail.nextLine();
                String[] lineSplit = line.split(";;");
                LocalDate lineDate = LocalDate.parse(lineSplit[0]);
                if (lineSplit[1].equals(this.name) && lineDate.equals(this.date)) {
                    entryFound = true;
                    output = "Date: " + lineSplit[0] + "; Name: " + lineSplit[1] + "; Location: " + lineSplit[2] + "; Description: " + lineSplit[3];
                    break;
                }
            }
        }
        if (!entryFound) {
            System.out.println("No entry found!");
        } else {
            System.out.println(output);
        }
    }

    public void findFromFileDate() throws Exception {
        File f = new File("main/entries.txt");
        boolean entryFound = false;
        List<String> output = new ArrayList<>();
        try (Scanner fail = new Scanner(f, "UTF-8")) {
            while (fail.hasNextLine()) {
                String line = fail.nextLine();
                String[] lineSplit = line.split(";;");
                LocalDate lineDate = LocalDate.parse(lineSplit[0]);
                if (lineDate.equals(this.date)) {
                    entryFound = true;
                    output.add("Date: " + lineSplit[0] + "; Name: " + lineSplit[1] + "; Location: " + lineSplit[2] + "; Description: " + lineSplit[3]);
                }
            }
        }
        if (!entryFound) {
            System.out.println("No entries with that date :(");
        } else {
            for (String entry : output) {
                System.out.println(entry);
            }
        }
    }

    public List<String> readFromFile(String fileName) throws Exception {
        List<String> entries = new ArrayList<>();
        File f = new File(fileName);
        try (Scanner fail = new Scanner(f, "UTF-8")) {
            while (fail.hasNextLine()) {
                String line = fail.nextLine();
                entries.add(line);
            }
        }
        return entries;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getPlace() {
        return place;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return getDate() + ";;" + getName() + ";;" + getPlace() + ";;" + getDescription();
    }
}
