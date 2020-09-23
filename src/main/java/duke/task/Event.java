package duke.task;

import duke.exception.DukeException;
import duke.exception.DukeExceptionType;

/**
 * A type of task represents an event.
 */
public class Event extends Task{
    private static final String AT_KW = " /at ";
    public static final String EVENT_KW = "event";

    private final String at;

    /**
     * Constructs a new Event instance by storing the given description and at.
     *
     * @param description The description of the event.
     * @param at The date and/or time of the event.
     */
    public Event(String description, String at) {
        super(description.trim(), TaskType.EVENT);
        this.at = at.trim();
    }

    /**
     * Returns the description and at of the detected event command of the user input.
     *
     * @param input The user input.
     * @throws DukeException if the description is empty or the input format is invalid.
     */
    public static String[] getDescAndAt(String input) throws DukeException {
        String descriptionAndAt = input.substring(EVENT_KW.length());
        String[] string = descriptionAndAt.split(AT_KW);
        ensureValidEventInput(descriptionAndAt, string);
        return string;
    }

    private static void ensureValidEventInput(String descriptionAndAt, String[] string) throws DukeException {
        if (string[0].isBlank()) {
            throw new DukeException(DukeExceptionType.EMPTY_DESCRIPTION, TaskType.EVENT);
        } else if (string.length != 2 || string[1].isBlank() || !descriptionAndAt.startsWith(" ")) {
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
        String[] details = data.split("\\|");
        String description = details[2].trim();
        String at = details[3].trim();
        Task event = new Event(description, at);
        if (details[1].trim().compareTo("1") == 0) {
            event.markDone();
        } else if (details[1].trim().compareTo( "0") != 0) {
            throw new DukeException(DukeExceptionType.INVALID_TASK_DATA);
        }
        return event;
    }

    /**
     * Returns a string of the details of the event to be printed for the Ui.
     *
     * @return The details of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Returns the details of the event to be saved in the file.
     *
     * @return The details of the event.
     */
    @Override
    public String getData() {
        return "E" + SEPARATOR + super.getData() + SEPARATOR + at;
    }
}
