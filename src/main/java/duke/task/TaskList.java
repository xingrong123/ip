package duke.task;

import duke.FileWriting;
import duke.exception.DukeException;
import duke.exception.DukeExceptionType;
import duke.UserInterface;

public class TaskList {
    private static final int MAXSIZE = 100;
    public static final String LIST_KW = "list";
    private static Task[] taskList;
    private int taskCount;


    public TaskList() {
        taskList = new Task[MAXSIZE];
    }

    public void printTaskList() {
        UserInterface.showTaskList(taskList, taskCount);
    }

    public void addTask(String input, TaskType taskType) throws DukeException {
        if (taskCount == MAXSIZE) {
            throw new DukeException(DukeExceptionType.FULL_TASK_LIST);
        }
        Task task = createTask(input, taskType);
        addTaskToList(task);
        UserInterface.showAddTask(task, taskCount);
        FileWriting.saveTaskList(getDataOfAllTasks());
    }

    private Task createTask(String input, TaskType taskType) throws DukeException {
        Task task = null;

        switch (taskType) {
        case TODO:
            task = addTodo(input);
            break;
        case DEADLINE:
            task = addDeadline(input);
            break;
        case EVENT:
            task = addEvent(input);
            break;
        }
        return task;
    }

    private void addTaskToList(Task task) {
        taskList[taskCount] = task;
        taskCount++;
    }

    private Task addTodo(String input) throws DukeException {
        Task task;
        String description = Todo.getDescription(input);
        task = new Todo(description);
        return task;
    }

    private Task addDeadline(String input) throws DukeException {
        Task task;
        String[] descAndBy = Deadline.getDescAndBy(input);
        String description = descAndBy[0];
        String by = descAndBy[1];
        task = new Deadline(description, by);
        return task;
    }

    private Task addEvent(String input) throws DukeException {
        Task task;
        String[] descAndAt = Event.getDescAndAt(input);
        String description = descAndAt[0];
        String at = descAndAt[1];
        task = new Event(description, at);
        return task;
    }

    public void markTaskDone(int taskOrder) throws DukeException {
        Task task = taskList[taskOrder - 1];
        task.markDone();
        UserInterface.showMarkTaskDone(task);
        FileWriting.saveTaskList(getDataOfAllTasks());
    }

    private String getDataOfAllTasks() {
        StringBuilder data = new StringBuilder();
        for (int i = 0; i < taskCount; i++) {
            data.append(taskList[i].getData()).append(System.lineSeparator());
        }
        return data.toString().trim();
    }
}
