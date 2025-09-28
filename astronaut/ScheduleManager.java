import java.time.LocalTime;
import java.util.*;
import java.util.logging.*;

public class ScheduleManager {
    private static volatile ScheduleManager instance;
    private final List<Task> tasks = new ArrayList<>();
    private final List<ScheduleObserver> observers = new ArrayList<>();
    private final Logger logger = Logger.getLogger(ScheduleManager.class.getName());

    private ScheduleManager() { logger.setLevel(Level.INFO); }

    public static ScheduleManager getInstance() {
        if (instance == null) {
            synchronized (ScheduleManager.class) {
                if (instance == null) instance = new ScheduleManager();
            }
        }
        return instance;
    }

    public void addObserver(ScheduleObserver o) { if (!observers.contains(o)) observers.add(o); }
    private void notifyConflict(Task e, Task t) { observers.forEach(o -> o.onConflictDetected(e,t)); }
    private void notifyAdded(Task t) { observers.forEach(o -> o.onTaskAdded(t)); }
    private void notifyRemoved(Task t) { observers.forEach(o -> o.onTaskRemoved(t)); }

    public synchronized boolean addTask(Task t) {
        for (Task e : tasks) if (overlaps(e,t)) { notifyConflict(e,t); return false; }
        tasks.add(t); tasks.sort(Comparator.naturalOrder());
        notifyAdded(t); return true;
    }

    public synchronized boolean removeTaskById(String id) {
        Optional<Task> f = tasks.stream().filter(x -> x.getId().startsWith(id)).findFirst();
        if (f.isPresent()) { Task t = f.get(); tasks.remove(t); notifyRemoved(t); return true; }
        return false;
    }

    public synchronized List<Task> viewTasks() { return new ArrayList<>(tasks); }

    public synchronized boolean editTask(String id, String desc, LocalTime s, LocalTime e, Task.Priority p) {
        Optional<Task> f = tasks.stream().filter(x -> x.getId().startsWith(id)).findFirst();
        if (!f.isPresent()) return false;
        Task t = f.get(); tasks.remove(t);
        Task tmp = new Task(desc,s,e,p);
        for (Task ex: tasks) if (overlaps(ex,tmp)) { tasks.add(t); notifyConflict(ex,tmp); return false; }
        t.setDescription(desc); t.setStart(s); t.setEnd(e); t.setPriority(p);
        tasks.add(t); tasks.sort(Comparator.naturalOrder()); notifyAdded(t); return true;
    }

    private boolean overlaps(Task a, Task b) {
        return a.getStart().isBefore(b.getEnd()) && b.getStart().isBefore(a.getEnd());
    }

    public synchronized List<Task> viewByPriority(Task.Priority p) {
        List<Task> r = new ArrayList<>(); for (Task t:tasks) if (t.getPriority()==p) r.add(t); return r;
    }
}