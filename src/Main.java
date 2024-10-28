public class Main {
    public static void main(String[] args) {
        System.out.println(" >>> >>> >>> >>> Comienza simulación.");
        Puente puente = new Puente();
        int tMinParaLlegadaPersona = 1;
        int tMaxParaLlegadaPersona = 10;
        int tMinPaso = 5;
        int tMaxPaso = 20;
        int minPesoPersona = 50;
        int maxPesoPersona = 120;
        Thread ladoDerecho = new Thread(new PasoPorPuente(puente, tMinParaLlegadaPersona, tMaxParaLlegadaPersona, tMinPaso, tMaxPaso, minPesoPersona, maxPesoPersona, 2));
        Thread ladoIzquierdo = new Thread(new PasoPorPuente(puente, tMinParaLlegadaPersona, tMaxParaLlegadaPersona, tMinPaso, tMaxPaso, minPesoPersona, maxPesoPersona, 1));

        ladoDerecho.start();
        ladoIzquierdo.start();
    }
}
