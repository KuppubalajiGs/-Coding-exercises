public class NewsSubscriber {
    private final String name;
    public NewsSubscriber(String name) { this.name = name; }
    public void update(String news) {
        System.out.println("[" + name + "] Received news: " + news);
    }
}