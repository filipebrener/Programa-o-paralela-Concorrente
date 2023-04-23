import java.util.ArrayList;
import java.util.List;

public class Monitor {

    private final int BUFFER_MAX_SIZE = 10;
    private final List<Integer> buffer = new ArrayList<>();
    private int numeroDeBuffersProduzidos = 0;
    private int numeroDeBuffersLidos = 0;
    private final int numeroMaximoDeBuffers;

    public Monitor(int numeroMaximoDeBuffer){
        this.numeroMaximoDeBuffers = numeroMaximoDeBuffer;
    }

    public boolean aindaPodeProduzir(){
        return numeroDeBuffersProduzidos < numeroMaximoDeBuffers;
    }

    public boolean aindaPodeConsumir(){
        return numeroDeBuffersLidos < numeroMaximoDeBuffers;
    }

    public synchronized void produce() throws InterruptedException {
        numeroDeBuffersProduzidos++;
        if(numeroDeBuffersProduzidos >= numeroMaximoDeBuffers) return;
        while (!buffer.isEmpty()) {
            wait();
        }
        System.out.println("Produtor começando a produzir o buffer: " + numeroDeBuffersProduzidos);
        while (buffer.size() < BUFFER_MAX_SIZE){
            int item = (int) (Math.random() * 100);
            buffer.add(item);
            System.out.println("Produtor produziu: " + item);
        }
        System.out.println("Produtor terminou de produzir o buffer: " + numeroDeBuffersProduzidos);
        notifyAll(); // Notifica uma thread que terminou de produzir
    }

    public synchronized void consume() throws InterruptedException {
        while (buffer.size() < BUFFER_MAX_SIZE) {
            wait();
        }
        System.out.println("Consumidor começou consumir o buffer " + numeroDeBuffersProduzidos );
        while (buffer.size() > 0){
            int item = buffer.remove(0);
            System.out.println("Consumidor consumiu: " + item);
        }
        numeroDeBuffersLidos++;
        System.out.println("Consumidor terminou de consumir o buffer " + numeroDeBuffersProduzidos );
        notifyAll(); // Notifica uma thread que estiver em modo de espera "wait" terminou de consumir.
    }
}