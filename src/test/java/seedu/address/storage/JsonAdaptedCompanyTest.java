package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedCompany.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.storage.JsonAdaptedInternshipTest.generateInvalidDateTimeInternshipMap;
import static seedu.address.storage.JsonAdaptedInternshipTest.generateInvalidDescInternshipMap;
import static seedu.address.storage.JsonAdaptedInternshipTest.generateInvalidNameInternshipMap;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCompanies.APPLE;
import static seedu.address.testutil.TypicalCompanies.MICROSOFT;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.company.CompanyEmail;
import seedu.address.model.company.CompanyName;
import seedu.address.model.company.CompanyPhone;
import seedu.address.model.company.Description;

public class JsonAdaptedCompanyTest {
    private static final String INVALID_NAME = "F@cebook";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_DESCRIPTION = "    ";
    private static final String INVALID_TAG = "#tech";
    private static final Map<String, String> INVALID_NAME_INTERNSHIP = generateInvalidNameInternshipMap();
    private static final Map<String, String> INVALID_DESC_INTERNSHIP = generateInvalidDescInternshipMap();
    private static final Map<String, String> INVALID_DATE_TIME_INTERNSHIP = generateInvalidDateTimeInternshipMap();


    private static final String VALID_NAME = APPLE.getCompanyName().toString();
    private static final String VALID_PHONE = APPLE.getCompanyPhone().toString();
    private static final String VALID_EMAIL = APPLE.getCompanyEmail().toString();
    private static final String VALID_DESCRIPTION = APPLE.getDescription().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = APPLE.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    private static final List<JsonAdaptedInternship> VALID_INTERNSHIPS = MICROSOFT.getInternships().stream()
            .map(JsonAdaptedInternship::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validCompanyDetails_returnsCompany() throws Exception {
        JsonAdaptedCompany company = new JsonAdaptedCompany(APPLE);
        assertEquals(APPLE, company.toModelType());
    }

    @Test
    public void toModelType_invalidCompanyName_throwsIllegalValueException() {
        JsonAdaptedCompany company =
                new JsonAdaptedCompany(INVALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_DESCRIPTION, VALID_TAGS, VALID_INTERNSHIPS);
        String expectedMessage = CompanyName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    @Test
    public void toModelType_nullCompanyName_throwsIllegalValueException() {
        JsonAdaptedCompany company =
                new JsonAdaptedCompany(null, VALID_PHONE, VALID_EMAIL,
                        VALID_DESCRIPTION, VALID_TAGS, VALID_INTERNSHIPS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, CompanyName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedCompany company =
                new JsonAdaptedCompany(VALID_NAME, INVALID_PHONE, VALID_EMAIL,
                        VALID_DESCRIPTION, VALID_TAGS, VALID_INTERNSHIPS);
        String expectedMessage = CompanyPhone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedCompany company =
                new JsonAdaptedCompany(VALID_NAME, null, VALID_EMAIL,
                        VALID_DESCRIPTION, VALID_TAGS, VALID_INTERNSHIPS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, CompanyPhone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedCompany company =
                new JsonAdaptedCompany(VALID_NAME, VALID_PHONE, INVALID_EMAIL,
                        VALID_DESCRIPTION, VALID_TAGS, VALID_INTERNSHIPS);
        String expectedMessage = CompanyEmail.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedCompany company =
                new JsonAdaptedCompany(VALID_NAME, VALID_PHONE, null,
                        VALID_DESCRIPTION, VALID_TAGS, VALID_INTERNSHIPS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, CompanyEmail.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedCompany company =
                new JsonAdaptedCompany(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        INVALID_DESCRIPTION, VALID_TAGS, VALID_INTERNSHIPS);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedCompany company =
                new JsonAdaptedCompany(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        null, VALID_TAGS, VALID_INTERNSHIPS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedCompany company =
                new JsonAdaptedCompany(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_DESCRIPTION, invalidTags, VALID_INTERNSHIPS);
        assertThrows(IllegalValueException.class, company::toModelType);
    }

    @Test
    public void toModelType_companyWithInvalidNameInternship_throwsIllegalValueException() {
        List<JsonAdaptedInternship> invalidInternships = new ArrayList<>(VALID_INTERNSHIPS);
        invalidInternships.add(new JsonAdaptedInternship(INVALID_NAME_INTERNSHIP.get("roleName"),
                INVALID_NAME_INTERNSHIP.get("description"),
                INVALID_NAME_INTERNSHIP.get("interviewDateTime")));
        JsonAdaptedCompany company =
                new JsonAdaptedCompany(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_DESCRIPTION, VALID_TAGS, invalidInternships);
        assertThrows(IllegalValueException.class, company::toModelType);
    }

    @Test
    public void toModelType_companyWithInvalidDescInternship_throwsIllegalValueException() {
        List<JsonAdaptedInternship> invalidInternships = new ArrayList<>(VALID_INTERNSHIPS);
        invalidInternships.add(new JsonAdaptedInternship(INVALID_DESC_INTERNSHIP.get("roleName"),
                INVALID_DESC_INTERNSHIP.get("description"),
                INVALID_DESC_INTERNSHIP.get("interviewDateTime")));
        JsonAdaptedCompany company =
                new JsonAdaptedCompany(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_DESCRIPTION, VALID_TAGS, invalidInternships);
        assertThrows(IllegalValueException.class, company::toModelType);
    }

    @Test
    public void toModelType_companyWithInvalidDateTimeInternship_throwsIllegalValueException() {
        List<JsonAdaptedInternship> invalidInternships = new ArrayList<>(VALID_INTERNSHIPS);
        invalidInternships.add(new JsonAdaptedInternship(INVALID_DATE_TIME_INTERNSHIP.get("roleName"),
                INVALID_DATE_TIME_INTERNSHIP.get("description"),
                INVALID_DATE_TIME_INTERNSHIP.get("interviewDateTime")));
        JsonAdaptedCompany company =
                new JsonAdaptedCompany(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_DESCRIPTION, VALID_TAGS, invalidInternships);
        assertThrows(IllegalValueException.class, company::toModelType);
    }

}
