import java.util.ArrayList;
import java.util.List;

// Интерфейс для оповещения наблюдателей
interface Observer {
    void update(String message);
}

// Класс для группы
class Group {
    private List<Observer> observers = new ArrayList<>();
    private String name;

    public Group(String name) {
        this.name = name;
    }

    public void addSubscriber(Observer observer) {
        observers.add(observer);
    }

    public void removeSubscriber(Observer observer) {
        observers.remove(observer);
    }

    // Оповещаем всех наблюдателей о новом сообщении
    public void notifyAllSubscribers(String message) {
        for (Observer observer : observers) {
            observer.update(name + ": " + message);
        }
    }
}

// Класс для пользователя
class User implements Observer {
    private String name;
    private List<Group> groups = new ArrayList<>();

    public User(String name) {
        this.name = name;
    }

    // Подписываемся на группу
    public void subscribe(Group group) {
        groups.add(group);
        group.addSubscriber(this);
    }

    // Отписываемся от группы
    public void unsubscribe(Group group) {
        groups.remove(group);
        group.removeSubscriber(this);
    }

    // Получаем сообщение от группы
    @Override
    public void update(String message) {
        System.out.println(name + " получил сообщение: " + message);
    }

    // Получаем все сообщения от всех групп, на которые мы подписаны
    public void getAllMessages() {
        for (Group group : groups) {
            System.out.println(name + " получил все сообщения от группы " + group);
            group.notifyAllSubscribers("Новое сообщение в группе!");
        }
    }
}

// Тестовый класс
public class TestObserverPattern {
    public static void main(String[] args) {
        // Создаем пользователей и группы
        User user1 = new User("Костя");
        User user2 = new User("Вава");

        Group group1 = new Group("ВКонтакте");
        Group group2 = new Group("Одноклассники");
        Group group3 = new Group("Discord");
        Group group4 = new Group("Telegram");

        // Пользователь 1 подписывается на три группы
        user1.subscribe(group1);
        user1.subscribe(group2);
        user1.subscribe(group3);

        // Пользователь 2 подписывается на четыре группы
        user2.subscribe(group1);
        user2.subscribe(group2);
        user2.subscribe(group3);
        user2.subscribe(group4);

        // Получаем все сообщения для каждого пользователя
        user1.getAllMessages();
        user2.getAllMessages();
    }
}
