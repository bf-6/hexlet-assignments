package exercise;

// BEGIN
public class MinThread extends Thread {
    private int[] numbers;
    private int min;

    public MinThread(int[] numbers) {
        this.numbers = numbers;
    }

    public int getMin() {
        return min;
    }

    @Override
    public void run() {
        int count = numbers[0];

        for (int number : numbers) {
            if (number < count) {
                count = number;
            }
            min = count;
        }
    }
}
// END
