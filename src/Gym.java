import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Gym {

    public static String takeInputFromUser() {
        while (true) {
            String input = JOptionPane.showInputDialog("Skriv Namn/PersonNr");
            if (input == null) {
                System.exit(0);
            }
            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Du behöver skriva ett Namn/PersonNr");
                continue;
            }
            return input.toLowerCase().trim();
        }
    }

    public static Person findPersonByFullNameOrPersonNr(String userInput, List<Person> personList) {
        for (Person person : personList) {
            if (person.getLowerCaseName().equals(userInput) || person.getPersonNr().equals(userInput)) {
                return person;
            }
        }
        return null;
    }

    public static boolean validateGymMembership(Person person) {
        if (person == null) {
            beep(1);
            showMessageToUser("Personen är obehörig.");
            return false;
        }
        if (DateUtility.isDateWithinOneYear(person.getAnnualFee())) {
            showMessageToUser("Kunden är nuvarande medlem.\n " +
                    "Senast betalda medlemskap: " + person.getAnnualFee());
            return true;
        } else {
            showMessageToUser("Kunden är en fd. medlem.\n " +
                    "Senast betalda medlemskap: " + person.getAnnualFee());
            return false;
        }
    }

    public static void showMessageToUser(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public static void beep(int n) {
        for (int i = 1; i <= n; i++) {
            Toolkit.getDefaultToolkit().beep();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
