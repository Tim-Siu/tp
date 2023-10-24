package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.DateTimeParserUtil.DATE_FORMAT;
import static seedu.address.commons.util.DateTimeParserUtil.TIME_FORMAT;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DateTimeParserUtilTest {
    @Test
    public void parseStringToDateTime_validDateTimeString_success() {
        String dateTimeString = "01-01-2023 12:00";
        LocalDateTime expectedDateTime = LocalDateTime.parse(dateTimeString,
                DateTimeFormatter.ofPattern(DATE_FORMAT + " " + TIME_FORMAT));

        LocalDateTime result = DateTimeParserUtil.parseStringToDateTime(dateTimeString);

        assertEquals(expectedDateTime, result);
    }

    @Test
    public void parseStringToDateTime_emptyString_returnsNull() {
        String dateTimeString = "";
        assertNull(DateTimeParserUtil.parseStringToDateTime(dateTimeString));
    }

    @Test
    public void parseStringToDateTime_invalidDateTimeString_returnsNull() {
        String dateTimeString = "2023-01-01 12:00"; // Invalid format
        assertNull(DateTimeParserUtil.parseStringToDateTime(dateTimeString));
    }

    @Test
    public void parseDateTimeToString_validLocalDateTime_success() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 1, 1, 12, 0);
        String expectedString = "01-01-2023 12:00";

        String result = DateTimeParserUtil.parseDateTimeToString(dateTime);

        assertEquals(expectedString, result);
    }

    @Test
    public void parseDateTimeToString_nullLocalDateTime_returnsEmptyString() {
        LocalDateTime dateTime = null;
        assertEquals("", DateTimeParserUtil.parseDateTimeToString(dateTime));
    }

    @Test
    public void isValidDateTimeString_validDateTimeString_true() {
        String dateTimeString = "01-01-2023 12:00";
        assertTrue(DateTimeParserUtil.isValidDateTimeString(dateTimeString));
    }

    @Test
    public void isValidDateTimeString_invalidDateTimeString_false() {
        String dateTimeString = "2023-01-01 12:00"; // Invalid format
        assertFalse(DateTimeParserUtil.isValidDateTimeString(dateTimeString));
    }

    @Test
    public void isValidLocalDateTime_validLocalDateTime_true() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 1, 1, 12, 0);
        assertTrue(DateTimeParserUtil.isValidLocalDateTime(dateTime));
    }
}
