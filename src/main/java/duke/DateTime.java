package duke;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * This class is used to process strings with certain formats as date and time.
 */
public class DateTime {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
    private static final DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HHmm");
    private static final DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Returns an instance of a LocalDate object by parsing a string of date in a specified format.
     * E.g. 20/12/2020, 1/3/1994
     *
     * @param input String of date.
     * @return date in LocalDate.
     * @throws DateTimeException If invalid date format is used.
     */
    public static LocalDate getDate(String input) {
        LocalDate date = LocalDate.parse(input, formatter);
        return date;
    }

    /**
     * Returns an instance of a LocalTime object by parsing a string of time in a specified format.
     * E.g. 1800, 0345
     *
     * @param input String of time.
     * @return time in LocalTime.
     * @throws DateTimeException If invalid time format is used.
     */
    public static LocalTime getTime(String input) {
        LocalTime time = LocalTime.parse(input, formatter1);
        return time;
    }

    /**
     * Returns a string of the date in a certain format.
     * This method is used when saving data in a file.
     *
     * @param date The date.
     * @return date in string.
     */
    public static String getDateData(LocalDate date) {
        String dateString;
        dateString = date.format(formatter);
        return dateString;
    }

    /**
     * Returns a string of the time in a certain format.
     * This method is used when saving time in a file.
     *
     * @param time The time.
     * @return time in string.
     */
    public static String getTimeData(LocalTime time) {
        return getTimeString(time);
    }

    /**
     * Returns a string of the date in a certain format.
     * This method is used when printing date on the command line.
     *
     * @param date The date.
     * @return date in string.
     */
    public static String getDateString(LocalDate date) {
        String dateString;
        dateString = date.format(formatter2);
        return dateString;
    }

    /**
     * Returns a string of the time in a certain format.
     * This method is used when printing date on the command line.
     *
     * @param time The time.
     * @return time in string.
     */
    public static String getTimeString(LocalTime time) {
        String dateString;
        dateString = time.format(formatter1);
        return dateString;
    }

}
