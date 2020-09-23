package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ListCommand extends Command {
    public static final String LIST_KW = "list";

    public ListCommand() {

    }

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
