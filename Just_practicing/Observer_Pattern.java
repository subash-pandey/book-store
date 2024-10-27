import java.util.ArrayList;
import java.util.List;

interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

interface Observer {
    void update(String data);
}

class NewsPublisher implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String latestNews;

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(latestNews);
        }
    }

    public void setLatestNews(String news) {
        this.latestNews = news;
        notifyObservers();
    }
}

class NewsSubscriber implements Observer {
    @Override
    public void update(String news) {
        System.out.println("Received News: " + news);
    }
}

public class Observer_Pattern {
    public static void main(String[] args) {
        NewsPublisher publisher = new NewsPublisher();
        NewsSubscriber subscriber1 = new NewsSubscriber();
        NewsSubscriber subscriber2 = new NewsSubscriber();

        publisher.registerObserver(subscriber1);
        publisher.registerObserver(subscriber2);

        publisher.setLatestNews("Breaking News!");
    }
}