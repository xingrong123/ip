package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Event;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.List;

public class AddEventCommand extends Command {

    private String description;
    private String at;

    public AddEventCommand(String command) throws DukeException {
        List<String> descAndAt = Event.getDescAndAt(command);
        description = descAndAt.get(0);
        at = descAndAt.get(1);
    }


    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Event event = new Event(description, at);
        taskList.addTask(event);
        ui.showAddTask(event, taskList.getSize());
        storage.save(taskList.getData());
    }
}
