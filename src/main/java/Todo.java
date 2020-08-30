public class Todo extends Task{
    public static final String TODO_KW = "todo ";

    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
