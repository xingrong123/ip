package duke.exception;

import duke.UserInterface;
import duke.task.TaskType;

public class DukeException extends Exception{
    private TaskType taskType = null;
    private final DukeExceptionType dukeExceptionType;

    public DukeException(DukeExceptionType dukeExceptionType) {
        this.dukeExceptionType = dukeExceptionType;
    }

    public DukeException(DukeExceptionType dukeExceptionType, TaskType taskType) {
        this.dukeExceptionType = dukeExceptionType;
        this.taskType = taskType;
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
        case INVALID_DONE_NUMBER:
            UserInterface.printUseValidNumberForDoneMessage();
            break;
        }
    }
}
