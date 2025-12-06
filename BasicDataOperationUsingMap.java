import java.util.*;

public class BasicDataOperationUsingMap {
    private final Hedgehog KEY_TO_SEARCH_AND_DELETE = new Hedgehog("Чіп", "дратівливий");
    private final String VALUE_TO_SEARCH_AND_DELETE = "Тетяна";
    private final Hedgehog KEY_TO_ADD = new Hedgehog("Стріла", "цікавий");
    private final String VALUE_TO_ADD = "Юрій";

    private Map<Hedgehog, String> map;

    public BasicDataOperationUsingMap(Map<Hedgehog, String> map) {
        this.map = map;
    }

    public void executeDataOperations() {
        // Пошук за ключем
        System.out.println("========= Пошук за ключем =========");
        findByKeyInMap();
        
        // Пошук за значенням (пошук у всіх парах)
        System.out.println("========= Пошук за значенням =========");
        findByValueInMap();
        
        // Виведення до сортування
        System.out.println("========= Виведення колекції до сортування =========");
        printMap();

        // Сортування за ключами
        System.out.println("========= Сортування за ключами =========");
        sortByKeyInMap();
        printMap();

        // Додавання пари ключ/значення
        System.out.println("========= Додавання пари ключ/значення =========");
        addEntryToMap();

        // Видалення за ключем
        System.out.println("========= Видалення за ключем =========");
        removeByKeyFromMap();

        // Видалення за значенням
        System.out.println("========= Видалення за значенням =========");
        removeByValueFromMap();
    }

    // Пошук за ключем
    private void findByKeyInMap() {
        if (map.containsKey(KEY_TO_SEARCH_AND_DELETE)) {
            System.out.println("Знайдено пару: " + KEY_TO_SEARCH_AND_DELETE + " -> " + map.get(KEY_TO_SEARCH_AND_DELETE));
        } else {
            System.out.println("Пару з ключем " + KEY_TO_SEARCH_AND_DELETE + " не знайдено.");
        }
    }

    // Пошук за значенням
    private void findByValueInMap() {
        for (Map.Entry<Hedgehog, String> entry : map.entrySet()) {
            if (entry.getValue().equals(VALUE_TO_SEARCH_AND_DELETE)) {
                System.out.println("Знайдено пару: " + entry.getKey() + " -> " + entry.getValue());
            }
        }
    }

    // Виведення колекції
    private void printMap() {
        System.out.println("=== Вміст колекції ===");
        for (Map.Entry<Hedgehog, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    // Сортування за ключами
    private void sortByKeyInMap() {
        Map<Hedgehog, String> sortedMap = new TreeMap<>(map);
        map = sortedMap;
    }

    // Додавання пари ключ/значення
    private void addEntryToMap() {
        map.put(KEY_TO_ADD, VALUE_TO_ADD);
        System.out.println("Додано нову пару: " + KEY_TO_ADD + " -> " + VALUE_TO_ADD);
    }

    // Видалення за ключем
    private void removeByKeyFromMap() {
        map.remove(KEY_TO_SEARCH_AND_DELETE);
        System.out.println("Видалено пару з ключем: " + KEY_TO_SEARCH_AND_DELETE);
    }

    // Видалення за значенням
    private void removeByValueFromMap() {
        map.entrySet().removeIf(entry -> entry.getValue().equals(VALUE_TO_SEARCH_AND_DELETE));
        System.out.println("Видалено пари з значенням: " + VALUE_TO_SEARCH_AND_DELETE);
    }

    // Основний метод для запуску
    public static void main(String[] args) {
        Map<Hedgehog, String> map = new HashMap<>();
        map.put(new Hedgehog("Шипик", "активний"), "Людмила");
        map.put(new Hedgehog("Чіп", "дратівливий"), "Мирослав");
        map.put(new Hedgehog("Колючка", "лагідний"), "Орест");
        map.put(new Hedgehog("Гострий", "спокійний"), "Тетяна");
        map.put(new Hedgehog("Чіп", "грайливий"), "Злата");
        map.put(new Hedgehog("Бодя", "активний"), "Володимир");
        map.put(new Hedgehog("Айстра", "цікавий"), "Лариса");
        map.put(new Hedgehog("Гострий", "спокійний"), "Тетяна");
        map.put(new Hedgehog("Нічник", "лагідний"), "Лариса");
        map.put(new Hedgehog("Піксель", "грайливий"), "Христина");

        BasicDataOperationUsingMap operations = new BasicDataOperationUsingMap(map);
        operations.executeDataOperations();
    }
}
