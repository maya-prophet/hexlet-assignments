package exercise;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
	 public static void main(String[] args) {
        int[] numbers = {10, -4, 67, 100, -100, 8};
        Map<String, Integer> result = getMinMax(numbers);
        System.out.println(result);
    }

    public static Map<String, Integer> getMinMax(int[] numbers) {
        Map<String, Integer> result = new HashMap<>();
        Arrays.sort(numbers);

        MinThread minThread = new MinThread(numbers);
        MaxThread maxThread = new MaxThread(numbers);
        minThread.start();
        maxThread.start();
        try {
            minThread.join();
            maxThread.join();
        } catch (InterruptedException e) {
            System.out.println("App#getMinMax(): " + e.getMessage());
        }
        

        result.put("min", minThread.getMinNumber());
        result.put("max", maxThread.getMaxNumber());
        return result;
    }

    // END
}
