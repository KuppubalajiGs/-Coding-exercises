import java.time.LocalTime;
import java.util.UUID;

public class Task implements Comparable<Task> {
    private final String id;
    private String description;
    private LocalTime start;
    private LocalTime end;
    private Priority priority;
    private boolean completed = false;

    public enum Priority { LOW, MEDIUM, HIGH }

    public Task(String description, LocalTime start, LocalTime end, Priority p) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.start = start;
        this.end = end;
        this.priority = p;
    }

    public String getId() { return id; }
    public String getDescription() { return description; }
    public LocalTime getStart() { return start; }
    public LocalTime getEnd() { return end; }
    public Priority getPriority() { return priority; }
    public boolean isCompleted() { return completed; }

    public void setDescription(String d) { this.description = d; }
    public void setStart(LocalTime s) { this.start = s; }
    public void setEnd(LocalTime e) { this.end = e; }
    public void setPriority(Priority p) { this.priority = p; }
    public void setCompleted(boolean c) { this.completed = c; }

    @Override
    public int compareTo(Task o) {
        return this.start.compareTo(o.start);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s : %s [%s]%s", id.substring(0,8),
            start, end, description, priority, completed ? " (Done)":"");
    }
}