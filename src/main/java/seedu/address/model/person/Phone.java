package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a Person's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone {

    public static final String DEFAULT_COUNTRY_CODE = "65"; // to be transferred to a separate command
    public static final String DEFAULT_COUNTRY = "Singapore";
    public static final String MESSAGE_PHONE_CONSTRAINTS =
            "Phone numbers can only contain numbers, and should be at least 4 digits long";
    private static final String PHONE_VALIDATION_REGEX = "\\d{4,}";
    private static final String PHONE_VALIDATION_REGEX_ALT = "^\\+(?:[0-9] ?){6,14}[0-9]$";
    public final String value;
    private String countryCode;


    /**
     * Validates given phone number.
     *
     * @throws IllegalValueException if given phone string is invalid.
     */
    public Phone(String phone) throws IllegalValueException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!isValidPhone(trimmedPhone)) {
            throw new IllegalValueException(MESSAGE_PHONE_CONSTRAINTS);
        }
        this.value = trimmedPhone;
        this.countryCode = trimCode(trimmedPhone);
    }

    /**
     * Extracts country code from a valid phone number, otherwise returns a default code.
     */
    public static String trimCode(String trimmedPhone) {
        if (trimmedPhone.matches(PHONE_VALIDATION_REGEX_ALT)) {
            // take pattern: start w/ '+', end with whitespace
            Pattern pattern = Pattern.compile("^\\+(.*?)\\s");
            Matcher matcher = pattern.matcher(trimmedPhone);
            return matcher.group(1);
        } else {
            return DEFAULT_COUNTRY_CODE;
        }
    }

    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Returns true if a given string is a valid person phone number.
     */
    public static boolean isValidPhone(String test) {
        return test.matches(PHONE_VALIDATION_REGEX) || test.matches(PHONE_VALIDATION_REGEX_ALT);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Phone // instanceof handles nulls
                && this.value.equals(((Phone) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
