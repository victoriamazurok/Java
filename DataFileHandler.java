import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class DataFileHandler {

    @SuppressWarnings("unchecked")
    public static <T> T[] loadArrayFromFile(String filePath, Class<T> clazz) {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
            return fileReader.lines()
                    .map(line -> line.replaceAll("^\\uFEFF", ""))  // Remove BOM if present
                    .map(String::trim)
                    .filter(line -> !line.isEmpty())
                    .map(line -> convertToType(line, clazz))
                    .toArray(size -> (T[]) java.lang.reflect.Array.newInstance(clazz, size));
        } catch (IOException e) {
            throw new RuntimeException("Помилка читання з файлу: " + filePath, e);
        }
    }

    private static <T> T convertToType(String line, Class<T> clazz) {
        if (clazz == Byte.class) {
            try {
                byte parsedValue = Byte.parseByte(line);
                return clazz.cast(parsedValue);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Невірний формат числа для типу byte: " + line);
            }
        } else if (clazz == Integer.class) {
            try {
                return clazz.cast(Integer.parseInt(line));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Невірний формат числа для типу int: " + line);
            }
        } else if (clazz == String.class) {
            return clazz.cast(line);
        }
        // Для інших типів можна додати аналогічну перевірку
        throw new UnsupportedOperationException("Тип не підтримується: " + clazz.getSimpleName());
    }

    public static <T> void writeArrayToFile(T[] array, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            String content = Arrays.stream(array)
                    .map(String::valueOf) // adjust this for different types
                    .collect(Collectors.joining(System.lineSeparator()));
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Помилка запису в файл: " + filePath, e);
        }
    }
}
