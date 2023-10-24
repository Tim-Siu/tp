package seedu.address.testutil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.editcommand.EditCompanyCommand;
import seedu.address.logic.commands.editcommand.EditCompanyCommand.EditCompanyDescriptor;
import seedu.address.model.company.Company;
import seedu.address.model.company.CompanyEmail;
import seedu.address.model.company.CompanyName;
import seedu.address.model.company.CompanyPhone;
import seedu.address.model.company.Description;
import seedu.address.model.company.internship.Internship;
import seedu.address.model.tag.Tag;



/**
 * A utility class to help with building EditCompanyDescriptor objects.
 */
public class EditCompanyDescriptorBuilder {
    private EditCompanyDescriptor descriptor;

    public EditCompanyDescriptorBuilder() {
        descriptor = new EditCompanyDescriptor();
    }

    public EditCompanyDescriptorBuilder(EditCompanyDescriptor descriptor) {
        this.descriptor = new EditCompanyCommand.EditCompanyDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditCompanyDescriptor} with fields containing {@code company}'s details
     */
    public EditCompanyDescriptorBuilder(Company company) {
        descriptor = new EditCompanyDescriptor();
        descriptor.setCompanyName(company.getCompanyName());
        descriptor.setCompanyPhone(company.getCompanyPhone());
        descriptor.setCompanyEmail(company.getCompanyEmail());
        descriptor.setDescription(company.getDescription());
        descriptor.setTags(company.getTags());
        descriptor.setInternships(company.getInternships());
    }

    /**
     * Sets the {@code CompanyName} of the {@code EditCompanyDescriptor} that we are building.
     */
    public EditCompanyDescriptorBuilder withCompanyName(String companyName) {
        descriptor.setCompanyName(new CompanyName(companyName));
        return this;
    }

    /**
     * Sets the {@code CompanyPhone} of the {@code EditCompanyDescriptor} that we are building.
     */
    public EditCompanyDescriptorBuilder withCompanyPhone(String companyPhone) {
        descriptor.setCompanyPhone(new CompanyPhone(companyPhone));
        return this;
    }

    /**
     * Sets the {@code CompanyEmail} of the {@code EditCompanynDescriptor} that we are building.
     */
    public EditCompanyDescriptorBuilder withCompanyEmail(String companyEmail) {
        descriptor.setCompanyEmail(new CompanyEmail(companyEmail));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditCompanyDescriptor} that we are building.
     */
    public EditCompanyDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditCompanyDescriptor}
     * that we are building.
     */
    public EditCompanyDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Parses the {@code internships} into a {@code Set<Internship>} and set it to the {@code EditCompanyDescriptor}
     * that we are building.
     */
    public EditCompanyDescriptorBuilder withInternships(Internship... internships) {
        this.descriptor.setInternships(new HashSet<>(Arrays.asList(internships)));
        return this;
    }

    public EditCompanyDescriptor build() {
        return descriptor;
    }
}
