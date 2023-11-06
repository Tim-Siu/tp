package seedu.address.logic.commands.deletecommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.commandresults.CommandResult;
import seedu.address.logic.commands.commandresults.DisplayableCommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.company.Company;
import seedu.address.model.company.internship.Internship;


/**
 * Deletes internships identified using its name and the company it belongs to from the address book.
 */
public class DeleteInternshipCommand extends DeleteCommand {
    public static final String MESSAGE_SUCCESS = "Internship of index %1$s in %2$s successfully deleted.";
    private final Index targetCompanyIndex;
    private final Index targetInternshipIndex;
    /**
     * Creates an DeleteCommand to delete the specified {@code Internship} of a {@code Company}
     * @param targetCompanyIndex The index of the company as shown in the last shown list.
     * @param targetInternshipIndex The index of the internship to be deleted from the company.
     */
    public DeleteInternshipCommand(Index targetCompanyIndex, Index targetInternshipIndex) {
        requireAllNonNull(targetCompanyIndex, targetInternshipIndex);
        this.targetCompanyIndex = targetCompanyIndex;
        this.targetInternshipIndex = targetInternshipIndex;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Company> lastShownList = model.getFilteredCompanyList();
        if (targetCompanyIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
        }
        Company companyToDelete = lastShownList.get(targetCompanyIndex.getZeroBased());
        List<Internship> currInternships = companyToDelete.getInternshipList();
        if (targetInternshipIndex.getZeroBased() >= currInternships.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
        }
        List<Internship> updatedInternships = new ArrayList<>(currInternships);
        updatedInternships.remove(targetInternshipIndex.getZeroBased());
        Company updatedCompany = new Company(companyToDelete.getCompanyName(),
                companyToDelete.getCompanyPhone(), companyToDelete.getCompanyEmail(),
                companyToDelete.getCompanyDescription(), companyToDelete.getTags(),
                updatedInternships);

        model.setCompany(companyToDelete, updatedCompany);
        model.updateFilteredCompanyList(Model.PREDICATE_SHOW_ALL_COMPANIES);
        return new DisplayableCommandResult(
                String.format(MESSAGE_SUCCESS,
                        this.targetInternshipIndex.getOneBased(),
                        companyToDelete.getCompanyName()),
                companyToDelete);
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteInternshipCommand)) {
            return false;
        }

        DeleteInternshipCommand otherDeleteInternshipCommand = (DeleteInternshipCommand) other;
        return targetCompanyIndex.equals(otherDeleteInternshipCommand.targetCompanyIndex)
                && targetInternshipIndex.equals(otherDeleteInternshipCommand.targetInternshipIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetCompanyIndex", targetCompanyIndex)
                .add("targetInternshipIndex", targetInternshipIndex)
                .toString();
    }

}
