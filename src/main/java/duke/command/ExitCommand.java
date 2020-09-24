package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents the user command exit the Duke program.
 */
public class ExitCommand extends Command{
    public static final String BYE_KW = "bye";

    /**
     * Constructs a new ExitCommand instance and sets isExitCommand to true.
     */
    public ExitCommand() {
        isExitCommand = true;
    }

    /**
     * Prints the exit screen before the program exits.
     *
     * @param taskList The list of tasks.
     * @param ui The user interface.
     * @param storage The storage for saving and loading.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showExitScreen();
    }
}
