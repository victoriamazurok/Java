import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class BasicDataOperationUsingList {
    private Byte byteValueToSearch;
    private Byte[] byteArray;
    private List<Byte> byteList;

    public BasicDataOperationUsingList(Byte byteValueToSearch, Byte[] byteArray) {
        this.byteValueToSearch = byteValueToSearch;
        this.byteArray = byteArray;
        this.byteList = new ArrayList<>(Arrays.asList(byteArray));
    }

    public void executeDataOperations() {
        findInList();
        locateMinMaxInList();

        sortList();

        findInList();
        locateMinMaxInList();

        findInArray();
        locateMinMaxInArray();

        performArraySorting();

        findInArray();
        locateMinMaxInArray();

        DataFileHandler.writeArrayToFile(byteArray, BasicDataOperation.PATH_TO_DATA_FILE + ".sorted");
    }

    public void performArraySorting() {
        long timeStart = System.nanoTime();

        byteArray = Arrays.stream(byteArray)
                          .map(Byte::valueOf)  // Converts primitive byte to Byte object
                          .sorted()
                          .toArray(Byte[]::new);

        PerformanceTracker.displayOperationTime(timeStart, "упорядкування масиву byte");
    }

    public void findInArray() {
        long timeStart = System.nanoTime();

        int position = Arrays.stream(byteArray)
                             .map(Byte::valueOf)  // Converts primitive byte to Byte object
                             .collect(Collectors.toList())
                             .indexOf(byteValueToSearch);

        PerformanceTracker.displayOperationTime(timeStart, "пошук елемента в масиві byte");

        if (position >= 0) {
            System.out.println("Елемент '" + byteValueToSearch + "' знайдено в масиві за позицією: " + position);
        } else {
            System.out.println("Елемент '" + byteValueToSearch + "' відсутній в масиві.");
        }
    }

    public void locateMinMaxInArray() {
        if (byteArray == null || byteArray.length == 0) {
            System.out.println("Масив є пустим або не ініціалізованим.");
            return;
        }

        long timeStart = System.nanoTime();

        Byte minValue = Arrays.stream(byteArray)
                              .map(Byte::valueOf)  // Converts primitive byte to Byte object
                              .min(Byte::compareTo)
                              .orElse(null);

        Byte maxValue = Arrays.stream(byteArray)
                              .map(Byte::valueOf)  // Converts primitive byte to Byte object
                              .max(Byte::compareTo)
                              .orElse(null);

        PerformanceTracker.displayOperationTime(timeStart, "визначення мiнiмальної i максимальної дати в масиві byte");

        System.out.println("Найменше значення в масиві: " + minValue);
        System.out.println("Найбільше значення в масиві: " + maxValue);
    }

    public void findInList() {
        long timeStart = System.nanoTime();

        int position = byteList.stream()
                               .map(Byte::valueOf)  // Converts primitive byte to Byte object
                               .collect(Collectors.toList())
                               .indexOf(byteValueToSearch);

        PerformanceTracker.displayOperationTime(timeStart, "пошук елемента в List byte");

        if (position >= 0) {
            System.out.println("Елемент '" + byteValueToSearch + "' знайдено в ArrayList за позицією: " + position);
        } else {
            System.out.println("Елемент '" + byteValueToSearch + "' відсутній в ArrayList.");
        }
    }

    public void locateMinMaxInList() {
        if (byteList == null || byteList.isEmpty()) {
            System.out.println("Колекція ArrayList є пустою або не ініціалізованою.");
            return;
        }

        long timeStart = System.nanoTime();

        Byte minValue = byteList.stream()
                               .map(Byte::valueOf)  // Converts primitive byte to Byte object
                               .min(Byte::compareTo)
                               .orElse(null);

        Byte maxValue = byteList.stream()
                               .map(Byte::valueOf)  // Converts primitive byte to Byte object
                               .max(Byte::compareTo)
                               .orElse(null);

        PerformanceTracker.displayOperationTime(timeStart, "визначення мiнiмальної i максимальної дати в List byte");

        System.out.println("Найменше значення в List: " + minValue);
        System.out.println("Найбільше значення в List: " + maxValue);
    }

    public void sortList() {
        long timeStart = System.nanoTime();

        byteList = byteList.stream()
                           .map(Byte::valueOf)  // Converts primitive byte to Byte object
                           .sorted()
                           .collect(Collectors.toList());

        PerformanceTracker.displayOperationTime(timeStart, "упорядкування ArrayList byte");
    }
}
