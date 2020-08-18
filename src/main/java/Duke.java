import java.util.Scanner;

public class Duke {

    private static final String LINE = "\t____________________________________________________________" + System.lineSeparator();

    public static void echo(String input) {
        System.out.print(LINE + "\t" + input + System.lineSeparator() + LINE);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";

        System.out.print(LINE +
                "\tHello! I'm Duke\n" +
                "\tWhat can I do for you?\n" +
                LINE);

        while(true) {
            input = scanner.nextLine();
            if(input.toLowerCase().equals("bye"))
                break;
            echo(input);
        }


        System.out.println(LINE +
                "\tBye. Hope to see you again soon!\n" +
                LINE);
    }
}
