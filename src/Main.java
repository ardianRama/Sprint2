import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    Main() {
        Path inFile = Paths.get("src/Personuppgifter.txt");
        String outFile = "src/Gymbesökare.txt";

        List<Person> personList = IOUtility.readDataFromFile(inFile);
        String userInput = Gym.takeInputFromUser();
        Person foundPerson = Gym.findPersonByFullNameOrPersonNr(userInput, personList);
        boolean validMembership = Gym.validateGymMembership(foundPerson);

        if (validMembership) {
            IOUtility.writeDataToFile(outFile, foundPerson);
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
    }
}