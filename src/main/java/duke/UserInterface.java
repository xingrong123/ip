package duke;

import duke.task.Task;
import duke.task.TaskType;

public class UserInterface {
    private static final String LINE = "\t____________________________________________________________"
            + System.lineSeparator();
    public static final String BYE_KW = "bye";

    public static void showWelcomeScreen() {
        System.out.println(LINE
                + "\tHello! I'm Duke.\n"
                + "\tWhat can I do for you?\n"
                + LINE);
    }

    public static void showExitScreen() {
        System.out.println(LINE
                + "\tBye. Hope to see you again soon!\n"
                + LINE);
    }

    public static void showTaskList(Task[] tasklist, int taskCount) {
        System.out.print(UserInterface.LINE
                + "\tHere are the tasks in your list:\n");
        for(int i = 0; i < taskCount; i++) {
            System.out.printf("\t%d.%s\n", i+1, tasklist[i]);
        }
        System.out.println(UserInterface.LINE);
    }

    public static void showAddTask(Task task, int taskCount) {
        String taskCountStr = taskCount + (taskCount == 1 ? " task" : " tasks");
        System.out.println(UserInterface.LINE
                + "\tGot it. I've added this task:" + System.lineSeparator()
                + "\t  " + task + System.lineSeparator()
                + "\tNow you have " + taskCountStr + " in the list." + System.lineSeparator()
                + UserInterface.LINE);
    }

    public static void showMarkTaskDone(Task task) {
        System.out.println(LINE
                + "\tNice! I've marked this task as done:");
        System.out.printf("\t  [%s] %s\n", task.getStatusIcon(), task.getDescription());
        System.out.println(LINE);
    }

    public static void printUnknownInputMessage() {
        System.out.println(LINE
                + "\tOOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println(LINE);
    }

    public static void printFullTaskListMessage() {
        System.out.println(LINE
                + "\tOOPS!!! Tasklist is full.");
        System.out.println(LINE);
    }

    public static void printEmptyTaskDescriptionMessage(TaskType taskType) {
        System.out.println(LINE
                + "\tOOPS!!! The description of " + taskType + " cannot be empty.");
        System.out.println(LINE);
    }

    public static void printUseValidNumberForDoneMessage() {
        System.out.println(LINE
                + "\tOOPS!!! Please enter valid number for done command.");
        System.out.println(LINE);
    }

    public static void printEnterValidTaskFormatMessage(TaskType taskType) {
        System.out.println(LINE
                + "\tOOPS!!! Invalid " + taskType + " format!!");
        System.out.println(LINE);
    }

    public static void printErrorWritingToFile(String message) {
        System.out.println(LINE
                + "\tError writing to file: " + message);
        System.out.println(LINE);
    }
}
