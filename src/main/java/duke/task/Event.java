package duke.task;

import duke.exception.DukeException;
import duke.exception.DukeExceptionType;

public class Event extends Task{
    public static final String AT_KW = " /at ";
    public static final String EVENT_KW = "event";
    private final String at;

    public Event(String description, String at) {
        super(description.trim(), TaskType.EVENT);
        this.at = at.trim();
    }

    public static String[] getDescAndAt(String description) throws DukeException {
        description = description.substring(EVENT_KW.length());
        String[] string = description.split(AT_KW);
        ensureValidEventInput(description, string);
        return string;
    }

    private static void ensureValidEventInput(String description, String[] string) throws DukeException {
        if (string[0].isBlank()) {
            throw new DukeException(DukeExceptionType.EMPTY_DESCRIPTION, TaskType.EVENT);
        } else if (string.length != 2 || string[1].isBlank() || !description.startsWith(" ")) {
            throw new DukeException(DukeExceptionType.INVALID_TASK_FORMAT, TaskType.EVENT);
        }
    }

    public static Task initEvent(String data) {
        String[] details = data.split("\\|");
        String description = details[2].trim();
        String at = details[3].trim();
        Task event = new Event(description, at);
        if (details[1].trim().equals("1")) {
            event.markDone();
        }
        return event;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String getData() {
        return "E" + SEPARATOR + super.getData() + SEPARATOR + at;
    }
}
