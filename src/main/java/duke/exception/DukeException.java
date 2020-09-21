package duke.exception;

import duke.task.TaskType;

public class DukeException extends Exception{
    private TaskType taskType = null;
    private DukeExceptionType dukeExceptionType;

    public DukeException(DukeExceptionType dukeExceptionType) {
        this.dukeExceptionType = dukeExceptionType;
    }

    public DukeException(DukeExceptionType dukeExceptionType, TaskType taskType) {
        this.dukeExceptionType = dukeExceptionType;
        this.taskType = taskType;
    }

    public DukeExceptionType getError() {
        return dukeExceptionType;
    }

    public TaskType getTaskType() {
        return taskType;
    }

}
