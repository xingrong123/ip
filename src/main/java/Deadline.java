public class Deadline extends Task{
    public static final String BY_KW = " /by ";
    public static final String DEADLINE_KW = "deadline ";
    private final String deadline;

    public Deadline(String description, String deadline) {
        super(description, TaskType.DEADLINE);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
