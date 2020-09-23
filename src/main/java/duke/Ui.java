package duke;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskType;

import java.util.Scanner;

import static java.lang.System.lineSeparator;

/**
 * Represents the user interface on the command line and deals with interactions with the user.
 */
public class Ui {
    private static final String LINE = "\t____________________________________________________________"
            + lineSeparator();
    private Scanner scanner;

    /**
     * Constructs a new Ui instance.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Returns a string of the user input in the command line.
     *
     * @return the string of user input.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    private void showMessageLayout(String message) {
        System.out.println(LINE
                + message
                + LINE);
    }

    /**
     * Prints a message after starting the program.
     */
    public void showWelcomeScreen() {
        String message = "\tHello! I'm Duke.\n"
                + "\tWhat can I do for you?\n";
        showMessageLayout(message);
    }

    /**
     * Prints a message before exiting the program.
     */
    public void showExitScreen() {
        String message = "\tBye. Hope to see you again soon!\n";
        showMessageLayout(message);
    }

    /**
     * Prints the details of all the tasks in the list.
     *
     * @param message The string to be printed.
     */
    public void showTaskList(String message) {
        showMessageLayout(message);
    }

    public void showTaskDate(String message) {
        showMessageLayout(message);
    }

    /**
     * Prints the message after adding a task.
     *
     * @param task The added task.
     * @param taskCount The number of tasks in the list.
     */
    public void showAddTask(Task task, int taskCount) {
        String message = getAddTaskMessage(task, taskCount);
        showMessageLayout(message);
    }

    private String getAddTaskMessage(Task task, int size) {
        String taskCount = size + (size == 1 ? " task" : " tasks");
        String message = "\tGot it. I've added this task:" + lineSeparator()
                + "\t  " + task + "\n"
                + "\tNow you have " + taskCount + " in the list.\n";
        return message;
    }

    public void showFindKeyword(String message) {
        showMessageLayout(message);
    }

    /**
     * Prints the message after marking a task as done.
     *
     * @param message The string to be printed.
     */
    public void showMarkTaskDone(String message) {
        showMessageLayout(message);
    }

    /**
     * Prints the message after deleting a task.
     *
     * @param message The string to be printed.
     */
    public void showDeleteTask(String message) {
        showMessageLayout(message);
    }

    /**
     * This methods detects the type of dukeException error and
     * prints the corresponding error message.
     *
     * @param dukeException The dukeException error.
     */
    public void showErrorMessage(DukeException dukeException) {
        switch (dukeException.getError()) {
        case FULL_TASK_LIST:
            printFullTaskListMessage();
            break;
        case UNKNOWN_INPUT:
            printUnknownInputMessage();
            break;
        case INVALID_TASK_NUMBER:
            printUseValidTaskNumberMessage();
            break;
        case WRITE_FILE_ERROR:
            printErrorWritingToFile();
            break;
        case EMPTY_DESCRIPTION:
            printEmptyTaskDescriptionMessage(dukeException.getTaskType());
            break;
        case INVALID_TASK_FORMAT:
            printEnterValidTaskFormatMessage(dukeException.getTaskType());
            break;
        case INVALID_DATE_TIME:
            printInvalidDateTime();
            break;
        }
    }

    public void showLoadingError() {
        String message = "\tOOPS!!! Data file not found\n";
        showMessageLayout(message);
    }

    private void printUnknownInputMessage() {
        String message = "\tOOPS!!! I'm sorry, but I don't know what that means :-(\n";
        showMessageLayout(message);
    }

    private void printFullTaskListMessage() {
        String message = "\tOOPS!!! Tasklist is full.\n";
        showMessageLayout(message);
    }

    private void printEmptyTaskDescriptionMessage(TaskType taskType) {
        String message = "\tOOPS!!! The description of " + taskType + " cannot be empty.\n";
        showMessageLayout(message);
    }

    private void printUseValidTaskNumberMessage() {
        String message = "\tOOPS!!! Please enter valid task number.\n";
        showMessageLayout(message);
    }

    private void printEnterValidTaskFormatMessage(TaskType taskType) {
        String message = "\tOOPS!!! Invalid " + taskType + " format!!\n";
        showMessageLayout(message);
    }

    private void printErrorWritingToFile() {
        String message = "\tOOPS!!! Error writing to file.\n";
        showMessageLayout(message);
    }

    private void printInvalidDateTime() {
        String message = "\tOOPS!!! Invalid date or time used.\n";
        showMessageLayout(message);
    }
}
