public class TaskList {
    public static final int MAXSIZE = 100;
    public static final String LIST_KW = "list";
    private static final int DescAndAtCount = 2;
    private static final int DescAndByCount = 2;
    private Task[] tasklist;
    private int taskCount;


    public TaskList() {
        tasklist = new Task[MAXSIZE];
    }

    public void printTaskList() {
        UserInterface.showsList(tasklist, taskCount);
    }

    public void addTask(String description, TaskType taskType) {
        Task task = null;
        task = createTask(description, taskType, task);
        addTaskToList(task);
        UserInterface.showsAddTask(task, taskCount);
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

    private Task addDeadline(String input) {
        Task task;
        String[] descAndAt = input.split(Deadline.BY_KW, DescAndByCount);
        String description = descAndAt[0];
        String by = descAndAt[1];
        task = new Deadline(description, by);
        return task;
    }

    private Task addEvent(String input) {
        Task task;
        String[] descAndAt = input.split(Event.AT_KW, DescAndAtCount);
        String description = descAndAt[0];
        String at = descAndAt[1];
        task = new Event(description, at);
        return task;
    }

    public void markTaskDone(int listOrder) {
        Task task = tasklist[listOrder - 1];
        task.markDone();
        UserInterface.showsMarkTaskDone(task);
    }

    public int getTaskCount() {
        return taskCount;
    }

}
