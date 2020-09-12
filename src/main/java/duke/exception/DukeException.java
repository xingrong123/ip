package duke.exception;

import duke.UserInterface;
import duke.task.TaskType;

public class DukeException extends Exception{
    private TaskType taskType = null;
    private final DukeExceptionType dukeExceptionType;
    private String message = null;

    public DukeException(DukeExceptionType dukeExceptionType) {
        this.dukeExceptionType = dukeExceptionType;
    }

    public DukeException(DukeExceptionType dukeExceptionType, TaskType taskType) {
        this.dukeExceptionType = dukeExceptionType;
        this.taskType = taskType;
    }

    public DukeException(DukeExceptionType dukeExceptionType, String message) {
        this.dukeExceptionType = dukeExceptionType;
        this.message = message;
    }

    public void printErrorMessage() {
        switch (dukeExceptionType) {
        case EMPTY_DESCRIPTION:
            UserInterface.printEmptyTaskDescriptionMessage(taskType);
            break;
        case FULL_TASK_LIST:
            UserInterface.printFullTaskListMessage();
            break;
        case INVALID_TASK_FORMAT:
            UserInterface.printEnterValidTaskFormatMessage(taskType);
            break;
        case UNKNOWN_INPUT:
            UserInterface.printUnknownInputMessage();
            break;
        case INVALID_TASK_NUMBER:
            UserInterface.printUseValidTaskNumberMessage();
            break;
        case WRITE_FILE_ERROR:
            UserInterface.printErrorWritingToFile(message);
            break;
        }
    }
}
