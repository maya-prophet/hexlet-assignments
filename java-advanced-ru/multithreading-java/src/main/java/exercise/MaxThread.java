package exercise;

// BEGIN
public class MaxThread extends Thread {

    private int[] numbers;
    private int maxNumber;

    public MaxThread(int[] numbers) {
        this.numbers = new int[numbers.length];
        System.arraycopy(numbers, 0, this.numbers, 0, numbers.length);
    }

    @Override
    public void run() {
        System.out.println("Thread MaxThread started");
        maxNumber  = numbers[numbers.length - 1];
        System.out.println("Thread MaxThread ended");
    }

    public int getMaxNumber() {
        return maxNumber;
    }
}
// END
