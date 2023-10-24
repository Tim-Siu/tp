package seedu.address.commons.util;

import static java.util.Objects.isNull;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Parses String objects into LocalDateTime, and vice versa.
 */
public class DateTimeParserUtil {
    // Do NOT change DATE_FORMAT and TIME_FORMAT for now, this WILL mess up the parsing.
    public static final String DATE_FORMAT = "dd-MM-yyyy";
    public static final String TIME_FORMAT = "HH:mm";

    public static final String DATE_TIME_CONSTRAINTS =
            "Date and time must follow the format of " + DATE_FORMAT + " " + TIME_FORMAT;

    private static final Logger logger = Logger.getLogger(DateTimeParserUtil.class.getName());

    /**
     * Parses a given string of the format stated in DATE_FORMAT and TIME_FORMAT into the LocalDateTime format.
     * If the string provided is empty or invalid, this method returns null.
     *
     * @param dateTimeString The String object to be converted.
     * @return The dateTime as a LocalDateTime object.
     */
    public static LocalDateTime parseStringToDateTime(String dateTimeString) throws DateTimeParseException {
        String toParse = dateTimeString.strip();

        if (Objects.equals(toParse, "")) {
            logger.info("The String provided for parsing is empty");
            return null;
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT + " " + TIME_FORMAT);
            return LocalDateTime.parse(toParse, formatter);
        } catch (DateTimeParseException e) {
            logger.info("ERROR PARSING String TO LocalDateTime : " + e.getMessage());
            return null;
        }

    }

    /**
     * Converts the interview date and time to a formatted string and returns it.
     * If the LocalDateTime object provided is empty or invalid, this method returns an empty String.
     *
     * @param dateTimeLdt The LocalDateTime object to be converted.
     * @return The dateTime as a String object.
     */
    public static String parseDateTimeToString(LocalDateTime dateTimeLdt) {
        if (isNull(dateTimeLdt)) {
            logger.info("The LocalDateTime object provided for parsing is empty");
            return "";
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT + " " + TIME_FORMAT);
            return dateTimeLdt.format(formatter);
        } catch (DateTimeException e) {
            logger.info("ERROR PARSING LocalDateTime TO String : " + e.getMessage());
            return "";
        }

    }

    /**
     * Checks if a given dateTime String is in the correct format to be parsed, based on DATE_FORMAT and TIME_FORMAT.
     *
     * @param input The String to be checked.
     * @return true if the string is in the correct format, and false if not.
     */
    public static boolean isValidDateTimeString(String input) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT + " " + TIME_FORMAT);
            LocalDateTime.parse(input, formatter);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

    /**
     * Checks if a given dateTime LocalDateTime object is in the correct format to be parsed,
     * based on DATE_FORMAT and TIME_FORMAT.
     *
     * @param input The LocalDateTime object to be checked.
     * @return true if the LocalDateTime object is in the correct format, and false if not.
     */
    public static boolean isValidLocalDateTime(LocalDateTime input) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT + " " + TIME_FORMAT);
            input.format(formatter);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }
}
