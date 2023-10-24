package seedu.address.model.util;

import java.time.LocalDateTime;

import seedu.address.model.company.internship.Internship;
import seedu.address.model.company.internship.InternshipDescription;
import seedu.address.model.company.internship.InternshipInterviewDateTime;
import seedu.address.model.company.internship.InternshipName;

public class InternshipSampleDataUtil {
    public static final Internship SOFTWARE_ENGINEER_WITH_DATETIME = new Internship(
            new InternshipName("Software Engineering Intern 2024"),
            new InternshipDescription("Produce high quality software following good architecture that are scalable"),
            new InternshipInterviewDateTime(LocalDateTime.of(2023, 12, 15, 14, 30))
    );

    public static final Internship DATA_ANALYST_WITH_DATETIME = new Internship(
            new InternshipName("Data Analyst Intern 2024"),
            new InternshipDescription("Learn data analysis tools and techniques"),
            new InternshipInterviewDateTime(LocalDateTime.of(2024, 1, 15, 10, 30))
    );

    public static final Internship MARKETING_INTERN_WITH_DATETIME = new Internship(
            new InternshipName("Marketing Intern 2024"),
            new InternshipDescription("Conduct market research and analysis"),
            new InternshipInterviewDateTime(LocalDateTime.of(2024, 2, 20, 9, 45))
    );

    public static final Internship HR_INTERNSHIP_WITH_DATETIME = new Internship(
            new InternshipName("HR Intern 2024"),
            new InternshipDescription("Assist in recruitment and onboarding processes"),
            new InternshipInterviewDateTime(LocalDateTime.of(2024, 3, 10, 11, 15))
    );

    public static final Internship SOFTWARE_ENGINEER_WITHOUT_DATETIME = new Internship(
            new InternshipName("Software Engineering Intern 2024"),
            new InternshipDescription("Develop new features/functionalities on existing software products")
    );

    public static final Internship DATA_SCIENCE_INTERN_WITHOUT_DATETIME = new Internship(
            new InternshipName("Data Science Intern 2024"),
            new InternshipDescription("Work on data analytics projects and machine learning models")
    );

    public static final Internship MARKETING_INTERN_WITHOUT_DATETIME = new Internship(
            new InternshipName("Marketing Intern 2024"),
            new InternshipDescription("Create marketing campaigns and analyze market trends")
    );

}
