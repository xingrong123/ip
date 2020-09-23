package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ExitCommand extends Command {
    public static final String BYE_KW = "bye";

    public ExitCommand() {
        isExitCommand = true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showExitScreen();
    }
}
