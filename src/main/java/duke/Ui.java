package duke;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskType;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String LINE = "\t____________________________________________________________"
            + System.lineSeparator();
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showWelcomeScreen() {
        System.out.println(LINE
                + "\tHello! I'm Duke.\n"
                + "\tWhat can I do for you?\n"
                + LINE);
    }

    public void showExitScreen() {
        System.out.println(LINE
                + "\tBye. Hope to see you again soon!\n"
                + LINE);
    }

    public void showTaskList(ArrayList<Task> tasklist, int taskCount) {
        System.out.print(Ui.LINE
                + "\tHere are the tasks in your list:\n");
        for(int i = 0; i < taskCount; i++) {
            System.out.printf("\t%d.%s\n", i+1, tasklist.get(i));
        }
        System.out.println(Ui.LINE);
    }

    public void showAddTask(Task task, int taskCount) {
        String taskCountStr = taskCount + (taskCount == 1 ? " task" : " tasks");
        System.out.println(Ui.LINE
                + "\tGot it. I've added this task:" + System.lineSeparator()
                + "\t  " + task + System.lineSeparator()
                + "\tNow you have " + taskCountStr + " in the list." + System.lineSeparator()
                + Ui.LINE);
    }

    public void showMarkTaskDone(Task task) {
        System.out.println(LINE
                + "\tNice! I've marked this task as done:");
        System.out.printf("\t  [%s] %s\n", task.getStatusIcon(), task.getDescription());
        System.out.println(LINE);
    }

    public void showFindKeyword(String message) {
        System.out.println(LINE
                + message
                + LINE);
    }

    public void showDeleteTask(Task task, int taskCount) {
        System.out.println(LINE
                + "\tNoted. I've removed this task:");
        System.out.printf("\t  %s\n", task);
        System.out.printf("\tNow you have %d tasks in the list." + System.lineSeparator(), taskCount);
        System.out.println(LINE);
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
        }
    }

    public void showLoadingError() {
        System.out.println(LINE
                + "\tOOPS!!! Data file not found");
        System.out.println(LINE);
    }

    private void printUnknownInputMessage() {
        System.out.println(LINE
                + "\tOOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println(LINE);
    }

    private void printFullTaskListMessage() {
        System.out.println(LINE
                + "\tOOPS!!! Tasklist is full.");
        System.out.println(LINE);
    }

    private void printEmptyTaskDescriptionMessage(TaskType taskType) {
        System.out.println(LINE
                + "\tOOPS!!! The description of " + taskType + " cannot be empty.");
        System.out.println(LINE);
    }

    private void printUseValidTaskNumberMessage() {
        System.out.println(LINE
                + "\tOOPS!!! Please enter valid task number.");
        System.out.println(LINE);
    }

    private void printEnterValidTaskFormatMessage(TaskType taskType) {
        System.out.println(LINE
                + "\tOOPS!!! Invalid " + taskType + " format!!");
        System.out.println(LINE);
    }

    private static void printErrorWritingToFile() {
        System.out.println(LINE
                + "\tOOPS!!! Error writing to file: ");
        System.out.println(LINE);
    }
}
