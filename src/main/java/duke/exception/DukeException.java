package duke.exception;

import duke.task.TaskType;

/**
 * The class DukeException is a subclass of Exception. It is thrown when
 */
public class DukeException extends Exception{
    private TaskType taskType = null;
    private DukeExceptionType dukeExceptionType;

    /**
     * Constructs a new DukeException instance by storing the given DukeExceptionType.
     *
     * @param dukeExceptionType The type of dukeException.
     */
    public DukeException(DukeExceptionType dukeExceptionType) {
        this.dukeExceptionType = dukeExceptionType;
    }

    /**
     * Constructs a new DukeException instance by storing the given DukeExceptionType and TaskType.
     *
     * @param dukeExceptionType The type of dukeException.
     * @param taskType The type of Task where the error occurred.
     */
    public DukeException(DukeExceptionType dukeExceptionType, TaskType taskType) {
        this.dukeExceptionType = dukeExceptionType;
        this.taskType = taskType;
    }

    /**
     * Returns the dukeExceptionType of the dukeException.
     *
     * @return the dukeExceptionType.
     */
    public DukeExceptionType getError() {
        return dukeExceptionType;
    }

    /**
     * Returns the taskType of where the dukeException occurred.
     *
     * @return the taskType.
     */
    public TaskType getTaskType() {
        return taskType;
    }

}
