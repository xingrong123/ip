public class Event extends Task{
    public static final String AT_KW = " /at ";
    public static final String EVENT_KW = "event ";
    private final String at;

    public Event(String description, String at) {
        super(description, TaskType.EVENT);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
