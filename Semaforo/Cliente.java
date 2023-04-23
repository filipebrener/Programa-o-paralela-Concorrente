import java.util.concurrent.Semaphore;

class Cliente implements Runnable {

    private final Semaphore semaforoBarbeiro;
    private final Semaphore semaforoCadeiras;
    private final int id;

    public Cliente(int id, Semaphore semaforoBarbeiro, Semaphore semaforoCadeiras){
        this.id = id;
        this.semaforoBarbeiro = semaforoBarbeiro;
        this.semaforoCadeiras = semaforoCadeiras;
    }

    public int getId() {
        return id;
    }

    public void run() {
        boolean podeEsperar = semaforoCadeiras.tryAcquire();
        System.out.println("Cliente " + id + " chegou na barbearia");
        if(podeEsperar){
            System.out.println("Cliente " + id + " sentou na cadeira de espera.");
            semaforoBarbeiro.release(); // cliente acordando o barbeiro para poder cortar o cabelo
        } else{
            System.out.println("Barbearia cheia, cliente " + id + " indo embora");
        }
    }

}