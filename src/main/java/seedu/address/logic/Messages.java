package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.company.Company;
import seedu.address.model.company.Internship;
import seedu.address.model.person.Person;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX = "The company index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_COMPANIES_LISTED_OVERVIEW = "%1$d companies listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code person} for display to the user.
     */
    public static String formatPerson(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append(person.getName())
                .append(";\n Phone: ")
                .append(person.getPhone())
                .append(";\n Email: ")
                .append(person.getEmail())
                .append(";\n Address: ")
                .append(person.getAddress())
                .append(";\n Tags: ");
        person.getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the {@code company} for display to the user.
     */
    public static String formatCompany(Company company) {
        final StringBuilder builder = new StringBuilder();
        builder.append(company.getCompanyName())
                .append(";\n Phone: ")
                .append(company.getCompanyPhone())
                .append(";\n Email: ")
                .append(company.getCompanyEmail())
                .append(";\n Descriptions: ")
                .append(company.getDescription())
                .append(";\n Tags: ");
        company.getTags().forEach(builder::append);
        builder.append(";\n Internships: ");

        for (Internship i : company.getInternships()) {
            builder.append(formatInternship(i));
        }
        return builder.toString();
    }

    /**
     * Formats the {@code internship} for display to the user.
     */
    public static String formatInternship(Internship internship) {
        return "\n " + internship.getInternshipName() +
                "; Description: " +
                internship.getInternshipDesc() + ";";
    }
}

