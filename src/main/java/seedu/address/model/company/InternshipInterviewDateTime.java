package seedu.address.model.company;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.DateTimeParserUtil.DATE_TIME_CONSTRAINTS;
import static seedu.address.commons.util.DateTimeParserUtil.isValidDateTimeString;
import static seedu.address.commons.util.DateTimeParserUtil.isValidLocalDateTime;
import static seedu.address.commons.util.DateTimeParserUtil.parseDateTimeToString;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents an Internship's interview's date and time in the address book.
 */
public class InternshipInterviewDateTime {
    public LocalDateTime interviewDateTime;
    public static String MESSAGE_CONSTRAINTS = DATE_TIME_CONSTRAINTS;

    /**
     * Constructs a {@code InternshipInterviewDateTime} with an interview date.
     *
     * @param dateTime The date and time of the interview.
     */
    public InternshipInterviewDateTime(LocalDateTime dateTime) {
        requireNonNull(dateTime);
        this.interviewDateTime = dateTime;
    }

    /**
     * Returns the date and time of the interview, in LocalDateTime format.
     *
     * @return The date and time of the interview.
     */
    public LocalDateTime getInternshipDateTime() {
        return this.interviewDateTime;
    }

    /**
     * Checks if a given string is in the correct date and time format for parsing.
     *
     * @return true if valid, false if not.
     */
    public static boolean isValidInterviewDateTime(String test) {
        if (isNull(test) || Objects.equals(test.strip(), "")) {
            return false;
        }

        return isValidDateTimeString(test);
    }

    /**
     * Checks if a given LocalDateTime is in the correct date and time format for parsing.
     *
     * @return true if valid, false if not.
     */
    public static boolean isValidInterviewDateTime(LocalDateTime test) {
        if (isNull(test)) {
            return false;
        }

        return isValidLocalDateTime(test);
    }

    @Override
    public int hashCode() {
        return interviewDateTime.hashCode();
    }

    @Override
    public String toString() {
        return parseDateTimeToString(interviewDateTime);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InternshipInterviewDateTime)) {
            return false;
        }

        InternshipInterviewDateTime otherInternship = (InternshipInterviewDateTime) other;
        return interviewDateTime.equals(otherInternship.interviewDateTime);
    }
}
