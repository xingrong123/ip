package duke.command;

import duke.DateTime;
import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.DukeExceptionType;
import duke.task.Schedulable;
import duke.task.Task;
import duke.task.TaskList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class DateCommand extends Command {
    public static final String DATE_KW = "date";

    private LocalDate date;
    private LocalTime time;

    public DateCommand(String command) throws DukeException {
        String details = command.substring(DATE_KW.length());
        if (!details.startsWith(" ")) {
            throw new DukeException(DukeExceptionType.UNKNOWN_INPUT);
        }
        try {
            setDateTime(details);
        } catch (DateTimeParseException e) {
            throw new DukeException(DukeExceptionType.INVALID_DATE_TIME);
        }
    }

    private void setDateTime(String details) {
        if (details.trim().contains(" ")) {
            String[] dateTimeString = details.trim().split(" ", 2);
            date = DateTime.getDate(dateTimeString[0]);
            time = DateTime.getTime(dateTimeString[1]);
        } else {
            date = DateTime.getDate(details.trim());
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String dateString = getDate();
        ArrayList<String> tasksWithDate = getTasksWithDate(taskList);
        String message = getMessage(tasksWithDate, dateString);
        ui.showTaskDate(message);
    }

    private String getDate() {
        String dateString;
        if (time != null) {
            dateString = DateTime.getDateString(date) + " " + DateTime.getTimeString(time);
        } else {
            dateString = DateTime.getDateString(date);
        }
        return dateString;
    }

    private ArrayList<String> getTasksWithDate(TaskList taskList) {
        ArrayList<String> arrayString = new ArrayList<>();
        for (int i = 0; i < taskList.getTaskList().size(); i++) {
            Task task = taskList.getTaskList().get(i);
             if (checkForDateInTask(task)) {
                 arrayString.add(task.toString());
             }
        }
        return arrayString;
    }

    private boolean checkForDateInTask(Task task) {
        boolean hasMatchingDate = false;
        if (task instanceof Schedulable) {
            Schedulable scheduledTask = (Schedulable) task;
            hasMatchingDate = checkMatchingDate(scheduledTask);
        }
        return hasMatchingDate;
    }

    private boolean checkMatchingDate(Schedulable scheduledTask) {
        boolean hasMatchingDate = false;
        if (time != null) {
            if (scheduledTask.hasDate() && scheduledTask.hasTime()) {
                if ((date.compareTo(scheduledTask.getDate()) == 0) && (time.compareTo(scheduledTask.getTime()) == 0)) {
                    hasMatchingDate = true;
                }
            }
        } else {
            if (scheduledTask.hasDate()) {
                if (date.compareTo(scheduledTask.getDate()) == 0) {
                    hasMatchingDate = true;
                }
            }
        }
        return hasMatchingDate;
    }

    private String getMessage(ArrayList<String> arrayString, String dateString) {
        String message = "\tHere are the deadlines/events occurring on " + dateString + " in your list:\n";
        for (String task : arrayString) {
            message += ("\t" + task + "\n");
        }
        return message;
    }
}
