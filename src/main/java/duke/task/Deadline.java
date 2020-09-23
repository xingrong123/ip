package duke.task;

import duke.exception.DukeException;
import duke.exception.DukeExceptionType;

/**
 * A type of task that contains a deadline.
 */
public class Deadline extends Task {
    private static final String BY_KW = " /by ";
    public static final String DEADLINE_KW = "deadline";

    private final String by;

    /**
     * Constructs a new Deadline instance by storing the given description and by.
     *
     * @param description The description of the event.
     * @param deadline The date and/or time of the deadline.
     */
    public Deadline(String description, String deadline) {
        super(description.trim(), TaskType.DEADLINE);
        this.by = deadline.trim();
    }

    /**
     * Returns the description and by of the detected deadline command of the user input.
     *
     * @param input The user input.
     * @throws DukeException if the description is empty or the input format is invalid.
     */
    public static String[] getDescAndBy(String input) throws DukeException {
        String descriptionAndBy = input.substring(DEADLINE_KW.length());
        String[] string = descriptionAndBy.split(BY_KW);
        ensureValidDeadlineInput(descriptionAndBy, string);
        return string;
    }

    private static void ensureValidDeadlineInput(String descriptionAndBy, String[] string) throws DukeException {
        if (string[0].isBlank()) {
            throw new DukeException(DukeExceptionType.EMPTY_DESCRIPTION, TaskType.DEADLINE);
        } else if (string.length != 2 || string[1].isBlank() || !descriptionAndBy.startsWith(" ")) {
            throw new DukeException(DukeExceptionType.INVALID_TASK_FORMAT, TaskType.DEADLINE);
        }
    }

    /**
     * Creates and returns an deadline from the data read from the file.
     *
     * @param data The user input.
     * @return The deadline created.
     * @throws DukeException if the task data is invalid.
     */
    public static Task initDeadline(String data) throws DukeException {
        String[] details = data.split("\\|");
        String description = details[2].trim();
        String by = details[3].trim();
        Task deadline = new Deadline(description, by);
        if (details[1].trim().compareTo("1") == 0) {
            deadline.markDone();
        } else if (details[1].trim().compareTo( "0") != 0) {
            throw new DukeException(DukeExceptionType.INVALID_TASK_DATA);
        }
        return deadline;
    }

    /**
     * Returns a String of the details of the deadline to be printed for the Ui.
     *
     * @return The details of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns the details of the deadline to be saved in the file.
     *
     * @return The details of the deadline.
     */
    @Override
    public String getData() {
        return "D" + SEPARATOR + super.getData() + SEPARATOR + by;
    }
}
