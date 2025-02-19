package exercise;

// BEGIN
public class MaxThread extends Thread {

    private int[] numbers;
    private int max;

    public MaxThread(int[] numbers) {
        this.numbers = numbers;
    }

    public int getMax() {
        return max;
    }

    @Override
    public void run() {
        int count = numbers[0];

        for(int number : numbers) {
            if (number > count) {
                count = number;
            }
            max = count;
        }
    }
}
// END
