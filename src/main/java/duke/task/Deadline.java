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
 * A type of task that contains a deadline.
 */
public class Deadline extends Task implements Schedulable {
    private static final String BY_KW = " /by ";
    public static final String DEADLINE_KW = "deadline";
    public static final char CHAR_IDENTIFIER  = 'D';

    private String by;
    private LocalDate byDate = null;
    private LocalTime byTime = null;

    /**
     * Constructs a new Deadline instance by storing the given description and by.
     *
     * @param description The description of the event.
     * @param deadline The date and/or time of the deadline.
     */
    public Deadline(String description, String deadline) {
        super(description.trim(), TaskType.DEADLINE);

        try {
            if (deadline.contains(" ")) {
                List<String> dateTimeString =  Arrays.asList(deadline.split(" ", 2));
                byDate = DateTime.getDate(dateTimeString.get(0));
                byTime = DateTime.getTime(dateTimeString.get(1));
            } else {
                byDate = DateTime.getDate(deadline);
            }
        } catch (DateTimeParseException e) {
            this.by = deadline;
        }
    }

    /**
     * Returns the description and by of the detected deadline command of the user input.
     *
     * @param input The user input.
     * @throws DukeException if the description is empty or the input format is invalid.
     */
    public static List<String> getDescAndBy(String input) throws DukeException {
        String descriptionAndBy = input.substring(DEADLINE_KW.length());
        List<String> strings = Arrays.asList(descriptionAndBy.split(BY_KW));
        ensureValidDeadlineInput(descriptionAndBy, strings);
        return strings;
    }

    private static void ensureValidDeadlineInput(String descriptionAndBy, List<String> strings) throws DukeException {
        if (strings.get(0).isBlank()) {
            throw new DukeException(DukeExceptionType.EMPTY_DESCRIPTION, TaskType.DEADLINE);
        } else if (strings.size() != 2 || strings.get(1).isBlank() || !descriptionAndBy.startsWith(" ")) {
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
        List<String> details =  Arrays.asList(data.split("\\|"));
        String description = details.get(2).trim();
        String by = details.get(3).trim();
        Task deadline = new Deadline(description, by);
        String done = details.get(1).trim();
        initCheckDone(deadline, done);
        return deadline;
    }

    private String getBy() {
        String value;
        if (byDate != null && byTime != null) {
            value = DateTime.getDateString(byDate) + " " + DateTime.getTimeString(byTime);
        } else if (byDate != null && byTime == null) {
            value = DateTime.getDateString(byDate);
        } else {
            value = by;
        }
        return value;
    }

    private String getByData() {
        String value;
        if (byDate != null && byTime != null) {
            value = DateTime.getDateData(byDate) + " " + DateTime.getTimeData(byTime);
        } else if (byDate != null && byTime == null) {
            value = DateTime.getDateData(byDate);
        } else {
            value = by;
        }
        return value;
    }

    @Override
    public boolean hasDate() {
        return byDate != null;
    }

    @Override
    public boolean hasTime() {
        return byTime != null;
    }

    @Override
    public LocalDate getDate() {
        return byDate;
    }

    @Override
    public LocalTime getTime() {
        return byTime;
    }

    /**
     * Returns a String of the details of the deadline to be printed for the Ui.
     *
     * @return The details of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getBy() + ")";
    }

    /**
     * Returns the details of the deadline to be saved in the file.
     *
     * @return The details of the deadline.
     */
    @Override
    public String getData() {
        return "D" + SEPARATOR + super.getData() + SEPARATOR + getByData();
    }
}
