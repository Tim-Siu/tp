package seedu.address.model.company;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents an Internship in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Internship {

    public final InternshipName roleName;
    public final InternshipDescription description;

    // Internship may or may not have an interviewDateTime.
    public final Optional<InternshipInterviewDateTime> interviewDateTime;

    /**
     * Constructs a {@code Internship} with an interviewDateTime.
     *
     * @param roleName The name of the internship role.
     * @param desc The description of the internship role.
     * @param interviewDateTime The date and time of the interview.
     */
    public Internship(InternshipName roleName, InternshipDescription desc, InternshipInterviewDateTime interviewDateTime) {
        requireAllNonNull(roleName, desc, interviewDateTime);
        this.roleName = roleName;
        this.description = desc;
        this.interviewDateTime = Optional.of(interviewDateTime);
    }

    /**
     * Constructs a {@code Internship} without an interviewDateTime.
     *
     * @param roleName The name of the internship role.
     * @param desc The description of the internship role.
     */
    public Internship(InternshipName roleName, InternshipDescription desc) {
        requireAllNonNull(roleName, desc);
        this.roleName = roleName;
        this.description = desc;
        this.interviewDateTime = Optional.empty();
    }

    /**
     * Returns the date and time of the interview.
     * Optional<InternshipInterviewDateTime> is returned as the date and time may be empty.
     *
     * @return The date and time of the interview.
     */
    public Optional<InternshipInterviewDateTime> getInternshipDateTime() {
        return this.interviewDateTime;
    }

    /**
     * Returns the role name of the Internship.
     *
     * @return The role name of the Internship.
     */
    public InternshipName getInternshipName() {
        return this.roleName;
    }

    /**
     * Returns the description of the Internship.
     *
     * @return The description of the Internship.
     */
    public InternshipDescription getInternshipDesc() {
        return this.description;
    }

    /**
     * Format the internship as text for viewing.
     */
    @Override
    public String toString() {
        return new ToStringBuilder("")
                .add("name", roleName.toString())
                .add("description", description.toString())
                .add("interview date",
                        interviewDateTime.map(InternshipInterviewDateTime::toString).orElse("N/A"))
                .toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleName, description, interviewDateTime);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Internship)) {
            return false;
        }

        Internship otherInternship = (Internship) other;
        return roleName.equals(otherInternship.roleName)
                && description.equals(otherInternship.description)
                && interviewDateTime.equals(otherInternship.interviewDateTime);
    }
}
