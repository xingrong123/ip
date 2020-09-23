package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.Todo;

public class AddTodoCommand extends Command {

    private String description;

    public AddTodoCommand(String command) throws DukeException {
        description = Todo.getDescription(command);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Todo todo = new Todo(description);
        taskList.addTask(todo);
        ui.showAddTask(todo, taskList.getSize());
        storage.save(taskList.getData());
    }
}
