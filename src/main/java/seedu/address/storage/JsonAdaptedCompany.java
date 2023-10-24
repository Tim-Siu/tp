package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.company.Company;
import seedu.address.model.company.CompanyEmail;
import seedu.address.model.company.CompanyName;
import seedu.address.model.company.CompanyPhone;
import seedu.address.model.company.Description;
import seedu.address.model.company.internship.Internship;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Company}.
 */
class JsonAdaptedCompany {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Company's %s field is missing!";

    private final String companyName;
    private final String phone;
    private final String email;
    private final String description;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final List<JsonAdaptedInternship> internships = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedCompany} with the given company details.
     */
    @JsonCreator
    public JsonAdaptedCompany(@JsonProperty("companyName") String name, @JsonProperty("phone") String phone,
                              @JsonProperty("email") String email, @JsonProperty("description") String description,
                              @JsonProperty("tags") List<JsonAdaptedTag> tags,
                              @JsonProperty("internships") List<JsonAdaptedInternship> internships) {
        this.companyName = name;
        this.phone = phone;
        this.email = email;
        this.description = description;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        if (internships != null) {
            this.internships.addAll(internships);
        }
    }

    /**
     * Converts a given {@code Company} into this class for Jackson use.
     */
    public JsonAdaptedCompany(Company source) {
        companyName = source.getCompanyName().fullName;
        phone = source.getCompanyPhone().value;
        email = source.getCompanyEmail().value;
        description = source.getDescription().value;
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        internships.addAll(source.getInternships().stream()
                .map(JsonAdaptedInternship::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted company object into the model's {@code Company} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted company.
     */
    public Company toModelType() throws IllegalValueException {
        final List<Tag> companyTags = new ArrayList<>();
        final List<Internship> companyInternships = new ArrayList<>();

        for (JsonAdaptedTag tag : tags) {
            companyTags.add(tag.toModelType());
        }

        for (JsonAdaptedInternship internship : internships) {
            companyInternships.add(internship.toModelType());
        }

        if (companyName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CompanyName.class.getSimpleName()));
        }
        if (!CompanyName.isValidName(companyName)) {
            throw new IllegalValueException(CompanyName.MESSAGE_CONSTRAINTS);
        }
        final CompanyName modelName = new CompanyName(companyName);

        if (phone == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, CompanyPhone.class.getSimpleName()));
        }
        if (!CompanyPhone.isValidPhone(phone)) {
            throw new IllegalValueException(CompanyPhone.MESSAGE_CONSTRAINTS);
        }
        final CompanyPhone modelPhone = new CompanyPhone(phone);

        if (email == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, CompanyEmail.class.getSimpleName()));
        }
        if (!CompanyEmail.isValidEmail(email)) {
            throw new IllegalValueException(CompanyEmail.MESSAGE_CONSTRAINTS);
        }
        final CompanyEmail modelEmail = new CompanyEmail(email);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDesc = new Description(description);

        final Set<Tag> modelTags = new HashSet<>(companyTags);
        final Set<Internship> modelInternships = new HashSet<>(companyInternships);
        return new Company(modelName, modelPhone, modelEmail, modelDesc, modelTags, modelInternships);
    }

}
