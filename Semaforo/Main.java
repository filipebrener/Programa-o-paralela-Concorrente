import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {
    private static final int NUM_CLIENTES = 20;

    public static void main(String[] args) throws InterruptedException {

        final Semaphore semaforoBarbeiro = new Semaphore(0);
        final Semaphore semaforoCadeiras = new Semaphore(5);

        Thread barbeiro = new Thread(new Barbeiro(semaforoBarbeiro, semaforoCadeiras));
        barbeiro.start();

        for (int i = 0; i < NUM_CLIENTES; i++) {
            Thread cliente = new Thread(new Cliente(i,semaforoBarbeiro, semaforoCadeiras));
            long tempoEmMilliseconds = new Random().nextLong(0 , 3000); // tempo aleatório entre 0 e 3 segundos
            Thread.sleep(tempoEmMilliseconds); // Simula o intervalo de tempo de chegada dos clientes na barbearia
            cliente.start();
        }
        Thread.sleep(180000);
        System.out.println("Fechando barbearia, o barbeiro precisa ir pra casa!");
        barbeiro.interrupt();
    }


}