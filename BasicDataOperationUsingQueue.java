import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Клас BasicDataOperationUsingQueue реалізує роботу з колекціями типу Queue для byte.
 */
public class BasicDataOperationUsingQueue {
    private Byte byteValueToSearch;
    private Byte[] byteArray;
    private Queue<Byte> byteQueue;

    BasicDataOperationUsingQueue(Byte byteValueToSearch, Byte[] byteArray) {
        this.byteValueToSearch = byteValueToSearch;
        this.byteArray = byteArray;
        this.byteQueue = new PriorityQueue<>(Arrays.asList(byteArray));
    }
    
    public void runDataProcessing() {
        findInQueue();
        locateMinMaxInQueue();
        performQueueOperations();

        findInArray();
        locateMinMaxInArray();

        performArraySorting();

        findInArray();
        locateMinMaxInArray();

        DataFileHandler.writeArrayToFile(byteArray, BasicDataOperation.PATH_TO_DATA_FILE + ".sorted");
    }

    private void performArraySorting() {
        long timeStart = System.nanoTime();

        Arrays.sort(byteArray);

        PerformanceTracker.displayOperationTime(timeStart, "упорядкування масиву byte");
    }

    private void findInArray() {
        long timeStart = System.nanoTime();

        int position = Arrays.binarySearch(this.byteArray, byteValueToSearch);

        PerformanceTracker.displayOperationTime(timeStart, "пошук елемента в масивi byte");

        if (position >= 0) {
            System.out.println("Елемент '" + byteValueToSearch + "' знайдено в масиви за позицією: " + position);
        } else {
            System.out.println("Елемент '" + byteValueToSearch + "' відсутній в масиві.");
        }
    }

    private void locateMinMaxInArray() {
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

        PerformanceTracker.displayOperationTime(timeStart, "визначення мiнiмальної i максимальної дати в масиви byte");

        System.out.println("Найменше значення в масиви: " + minValue);
        System.out.println("Найбільше значення в масиви: " + maxValue);
    }

    private void findInQueue() {
        long timeStart = System.nanoTime();

        boolean elementExists = this.byteQueue.contains(byteValueToSearch);

        PerformanceTracker.displayOperationTime(timeStart, "пошук елемента в Queue byte");

        if (elementExists) {
            System.out.println("Елемент '" + byteValueToSearch + "' знайдено в Queue");
        } else {
            System.out.println("Елемент '" + byteValueToSearch + "' відсутній в Queue.");
        }
    }

    private void locateMinMaxInQueue() {
        if (byteQueue == null || byteQueue.isEmpty()) {
            System.out.println("Черга є пустою або не ініціалізованою.");
            return;
        }

        long timeStart = System.nanoTime();

        Byte minValue = Collections.min(byteQueue);
        Byte maxValue = Collections.max(byteQueue);

        PerformanceTracker.displayOperationTime(timeStart, "визначення мiнiмальної i максимальної дати в Queue byte");

        System.out.println("Найменше значення в Queue: " + minValue);
        System.out.println("Найбільше значення в Queue: " + maxValue);
    }

    private void performQueueOperations() {
        if (byteQueue == null || byteQueue.isEmpty()) {
            System.out.println("Черга є пустою або не ініціалізованою.");
            return;
        }

        Byte headElement = byteQueue.peek();
        System.out.println("Головний елемент черги (peek): " + headElement);

        headElement = byteQueue.poll();
        System.out.println("Видалений елемент черги (poll): " + headElement);

        headElement = byteQueue.peek();
        System.out.println("Новий головний елемент черги: " + headElement);
    }
}
