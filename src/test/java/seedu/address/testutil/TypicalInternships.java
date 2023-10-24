package seedu.address.testutil;

import static seedu.address.commons.util.DateTimeParserUtil.parseStringToDateTime;

import seedu.address.model.company.internship.Internship;

/**
 * A utility class containing a list of {@code Internship} objects to be used in tests.
 */
public class TypicalInternships {
    public static final Internship SOFTWARE_ENGINEER_WITH_DATETIME = new InternshipBuilder()
            .withInternshipName("Software Engineering Intern 2024")
            .withDescription("Produce high quality software following good architecture that are scalable")
            .withInterviewDateTime(parseStringToDateTime("20-12-2023 08:00")).build();

    public static final Internship SOFTWARE_ENGINEER_WITHOUT_DATETIME = new InternshipBuilder()
            .withInternshipName("Software Engineering Intern 2024")
            .withDescription("Develop new features/functionalities on existing software products")
            .withoutInterviewDateTime().build();

    public static final Internship DATA_ANALYST_WITH_DATETIME = new InternshipBuilder()
            .withInternshipName("Data Analyst Intern 2024")
            .withDescription("Learn data analysis tools and techniques")
            .withInterviewDateTime(parseStringToDateTime("15-01-2024 10:30"))
            .build();

    public static final Internship MARKETING_INTERN_WITHOUT_DATETIME = new InternshipBuilder()
            .withInternshipName("Marketing Intern 2024")
            .withDescription("Conduct market research and analysis")
            .withoutInterviewDateTime()
            .build();
}
