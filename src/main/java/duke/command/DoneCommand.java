package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.DukeExceptionType;
import duke.task.Task;
import duke.task.TaskList;

public class DoneCommand extends Command {
    public static final String DONE_KW = "done";
    private int index;

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