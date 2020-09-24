package duke.task;

import duke.exception.DukeException;
import duke.exception.DukeExceptionType;

import java.util.ArrayList;

/**
 * Represents a list containing all of the tasks.
 */
public class TaskList {
    private static final int MAX_SIZE = 100;

    private ArrayList<Task> taskList;

    /**
     * Constructs a new TaskList instance by creating an empty ArrayList.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Constructs a new TaskList instance and storing the tasks in an ArrayList.
     *
     * @param tasks The tasks read from the file.
     */
    public TaskList(ArrayList<String> tasks) {
        taskList = new ArrayList<>();
        loadTaskList(tasks);
    }

    /**
     * This method adds a task to the list
     *
     * @param task The task to be added to the list.
     * @throws DukeException when list reaches MAX_SIZE.
     */
    public void addTask(Task task) throws DukeException {
        if (taskList.size() == MAX_SIZE) {
            throw new DukeException(DukeExceptionType.FULL_TASK_LIST);
        }
        taskList.add(task);
    }

    private void loadTaskList(ArrayList<String> tasks) {
        for (String task : tasks) {
            loadTask(task);
        }
    }

    private void loadTask(String task) {
        try {
            switch (task.charAt(0)) {
            case Todo.CHAR_IDENTIFIER:
                taskList.add(Todo.initTodo(task));
                break;
            case Deadline.CHAR_IDENTIFIER:
                taskList.add(Deadline.initDeadline(task));
                break;
            case Event.CHAR_IDENTIFIER:
                taskList.add(Event.initEvent(task));
                break;
            }
        } catch (IndexOutOfBoundsException | DukeException e) {
            // Invalid task data, skips to the next entry
        }
    }

    /**
     * This method marks a task in the list as done.
     *
     * @param index The index of the task in the list to be marked as done.
     */
    public void markTaskDone(int index) {
        Task task = taskList.get(index);
        task.markDone();
    }

    /**
     * Returns the the data of all of the tasks in the list to be saved in the file.
     *
     * @return the data of all the tasks.
     */
    public String getData() {
        StringBuilder data = new StringBuilder();
        for (Task task : taskList) {
            data.append(task.getData()).append(System.lineSeparator());
        }
        return data.toString().trim();
    }

    /**
     * Returns the number of tasks in the list
     *
     * @return the size of the taskList.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Returns task based on the index.
     *
     * @param index The index of the task in the list.
     * @return Task The task with the corresponding index in the list.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Returns the taskList.
     *
     * @return The taskList.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * This method deletes the task from the list.
     *
     * @param task The task to be deleted.
     */
    public void deleteTask(Task task) {
        taskList.remove(task);
    }
}
