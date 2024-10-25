import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IOUtilityTest {

    Path inFile = Paths.get("src/Personuppgifter.txt");
    String outFile = "src/testGymbesökare.txt";

    @Test
    void readDataFromFile() {
        List<Person> personList = IOUtility.readDataFromFile(inFile);

        assertTrue(personList.size() == 14);
        assertFalse(personList.size() == 10);
    }

    @Test
    void readExactDataFromFile() {
        List<Person> personList = IOUtility.readDataFromFile(inFile);

        assertEquals("8204021234", personList.get(1).getPersonNr());
        assertEquals("Bear Belle", personList.get(1).getName());
        assertEquals(LocalDate.of(2019, 12, 02), personList.get(1).getAnnualFee());

        assertNotEquals("7512166544", personList.get(1).getPersonNr());
        assertNotEquals("Liu Lingren", personList.get(1).getName());
        assertNotEquals(LocalDate.of(2023, 2, 27), personList.get(1).getAnnualFee());
    }

    public final int countLinesInFile(String fileToCount){
        int lines = 0;
        try (BufferedReader reader = new BufferedReader(
                new FileReader(fileToCount));) {
            while (reader.readLine() != null) lines++;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return lines;
    }

    @Test
    void writeDataToFile() {
        new File(outFile).delete();

        Person member = new Person("7703021234", "Kalle Anka", LocalDate.now());
        Person notMemberAnyMore = new Person("5711121234", "Hilmer Heur", LocalDate.now().minusYears(1).minusDays(1));

        List<Person> persons = Arrays.asList(member, notMemberAnyMore);

        for (Person list : persons) {
            if (LocalDate.now().isBefore(list.getAnnualFee().plusYears(1))) {
                IOUtility.writeDataToFile(outFile, list);
            }
        }

        assertEquals(2, countLinesInFile(outFile));
        assertFalse(countLinesInFile(outFile) == 3);

        try(BufferedReader br = new BufferedReader(new FileReader(outFile))) {
            String s = br.readLine();
            assertTrue(s.startsWith("7703021234, Kalle Anka"));
            assertFalse(s.startsWith("5711121234, Hilmer Heur"));
        }
        catch (FileNotFoundException e) {
            System.out.println("Filen kunde inte hittas");
            e.printStackTrace();
            System.exit(0);
        }
        catch (IOException e) {
            System.out.println("Det gick inte att läsa från fil");
            e.printStackTrace();
            System.exit(0);
        }
        catch (Exception e) {
            System.out.println("Något gick fel");
            e.printStackTrace();
            System.exit(0);
        }
    }
}