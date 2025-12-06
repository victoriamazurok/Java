public class PerformanceTracker {
    public static void displayOperationTime(long startTime, String operationName) {
        long finishTime = System.nanoTime();
        long executionTime = finishTime - startTime;
        System.out.println("\n========= Тривалість операції '" + operationName + "': " + executionTime + " нс =========");
    }
}
