package seedu.address.storage;

import static seedu.address.commons.util.DateTimeParserUtil.parseDateTimeToString;
import static seedu.address.commons.util.DateTimeParserUtil.parseStringToDateTime;

import java.util.Objects;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.company.internship.Internship;
import seedu.address.model.company.internship.InternshipDescription;
import seedu.address.model.company.internship.InternshipInterviewDateTime;
import seedu.address.model.company.internship.InternshipName;

/**
 * Jackson-friendly version of {@link Internship}.
 */
public class JsonAdaptedInternship {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Internship's %s field is missing!";
    private final String roleName;
    private final String description;
    private final String interviewDateTime;

    /**
     * Constructs a {@code JsonAdaptedInternship} with the given {@code Internship}.
     */
    @JsonCreator
    public JsonAdaptedInternship(@JsonProperty("roleName") String name, @JsonProperty("description") String description,
                                 @JsonProperty("interviewDateTime") String interviewDateTime) {
        this.roleName = name;
        this.description = description;
        this.interviewDateTime = interviewDateTime;
    }

    /**
     * Converts a given {@code Internship} into this class for Jackson use.
     */
    public JsonAdaptedInternship(Internship source) {
        this.roleName = source.getInternshipName().fullName;
        this.description = source.getInternshipDesc().value;

        // Process the dateTime of the interview, and adds it to the JsonAdaptedInternship object
        Optional<InternshipInterviewDateTime> internshipDateTimeOptional = source.getInternshipDateTime();
        // Parses LocalDateTime to String
        this.interviewDateTime = internshipDateTimeOptional
                .map(internshipInterviewDateTime -> parseDateTimeToString(internshipInterviewDateTime
                        .getInternshipDateTime()))
                .orElse("");
    }

    /**
     * Converts this Jackson-friendly adapted internship object into the model's {@code Internship} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted internship.
     */
    public Internship toModelType() throws IllegalValueException {

        // Checking for roleName
        if (roleName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    InternshipName.class.getSimpleName()));
        }
        if (!InternshipName.isValidName(roleName)) {
            throw new IllegalValueException(InternshipName.MESSAGE_CONSTRAINTS);
        }
        final InternshipName modelName = new InternshipName(roleName);

        // Checking for description
        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    InternshipDescription.class.getSimpleName()));
        }
        if (!InternshipDescription.isValidDescription(description)) {
            throw new IllegalValueException(InternshipDescription.MESSAGE_CONSTRAINTS);
        }
        final InternshipDescription modelDesc = new InternshipDescription(description);

        // Checking for interviewDateTime
        if (interviewDateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    InternshipInterviewDateTime.class.getSimpleName()));
        }

        if (Objects.equals(interviewDateTime, "")) {
            // Creates an Internship object without an interview dateTime
            return new Internship(modelName, modelDesc);
        } else {
            // Checks if the value of the interviewDateTime is valid and can be parsed.
            if (!InternshipInterviewDateTime.isValidInterviewDateTime(interviewDateTime)) {
                throw new IllegalValueException(InternshipInterviewDateTime.MESSAGE_CONSTRAINTS);
            }

            // Creates an Internship object with an interview dateTime
            final InternshipInterviewDateTime modelInterviewDateTime =
                    new InternshipInterviewDateTime(parseStringToDateTime(interviewDateTime));

            return new Internship(modelName, modelDesc, modelInterviewDateTime);
        }
    }

}
