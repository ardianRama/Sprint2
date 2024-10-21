import java.time.LocalDate;

public class Person {

    protected String personNr;
    protected String name;
    protected LocalDate annualFee;

    public Person(String personNr, String name, LocalDate annualFee) {
        this.name = name;
        this.personNr = personNr;
        this.annualFee = annualFee;
    }

    public String getName() {
        return name;
    }

    public String getLowerCaseName() {
        return name.toLowerCase();
    }

    public String getPersonNr() {
        return personNr;
    }

    public LocalDate getAnnualFee() {
        return annualFee;
    }
}
