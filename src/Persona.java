import java.util.Random;

public class Persona implements Runnable {
    private final String idPersona;
    private final int peso;
    private final int tMinPaso, tMaxPaso;
    private final Puente puente;
    private int lado;

    public int getPeso() {
        return peso;
    }

    Persona(Puente puente, int peso, int tMinPaso, int tMaxPaso, String idP, int lado) {
        this.puente = puente;
        this.peso = peso;
        this.tMinPaso = tMinPaso;
        this.tMaxPaso = tMaxPaso;
        this.idPersona = idP;
        this.lado = lado;
    }

    @Override
    public void run() {
        System.out.printf("- %sL" + lado + " de %d kg quiere cruzar, en puente %d kg, %d persona %s por el lado" + lado + ".\n",
                this.idPersona, this.peso, puente.getPeso(),
                puente.getNumPersonas(), puente.getNumPersonas() != 1 ? "s" : "");

        // Espera para conseguir autorización
        boolean autorizado = false;
        while (!autorizado) {
            synchronized (this.puente) {
                if (this.lado == 1){
                    autorizado = this.puente.autorizacionPasoIzquierdaDerecha(this);
                } else {
                    autorizado = this.puente.autorizacionPasoDerechaIzquierda(this);
                }
                if (!autorizado) {
                    try {
                        System.out.printf("# %sL" + lado + " debe esperar.\n", this.idPersona);
                        this.puente.wait();
                    } catch (InterruptedException ex) {
                        System.out.printf("Interrupción mientras %s espera.\n", this.idPersona);
                    }
                }
            }
        }

        System.out.printf(" > %sL" + lado + " con peso %d puede cruzar por el lado " + lado + ", peso en puente %d con %d personas.\n",
                this.idPersona, this.getPeso(), this.puente.getPeso(),
                puente.getNumPersonas(), this.puente.getNumPersonas() != 1 ? "s" : "");

        Random r = new Random();
        // Pasa al puente, y tarda un tiempo en cruzar
        int tiempoPaso = this.tMinPaso + r.nextInt(this.tMaxPaso - this.tMinPaso + 1);
        try {
            System.out.printf(" %sL" + lado + " va a tardar tiempo %d en cruzar.\n", this.idPersona, tiempoPaso);
            Thread.sleep(1000 * tiempoPaso);
        } catch (InterruptedException ex) {
            System.out.printf("Interrupción mientras %s pasa.\n", this.idPersona);
        }

        // Sale del puente
        synchronized (this.puente) {
            this.puente.terminaPaso(this, lado);
            System.out.printf(" < %sL" + lado + " sale del puente, puente soporta peso %d, %d persona %s.\n",
                    this.idPersona, this.puente.getPeso(), this.puente.getNumPersonas(),
                    this.puente.getNumPersonas() != 1 ? "s" : "");
            puente.notifyAll();
        }
    }
}
