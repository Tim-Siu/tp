package seedu.address.logic.commands.addcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertPersonCommandFailure;
import static seedu.address.testutil.TypicalCompanies.getTypicalAddressBook;
import static seedu.address.testutil.TypicalInternships.SOFTWARE_ENGINEER_WITH_DATETIME;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.company.Company;
import seedu.address.testutil.CompanyBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCompanyCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newCompany_success() {
        Company validCompany = new CompanyBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addCompany(validCompany);

        assertCommandSuccess(new AddCompanyCommand(validCompany), model,
                String.format(AddCompanyCommand.MESSAGE_SUCCESS, Messages.formatCompany(validCompany)),
                expectedModel);
    }

    @Test
    public void execute_duplicateCompany_throwsCommandException() {
        Company companyInList = model.getAddressBook().getCompanyList().get(0);
        assertPersonCommandFailure(new AddCompanyCommand(companyInList), model,
                AddCompanyCommand.MESSAGE_DUPLICATE_COMPANY);
    }

    @Test
    public void execute_addCompanyWithNullModel_throwsNullPointerException() {
        Company validCompany = new CompanyBuilder().build();
        assertThrows(NullPointerException.class, () -> new AddCompanyCommand(validCompany).execute(null));
    }

    @Test
    public void execute_addCompanyWithNullCompany_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCompanyCommand(null).execute(model));
    }

    @Test
    public void execute_addDuplicateCompany_throwsCommandException() {
        Company companyInList = model.getFilteredCompanyList().get(0);
        assertPersonCommandFailure(new AddCompanyCommand(companyInList), model,
                AddCompanyCommand.MESSAGE_DUPLICATE_COMPANY);
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        Company validCompany = new CompanyBuilder().build();
        AddCompanyCommand addCompanyCommand = new AddCompanyCommand(validCompany);
        assertTrue(addCompanyCommand.equals(addCompanyCommand));
    }

    @Test
    public void equals_nullObject_returnsFalse() {
        Company validCompany = new CompanyBuilder().build();
        AddCompanyCommand addCompanyCommand = new AddCompanyCommand(validCompany);
        assertFalse(addCompanyCommand.equals(null));
    }

    @Test
    public void equals_differentCompany_returnsFalse() {
        Company company1 = new CompanyBuilder().withCompanyName("Company1").build();
        Company company2 = new CompanyBuilder().withCompanyName("Company2").build();
        AddCompanyCommand addCompanyCommand1 = new AddCompanyCommand(company1);
        AddCompanyCommand addCompanyCommand2 = new AddCompanyCommand(company2);
        assertFalse(addCompanyCommand1.equals(addCompanyCommand2));
    }

    @Test
    public void equals_differentCompanyWithInternships_returnsFalse() {
        Company company1 = new CompanyBuilder().withCompanyName("Company1")
                .withInternships(SOFTWARE_ENGINEER_WITH_DATETIME).build();
        Company company2 = new CompanyBuilder().withCompanyName("Company2").withInternships()
                .withInternships(SOFTWARE_ENGINEER_WITH_DATETIME).build();
        AddCompanyCommand addCompanyCommand1 = new AddCompanyCommand(company1);
        AddCompanyCommand addCompanyCommand2 = new AddCompanyCommand(company2);
        assertFalse(addCompanyCommand1.equals(addCompanyCommand2));
    }

    @Test
    public void equals_sameCompany_returnsTrue() {
        Company validCompany = new CompanyBuilder().build();
        AddCompanyCommand addCompanyCommand1 = new AddCompanyCommand(validCompany);
        AddCompanyCommand addCompanyCommand2 = new AddCompanyCommand(validCompany);
        assertTrue(addCompanyCommand1.equals(addCompanyCommand2));
    }


}
