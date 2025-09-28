import java.util.ArrayList;
import java.util.List;

public class NewsPublisher {
    private final List<NewsSubscriber> subscribers = new ArrayList<>();
    private String latestNews;

    public void subscribe(NewsSubscriber s) { if (!subscribers.contains(s)) subscribers.add(s); }
    public void unsubscribe(NewsSubscriber s) { subscribers.remove(s); }

    public void publish(String news) {
        latestNews = news;
        for (NewsSubscriber s : subscribers) {
            s.update(latestNews);
        }
    }
}