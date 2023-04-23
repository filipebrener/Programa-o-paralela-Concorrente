import java.util.ArrayList;
import java.util.List;


public class Main {

    public static final int NUMERO_MAX_BUFFERS = 10;

    public static void main(String[] args) {
        Monitor monitor = new Monitor(NUMERO_MAX_BUFFERS);

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            Thread producer = new Thread(() -> {
                while (monitor.aindaPodeProduzir()) {
                    try {
                        monitor.produce();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Encerrando produtor");
            });
            threads.add(producer);
        }

        for (int i = 0; i < 2; i++) {
            Thread consumer = new Thread(() -> {
                while (monitor.aindaPodeConsumir()) {
                    try {
                        monitor.consume();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Encerrando consumidor");
            });
            threads.add(consumer);
        }

        threads.forEach(Thread::start);
    }
}