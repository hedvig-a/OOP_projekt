import java.io.File;
import java.time.LocalDate;
import java.util.Scanner;

abstract class Entry implements Comparable {
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

    public abstract void writeToFile();

    public void readFromFile(String fileName, LocalDate inqDate, String inqName) throws Exception{
        File f = new File(fileName);
        try (Scanner fail = new Scanner(f, "UTF-8")) {
            while (fail.hasNextLine()) {
                String line = fail.nextLine();
                String[] lineSplit = line.split(";");
                LocalDate lineDate = LocalDate.parse(lineSplit[0]);
                if (lineDate == inqDate && lineSplit[1].equals(inqName)){
                    System.out.println();// siia sisse output sellel kujul nag tahame
                }
            }
        }
    }


}
