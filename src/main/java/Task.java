public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
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
}
