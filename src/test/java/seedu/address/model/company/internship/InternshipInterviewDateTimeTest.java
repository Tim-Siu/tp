package seedu.address.model.company.internship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class InternshipInterviewDateTimeTest {

    private LocalDateTime validDateTime;

    @BeforeEach
    public void setUp() {
        validDateTime = LocalDateTime.of(2023, 12, 15, 14, 30);
    }

    @Test
    public void testConstructorWithValidDateTime() {
        InternshipInterviewDateTime interviewDateTime = new InternshipInterviewDateTime(validDateTime);
        assertEquals(validDateTime, interviewDateTime.getInternshipDateTime());
    }

    @Test
    public void testIsValidInterviewDateTimeWithValidDateTime() {
        String validDateTimeString = "15-12-2023 14:30";
        assertTrue(InternshipInterviewDateTime.isValidInterviewDateTime(validDateTimeString));
    }

    @Test
    public void testIsValidInterviewDateTimeWithInvalidDateTime() {
        String invalidDateTimeString = "InvalidDateTimeString";
        assertFalse(InternshipInterviewDateTime.isValidInterviewDateTime(invalidDateTimeString));
    }

    @Test
    public void testIsValidInterviewDateTimeWithNullDateTime() {
        String invalidDateTimeString = null;
        assertFalse(InternshipInterviewDateTime.isValidInterviewDateTime(invalidDateTimeString));
    }

    @Test
    public void testIsValidInterviewDateTimeWithEmptyDateTime() {
        String invalidDateTimeString = "";
        assertFalse(InternshipInterviewDateTime.isValidInterviewDateTime(invalidDateTimeString));
    }

    @Test
    public void testToString() {
        InternshipInterviewDateTime interviewDateTime = new InternshipInterviewDateTime(validDateTime);
        String expected = "15-12-2023 14:30";
        assertEquals(expected, interviewDateTime.toString());
    }

    @Test
    public void testEquals() {
        InternshipInterviewDateTime interviewDateTime1 = new InternshipInterviewDateTime(validDateTime);
        InternshipInterviewDateTime interviewDateTime2 = new InternshipInterviewDateTime(validDateTime);

        // Objects with same dateTime value -> returns True
        assertTrue(interviewDateTime1.equals(interviewDateTime2));

        // Same object -> returns True
        assertTrue(interviewDateTime1.equals(interviewDateTime1));

        // null -> returns False
        assertFalse(interviewDateTime1.equals(null));
    }

    @Test
    public void testHashCode() {
        InternshipInterviewDateTime interviewDateTime1 = new InternshipInterviewDateTime(validDateTime);
        InternshipInterviewDateTime interviewDateTime2 = new InternshipInterviewDateTime(validDateTime);

        assertEquals(interviewDateTime1.hashCode(), interviewDateTime2.hashCode());
    }
}
