public class Task {
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
        return (isDone ? "\u2713" : "\u2718");
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
