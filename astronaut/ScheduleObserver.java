public interface ScheduleObserver {
    void onConflictDetected(Task existing, Task attempted);
    void onTaskAdded(Task t);
    void onTaskRemoved(Task t);
}