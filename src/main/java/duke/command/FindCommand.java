package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.DukeExceptionType;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command {
    public static final String FIND_KW = "find";
    private String keyword;

    public FindCommand(String command) throws DukeException {
        String details = command.substring(FIND_KW.length());
        if (!details.startsWith(" ")) {
            throw new DukeException(DukeExceptionType.UNKNOWN_INPUT);
        }
        keyword = details.trim();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<String> tasks = new ArrayList<>();
        for (Task task : taskList.getTaskList()) {
            String description = task.getDescription();
            if (description.contains(keyword)) {
                tasks.add(task.toString());
            }
        }
        String message = getMessage(tasks);
        ui.showFindKeyword(message);
    }

    private String getMessage(ArrayList<String> tasks) {
        String message = "\tHere are the matching tasks in your list:\n";
        int count = 0;
        for (String task : tasks) {
            count++;
            message = message + "\t" + count + "." + task + "\n";
        }
        return message;
    }
}
