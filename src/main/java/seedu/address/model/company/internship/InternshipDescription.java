package seedu.address.model.company.internship;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Internship's description in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDescription(String)}
 */
public class InternshipDescription {
    public static final String MESSAGE_CONSTRAINTS =
            "Descriptions can be any non-empty string and should not be blank.";
    public static final String VALIDATION_REGEX = "\\S+.*";
    public final String value;

    /**
     * Constructs a {@code InternshipDescription}.
     *
     * @param description A valid description.
     */
    public InternshipDescription(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        value = description;
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InternshipDescription)) {
            return false;
        }

        InternshipDescription otherDescription = (InternshipDescription) other;
        return value.equals(otherDescription.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
