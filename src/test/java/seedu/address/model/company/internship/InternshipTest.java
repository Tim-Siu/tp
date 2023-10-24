package seedu.address.model.company.internship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.DateTimeParserUtil.parseStringToDateTime;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InternshipTest {

    private InternshipName roleName;
    private InternshipDescription description;
    private InternshipInterviewDateTime interviewDateTime;

    @BeforeEach
    public void setUp() {
        roleName = new InternshipName("Software Engineer");
        description = new InternshipDescription("Job description...");
        interviewDateTime = new InternshipInterviewDateTime(parseStringToDateTime("15-12-2023 14:30"));
    }

    @Test
    public void testConstructorWithDateTime() {
        Internship internship = new Internship(roleName, description, interviewDateTime);
        assertEquals(roleName, internship.getInternshipName());
        assertEquals(description, internship.getInternshipDesc());
        assertEquals(Optional.of(interviewDateTime), internship.getInternshipDateTime());
    }

    @Test
    public void testConstructorWithoutDateTime() {
        Internship internship = new Internship(roleName, description);
        assertEquals(roleName, internship.getInternshipName());
        assertEquals(description, internship.getInternshipDesc());
        assertEquals(Optional.empty(), internship.getInternshipDateTime());
    }

    @Test
    public void testToStringWithDateTime() {
        Internship internship = new Internship(roleName, description, interviewDateTime);
        String expected = "{name=Software Engineer, description=Job description..., interview date=15-12-2023 14:30}";
        assertEquals(expected, internship.toString());
    }

    @Test
    public void testToStringWithoutDateTime() {
        Internship internship = new Internship(roleName, description);
        String expected = "{name=Software Engineer, description=Job description..., interview date=N/A}";
        assertEquals(expected, internship.toString());
    }

    @Test
    public void testEquals() {
        Internship internship1 = new Internship(roleName, description, interviewDateTime);
        Internship internship2 = new Internship(roleName, description, interviewDateTime);
        Internship internship3 = new Internship(roleName, description);

        // Same object -> returns True
        assertTrue(internship1.equals(internship1));

        // Objects with same variable values -> return True
        assertTrue(internship1.equals(internship2));

        // Objects with same variable values -> return True
        assertTrue(internship2.equals(internship1));

        // Objects with different variable values -> return False
        assertFalse(internship1.equals(internship3));

        // equals with things not of Internship class -> return False
        assertFalse(internship1.equals(null));
    }

    @Test
    public void testHashCode() {
        Internship internship1 = new Internship(roleName, description, interviewDateTime);
        Internship internship2 = new Internship(roleName, description, interviewDateTime);
        Internship internship3 = new Internship(roleName, description);

        assertEquals(internship1.hashCode(), internship2.hashCode());
        assertNotEquals(internship1.hashCode(), internship3.hashCode());
    }
}
