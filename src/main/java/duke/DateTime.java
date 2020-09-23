package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTime {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
    private static final DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HHmm");
    private static final DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MMM dd yyyy");


    public static LocalDate getDate(String input) {
        LocalDate date = LocalDate.parse(input, formatter);
        return date;
    }

    public static LocalTime getTime(String input) {
        LocalTime time = LocalTime.parse(input, formatter1);
        return time;
    }

    public static String getDateData(LocalDate date) {
        String dateString;
        dateString = date.format(formatter);
        return dateString;
    }

    public static String getTimeData(LocalTime date) {
        return getTimeString(date);
    }

    public static String getDateString(LocalDate date) {
        String dateString;
        dateString = date.format(formatter2);
        return dateString;
    }

    public static String getTimeString(LocalTime date) {
        String dateString;
        dateString = date.format(formatter1);
        return dateString;
    }

}
