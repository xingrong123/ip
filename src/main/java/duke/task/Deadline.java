package duke.task;

import duke.exception.DukeException;
import duke.exception.DukeExceptionType;

public class Deadline extends Task {
    public static final String BY_KW = " /by ";
    public static final String DEADLINE_KW = "deadline";
    private final String by;

    public Deadline(String description, String deadline) {
        super(description.trim(), TaskType.DEADLINE);
        this.by = deadline.trim();
    }

    public static String[] getDescAndBy(String description) throws DukeException {
        description = description.substring(DEADLINE_KW.length());
        String[] string = description.split(BY_KW);
        ensureValidDeadlineInput(description, string);
        return string;
    }

    private static void ensureValidDeadlineInput(String description, String[] string) throws DukeException {
        if (string[0].isBlank()) {
            throw new DukeException(DukeExceptionType.EMPTY_DESCRIPTION, TaskType.DEADLINE);
        } else if (string.length != 2 || string[1].isBlank() || !description.startsWith(" ")) {
            throw new DukeException(DukeExceptionType.INVALID_TASK_FORMAT, TaskType.DEADLINE);
        }
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
