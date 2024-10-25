import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GymTest {

    Person member = new Person("7703021234", "Kalle Anka", LocalDate.now());
    Person notMemberAnyMore = new Person("5711121234", "Hilmer Heur", LocalDate.now().minusYears(1).minusDays(1));
    List<Person> persons = Arrays.asList(member, notMemberAnyMore);

    //finns inte med i listan
    Person unauthorized = new Person("6907082345", "Calle Svensson", LocalDate.now());

    @Test
    void findPersonByFullNameOrPersonNr() {
        assertEquals(member.getName(), persons.get(0).getName());
        assertEquals(member.getPersonNr(), persons.get(0).getPersonNr());
        assertFalse("Pia Svensson" == persons.get(0).getName());
        assertFalse("9204091245" == persons.get(0).getPersonNr());

        assertEquals(notMemberAnyMore.getName(), persons.get(1).getName());
        assertEquals(notMemberAnyMore.getPersonNr(), persons.get(1).getPersonNr());
        assertFalse("Johan Svensson" == notMemberAnyMore.getName());
        assertFalse("8904172182" == notMemberAnyMore.getPersonNr());


        assertNull(Gym.findPersonByFullNameOrPersonNr(unauthorized.getName(), persons));
        assertNull(Gym.findPersonByFullNameOrPersonNr(unauthorized.getPersonNr(), persons));
    }

    @Test
    void validateGymMembership() {
        assertTrue(DateUtility.isDateWithinOneYear(member.getAnnualFee()));
        assertFalse(DateUtility.isDateWithinOneYear(notMemberAnyMore.getAnnualFee()));
    }
}