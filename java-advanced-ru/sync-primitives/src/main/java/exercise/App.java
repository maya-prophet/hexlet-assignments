package exercise;

class App {

    public static void main(String[] args) {
        // BEGIN
        // Создаём лист
        SafetyList list = new SafetyList();

        // Создаём поток, передав туда созданный лист
        Thread thread1 = new Thread(new ListThread(list));
        Thread thread2 = new Thread(new ListThread(list));

        // Запускаем поток
        thread1.start();
        thread2.start();

    

        // Дожидаемся его окончания
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(list.getSize());
        // END
    }
}

