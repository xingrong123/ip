package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.DukeExceptionType;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

/**
 * Represents the user command to search for tasks which description contains a specified keyword(s).
 */
public class FindCommand extends Command {
    public static final String FIND_KW = "find";
    private String keyword;

    /**
     * Constructs a new ListCommand instance and sets isExitCommand to true.
     *
     * @param command The string of user input.
     * @throws DukeException if input is unknown.
     */
    public FindCommand(String command) throws DukeException {
        String details = command.substring(FIND_KW.length());
        if (!details.startsWith(" ")) {
            throw new DukeException(DukeExceptionType.UNKNOWN_INPUT);
        }
        keyword = details.trim();
    }

    /**
     * Prints all the details of the tasks which contains the keyword(s).
     *
     * @param taskList The list of tasks.
     * @param ui The user interface.
     * @param storage The storage for saving and loading.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<String> tasks = getTasks(taskList);
        String message = getMessage(tasks);
        ui.showFindKeyword(message);
    }

    private ArrayList<String> getTasks(TaskList taskList) {
        ArrayList<String> tasks = new ArrayList<>();
        for (Task task : taskList.getTaskList()) {
            String description = task.getDescription();
            if (description.contains(keyword)) {
                tasks.add(task.toString());
            }
        }
        return tasks;
    }

    private String getMessage(ArrayList<String> tasks) {
        String message = "\tHere are the matching tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            message = message + "\t" + (i + 1) + "." + tasks.get(i) + "\n";
        }
        return message;
    }
}
