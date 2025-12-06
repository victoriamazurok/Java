import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * Клас BasicDataOperationUsingList реалізує операції з колекціями типу ArrayList для даних byte.
 */
public class BasicDataOperationUsingList {
    private Byte byteValueToSearch;
    private Byte[] byteArray;
    private List<Byte> byteList;

    BasicDataOperationUsingList(Byte byteValueToSearch, Byte[] byteArray) {
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

    void performArraySorting() {
        long timeStart = System.nanoTime();

        Arrays.sort(byteArray);

        PerformanceTracker.displayOperationTime(timeStart, "упорядкування масиву byte");
    }

    void findInArray() {
        long timeStart = System.nanoTime();

        int position = Arrays.binarySearch(this.byteArray, byteValueToSearch);

        PerformanceTracker.displayOperationTime(timeStart, "пошук елемента в масивi byte");

        if (position >= 0) {
            System.out.println("Елемент '" + byteValueToSearch + "' знайдено в масивi за позицією: " + position);
        } else {
            System.out.println("Елемент '" + byteValueToSearch + "' відсутній в масиві.");
        }
    }

    void locateMinMaxInArray() {
        if (byteArray == null || byteArray.length == 0) {
            System.out.println("Масив є пустим або не ініціалізованим.");
            return;
        }

        long timeStart = System.nanoTime();

        Byte minValue = byteArray[0];
        Byte maxValue = byteArray[0];

        for (Byte currentByte : byteArray) {
            if (currentByte < minValue) {
                minValue = currentByte;
            }
            if (currentByte > maxValue) {
                maxValue = currentByte;
            }
        }

        PerformanceTracker.displayOperationTime(timeStart, "визначення мiнiмальної i максимальної дати в масивi byte");

        System.out.println("Найменше значення в масиви: " + minValue);
        System.out.println("Найбільше значення в масиви: " + maxValue);
    }

    void findInList() {
        long timeStart = System.nanoTime();

        int position = Collections.binarySearch(this.byteList, byteValueToSearch);

        PerformanceTracker.displayOperationTime(timeStart, "пошук елемента в List byte");

        if (position >= 0) {
            System.out.println("Елемент '" + byteValueToSearch + "' знайдено в List за позицією: " + position);
        } else {
            System.out.println("Елемент '" + byteValueToSearch + "' відсутній в List.");
        }
    }

    void locateMinMaxInList() {
        if (byteList == null || byteList.isEmpty()) {
            System.out.println("Колекція List є пустою або не ініціалізованою.");
            return;
        }

        long timeStart = System.nanoTime();

        Byte minValue = Collections.min(byteList);
        Byte maxValue = Collections.max(byteList);

        PerformanceTracker.displayOperationTime(timeStart, "визначення мiнiмальної i максимальної дати в List byte");

        System.out.println("Найменше значення в List: " + minValue);
        System.out.println("Найбільше значення в List: " + maxValue);
    }

    void sortList() {
        long timeStart = System.nanoTime();

        Collections.sort(byteList);

        PerformanceTracker.displayOperationTime(timeStart, "упорядкування List byte");
    }
}
