package duke.task;

import duke.DateTime;
import duke.exception.DukeException;
import duke.exception.DukeExceptionType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private static final String BY_KW = " /by ";
    public static final String DEADLINE_KW = "deadline";

    private String by;
    private LocalDate byDate = null;
    private LocalTime byTime = null;

    public Deadline(String description, String deadline) {
        super(description.trim(), TaskType.DEADLINE);

        try {
            if (deadline.contains(" ")) {
                String[] dateTimeString = deadline.split(" ", 2);
                byDate = DateTime.getDate(dateTimeString[0]);
                byTime = DateTime.getTime(dateTimeString[1]);
            } else {
                byDate = DateTime.getDate(deadline);
            }
        } catch (DateTimeParseException e) {
            this.by = deadline;
        }
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
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getBy() + ")";
    }

    @Override
    public String getData() {
        return "D" + SEPARATOR + super.getData() + SEPARATOR + getByData();
    }
}
