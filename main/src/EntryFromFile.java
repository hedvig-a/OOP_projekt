import java.time.LocalDate;

public class EntryFromFile extends Entry{
    public EntryFromFile(LocalDate date, String name, String place, String description) {
        super(date, name, place, description);
    }

    //class is needed for creating lists of entries, writetofile is unnecessary here.
    @Override
    public void writeToFile() throws Exception {
        return;
    }
}
