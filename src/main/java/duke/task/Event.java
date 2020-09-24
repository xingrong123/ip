package duke.task;

import duke.DateTime;
import duke.exception.DukeException;
import duke.exception.DukeExceptionType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * A type of task represents an event.
 */
public class Event extends Task implements Schedulable {
    private static final String AT_KW = " /at ";
    public static final String EVENT_KW = "event";
    public static final char CHAR_IDENTIFIER  = 'E';

    private String at;
    private LocalDate atDate = null;
    private LocalTime atTime = null;

    /**
     * Constructs a new Event instance by storing the given description and at.
     *
     * @param description The description of the event.
     * @param at The date and/or time of the event.
     */
    public Event(String description, String at) {
        super(description.trim(), TaskType.EVENT);

        try {
            if (at.contains(" ")) {
                List<String> dateTimeString = Arrays.asList(at.split(" ", 2));
                atDate = DateTime.getDate(dateTimeString.get(0));
                atTime = DateTime.getTime(dateTimeString.get(1));
            } else {
                atDate = DateTime.getDate(at);
            }
        } catch (DateTimeParseException e) {
            this.at = at;
        }
    }


    /**
     * Returns the description and at of the detected event command of the user input.
     *
     * @param input The user input.
     * @throws DukeException if the description is empty or the input format is invalid.
     */
    public static List<String> getDescAndAt(String input) throws DukeException {
        String descriptionAndAt = input.substring(EVENT_KW.length());
        List<String> strings =  Arrays.asList(descriptionAndAt.split(AT_KW));
        ensureValidEventInput(descriptionAndAt, strings);
        return strings;
    }

    private static void ensureValidEventInput(String descriptionAndAt, List<String> strings) throws DukeException {
        if (strings.get(0).isBlank()) {
            throw new DukeException(DukeExceptionType.EMPTY_DESCRIPTION, TaskType.EVENT);
        } else if (strings.size() != 2 || strings.get(1).isBlank() || !descriptionAndAt.startsWith(" ")) {
            throw new DukeException(DukeExceptionType.INVALID_TASK_FORMAT, TaskType.EVENT);
        }
    }

    /**
     * Creates and returns an event from the data read from the file.
     *
     * @param data The user input.
     * @return The event created.
     * @throws DukeException if the task data is invalid.
     */
    public static Task initEvent(String data) throws DukeException {
        List<String> details =  Arrays.asList(data.split("\\|"));
        String description = details.get(2).trim();
        String at = details.get(3).trim();
        Task event = new Event(description, at);
        String done = details.get(1).trim();
        initCheckDone(event, done);
        return event;
    }

    private String getAt() {
        String value;
        if (atDate != null && atTime != null) {
            value = DateTime.getDateString(atDate) + " " + DateTime.getTimeString(atTime);
        } else if (atDate != null && atTime == null) {
            value = DateTime.getDateString(atDate);
        } else {
            value = at;
        }
        return value;
    }

    private String getAtData() {
        String value;
        if (atDate != null && atTime != null) {
            value = DateTime.getDateData(atDate) + " " + DateTime.getTimeData(atTime);
        } else if (atDate != null && atTime == null) {
            value = DateTime.getDateData(atDate);
        } else {
            value = at;
        }
        return value;
    }

    @Override
    public boolean hasDate() {
        return atDate != null;
    }

    @Override
    public boolean hasTime() {
        return atTime != null;
    }

    @Override
    public LocalDate getDate() {
        return atDate;
    }

    @Override
    public LocalTime getTime() {
        return atTime;
    }

    /**
     * Returns a string of the details of the event to be printed for the Ui.
     *
     * @return The details of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getAt() + ")";
    }

    /**
     * Returns the details of the event to be saved in the file.
     *
     * @return The details of the event.
     */
    @Override
    public String getData() {
        return "E" + SEPARATOR + super.getData() + SEPARATOR + getAtData();
    }
}
