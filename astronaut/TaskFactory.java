import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class TaskFactory {
    public static Task create(String description, String startStr, String endStr, String priorityStr) {
        try {
            LocalTime start = LocalTime.parse(startStr);
            LocalTime end = LocalTime.parse(endStr);
            if (!end.isAfter(start)) throw new IllegalArgumentException("End must be after start.");
            Task.Priority p;
            switch (priorityStr.toLowerCase()) {
                case "high": p = Task.Priority.HIGH; break;
                case "medium": p = Task.Priority.MEDIUM; break;
                case "low": p = Task.Priority.LOW; break;
                default: throw new IllegalArgumentException("Invalid priority: " + priorityStr);
            }
            return new Task(description, start, end, p);
        } catch (DateTimeParseException dte) {
            throw new IllegalArgumentException("Invalid time format. Use HH:mm (e.g., 09:30).");
        }
    }
}