package seedu.address.logic.viewcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.viewcommands.ViewPersonCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ContactIsEqualsPredicate;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code ViewPersonCommand}.
 */
public class ViewPersonCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_viewPersonAtIndex1_success() {
        List<Person> listToCheck = expectedModel.getFilteredPersonList();
        Person personToView = listToCheck.get(0);
        expectedModel.updateFilteredPersonList(new ContactIsEqualsPredicate(personToView));
        assertCommandSuccess(new ViewPersonCommand(Index.fromOneBased(1)), model,
                String.format(ViewPersonCommand.MESSAGE_VIEW_PERSON_SUCCESS, 1), expectedModel);
    }

    @Test
    public void execute_viewPersonAtIndexExceedsListSize_exceptionThrown() {
        assertCommandFailure(new ViewPersonCommand(Index.fromOneBased(99)), model,
                Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ViewPersonCommand viewPersonFirstCommand = new ViewPersonCommand(INDEX_FIRST_PERSON);
        ViewPersonCommand viewPersonSecondCommand = new ViewPersonCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(viewPersonFirstCommand.equals(viewPersonFirstCommand));

        // same values -> returns true
        ViewPersonCommand viewPersonFirstCommandCopy = new ViewPersonCommand(INDEX_FIRST_PERSON);
        assertTrue(viewPersonFirstCommand.equals(viewPersonFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewPersonFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewPersonFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(viewPersonFirstCommand.equals(viewPersonSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        ViewPersonCommand viewPersonCommand = new ViewPersonCommand(targetIndex);
        String expected = ViewPersonCommand.class.getCanonicalName()
                + "{type=Person, "
                + "targetIndex=" + targetIndex + "}";
        assertEquals(expected, viewPersonCommand.toString());
    }
}
