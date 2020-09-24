package duke.task;

/**
 * Enumeration of the different task types: todo, deadline, event.
 */
public enum TaskType {
    TODO,
    DEADLINE,
    EVENT;

    /**
     * Returns a string of the task type.
     *
     * @return the string.
     */
    @Override
    public String toString() {
        String task = null;
        switch (this) {
        case TODO:
            task = Todo.TODO_KW;
            break;
        case EVENT:
            task = Event.EVENT_KW;
            break;
        case DEADLINE:
            task = Deadline.DEADLINE_KW;
            break;
        }
        return task;
    }
}
