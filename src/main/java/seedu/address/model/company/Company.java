package seedu.address.model.company;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Company in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Company {

    // Identity fields
    private final CompanyName companyName;
    private final CompanyPhone phone;
    private final CompanyEmail email;

    // Data fields
    private final Description description;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<Internship> internships = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Company(CompanyName companyName, CompanyPhone phone,
                   CompanyEmail email, Description description, Set<Tag> tags, Set<Internship> internships) {
        requireAllNonNull(companyName, phone, email, description, tags, internships);
        this.companyName = companyName;
        this.phone = phone;
        this.email = email;
        this.description = description;
        this.tags.addAll(tags);
        this.internships.addAll(internships);
    }

    public CompanyName getCompanyName() {
        return companyName;
    }

    public CompanyPhone getCompanyPhone() {
        return phone;
    }

    public CompanyEmail getCompanyEmail() {
        return email;
    }

    public Description getDescription() {
        return description;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable internship set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Internship> getInternships() {
        return Collections.unmodifiableSet(internships);
    }

    /**
     * Returns true if both companys have the same name.
     * This defines a weaker notion of equality between two companys.
     */
    public boolean isSameCompany(Company otherCompany) {
        if (otherCompany == this) {
            return true;
        }

        return otherCompany != null
            && otherCompany.getCompanyName().equals(getCompanyName());
    }

    /**
     * Returns true if both companys have the same identity and data fields.
     * This defines a stronger notion of equality between two companys.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Company)) {
            return false;
        }

        Company otherCompany = (Company) other;
        return companyName.equals(otherCompany.companyName)
            && phone.equals(otherCompany.phone)
            && email.equals(otherCompany.email)
            && description.equals(otherCompany.description)
            && tags.equals(otherCompany.tags)
            && internships.equals(otherCompany.internships);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(companyName, phone, email, description, tags, internships);
    }

    /**
     * Prints a list of internships for viewing.
     *
     * @return String of List of internships
     */
    public String toStringInternships() {
        StringBuilder result = new StringBuilder("[");
        for (Internship i : this.internships) {
            result.append(i.toString()).append(", ");
        }

        if (result.length() >= 2) {
            result.delete(result.length() - 2, result.length());
        }
        result.append("]");
        return result.toString();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("\nname", companyName)
            .add("\nphone", phone)
            .add("\nemail", email)
            .add("\ndescription", description)
            .add("\ntags", tags)
            .add("\ninternships", toStringInternships())
            .toString();
    }
}
