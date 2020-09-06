public class Todo extends Task{
    public static final String TODO_KW = "todo";

    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    public static String getDescription(String input) throws DukeException {
        String description = input.substring(TODO_KW.length());
        ensureValidTodoInput(description);
        return description.trim();
    }

    private static void ensureValidTodoInput(String description) throws DukeException {
        if (description.isBlank()) {
            throw new DukeException(DukeExceptionType.EMPTY_DESCRIPTION, TaskType.TODO);
        } else if (!description.startsWith(" ")) {
            throw new DukeException(DukeExceptionType.INVALID_TASK_FORMAT, TaskType.TODO);
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
