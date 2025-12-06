import java.util.*;
import java.util.stream.Collectors;

public class BasicDataOperationUsingMap {
    private final Hedgehog KEY_TO_SEARCH_AND_DELETE = new Hedgehog("Чіп", "дратівливий");
    private final String VALUE_TO_SEARCH_AND_DELETE = "Тетяна";
    private final String VALUE_TO_ADD = "Юрій";
    
    private Map<Hedgehog, String> hedgehogMap;

    public BasicDataOperationUsingMap(Map<Hedgehog, String> hedgehogMap) {
        this.hedgehogMap = hedgehogMap;
    }

    public void executeDataOperations() {
        findByKeyInMap();
        findByValueInMap();
        printMap();
        sortMap();
        printMap();
        addEntryToMap();
        removeByKeyFromMap();
        removeByValueFromMap();
    }

    void findByKeyInMap() {
        long timeStart = System.nanoTime();
        boolean found = hedgehogMap.containsKey(KEY_TO_SEARCH_AND_DELETE);
        PerformanceTracker.displayOperationTime(timeStart, "пошук за ключем в Map");
        if (found) {
            String value = hedgehogMap.get(KEY_TO_SEARCH_AND_DELETE);
            System.out.println("Елемент з ключем '" + KEY_TO_SEARCH_AND_DELETE + "' знайдено. Власник: " + value);
        } else {
            System.out.println("Елемент з ключем '" + KEY_TO_SEARCH_AND_DELETE + "' відсутній в Map.");
        }
    }

    void findByValueInMap() {
        long timeStart = System.nanoTime();
        boolean found = hedgehogMap.containsValue(VALUE_TO_SEARCH_AND_DELETE);
        PerformanceTracker.displayOperationTime(timeStart, "пошук за значенням в Map");
        if (found) {
            System.out.println("Власника '" + VALUE_TO_SEARCH_AND_DELETE + "' знайдено.");
        } else {
            System.out.println("Власник '" + VALUE_TO_SEARCH_AND_DELETE + "' відсутній в Map.");
        }
    }

    void printMap() {
        System.out.println("=== Виведення Map ===");
        hedgehogMap.entrySet().forEach(entry ->
            System.out.println(entry.getKey() + " -> " + entry.getValue())
        );
    }

    void sortMap() {
        long timeStart = System.nanoTime();
        hedgehogMap = hedgehogMap.entrySet().stream()
                                .sorted(Map.Entry.comparingByKey())
                                .collect(Collectors.toMap(
                                        Map.Entry::getKey,
                                        Map.Entry::getValue,
                                        (e1, e2) -> e1,
                                        LinkedHashMap::new
                                ));
        PerformanceTracker.displayOperationTime(timeStart, "сортування Map за ключами");
    }

    void addEntryToMap() {
        long timeStart = System.nanoTime();
        hedgehogMap.put(new Hedgehog("Стріла", "цікавий"), VALUE_TO_ADD);
        PerformanceTracker.displayOperationTime(timeStart, "додавання пари до Map");
        System.out.println("Додано нову пару: " + "Hedgehog{nickname='Стріла', temperament='цікавий'} -> Юрій");
    }

    void removeByKeyFromMap() {
        long timeStart = System.nanoTime();
        hedgehogMap.remove(KEY_TO_SEARCH_AND_DELETE);
        PerformanceTracker.displayOperationTime(timeStart, "видалення за ключем з Map");
        System.out.println("Видалено пару з ключем: " + KEY_TO_SEARCH_AND_DELETE);
    }

    void removeByValueFromMap() {
        long timeStart = System.nanoTime();
        List<Hedgehog> keysToRemove = hedgehogMap.entrySet().stream()
                                                 .filter(entry -> entry.getValue() != null && entry.getValue().equals(VALUE_TO_SEARCH_AND_DELETE))
                                                 .map(Map.Entry::getKey)
                                                 .collect(Collectors.toList());
        keysToRemove.forEach(hedgehogMap::remove);
        PerformanceTracker.displayOperationTime(timeStart, "видалення за значенням з Map");
        System.out.println("Видалено пари з значенням: " + VALUE_TO_SEARCH_AND_DELETE);
    }

    public static void main(String[] args) {
        Map<Hedgehog, String> hedgehogMap = new HashMap<>();
        hedgehogMap.put(new Hedgehog("Шипик", "активний"), "Лариса");
        hedgehogMap.put(new Hedgehog("Чіп", "дратівливий"), "Мирослав");
        hedgehogMap.put(new Hedgehog("Колючка", "лагідний"), "Орест");
        hedgehogMap.put(new Hedgehog("Гострий", "спокійний"), "Тетяна");
        hedgehogMap.put(new Hedgehog("Чіп", "грайливий"), "Злата");

        BasicDataOperationUsingMap operations = new BasicDataOperationUsingMap(hedgehogMap);
        operations.executeDataOperations();
    }
}
