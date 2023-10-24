package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_EMAIL_ORACLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_ORACLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_PHONE_ORACLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_ORACLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TECH;
import static seedu.address.testutil.TypicalInternships.DATA_ANALYST_WITH_DATETIME;
import static seedu.address.testutil.TypicalInternships.MARKETING_INTERN_WITHOUT_DATETIME;
import static seedu.address.testutil.TypicalInternships.SOFTWARE_ENGINEER_WITHOUT_DATETIME;
import static seedu.address.testutil.TypicalInternships.SOFTWARE_ENGINEER_WITH_DATETIME;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.company.Company;
/**
 * A utility class containing a list of {@code Company} objects to be used in tests.
 */
public class TypicalCompanies {
    public static final Company APPLE = new CompanyBuilder().withCompanyName("Apple Inc.")
        .withCompanyEmail("apple@example.com")
        .withCompanyPhone("12345678")
        .withDescription("A technology company.")
        .withTags("tech", "innovative")
        .withInternships(SOFTWARE_ENGINEER_WITH_DATETIME, DATA_ANALYST_WITH_DATETIME).build();

    public static final Company MICROSOFT = new CompanyBuilder().withCompanyName("Microsoft Corporation")
        .withCompanyEmail("microsoft@example.com")
        .withCompanyPhone("23456789")
        .withDescription("A multinational technology company.")
        .withTags("tech", "windows")
        .withInternships(SOFTWARE_ENGINEER_WITHOUT_DATETIME, DATA_ANALYST_WITH_DATETIME,
                MARKETING_INTERN_WITHOUT_DATETIME).build();

    public static final Company GOOGLE = new CompanyBuilder().withCompanyName("Google LLC")
        .withCompanyEmail("google@example.com")
        .withCompanyPhone("34567890")
        .withDescription("An internet-related services and products company.")
        .withTags("tech", "search")
        .withInternships(SOFTWARE_ENGINEER_WITHOUT_DATETIME, MARKETING_INTERN_WITHOUT_DATETIME).build();

    // Manually added
    public static final Company AMAZON = new CompanyBuilder().withCompanyName("Amazon.com Inc.")
        .withCompanyEmail("amazon@example.com")
        .withCompanyPhone("45678901")
        .withDescription("An online retailer and cloud services company.")
        .build();

    public static final Company NETFLIX = new CompanyBuilder().withCompanyName("Netflix Inc.")
        .withCompanyEmail("netflix@example.com")
        .withCompanyPhone("56789012")
        .withDescription("A streaming service company.")
        .build();

    public static final Company ORACLE = new CompanyBuilder().withCompanyName(VALID_COMPANY_NAME_ORACLE)
            .withCompanyPhone(VALID_COMPANY_PHONE_ORACLE).withCompanyEmail(VALID_COMPANY_EMAIL_ORACLE)
            .withDescription(VALID_DESCRIPTION_ORACLE)
            .withTags(VALID_TAG_TECH).build();

    public static final String KEYWORD_MATCHING_TECH = "tech"; // A keyword that matches TECH

    private TypicalCompanies() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical companies.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Company company : getTypicalCompanies()) {
            ab.addCompany(company);
        }
        return ab;
    }

    public static List<Company> getTypicalCompanies() {
        return new ArrayList<>(Arrays.asList(APPLE, MICROSOFT, GOOGLE, AMAZON, NETFLIX));
    }
}
