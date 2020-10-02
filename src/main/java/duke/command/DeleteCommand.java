package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.DukeExceptionType;
import duke.task.Task;
import duke.task.TaskList;


/**
 * Represents the user command to delete a task in the list.
 */
public class DeleteCommand extends Command{
    public static final String DELETE_KW = "delete";
    private int index;

    /**
     * Constructs a new DeleteCommand instance by detecting and storing the task number from the user input.
     *
     * @param command The user input command.
     * @throws DukeException if the format of the command is invalid or the task number is invalid.
     */
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

    /**
     * This method gets the task with the corresponding index from the list, then deletes it.
     * Then it prints out a message and saves the list into the file.
     *
     * @param taskList The list of tasks.
     * @param ui The user interface.
     * @param storage The storage for saving and loading.
     * @throws DukeException if the task number entered is not a valid index number of an existing task in the list.
     */
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
