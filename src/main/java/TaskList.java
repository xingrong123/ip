import java.util.LinkedList;

public class TaskList {
    private LinkedList<Task> list;

    public TaskList() {
        list = new LinkedList<>();
    }

    public void printTaskList() {
        for(int i = 0; i < list.size(); i++) {
            System.out.printf("\t%d.[%s] %s\n", i+1, list.get(i).getStatusIcon(), list.get(i).getDescription());
        }
    }

    public void addTask(String description) {
        list.add(new Task(description));
    }

    public void markTaskDone(int listOrder) {
        Task task = list.get(listOrder - 1);
        task.markDone();
        System.out.printf("\t  [%s] %s\n", task.getStatusIcon(), task.getDescription());
    }

    public int getTaskListSize() {
        return list.size();
    }


}
