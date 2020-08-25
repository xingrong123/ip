import java.util.Scanner;

public class Duke {

    private static final String LINE = "\t____________________________________________________________"
            + System.lineSeparator();
    private static TaskList taskList;

    public static void printList() {
        System.out.print(LINE
                + "\tHere are the tasks in your list:\n");
        taskList.printTaskList();
        System.out.println(LINE);
    }

    public static void addList(String input) {
        System.out.println(LINE
                + "\tadded: "+ input + System.lineSeparator()
                + LINE);
        taskList.addTask(input);
    }

    public static void markDone(int listOrder) {
        System.out.println(LINE
                + "\tNice! I've marked this task as done:");
        taskList.markTaskDone(listOrder);
        System.out.println(LINE);
    }

    public static boolean isInteger(String string) {
        for(char digit : string.toCharArray()) {
            if(!Character.isDigit(digit)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        taskList = new TaskList();

        System.out.println(LINE
                + "\tHello! I'm Duke\n"
                + "\tWhat can I do for you?\n"
                + LINE);

        while(true) {
            input = scanner.nextLine();
            if (input.trim().toLowerCase().equals("bye")) {
                break;
            } else if (input.trim().toLowerCase().equals("list")) {
                printList();
            } else if (input.trim().toLowerCase().startsWith("done ")) {
                String inputCheck = input.trim().substring(5).trim();

                if (isInteger(inputCheck)) {
                    int number = Integer.parseInt(inputCheck);
                    // Checks if number after done is valid
                    // inputs not recognized as valid instructions are added as tasks
                    if (number <= taskList.getTaskListSize() && number > 0) {
                        markDone(Integer.parseInt(inputCheck));
                    } else {
                        addList(input);
                    }

                } else {
                    addList(input);
                }

            } else if (taskList.getTaskListSize() < 100 && !input.isBlank()) {
                addList(input);
            } else if(!input.isBlank()) {
                System.out.println("\tlist is full");
            }
        }


        System.out.println(LINE
                + "\tBye. Hope to see you again soon!\n"
                + LINE);
    }
}
