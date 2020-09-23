package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Represents the user command to add a todo task
 */
public class AddTodoCommand extends Command{

    private String description;

    /**
     * Constructs a new AddTodoCommand instance by detecting and storing the description from the user input.
     *
     * @param command The user input command.
     * @throws DukeException if the description is empty or the input format is invalid.
     */
    public AddTodoCommand(String command) throws DukeException {
        description = Todo.getDescription(command);
    }

    /**
     * This methods creates and adds a todo task to the list.
     * Then it prints out a message and saves the list into the file.
     *
     * @param taskList The list of tasks.
     * @param ui The user interface.
     * @param storage The storage for saving and loading.
     * @throws DukeException when list reaches MAX_SIZE or when there is an error writing to file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Todo todo = new Todo(description);
        taskList.addTask(todo);
        ui.showAddTask(todo, taskList.getSize());
        storage.save(taskList.getData());
    }
}
