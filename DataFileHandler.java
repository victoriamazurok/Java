import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Клас DataFileHandler управляє роботою з файлами даних byte.
 */
public class DataFileHandler {
    /**
     * Завантажує масив об'єктів Byte з файлу.
     * 
     * @param filePath Шлях до файлу з даними.
     * @return Масив об'єктів Byte.
     */
    public static Byte[] loadArrayFromFile(String filePath) {
        Byte[] temporaryArray = new Byte[1000];
        int currentIndex = 0;

        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
            String currentLine;
            while ((currentLine = fileReader.readLine()) != null) {
                currentLine = currentLine.trim().replaceAll("^\\uFEFF", "");
                if (!currentLine.isEmpty()) {
                    Byte parsedByte = Byte.parseByte(currentLine);
                    temporaryArray[currentIndex++] = parsedByte;
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        Byte[] resultArray = new Byte[currentIndex];
        System.arraycopy(temporaryArray, 0, resultArray, 0, currentIndex);

        return resultArray;
    }

    /**
     * Зберігає масив об'єктів Byte у файл.
     * 
     * @param byteArray Масив об'єктів Byte.
     * @param filePath Шлях до файлу для збереження.
     */
    public static void writeArrayToFile(Byte[] byteArray, String filePath) {
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filePath))) {
            for (Byte byteElement : byteArray) {
                fileWriter.write(byteElement.toString());
                fileWriter.newLine();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
