package seedu.address.model.company.internship;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Internship's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class InternshipName {
    public static final String MESSAGE_CONSTRAINTS =
            "Internship role names can be any non-empty string and should not be blank.";
    public static final String VALIDATION_REGEX = "\\S+.*";


    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public InternshipName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InternshipName)) {
            return false;
        }

        InternshipName otherInternshipName = (InternshipName) other;
        return fullName.equals(otherInternshipName.fullName);
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }
}
