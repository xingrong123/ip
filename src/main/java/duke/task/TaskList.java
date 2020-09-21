package duke.task;

import duke.exception.DukeException;
import duke.exception.DukeExceptionType;

import java.util.ArrayList;

public class TaskList {
    private static final int MAXSIZE = 100;

    private ArrayList<Task> taskList;


    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(String[] tasks) {
        taskList = new ArrayList<>();
        loadTaskList(tasks);
    }

    public void addTask(Task task) throws DukeException {
        if (taskList.size() == MAXSIZE) {
            throw new DukeException(DukeExceptionType.FULL_TASK_LIST);
        }
        taskList.add(task);
    }

    private void loadTaskList(String[] tasks) {
        for (String task : tasks) {
            loadTask(task);
        }
    }

    private void loadTask(String task) {
        try {
            switch (task.charAt(0)) {
            case 'T':
                taskList.add(Todo.initTodo(task));
                break;
            case 'D':
                taskList.add(Deadline.initDeadline(task));
                break;
            case 'E':
                taskList.add(Event.initEvent(task));
                break;
            default:
                System.out.println("unidentified task");
                break;
            }
        } catch (IndexOutOfBoundsException | DukeException e) {
            // Invalid task data, skips to the next entry
        }
    }

    public void markTaskDone(int index) {
        Task task = taskList.get(index);
        task.markDone();
    }

    public String getData() {
        StringBuilder data = new StringBuilder();
        for (Task task : taskList) {
            data.append(task.getData()).append(System.lineSeparator());
        }
        return data.toString().trim();
    }

    public int getSize() {
        return taskList.size();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void deleteTask(Task task) {
        taskList.remove(task);
    }
}
