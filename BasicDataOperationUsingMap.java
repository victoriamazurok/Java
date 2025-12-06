import java.util.*;
import java.util.stream.Collectors;

public class BasicDataOperationUsingMap {
    private final Hedgehog KEY_TO_SEARCH_AND_DELETE = new Hedgehog("Чіп", "дратівливий");
    private final String VALUE_TO_SEARCH_AND_DELETE = "Тетяна";
    private final String VALUE_TO_ADD = "Юрій";
    
    private static final String SEPARATOR = "\n" + "=".repeat(80) + "\n";

    public void executeDataOperations() {
        System.out.println(SEPARATOR);
        System.out.println("🗂️ ПОРІВНЯННЯ ПРОДУКТИВНОСТІ HashMap VS TreeMap");
        System.out.println(SEPARATOR);
        
        processMapType("HashMap", new HashMap<>());
        System.out.println("\n" + "~".repeat(80) + "\n");
        
        processMapType("TreeMap", new TreeMap<>());
        
        System.out.println(SEPARATOR);
        System.out.println("✅ АНАЛІЗ ЗАВЕРШЕНО");
        System.out.println(SEPARATOR);
    }

    private void processMapType(String mapTypeName, Map<Hedgehog, String> map) {
        System.out.println("📊 ОБРОБКА ДАНИХ З ВИКОРИСТАННЯМ " + mapTypeName.toUpperCase());
        System.out.println("-".repeat(60));
        
        initializeMap(map);
        
        findByKeyInMap(map);
        findByValueInMap(map);
        printMapBefore(map);
        sortMap(map);
        printMapAfter(map);
        addEntryToMap(map);
        removeByKeyFromMap(map);
        removeByValueFromMap(map);
    }

    private void initializeMap(Map<Hedgehog, String> map) {
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
    }

    void findByKeyInMap(Map<Hedgehog, String> map) {
        long timeStart = System.nanoTime();
        boolean found = map.containsKey(KEY_TO_SEARCH_AND_DELETE);
        PerformanceTracker.displayOperationTime(timeStart, "пошук за ключем");
        if (found) {
            String value = map.get(KEY_TO_SEARCH_AND_DELETE);
            System.out.println("✓ Елемент з ключем '" + KEY_TO_SEARCH_AND_DELETE + "' знайдено. Власник: " + value);
        } else {
            System.out.println("✗ Елемент з ключем '" + KEY_TO_SEARCH_AND_DELETE + "' відсутній.");
        }
    }

    void findByValueInMap(Map<Hedgehog, String> map) {
        long timeStart = System.nanoTime();
        
        List<Hedgehog> keysToRemove = map.entrySet().stream()
                .filter(entry -> entry.getValue() != null && entry.getValue().equals(VALUE_TO_SEARCH_AND_DELETE))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        
        PerformanceTracker.displayOperationTime(timeStart, "пошук за значенням");
        
        if (!keysToRemove.isEmpty()) {
            System.out.println("✓ Власник '" + VALUE_TO_SEARCH_AND_DELETE + "' знайдено. Кількість: " + keysToRemove.size());
        } else {
            System.out.println("✗ Власник '" + VALUE_TO_SEARCH_AND_DELETE + "' не знайдено.");
        }
    }

    void printMapBefore(Map<Hedgehog, String> map) {
        System.out.println("\n=== Виведення Map ДО сортування ===");
        long timeStart = System.nanoTime();
        map.entrySet().forEach(entry ->
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue())
        );
        PerformanceTracker.displayOperationTime(timeStart, "виведення Map до сортування");
    }

    void sortMap(Map<Hedgehog, String> map) {
        long timeStart = System.nanoTime();
        Map<Hedgehog, String> sortedMap = map.entrySet().stream()
                .sorted((e1, e2) -> {
                    int nickCompare = e2.getKey().getNickname().compareTo(e1.getKey().getNickname());
                    if (nickCompare != 0) return nickCompare;
                    return e1.getKey().getTemperament().compareTo(e2.getKey().getTemperament());
                })
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
        map.clear();
        map.putAll(sortedMap);
        PerformanceTracker.displayOperationTime(timeStart, "сортування Map (кличка зменш., темперамент зрост.)");
    }

    void printMapAfter(Map<Hedgehog, String> map) {
        System.out.println("\n=== Виведення Map ПІСЛЯ сортування ===");
        long timeStart = System.nanoTime();
        map.entrySet().forEach(entry ->
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue())
        );
        PerformanceTracker.displayOperationTime(timeStart, "виведення Map після сортування");
    }

    void addEntryToMap(Map<Hedgehog, String> map) {
        long timeStart = System.nanoTime();
        map.put(new Hedgehog("Стріла", "цікавий"), VALUE_TO_ADD);
        PerformanceTracker.displayOperationTime(timeStart, "додавання пари до Map");
        System.out.println("✓ Додано нову пару: Hedgehog{nickname='Стріла', temperament='цікавий'} -> " + VALUE_TO_ADD);
    }

    void removeByKeyFromMap(Map<Hedgehog, String> map) {
        long timeStart = System.nanoTime();
        String removed = map.remove(KEY_TO_SEARCH_AND_DELETE);
        PerformanceTracker.displayOperationTime(timeStart, "видалення за ключем");
        if (removed != null) {
            System.out.println("✓ Видалено пару з ключем: " + KEY_TO_SEARCH_AND_DELETE + " (значення: " + removed + ")");
        }
    }

    void removeByValueFromMap(Map<Hedgehog, String> map) {
        long timeStart = System.nanoTime();
        List<Hedgehog> keysToRemove = map.entrySet().stream()
                .filter(entry -> entry.getValue() != null && entry.getValue().equals(VALUE_TO_SEARCH_AND_DELETE))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        
        keysToRemove.forEach(map::remove);
        PerformanceTracker.displayOperationTime(timeStart, "видалення за значенням");
        
        if (!keysToRemove.isEmpty()) {
            System.out.println("✓ Видалено " + keysToRemove.size() + " пари з значенням: " + VALUE_TO_SEARCH_AND_DELETE);
        }
    }

    public static void main(String[] args) {
        BasicDataOperationUsingMap analyzer = new BasicDataOperationUsingMap();
        analyzer.executeDataOperations();
    }
}
