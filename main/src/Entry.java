import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Entry {
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

    public abstract void writeToFile() throws Exception;

    public void findFromFileName(String fileName, LocalDate inqDate, String inqName) throws Exception {
        File f = new File(fileName);
        try (Scanner fail = new Scanner(f, "UTF-8")) {
            while (fail.hasNextLine()) {
                String line = fail.nextLine();
                String[] lineSplit = line.split(";;");
                LocalDate lineDate = LocalDate.parse(lineSplit[0]);
                if (lineDate == inqDate && lineSplit[1].equals(inqName)) {
                    System.out.println(lineSplit.toString());// siia sisse output sellel kujul nag tahame
                }
            }
        }
    }

    public void findFromFileDate() throws Exception {
        File f = new File("main/entries.txt");
        try (Scanner fail = new Scanner(f, "UTF-8")) {
            while (fail.hasNextLine()) {
                String line = fail.nextLine();
                String[] lineSplit = line.split(";;");
                LocalDate lineDate = LocalDate.parse(lineSplit[0]);
                if (lineDate.equals(this.date)) {
                    System.out.println("Date: " + lineSplit[0] + "; Name: " + lineSplit[1] + "; Location: " + lineSplit[2] + "; Description: " + lineSplit[3]);// siia sisse output sellel kujul nag tahame
                }
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
