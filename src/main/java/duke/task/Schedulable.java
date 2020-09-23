package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;

public interface Schedulable {

    boolean hasDate();
    boolean hasTime();
    LocalDate getDate();
    LocalTime getTime();
}
