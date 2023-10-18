package seedu.address.logic.commands.viewcommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.ContactIsEqualsPredicate;
import seedu.address.model.person.Person;

/**
 * Views the contact with the specified index from the persons contact list.
 */
public class ViewPersonCommand extends ViewCommand {
    public static final String MESSAGE_VIEW_PERSON_SUCCESS = "Person with index %d listed!";
    private final Index targetIndex;
    public ViewPersonCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    public Index getTargetIndex() {
        return targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (this.targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToView = lastShownList.get(targetIndex.getZeroBased());
        model.updateFilteredPersonList(new ContactIsEqualsPredicate(personToView));
        return new CommandResult(String.format(MESSAGE_VIEW_PERSON_SUCCESS, targetIndex.getOneBased()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewPersonCommand)) {
            return false;
        }

        ViewPersonCommand otherViewPersonCommand = (ViewPersonCommand) other;
        return this.targetIndex.equals(otherViewPersonCommand.getTargetIndex());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("type", "Person")
                .add("targetIndex", targetIndex)
                .toString();
    }
}
