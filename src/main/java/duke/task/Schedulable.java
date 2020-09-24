package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * A class which has this interface is able to be scheduled according to date and/or time.
 */
public interface Schedulable {

    /**
     * Returns true if the Schedulable has a date.
     *
     * @return true if date is not null.
     */
    boolean hasDate();

    /**
     * Returns true if the Schedulable has a time.
     *
     * @return true if time is not null.
     */
    boolean hasTime();

    /**
     * Returns the date of the Schedulable.
     *
     * @return date in LocalDate.
     */
    LocalDate getDate();

    /**
     * Returns the time of the Schedulable.
     *
     * @return time in LocalTime.
     */
    LocalTime getTime();
}
