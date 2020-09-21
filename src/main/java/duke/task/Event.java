package duke.task;

import duke.DateTime;
import duke.exception.DukeException;
import duke.exception.DukeExceptionType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    private static final String AT_KW = " /at ";
    public static final String EVENT_KW = "event";

    private String at;
    private LocalDate atDate = null;
    private LocalTime atTime = null;

    public Event(String description, String at) {
        super(description.trim(), TaskType.EVENT);


        try {
            if (at.contains(" ")) {
                String[] dateTimeString = at.split(" ", 2);
                atDate = DateTime.getDate(dateTimeString[0]);
                atTime = DateTime.getTime(dateTimeString[1]);
            } else {
                atDate = DateTime.getDate(at);
            }
        } catch (DateTimeParseException e) {
            this.at = at;
        }
    }

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
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getAt() + ")";
    }

    @Override
    public String getData() {
        return "E" + SEPARATOR + super.getData() + SEPARATOR + getAtData();
    }
}
