package exercise;

// BEGIN
public class ListThread implements Runnable {
    private final SafetyList list;

    public ListThread(SafetyList list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            list.add(i);
            try {
                Thread.sleep(1); // Задержка в 1 мс
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
    }
}
// END
