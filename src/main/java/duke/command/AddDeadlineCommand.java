package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.TaskList;

import java.util.List;

import static java.lang.System.lineSeparator;

public class AddDeadlineCommand extends Command {

    private String description;
    private String by;

    public AddDeadlineCommand(String command) throws DukeException {
        List<String> descAndAt = Deadline.getDescAndBy(command);
        description = descAndAt.get(0);
        by = descAndAt.get(1);
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Deadline deadline = new Deadline(description, by);
        taskList.addTask(deadline);
        ui.showAddTask(deadline, taskList.getSize());
        storage.save(taskList.getData());
    }


}
