package validation;

/**
 * Class that allow to gather performance of program.
 */
public class Metrics {
    private static long startTime;
    private static long stopTime;

    /**
     * Method that starts timer.
     */
    public static void start() {
        startTime = System.currentTimeMillis();
    }

    /**
     * Method that stops timer.
     */
    public static void stop() {
        stopTime = System.currentTimeMillis();
    }

    /**
     * Method that calculates time of execution.
     */
    public static void getExecutionTime() {
        long estimatedTime = stopTime - startTime;
        System.out.println("Execution time is " + estimatedTime + " ms");
    }

    /**
     * Method that gets memory used by program.
     */
    public static void getUsedMemory() {
        Runtime.getRuntime().gc();
        long usedBytes = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
        System.out.println("Used memory is " + convertToMegabytes(usedBytes) + " mb");
    }

    /**
     * Method that converts bytes into megabytes.
     * @param bytes -  bytes to convert.
     * @return
     */
    private static double convertToMegabytes(long bytes) {
        return bytes / (1024.0 * 1024.0);
    }

    /**
     * Method that gathers all metrics.
     */
    public static void getAllMetrics(){
        getExecutionTime();
        getUsedMemory();
    }
}

