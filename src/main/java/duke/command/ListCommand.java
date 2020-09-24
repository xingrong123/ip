package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents the user command to mark an exiting task from the list as done.
 */
public class ListCommand extends Command{
    public static final String LIST_KW = "list";

    /**
     * Constructs a new ListCommand instance.
     */
    public ListCommand() {

    }

    /**
     * Prints all the details of the tasks in the list.
     *
     * @param taskList The list of tasks.
     * @param ui The user interface.
     * @param storage The storage for saving and loading.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String message = getMessage(taskList);
        ui.showTaskList(message);
    }

    private String getMessage(TaskList taskList) {
        String message = "\tHere are the tasks in your list:\n";
        for (int i = 0; i < taskList.getSize(); i++) {
            message = message + "\t" + (i + 1) + "." + taskList.getTaskList().get(i) + "\n";
        }
        return message;
    }
}
