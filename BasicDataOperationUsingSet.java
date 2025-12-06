import java.util.HashSet;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

/**
 * Клас BasicDataOperationUsingSet реалізує операції з множиною HashSet для byte.
 */
public class BasicDataOperationUsingSet {
    Byte byteValueToSearch;
    Byte[] byteArray;
    Set<Byte> byteSet = new HashSet<>();

    BasicDataOperationUsingSet(Byte byteValueToSearch, Byte[] byteArray) {
        this.byteValueToSearch = byteValueToSearch;
        this.byteArray = byteArray;
        this.byteSet = new HashSet<>(Arrays.asList(byteArray));
    }
    
    public void executeDataAnalysis() {
        findInSet();
        locateMinMaxInSet();
        analyzeArrayAndSet();

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
            System.out.println("Елемент '" + byteValueToSearch + "' знайдено в масиві за позицією: " + position);
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

        PerformanceTracker.displayOperationTime(timeStart, "визначення мiнiмальної i максимальної дати в масиві byte");

        System.out.println("Найменше значення в масиві: " + minValue);
        System.out.println("Найбільше значення в масиві: " + maxValue);
    }

    void findInSet() {
        long timeStart = System.nanoTime();

        boolean elementExists = this.byteSet.contains(byteValueToSearch);

        PerformanceTracker.displayOperationTime(timeStart, "пошук елемента в HashSet byte");

        if (elementExists) {
            System.out.println("Елемент '" + byteValueToSearch + "' знайдено в HashSet");
        } else {
            System.out.println("Елемент '" + byteValueToSearch + "' відсутній в HashSet.");
        }
    }

    void locateMinMaxInSet() {
        if (byteSet == null || byteSet.isEmpty()) {
            System.out.println("HashSet є пустим або не ініціалізованим.");
            return;
        }

        long timeStart = System.nanoTime();

        Byte minValue = Collections.min(byteSet);
        Byte maxValue = Collections.max(byteSet);

        PerformanceTracker.displayOperationTime(timeStart, "визначення мiнiмальної i максимальної дати в HashSet byte");

        System.out.println("Найменше значення в HashSet: " + minValue);
        System.out.println("Найбільше значення в HashSet: " + maxValue);
    }

    void analyzeArrayAndSet() {
        System.out.println("Кількість елементів в масиві: " + byteArray.length);
        System.out.println("Кількість елементів в HashSet: " + byteSet.size());

        boolean allElementsPresent = true;
        for (Byte byteElement : byteArray) {
            if (!byteSet.contains(byteElement)) {
                allElementsPresent = false;
                break;
            }
        }

        if (allElementsPresent) {
            System.out.println("Всі елементи масиву наявні в HashSet.");
        } else {
            System.out.println("Не всі елементи масиву наявні в HashSet.");
        }
    }
}
