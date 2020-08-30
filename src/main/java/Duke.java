import java.util.Scanner;

public class Duke {

    private static TaskList taskList;
    private static final boolean EXIT = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean notExit;
        String input = "";
        taskList = new TaskList();

        UserInterface.showsWelcomeScreen();

        do {

            input = scanner.nextLine();
            notExit = checksInput(input);

        } while (notExit);

        UserInterface.showsExitScreen();
    }


    public static boolean checksInput(String input) {
        if (input.equals(UserInterface.BYE_KW)) {
            return EXIT;
        } else if (input.equals(TaskList.LIST_KW)) {
            taskList.printTaskList();
        } else if (input.startsWith(Task.DONE_KW)) {
            marksTaskDone(input);
        } else if (taskList.getTaskCount() < TaskList.MAXSIZE) {
            checksTaskType(input);
        }
        return !EXIT;
    }


    private static void marksTaskDone(String input) {
        String integerCheck = input.substring(Task.DONE_KW.length()).trim();
        if (checksValidDone(integerCheck)) {
            taskList.markTaskDone(Integer.parseInt(integerCheck));
        }
    }


    private static boolean checksValidDone(String integerCheck) {
        if (isInteger(integerCheck)) {
            int taskOrder = Integer.parseInt(integerCheck);
            return taskOrder <= taskList.getTaskCount() && taskOrder > 0;
        }
        return false;
    }


    private static void checksTaskType(String input) {
        if (input.startsWith(Todo.TODO_KW)) {
            checksValidTodo(input);
        } else if (input.startsWith(Deadline.DEADLINE_KW)) {
            checksValidDeadline(input);
        } else if (input.startsWith(Event.EVENT_KW)) {
            checksValidEvent(input);
        }
    }


    private static void checksValidTodo(String input) {
        if (checksForEmptyDescriptionTodo(input)) {
            taskList.addTask(input.substring(Todo.TODO_KW.length()), TaskType.TODO);
        }
    }


    private static void checksValidDeadline(String input) {
        if (checksForEmptyDescriptionDeadline(input)
                && input.contains(Deadline.BY_KW)
                && checksForEmptyByDeadline(input)) {
            taskList.addTask(input.substring(Deadline.DEADLINE_KW.length()), TaskType.DEADLINE);
        }
    }


    private static void checksValidEvent(String input) {
        if (checksForEmptyDescriptionEvent(input)
                && input.contains(Event.AT_KW)
                && checksForEmptyAtEvent(input)) {
            taskList.addTask(input.substring(Event.EVENT_KW.length()), TaskType.EVENT);
        }
    }


    private static boolean checksForEmptyDescriptionTodo(String input) {
        return !input.trim().equals(Todo.TODO_KW.trim());
    }


    private static boolean checksForEmptyDescriptionDeadline(String input) {
        return !input.substring(Deadline.DEADLINE_KW.length()).trim().startsWith(Deadline.BY_KW.trim());
    }


    private static boolean checksForEmptyByDeadline(String input) {
        return !input.trim().endsWith(Deadline.BY_KW.trim());
    }


    private static boolean checksForEmptyDescriptionEvent(String input) {
        return !input.substring(Event.EVENT_KW.length()).trim().startsWith(Event.AT_KW.trim());
    }


    private static boolean checksForEmptyAtEvent(String input) {
        return !input.trim().endsWith(Event.AT_KW.trim());
    }


    public static boolean isInteger(String string) {
        for (char digit : string.toCharArray()) {
            if (!Character.isDigit(digit)) {
                return false;
            }
        }
        return true;
    }
}
