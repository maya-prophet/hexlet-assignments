package exercise;

// BEGIN
public class MinThread extends Thread {

    private int[] numbers;
    private int minNumber;

    public MinThread(int[] numbers) {
        this.numbers = new int[numbers.length];
        System.arraycopy(numbers, 0, this.numbers, 0, numbers.length);
    }

    @Override
    public void run() {
        System.out.println("Thread MinThread started");
        minNumber  = numbers[0];
        System.out.println("Thread MinThread ended");
    }

    public int getMinNumber() {
        return minNumber;
    }
}
// END
