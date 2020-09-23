package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.DukeExceptionType;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    public static final String DELETE_KW = "delete";
    private int index;

    public DeleteCommand(String command) throws DukeException {
        String details = command.substring(DELETE_KW.length());
        if (!details.startsWith(" ")) {
            throw new DukeException(DukeExceptionType.UNKNOWN_INPUT);
        }
        try {
            index = Integer.parseInt(details.trim()) - 1;
        } catch (NullPointerException | NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException(DukeExceptionType.INVALID_TASK_NUMBER);
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = taskList.getTask(index);
            taskList.deleteTask(task);
            ui.showDeleteTask(getMessage(task, taskList.getSize()));
            storage.save(taskList.getData());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(DukeExceptionType.INVALID_TASK_NUMBER);
        }

    }

    private String getMessage(Task task, int size) {
        String message = "\tNoted. I've removed this task:\n"
                + "\t  " + task + "\n"
                + "\tNow you have " + size + " tasks in the list.\n";
        return message;
    }
}
