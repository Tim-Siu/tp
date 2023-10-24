package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedInternship.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternships.SOFTWARE_ENGINEER_WITH_DATETIME;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.company.InternshipDescription;
import seedu.address.model.company.InternshipInterviewDateTime;
import seedu.address.model.company.InternshipName;

public class JsonAdaptedInternshipTest {
    private static final String INVALID_NAME = " ";
    private static final String INVALID_DESCRIPTION = "    ";
    private static final String INVALID_DATETIME = "293HGK";

    private static final String VALID_NAME = SOFTWARE_ENGINEER_WITH_DATETIME.getInternshipName().toString();
    private static final String VALID_DESCRIPTION = SOFTWARE_ENGINEER_WITH_DATETIME.getInternshipDesc().toString();
    private static final String VALID_DATETIME = SOFTWARE_ENGINEER_WITH_DATETIME
            .getInternshipDateTime().get().toString();

    /**
     * Generates a Map object which contains an invalid roleName for Internship
     */
    public static Map<String, String> generateInvalidNameInternshipMap() {
        Map <String, String> INVALID_NAME_INTERNSHIP = new HashMap<>();
        INVALID_NAME_INTERNSHIP.put("roleName", INVALID_NAME);
        INVALID_NAME_INTERNSHIP.put("description", VALID_DESCRIPTION);
        INVALID_NAME_INTERNSHIP.put("interviewDateTime", VALID_DATETIME);

        return INVALID_NAME_INTERNSHIP;
    }

    /**
     * Generates a Map object which contains an invalid description for Internship
     */
    public static Map<String, String> generateInvalidDescInternshipMap() {
        Map <String, String> INVALID_DESC_INTERNSHIP = new HashMap<>();
        INVALID_DESC_INTERNSHIP.put("roleName", VALID_NAME);
        INVALID_DESC_INTERNSHIP.put("description", INVALID_DESCRIPTION);
        INVALID_DESC_INTERNSHIP.put("interviewDateTime", VALID_DATETIME);

        return INVALID_DESC_INTERNSHIP;
    }

    /**
     * Generates a Map object which contains an invalid datetime for Internship
     */
    public static Map<String, String> generateInvalidDateTimeInternshipMap() {
        Map <String, String> INVALID_DATE_TIME_INTERNSHIP = new HashMap<>();
        INVALID_DATE_TIME_INTERNSHIP.put("roleName", VALID_NAME);
        INVALID_DATE_TIME_INTERNSHIP.put("description", VALID_DESCRIPTION);
        INVALID_DATE_TIME_INTERNSHIP.put("interviewDateTime", INVALID_DATETIME);

        return INVALID_DATE_TIME_INTERNSHIP;
    }

    @Test
    public void toModelType_validInternshipDetails_returnsInternship() throws Exception {
        JsonAdaptedInternship company = new JsonAdaptedInternship(SOFTWARE_ENGINEER_WITH_DATETIME);
        assertEquals(SOFTWARE_ENGINEER_WITH_DATETIME, company.toModelType());
    }

    @Test
    public void toModelType_invalidInternshipName_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(INVALID_NAME, VALID_DESCRIPTION, VALID_DATETIME);
        String expectedMessage = InternshipName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullInternshipName_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(null, VALID_DESCRIPTION, VALID_DATETIME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, InternshipName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_NAME, INVALID_DESCRIPTION, VALID_DATETIME);
        String expectedMessage = InternshipDescription.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_NAME, null, VALID_DATETIME);
        String expectedMessage =
                String.format(MISSING_FIELD_MESSAGE_FORMAT, InternshipDescription.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_invalidDateTime_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_NAME, VALID_DESCRIPTION, INVALID_DATETIME);
        String expectedMessage = InternshipInterviewDateTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullDateTime_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_NAME, VALID_DESCRIPTION, null);
        String expectedMessage =
                String.format(MISSING_FIELD_MESSAGE_FORMAT, InternshipInterviewDateTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }
}
