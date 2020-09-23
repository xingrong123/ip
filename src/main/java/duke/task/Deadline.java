package duke.task;

import duke.DateTime;
import duke.exception.DukeException;
import duke.exception.DukeExceptionType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class Deadline extends Task implements Schedulable {
    private static final String BY_KW = " /by ";
    public static final String DEADLINE_KW = "deadline";
    public static final char CHAR_IDENTIFIER  = 'D';

    private String by;
    private LocalDate byDate = null;
    private LocalTime byTime = null;

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

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getBy() + ")";
    }

    @Override
    public String getData() {
        return "D" + SEPARATOR + super.getData() + SEPARATOR + getByData();
    }
}
