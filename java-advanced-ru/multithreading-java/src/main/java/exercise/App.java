package exercise;

import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] numbers) {
        MinThread minThread = new MinThread(numbers);
        MaxThread maxThread = new MaxThread(numbers);

        minThread.start();
        LOGGER.info("Thread " + minThread.getName() + " started");

        maxThread.start();
        LOGGER.info("Thread " + maxThread.getName() + " started");

        try {
            minThread.join();
            LOGGER.info("Thread " + minThread.getName() + " finished");

            maxThread.join();
            LOGGER.info("Thread " + maxThread.getName() + " finished");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Map result = Map.of("min", minThread.getMin(), "max", maxThread.getMax());

        return result;
    }
    // END
}
