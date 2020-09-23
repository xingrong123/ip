package duke;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskType;

import java.util.Scanner;

import static java.lang.System.lineSeparator;

public class Ui {
    private static final String LINE = "\t____________________________________________________________"
            + lineSeparator();
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    private void showMessageLayout(String message) {
        System.out.println(LINE
                + message
                + LINE);
    }

    public void showWelcomeScreen() {
        String message = "\tHello! I'm Duke.\n"
                + "\tWhat can I do for you?\n";
        showMessageLayout(message);
    }

    public void showExitScreen() {
        String message = "\tBye. Hope to see you again soon!\n";
        showMessageLayout(message);
    }

    public void showTaskList(String message) {
        showMessageLayout(message);
    }

    public void showTaskDate(String message) {
        showMessageLayout(message);
    }

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

//    public void showDeleteTask(Task task, int taskCount) {
//        System.out.println(LINE
//                + "\tNoted. I've removed this task:");
//        System.out.printf("\t  %s\n", task);
//        System.out.printf("\tNow you have %d tasks in the list." + System.lineSeparator(), taskCount);
//        System.out.println(LINE);

    public void showMarkTaskDone(String message) {
        showMessageLayout(message);
    }

    public void showDeleteTask(String message) {
        showMessageLayout(message);
    }

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
