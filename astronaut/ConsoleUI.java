import java.time.LocalTime;
import java.util.*;
import java.util.logging.Logger;

public class ConsoleUI implements ScheduleObserver {
    private final ScheduleManager manager = ScheduleManager.getInstance();
    private final Logger logger = Logger.getLogger(ConsoleUI.class.getName());

    public ConsoleUI() { manager.addObserver(this); }

    public void startInteractive() {
        Scanner sc = new Scanner(System.in);
        printHelp();
        while (true) {
            System.out.print("> ");
            String line = sc.nextLine().trim();
            if (line.equalsIgnoreCase("exit")) break;
            try { handle(line); } catch (Exception ex) { System.out.println("Error: "+ex.getMessage()); }
        }
    }

    private void handle(String line) {
        if (line.isEmpty()) return;
        String[] parts = line.split("\s+",2);
        String cmd = parts[0].toLowerCase();
        String args = parts.length>1?parts[1]:"";
        switch(cmd) {
            case "help": printHelp(); break;
            case "add": handleAdd(args); break;
            case "remove": System.out.println(manager.removeTaskById(args)? "Removed":"Not found"); break;
            case "view": List<Task> ts=manager.viewTasks(); if(ts.isEmpty())System.out.println("No tasks"); else ts.forEach(System.out::println); break;
            case "viewprio": Task.Priority p=parsePriority(args); manager.viewByPriority(p).forEach(System.out::println); break;
            case "edit": handleEdit(args); break;
            case "complete": markComplete(args); break;
            default: System.out.println("Unknown. Type help.");
        }
    }

    private void handleAdd(String a) {
        String[] p=a.split("\|"); if(p.length!=4){System.out.println("Usage: add desc|HH:mm|HH:mm|prio"); return;}
        Task t=TaskFactory.create(p[0],p[1],p[2],p[3]);
        System.out.println(manager.addTask(t)? "Added":"Conflict");
    }

    private void handleEdit(String a) {
        String[] p=a.split("\|"); if(p.length!=5){System.out.println("Usage: edit id|desc|start|end|prio"); return;}
        boolean ok=manager.editTask(p[0],p[1],LocalTime.parse(p[2]),LocalTime.parse(p[3]),parsePriority(p[4]));
        System.out.println(ok? "Edited":"Conflict or not found");
    }

    private void markComplete(String id) {
        for(Task t:manager.viewTasks()) if(t.getId().startsWith(id)){ t.setCompleted(true); System.out.println("Completed"); return;}
        System.out.println("Not found");
    }

    private Task.Priority parsePriority(String s) {
        switch(s.toLowerCase()){ case "high":return Task.Priority.HIGH; case "medium":return Task.Priority.MEDIUM; default:return Task.Priority.LOW; }
    }

    private void printHelp() {
        System.out.println("Commands: add desc|07:00|08:00|High | remove id | view | viewprio low|medium|high | edit id|desc|07:00|08:00|High | complete id | help | exit");
    }

    public void onConflictDetected(Task e, Task t){ System.out.println("Conflict with existing task"); }
    public void onTaskAdded(Task t){ System.out.println("Observer: added "+t.getDescription()); }
    public void onTaskRemoved(Task t){ System.out.println("Observer: removed "+t.getDescription()); }
}