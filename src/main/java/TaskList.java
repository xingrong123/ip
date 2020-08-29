public class TaskList {
    private Task[] tasklist;
    private int taskCount;
    public static final int MAXSIZE = 100;

    public TaskList() {
        tasklist = new Task[100];
    }

    public void printTaskList() {
        UserInterface.showList(tasklist, taskCount);
    }

    public void addTask(String description, TaskType taskType) {
        Task task = null;
        task = createTask(description, taskType, task);
        addTaskToList(task);
        UserInterface.showAddTask(task, taskCount);
    }

    private Task createTask(String description, TaskType taskType, Task task) {
        switch (taskType) {
        case TODO:
            task = addTodo(description);
            break;
        case DEADLINE:
            task = addDeadline(description);
            break;
        case EVENT:
            task = addEvent(description);
            break;
        }
        return task;
    }

    private void addTaskToList(Task task) {
        tasklist[taskCount] = task;
        taskCount++;
    }

    private Task addTodo(String description) {
        Task task;
        task = new Todo(description);
        return task;
    }

    private Task addDeadline(String description) {
        Task task;
        int index = description.indexOf(Deadline.BY_KEYWORD);
        String deadline = description.substring(index + Deadline.BY_KEYWORD.length());
        description = description.substring(0, index);
        task = new Deadline(description, deadline);
        return task;
    }

    private Task addEvent(String description) {
        Task task;
        int index = description.indexOf(Event.AT_KEYWORD);
        String at = description.substring(index + Event.AT_KEYWORD.length());
        description = description.substring(0, index);
        task = new Event(description, at);
        return task;
    }

    public void markTaskDone(int listOrder) {
        Task task = tasklist[listOrder - 1];
        task.markDone();
        UserInterface.showMarkTaskDone(task);
    }

    public int getTaskCount() {
        return taskCount;
    }

}
