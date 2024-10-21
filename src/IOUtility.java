import java.io.*;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IOUtility {

    public static List<Person> readDataFromFile(Path readFromFile) {

        List<Person> personList = new ArrayList<>();

        try (Scanner scan = new Scanner(readFromFile)) {
            while (scan.hasNext()) {
                String firstLine = scan.nextLine();
                String[] firstLineData = firstLine.split(",");
                String personNr = firstLineData[0].trim();
                String fullName = firstLineData[1].trim();

                if (scan.hasNext()) {
                    String secondLine = scan.nextLine();
                    LocalDate annualFee = DateUtility.convertLocalDateFromString(secondLine);

                    Person p = new Person(personNr, fullName, annualFee);
                    personList.add(p);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Filen kunde inte hittas");
            e.printStackTrace();
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Det gick inte att läsa från fil");
            e.printStackTrace();
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Något gick fel");
            e.printStackTrace();
            System.exit(0);
        }
        return personList;
    }

    public static void writeDataToFile(String writeToFile, Person person) {

        try (BufferedWriter
                     writer = new BufferedWriter(new FileWriter(writeToFile, true))) {

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String linesToAppend = person.getPersonNr() + ", " + person.getName() + "\nBesökdatum: " + dtf.format(LocalDateTime.now());
            writer.write(linesToAppend);
            writer.newLine();

        } catch (FileNotFoundException e) {
            System.out.println("Filen kunde inte hittas");
            e.printStackTrace();
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Det gick inte att skriva till fil");
            e.printStackTrace();
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Något gick fel");
            e.printStackTrace();
            System.exit(0);
        }
    }
}
