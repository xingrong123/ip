package duke.task;

import duke.exception.DukeException;
import duke.exception.DukeExceptionType;

import java.util.Arrays;
import java.util.List;

public class Todo extends Task{
    public static final String TODO_KW = "todo";
    public static final char CHAR_IDENTIFIER  = 'T';

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

    public static Task initTodo(String data) throws DukeException {
        List<String> details = Arrays.asList(data.split("\\|"));
        String description = details.get(2).trim();
        Task todo = new Todo(description);
        String done = details.get(1).trim();
        initCheckDone(todo, done);
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
