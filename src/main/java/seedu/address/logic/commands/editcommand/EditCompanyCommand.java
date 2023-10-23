package seedu.address.logic.commands.editcommand;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_COMPANIES;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.company.Company;
import seedu.address.model.company.CompanyEmail;
import seedu.address.model.company.CompanyName;
import seedu.address.model.company.CompanyPhone;
import seedu.address.model.company.Description;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing company in the address book.
 */
public class EditCompanyCommand extends EditCommand {

    public static final String MESSAGE_SUCCESS = "Edited Company: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_COMPANY = "This company already exists in the address book.";

    private final Index index;
    private final EditCompanyDescriptor editCompanyDescriptor;

    /**
     * @param index of the company in the filtered company list to edit
     * @param editCompanyDescriptor details to edit the company with
     */
    public EditCompanyCommand(Index index, EditCompanyDescriptor editCompanyDescriptor) {
        requireNonNull(index);
        requireNonNull(editCompanyDescriptor);

        this.index = index;
        this.editCompanyDescriptor = new EditCompanyDescriptor(editCompanyDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Company> lastShownList = model.getFilteredCompanyList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
        }

        Company companyToEdit = lastShownList.get(index.getZeroBased());
        Company editedCompany = createEditedCompany(companyToEdit, editCompanyDescriptor);

        if (!companyToEdit.isSameCompany(editedCompany) && model.hasCompany(editedCompany)) {
            throw new CommandException(MESSAGE_DUPLICATE_COMPANY);
        }

        model.setCompany(companyToEdit, editedCompany);
        model.updateFilteredCompanyList(PREDICATE_SHOW_ALL_COMPANIES);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.formatCompany(editedCompany)));
    }

    /**
     * Creates and returns a {@code Company} with the details of {@code companyToEdit}
     * edited with {@code editCompanyDescriptor}.
     */
    private static Company createEditedCompany(Company companyToEdit, EditCompanyDescriptor editCompanyDescriptor) {
        assert companyToEdit != null;

        CompanyName updatedCompanyName =
                editCompanyDescriptor.getCompanyName().orElse(companyToEdit.getCompanyName());
        CompanyPhone updatedCompanyPhone =
                editCompanyDescriptor.getCompanyPhone().orElse(companyToEdit.getCompanyPhone());
        CompanyEmail updatedCompanyEmail =
                editCompanyDescriptor.getCompanyEmail().orElse(companyToEdit.getCompanyEmail());
        Description updatedDescription =
                editCompanyDescriptor.getDescription().orElse(companyToEdit.getDescription());
        Set<Tag> updatedTags =
                editCompanyDescriptor.getTags().orElse(companyToEdit.getTags());

        return new Company(updatedCompanyName, updatedCompanyPhone,
                updatedCompanyEmail, updatedDescription, updatedTags, companyToEdit.getInternships());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCompanyCommand)) {
            return false;
        }

        EditCompanyCommand otherEditCompanyCommand = (EditCompanyCommand) other;
        return index.equals(otherEditCompanyCommand.index)
                && editCompanyDescriptor.equals(otherEditCompanyCommand.editCompanyDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editCompanyDescriptor", editCompanyDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the company with. Each non-empty field value will replace the
     * corresponding field value of the company.
     */
    public static class EditCompanyDescriptor {
        private CompanyName companyName;
        private CompanyPhone companyPhone;
        private CompanyEmail companyEmail;
        private Description description;
        private Set<Tag> tags;

        public EditCompanyDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditCompanyDescriptor(EditCompanyDescriptor toCopy) {
            setCompanyName(toCopy.companyName);
            setCompanyPhone(toCopy.companyPhone);
            setCompanyEmail(toCopy.companyEmail);
            setDescription(toCopy.description);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(
                    companyName, companyPhone, companyEmail, description, tags);
        }

        public void setCompanyName(CompanyName companyName) {
            this.companyName = companyName;
        }

        public Optional<CompanyName> getCompanyName() {
            return Optional.ofNullable(companyName);
        }

        public void setCompanyPhone(CompanyPhone companyPhone) {
            this.companyPhone = companyPhone;
        }

        public Optional<CompanyPhone> getCompanyPhone() {
            return Optional.ofNullable(companyPhone);
        }

        public void setCompanyEmail(CompanyEmail companyEmail) {
            this.companyEmail = companyEmail;
        }

        public Optional<CompanyEmail> getCompanyEmail() {
            return Optional.ofNullable(companyEmail);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditCompanyDescriptor)) {
                return false;
            }

            EditCompanyDescriptor otherEditCompanyDescriptor = (EditCompanyDescriptor) other;
            return Objects.equals(companyName, otherEditCompanyDescriptor.companyName)
                    && Objects.equals(companyPhone, otherEditCompanyDescriptor.companyPhone)
                    && Objects.equals(companyEmail, otherEditCompanyDescriptor.companyEmail)
                    && Objects.equals(description, otherEditCompanyDescriptor.description)
                    && Objects.equals(tags, otherEditCompanyDescriptor.tags);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", companyName)
                    .add("phone", companyPhone)
                    .add("email", companyEmail)
                    .add("description", description)
                    .add("tags", tags)
                    .toString();
        }
    }
}

