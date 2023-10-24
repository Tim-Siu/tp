package seedu.address.model.company.internship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InternshipNameTest {

    private InternshipName validName;

    @BeforeEach
    public void setUp() {
        validName = new InternshipName("Software Engineer");
    }

    @Test
    public void testConstructorWithValidName() {
        assertEquals("Software Engineer", validName.fullName);
    }

    @Test
    public void testConstructorWithInvalidName() {
        // Check if an exception is thrown for an invalid name.
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new InternshipName(invalidName));
    }

    @Test
    public void testIsValidNameWithValidName() {
        assertTrue(InternshipName.isValidName("Software Engineer"));
    }

    @Test
    public void testIsValidNameWithInvalidName() {
        assertFalse(InternshipName.isValidName("")); // Empty name is invalid.
        assertFalse(InternshipName.isValidName("   ")); // Blank name is invalid.
    }

    @Test
    public void testEquals() {
        InternshipName name1 = new InternshipName("Software Engineer");
        InternshipName name2 = new InternshipName("Software Engineer");

        // name's values are equal -> return True
        assertTrue(name1.equals(name2));

        // same object -> return True
        assertTrue(name1.equals(name1));

        // null -> return False
        assertFalse(name1.equals(null));
    }

    @Test
    public void testHashCode() {
        InternshipName name1 = new InternshipName("Software Engineer");
        InternshipName name2 = new InternshipName("Software Engineer");

        assertEquals(name1.hashCode(), name2.hashCode());
    }
}
