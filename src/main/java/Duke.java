import java.util.Scanner;

public class Duke {

    private static final String LINE = "\t____________________________________________________________" + System.lineSeparator();
    private static String[] list = new String[100];
    private static int listSize = 0;

    public static void printList() {
        System.out.print(LINE);
        for(int i = 0; i < listSize; i++) {
            System.out.printf("\t%d. %s\n", i + 1, list[i]);
        }
        System.out.println(LINE);
    }

    public static void addList(String input) {
        System.out.println(LINE +
                "\tadded: "+ input + System.lineSeparator() +
                LINE);
        list[listSize] = input;
        listSize++;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";

        System.out.println(LINE +
                "\tHello! I'm Duke\n" +
                "\tWhat can I do for you?\n" +
                LINE);

        while(true) {
            input = scanner.nextLine();
            if(input.toLowerCase().equals("bye"))
                break;
            else if(input.toLowerCase().equals("list"))
                printList();
            else if(listSize < 100)
                addList(input);
        }


        System.out.println(LINE +
                "\tBye. Hope to see you again soon!\n" +
                LINE);
    }
}
