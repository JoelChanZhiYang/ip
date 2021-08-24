import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Task {
    private String action;
    private boolean isCompleted;

    public Task(String action) {
        this.action = action;
        this.isCompleted = false;
    }

    public boolean isComplete() {
        return this.isCompleted;
    }

    public void complete() {
        this.isCompleted = true;
    }

    private String getStatusIcon() {
        return isCompleted ? "X" : " ";
    }

    public static LocalDateTime parseDate(String deadline) throws DateTimeParseException{
        LocalDateTime date = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        return date;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.action);
    }
}
