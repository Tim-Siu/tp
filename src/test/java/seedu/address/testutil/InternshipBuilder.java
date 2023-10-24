package seedu.address.testutil;

import static seedu.address.commons.util.DateTimeParserUtil.parseStringToDateTime;

import java.time.LocalDateTime;
import java.util.Optional;

import seedu.address.model.company.internship.Internship;
import seedu.address.model.company.internship.InternshipDescription;
import seedu.address.model.company.internship.InternshipInterviewDateTime;
import seedu.address.model.company.internship.InternshipName;

/**
 * A utility class to help with building Internship objects.
 */
public class InternshipBuilder {
    public static final String DEFAULT_NAME = "Software Engineering Internship 2024";
    public static final String DEFAULT_DESCRIPTION =
            "Requires proficiency in Java and Python, knowledge of SQL and Docker is a plus.";
    public static final LocalDateTime DEFAULT_DATETIME = parseStringToDateTime("24-11-2023 18:00");

    private InternshipName roleName;
    private InternshipDescription description;
    private Optional<InternshipInterviewDateTime> interviewDateTime;

    /**
     * Creates a {@code InternshipBuilder} with the default details.
     */
    public InternshipBuilder() {
        roleName = new InternshipName(DEFAULT_NAME);
        description = new InternshipDescription(DEFAULT_DESCRIPTION);
        interviewDateTime = Optional.of(new InternshipInterviewDateTime(DEFAULT_DATETIME));
    }

    /**
     * Initializes the InternshipBuilder with the data of {@code internshipToCopy}.
     */
    public InternshipBuilder(Internship internshipToCopy) {
        roleName = internshipToCopy.getInternshipName();
        interviewDateTime = internshipToCopy.getInternshipDateTime();
        description = internshipToCopy.getInternshipDesc();
    }

    /**
     * Sets the {@code InternshipName} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withInternshipName(String roleName) {
        this.roleName = new InternshipName(roleName);
        return this;
    }

    /**
     * Sets the {@code InternshipDescription} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withDescription(String description) {
        this.description = new InternshipDescription(description);
        return this;
    }

    /**
     * Sets the {@code InternshipInterviewDateTime} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withInterviewDateTime(LocalDateTime dateTime) {
        this.interviewDateTime = Optional.of(new InternshipInterviewDateTime(dateTime));
        return this;
    }

    /**
     * Removes the {@code InternshipInterviewDateTime} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withoutInterviewDateTime() {
        this.interviewDateTime = Optional.empty();
        return this;
    }

    /**
     * Builds the Internship object based on specified parameters.
     *
     * @return The built Internship object.
     */
    public Internship build() {
        return interviewDateTime
                .map(internshipInterviewDateTime -> new Internship(roleName, description, internshipInterviewDateTime))
                .orElseGet(() -> new Internship(roleName, description));
    }
}
