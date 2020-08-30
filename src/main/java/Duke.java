import java.util.Scanner;

public class Duke {

    private static TaskList taskList;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        taskList = new TaskList();

        UserInterface.showWelcomeScreen();

        while (true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else {
                checkInput(input);
            }
        }

        UserInterface.showExitScreen();
    }

    public static void checkInput(String input) {
        if (input.equals("list")) {
            taskList.printTaskList();
        } else if (input.startsWith("done ")) {
            markTaskDone(input);
        } else if (taskList.getTaskCount() < TaskList.MAXSIZE) {
            checkTaskType(input);
        }
    }

    private static void markTaskDone(String input) {
        String integerCheck = input.substring("done ".length()).trim();
        if (isInteger(integerCheck)) {
            int taskOrder = Integer.parseInt(integerCheck);
            // Checks if number after done is valid
            if (taskOrder <= taskList.getTaskCount() && taskOrder > 0) {
                taskList.markTaskDone(Integer.parseInt(integerCheck));
            }
        }
    }

    private static void checkTaskType(String input) {
        if (input.startsWith("todo ")) {
            checkValidTodo(input);
        } else if (input.startsWith("deadline ")) {
            checkValidDeadline(input);
        } else if (input.startsWith("event ")) {
            checkValidEvent(input);
        }
    }

    private static void checkValidTodo(String input) {
        if (noEmptyDescriptionTodo(input)) {
            taskList.addTask(input.substring("todo ".length()), TaskType.TODO);
        }
    }

    private static void checkValidDeadline(String input) {
        if (noEmptyDescriptionDeadline(input)
                && input.contains(Deadline.BY_KEYWORD)
                && noEmptyByDeadline(input)) {
            taskList.addTask(input.substring("deadline ".length()), TaskType.DEADLINE);
        }
    }

    private static void checkValidEvent(String input) {
        if (noEmptyDescriptionEvent(input)
                && input.contains(Event.AT_KEYWORD)
                && noEmptyAtEvent(input)) {
            taskList.addTask(input.substring("event ".length()), TaskType.EVENT);
        }
    }

    private static boolean noEmptyDescriptionTodo(String input) {
        return !input.trim().equals("todo");
    }

    private static boolean noEmptyDescriptionDeadline(String input) {
        return !input.substring("deadline".length()).trim().startsWith(Deadline.BY_KEYWORD.trim());
    }

    private static boolean noEmptyByDeadline(String input) {
        return !input.trim().endsWith(Deadline.BY_KEYWORD.trim());
    }

    private static boolean noEmptyDescriptionEvent(String input) {
        return !input.substring("event".length()).trim().startsWith(Event.AT_KEYWORD.trim());
    }

    private static boolean noEmptyAtEvent(String input) {
        return !input.trim().endsWith(Event.AT_KEYWORD.trim());
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
