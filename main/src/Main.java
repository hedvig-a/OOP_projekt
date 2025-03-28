import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.println("Sisesta enda sündmus kujul kuupäev(aaaa-kk-pp) / sündmuse nimi / sündmuse koht / sündmuse kirjeldus");
        String input = scan.nextLine();
        String[] data = input.split(" / ");
        NewEntry entry = new NewEntry(LocalDate.parse(data[0]), data[1], data[2], data[3]);
        entry.writeToFile();
    }
}