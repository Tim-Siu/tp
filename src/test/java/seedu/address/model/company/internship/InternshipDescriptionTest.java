package seedu.address.model.company.internship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InternshipDescriptionTest {

    private InternshipDescription validDescription;

    @BeforeEach
    public void setUp() {
        validDescription = new InternshipDescription("Job description...");
    }

    @Test
    public void testConstructorWithValidDescription() {
        assertEquals("Job description...", validDescription.value);
    }

    @Test
    public void testConstructorWithInvalidDescription() {
        // Check if an exception is thrown for an invalid description.
        String invalidDesc = "";
        assertThrows(IllegalArgumentException.class, () -> new InternshipDescription(invalidDesc));
    }

    @Test
    public void testIsValidDescriptionWithValidDescription() {
        assertTrue(InternshipDescription.isValidDescription("Job description..."));
    }

    @Test
    public void testIsValidDescriptionWithInvalidDescription() {
        assertFalse(InternshipDescription.isValidDescription("")); // Empty description is invalid.
        assertFalse(InternshipDescription.isValidDescription("   ")); // Blank description is invalid.
    }

    @Test
    public void testEquals() {
        InternshipDescription description1 = new InternshipDescription("Job description...");
        InternshipDescription description2 = new InternshipDescription("Job description...");

        // Same value -> returns True
        assertTrue(description1.equals(description2));

        // Same object -> returns True
        assertTrue(description1.equals(description1));

        // null -> returns False
        assertFalse(description1.equals(null));
    }

    @Test
    public void testHashCode() {
        InternshipDescription description1 = new InternshipDescription("Job description...");
        InternshipDescription description2 = new InternshipDescription("Job description...");

        assertEquals(description1.hashCode(), description2.hashCode());
    }
}
