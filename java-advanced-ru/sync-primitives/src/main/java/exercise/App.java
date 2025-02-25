package exercise;

class App {

    public static void main(String[] args) throws InterruptedException {
        // BEGIN
        // Создаем потокобезопасный лист
        SafetyList list = new SafetyList();

        // Создаем два потока
        Thread thread1 = new Thread(new ListThread(list));
        Thread thread2 = new Thread(new ListThread(list));

        // Запускаем потоки
        thread1.start();
        thread2.start();

        // Ждем завершения работы потоков
        thread1.join();
        thread2.join();

        // Выводим итоговое количество элементов
        System.out.println("Количество элементов в списке: " + list.getSize());
        // END
    }
}

