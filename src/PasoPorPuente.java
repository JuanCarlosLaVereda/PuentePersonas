import java.util.Random;

public class PasoPorPuente implements Runnable {
    Puente puente;
    int tMinParaLlegadaPersona;
    int tMaxParaLlegadaPersona;
    int tMinPaso;
    int tMaxPaso;
    int minPesoPersona;
    int maxPesoPersona;
    int lado;

    public PasoPorPuente(Puente puente, int tMinParaLlegadaPersona, int tMaxParaLlegadaPersona, int tMinPaso, int tMaxPaso, int minPesoPersona, int maxPesoPersona, int lado) {
        this.tMinParaLlegadaPersona = tMinParaLlegadaPersona;
        this.tMaxParaLlegadaPersona = tMaxParaLlegadaPersona;
        this.tMinPaso = tMinPaso;
        this.tMaxPaso = tMaxPaso;
        this.minPesoPersona = minPesoPersona;
        this.maxPesoPersona = maxPesoPersona;
        this.lado = lado;
        this.puente = puente;
    }






    @Override
    public void run() {
        Random r = new Random();
        int idPersona = 1;
        while (true) {
            int tParaLlegadaPersona = tMinParaLlegadaPersona + r.nextInt(tMaxParaLlegadaPersona - tMinParaLlegadaPersona + 1);
            int pesoPersona = minPesoPersona + r.nextInt(maxPesoPersona - minPesoPersona + 1);
            System.out.printf("Siguiente persona llega en %d segundos por el lado " + lado + "\n", tParaLlegadaPersona);
            try {
                Thread.sleep(1000 * tParaLlegadaPersona);
            } catch (InterruptedException ex) {
                System.out.printf("Interrumpido proceso principal");
            }
            Thread hiloPersona = new Thread(new Persona(puente, pesoPersona, tMinPaso, tMaxPaso, "P" + idPersona, lado));
            hiloPersona.start();
            idPersona++;
        }
    }
}