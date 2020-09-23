package duke.task;

import duke.exception.DukeException;
import duke.exception.DukeExceptionType;

public class Task {
    private static final String TICK = "\u2713";
    private static final String CROSS = "\u2718";
    private static final String DONE = "1";
    private static final String NOT_DONE = "0";
    protected static final String SEPARATOR = " | ";

    protected String description;
    protected boolean isDone;
    protected TaskType taskType;

    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public void markDone() {
        this.isDone = true;
    }

    protected static void initCheckDone(Task task, String done) throws DukeException {
        if (done.compareTo(DONE) == 0) {
            task.markDone();
        } else if (done.compareTo(NOT_DONE) != 0) {
            throw new DukeException(DukeExceptionType.INVALID_TASK_DATA);
        }
    }

    public String getStatusIcon() {
        // return tick or X symbols
        return (isDone ? TICK : CROSS);
    }

    public String getDescription() {
        return description;
    }

    public String getData() {
        return (isDone ? 1 : 0) + SEPARATOR + description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
