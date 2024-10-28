import java.util.Random;

public class PasoPorPuente {
    public static void main(String[] args) {
        final Puente puente = new Puente();
        int tMinParaLlegadaPersona = 1;
        int tMaxParaLlegadaPersona = 10;
        int tMinPaso = 5;
        int tMaxPaso = 20;
        int minPesoPersona = 50;
        int maxPesoPersona = 120;
        System.out.println(" >>> >>> >>> >>> Comienza simulación.");
        Random r = new Random();
        int idPersona = 1;
        while (true) {
            int tParaLlegadaPersona = tMinParaLlegadaPersona + r.nextInt(tMaxParaLlegadaPersona - tMinParaLlegadaPersona + 1);
            int pesoPersona = minPesoPersona + r.nextInt(maxPesoPersona - minPesoPersona + 1);
            System.out.printf("Siguiente persona llega en %d segundos.\n", tParaLlegadaPersona);
            try {
                Thread.sleep(1000 * tParaLlegadaPersona);
            } catch (InterruptedException ex) {
                System.out.printf("Interrumpido proceso principal");
            }
            Thread hiloPersona = new Thread(new Persona(puente, pesoPersona, tMinPaso, tMaxPaso, "P" + idPersona));
            hiloPersona.start();
            idPersona++;
        }
    }
}