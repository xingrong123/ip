package duke;

import duke.exception.DukeException;
import duke.exception.DukeExceptionType;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;
import duke.task.Todo;

import java.util.Scanner;

public class Duke {

    private static TaskList taskList;
    private static final boolean EXIT = true;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isExit;
        String input;
        taskList = new TaskList();
        taskList.initTaskList();
        UserInterface.showWelcomeScreen();


        do {
            input = scanner.nextLine();
            isExit = checksInput(input.trim());
        } while (!isExit);

        UserInterface.showExitScreen();
    }




    private static boolean checksInput(String input) {
        try {
            if (input.equals(UserInterface.BYE_KW)) {
                return EXIT;
            } else if (input.equals(TaskList.LIST_KW)) {
                taskList.printTaskList();
            } else if (input.startsWith(Task.DONE_KW)) {
                markTaskDone(input);
            } else if (input.startsWith(TaskList.DELETE_KW)) {
                deleteTask(input);
            } else {
                checkTaskType(input);
            }
        } catch (DukeException e) {
            e.printErrorMessage();
        }

        return !EXIT;
    }


    private static void markTaskDone(String input) throws DukeException {
        String inputCheck = input.substring(Task.DONE_KW.length());
        checkForValidDoneFormat(inputCheck);
        String taskOrder = inputCheck.trim();
        try {
            taskList.markTaskDone(Integer.parseInt(taskOrder));
        } catch (NullPointerException | NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException(DukeExceptionType.INVALID_TASK_NUMBER);
        }

    }

    private static void checkForValidDoneFormat(String inputCheck) throws DukeException {
        if (!inputCheck.startsWith(" ")) {
            throw new DukeException(DukeExceptionType.UNKNOWN_INPUT);
        }
    }

    private static void deleteTask(String input) throws DukeException {
        String inputCheck = input.substring(TaskList.DELETE_KW.length());
        checkForValidDeleteFormat(inputCheck);
        String taskOrder = inputCheck.trim();
        try {
            taskList.deleteTask(Integer.parseInt(taskOrder));
        } catch (NullPointerException | NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException(DukeExceptionType.INVALID_TASK_NUMBER);
        }
    }

    private static void checkForValidDeleteFormat(String inputCheck) throws DukeException {
        if (!inputCheck.startsWith(" ")) {
            throw new DukeException(DukeExceptionType.UNKNOWN_INPUT);
        }
    }


    private static void checkTaskType(String input) throws DukeException {
        if (input.startsWith(Todo.TODO_KW)) {
            taskList.addTask(input, TaskType.TODO);
        } else if (input.startsWith(Deadline.DEADLINE_KW)) {
            taskList.addTask(input, TaskType.DEADLINE);
        } else if (input.startsWith(Event.EVENT_KW)) {
            taskList.addTask(input, TaskType.EVENT);
        } else {
            throw new DukeException(DukeExceptionType.UNKNOWN_INPUT);
        }
    }

}
