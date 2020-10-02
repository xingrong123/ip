package duke.task;

import duke.exception.DukeException;
import duke.exception.DukeExceptionType;

/**
 * The Task class and its subclasses represents a task containing a description and an indicator of whether it is done.
 */
public class Task {
    private static final String TICK = "\u2713";
    private static final String CROSS = "\u2718";
    private static final String DONE = "1";
    private static final String NOT_DONE = "0";
    protected static final String SEPARATOR = " | ";

    protected String description;
    protected boolean isDone;
    protected TaskType taskType;

    /**
     * Used by subclasses to set description, done state and tasktype.
     */
    protected Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * This method sets the isDone variable of the task object to true.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * This method sets the isDone variable of the task object to true if it is done.
     *
     * @throws DukeException if data of done state is invalid.
     */
    protected static void initCheckDone(Task task, String done) throws DukeException {
        if (done.compareTo(DONE) == 0) {
            task.markDone();
        } else if (done.compareTo(NOT_DONE) != 0) {
            throw new DukeException(DukeExceptionType.INVALID_TASK_DATA);
        }
    }

    /**
     * Returns the corresponding icon depending on isDone.
     *
     * @return TICK if isDone is true, else CROSS.
     */
    public String getStatusIcon() {
        // return tick or X symbols
        return (isDone ? TICK : CROSS);
    }

    /**
     * Returns the description of the task.
     *
     * @return description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the full details of the task when saving.
     *
     * @return a string containing isDone and the description of the task.
     */
    public String getData() {
        return (isDone ? 1 : 0) + SEPARATOR + description;
    }

    /**
     * Returns the details of the task to be printed in the command line.
     *
     * @return The details of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
