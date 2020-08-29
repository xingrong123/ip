public class Deadline extends Task{
    public static final String BY_KEYWORD = " /by ";
    private String deadline;

    public Deadline(String description, String deadline) {
        super(description, TaskType.DEADLINE);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
