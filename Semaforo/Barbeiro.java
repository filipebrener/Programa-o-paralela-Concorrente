import java.util.concurrent.Semaphore;

class Barbeiro implements Runnable{

    private final Semaphore semaforoBarbeiro;
    private final Semaphore semaforoCadeiras;

    public Barbeiro(Semaphore semaforoBarbeiro, Semaphore semaforoCadeiras){
        this.semaforoBarbeiro = semaforoBarbeiro;
        this.semaforoCadeiras = semaforoCadeiras;
    }

    public void run() {
        try {
            while (true) {
                System.out.println("Barbeiro sentando para dormir");
                semaforoBarbeiro.acquire(); // barbeiro esperando para ser acordado
                System.out.println("Barbeiro foi acordado...");
                semaforoCadeiras.release(); // cliente liberando a cadeira de espera
                System.out.println("Barbeiro atendendo cliente...");
                Thread.sleep(2000); // Simula o tempo de atendimento do cliente
                System.out.println("Barbeiro terminou de atender cliente.");
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

}