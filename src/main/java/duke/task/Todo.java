package duke.task;

import duke.exception.DukeException;
import duke.exception.DukeExceptionType;

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

    public static Task initTodo(String data) {
        String[] details = data.split("\\|");
        String description = details[2].trim();
        Task todo = new Todo(description);
        if (details[1].trim().equals("1")) {
            todo.markDone();
        }
        return todo;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getData() {
        return "T" + SEPARATOR + super.getData();
    }
}
