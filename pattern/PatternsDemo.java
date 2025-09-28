public class PatternsDemo {
    public static void main(String[] args) {
        System.out.println("=== Observer Demo ===");
        NewsPublisher p = new NewsPublisher();
        NewsSubscriber a = new NewsSubscriber("Alice");
        NewsSubscriber b = new NewsSubscriber("Bob");
        p.subscribe(a); p.subscribe(b);
        p.publish("New mission scheduled at 07:00 UTC!");

        System.out.println("\n=== Strategy Demo ===");
        StrategyExample.demo();

        System.out.println("\n=== Singleton Demo ===");
        System.out.println("App name: " + AppConfig.getInstance().getAppName());

        System.out.println("\n=== Factory Demo ===");
        Animal dog = AnimalFactory.create("dog");
        Animal cat = AnimalFactory.create("cat");
        dog.speak(); cat.speak();

        System.out.println("\n=== Adapter Demo ===");
        EnemyRobot robot = new EnemyRobot();
        EnemyAttacker adapter = new EnemyRobotAdapter(robot);
        adapter.assignDriver("John");
        adapter.fireWeapon();

        System.out.println("\n=== Composite Demo ===");
        FolderComposite root = new FolderComposite("root");
        root.add(new FileLeaf("readme.txt", 1000));
        FolderComposite images = new FolderComposite("images");
        images.add(new FileLeaf("img1.png", 2048));
        root.add(images);
        root.print("");
        System.out.println("Total size: " + root.getSize());
    }
}