package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collections;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import seedu.address.logic.commands.addcommands.AddCommand;
import seedu.address.logic.commands.addcommands.AddCompanyCommand;
import seedu.address.logic.commands.addcommands.AddPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.company.Company;
import seedu.address.model.company.CompanyEmail;
import seedu.address.model.company.CompanyName;
import seedu.address.model.company.CompanyPhone;
import seedu.address.model.company.Description;
import seedu.address.model.company.Internship;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * The argument for adding companies.
     */
    public static final String ADD_COMPANIES_ARG_WORD = "c";

    /**
     * The argument for adding persons.
     */
    public static final String ADD_PERSONS_ARG_WORD = "p";

    /**
     * Regex used to confirm if the arguments are either c or p for add command.
     */
    private static final Pattern ARGUMENT_REGEX_PATTERN =
            Pattern.compile("^(" + ADD_COMPANIES_ARG_WORD + "|" + ADD_PERSONS_ARG_WORD + ")$");


    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.substring(0, 3).trim();

        // Used to check if argument is either c or p.
        Matcher matcher = ARGUMENT_REGEX_PATTERN.matcher(trimmedArgs);

        // Throw an error, if argument is invalid (i.e. not p or c) or empty.
        if (trimmedArgs.isEmpty() || !matcher.matches()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        if (trimmedArgs.equals(ADD_PERSONS_ARG_WORD)) {
            ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args.substring(2),
                        PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);
            if (!arePrefixesPresent(argMultimap,
                    PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS)
                || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCompanyCommand.MESSAGE_USAGE));
            }

            argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);
            Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
            Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
            Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
            Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

            Person person = new Person(name, phone, email, address, tagList);
            return new AddPersonCommand(person);
        } else {
            ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args.substring(2),
                        PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_DESCRIPTION, PREFIX_TAG);

            if (!arePrefixesPresent(argMultimap,
                    PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_DESCRIPTION)
                || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCompanyCommand.MESSAGE_USAGE));
            }

            argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_DESCRIPTION);
            CompanyName name = ParserUtil.parseCompanyName(argMultimap.getValue(PREFIX_NAME).get());
            CompanyPhone phone = ParserUtil.parseCompanyPhone(argMultimap.getValue(PREFIX_PHONE).get());
            CompanyEmail email = ParserUtil.parseCompanyEmail(argMultimap.getValue(PREFIX_EMAIL).get());
            Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
            Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
            Set<Internship> emptyInternshipList = Collections.emptySet();

            Company company = new Company(name, phone, email, description, tagList, emptyInternshipList);
            return new AddCompanyCommand(company);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
