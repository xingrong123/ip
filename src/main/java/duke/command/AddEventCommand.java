package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Event;
import duke.task.TaskList;

import java.util.List;

/**
 * Represents the user command to add an event task
 */
public class AddEventCommand extends Command {

    private String description;
    private String at;

    /**
     * Constructs a new AddEventCommand instance by detecting and storing the description and at from the user input.
     *
     * @param command The user input command.
     * @throws DukeException if the description is empty or the input format is invalid.
     */
    public AddEventCommand(String command) throws DukeException {
        List<String> descAndAt = Event.getDescAndAt(command);
        description = descAndAt.get(0);
        at = descAndAt.get(1);
    }

    /**
     * This methods creates and adds an event task to the list.
     * Then it prints out a message and saves the list into the file.
     *
     * @param taskList The list of tasks.
     * @param ui The user interface.
     * @param storage The storage for saving and loading.
     * @throws DukeException when list reaches MAX_SIZE or when there is an error writing to file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Event event = new Event(description, at);
        taskList.addTask(event);
        ui.showAddTask(event, taskList.getSize());
        storage.save(taskList.getData());
    }
}
