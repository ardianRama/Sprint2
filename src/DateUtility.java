import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtility {

    public static LocalDate convertLocalDateFromString(String date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, dtf);
    }

    public static boolean isDateWithinOneYear(LocalDate personsAnnualFee) {
        LocalDate currentDate = LocalDate.now();
        LocalDate newPersonAnnualFee = personsAnnualFee.plusYears(1);
        return currentDate.isBefore(newPersonAnnualFee);
    }
}
