package duke.task;

public class Task {
    private static final String TICK = "\u2713";
    private static final String CROSS = "\u2718";
    protected static final String SEPARATOR = " | ";

    protected String description;
    protected boolean isDone;
    protected TaskType taskType;

    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public void markDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        // return tick or X symbols
        return (isDone ? TICK : CROSS);
    }

    public String getDescription() {
        return description;
    }

    public String getData() {
        return (isDone ? 1 : 0) + SEPARATOR + description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
