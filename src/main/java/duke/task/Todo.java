package duke.task;

import duke.exception.DukeException;
import duke.exception.DukeExceptionType;

import java.util.Arrays;
import java.util.List;

    /**
     * The Todo class represents a type of task which contains a description.
     */
public class Todo extends Task{
    public static final String TODO_KW = "todo";
    public static final char CHAR_IDENTIFIER  = 'T';

    /**
     * Constructs a new Todo instance by storing the given description.
     *
     * @param description The description of the todo.
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Returns the description detected in the user input.
     *
     * @param input The user input.
     * @throws DukeException if the description is empty or the input format is invalid.
     */
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

    /**
     * Creates and returns a todo from the data read from the file.
     *
     * @param data The user input.
     * @return The todo created.
     * @throws DukeException if the task data is invalid.
     */
    public static Task initTodo(String data) throws DukeException {
        List<String> details = Arrays.asList(data.split("\\|"));
        String description = details.get(2).trim();
        Task todo = new Todo(description);
        String done = details.get(1).trim();
        initCheckDone(todo, done);
        return todo;
    }

    /**
     * Returns a string of the details of the todo to be printed for the Ui.
     *
     * @return The details of the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the details of the todo to be saved in the file.
     *
     * @return The details of the todo.
     */
    @Override
    public String getData() {
        return "T" + SEPARATOR + super.getData();
    }
}
