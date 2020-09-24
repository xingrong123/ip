package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.DukeExceptionType;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the user command to mark an exiting task from the list as done.
 */
public class DoneCommand extends Command{
    public static final String DONE_KW = "done";
    private int index;

    /**
     * Constructs a new DoneCommand instance by detecting and storing the task number from the user input.
     *
     * @param command The user input command.
     * @throws DukeException if the format of the command is invalid or the task number is invalid.
     */
    public DoneCommand(String command) throws DukeException {
        String details = command.substring(DONE_KW.length());
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
     * Gets the task with the corresponding index from the list, then marks it as done.
     * Then it prints out a message and saves the list into the file.
     *
     * @param taskList The list of tasks.
     * @param ui The user interface.
     * @param storage The storage for saving and loading.
     * @throws DukeException When list reaches MAX_SIZE or when there is an error writing to file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = taskList.getTask(index);
            taskList.markTaskDone(index);
            ui.showMarkTaskDone(getMessage(task));
            storage.save(taskList.getData());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(DukeExceptionType.INVALID_TASK_NUMBER);
        }
    }

    private String getMessage(Task task) {
        String message = "\tNice! I've marked this task as done:\n"
                + "\t  [" + task.getStatusIcon() + "] " + task.getDescription() + "\n";
        return message;
    }
}