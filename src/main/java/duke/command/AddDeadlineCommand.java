package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.TaskList;

import java.util.List;

/**
 * Represents the user command to add a deadline task
 */
public class AddDeadlineCommand extends Command {

    private String description;
    private String by;

    /**
     * Constructs a new AddDeadlineCommand instance by detecting and storing the description and at from the user input.
     *
     * @param command The user input command.
     * @throws DukeException if the description is empty or the input format is invalid.
     */
    public AddDeadlineCommand(String command) throws DukeException {
        List<String> descAndAt = Deadline.getDescAndBy(command);
        description = descAndAt.get(0);
        by = descAndAt.get(1);
    }

    /**
     * This methods creates and adds a deadline task to the list.
     * Then it prints out a message and saves the list into the file.
     *
     * @param taskList The list of tasks.
     * @param ui The user interface.
     * @param storage The storage for saving and loading.
     * @throws DukeException When list reaches MAX_SIZE or when there is an error writing to file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Deadline deadline = new Deadline(description, by);
        taskList.addTask(deadline);
        ui.showAddTask(deadline, taskList.getSize());
        storage.save(taskList.getData());
    }


}
