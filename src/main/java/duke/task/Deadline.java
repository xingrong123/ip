package duke.task;

import duke.exception.DukeException;
import duke.exception.DukeExceptionType;

public class Deadline extends Task {
    private static final String BY_KW = " /by ";
    public static final String DEADLINE_KW = "deadline";

    private final String by;

    public Deadline(String description, String deadline) {
        super(description.trim(), TaskType.DEADLINE);
        this.by = deadline.trim();
    }

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

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String getData() {
        return "D" + SEPARATOR + super.getData() + SEPARATOR + by;
    }
}
