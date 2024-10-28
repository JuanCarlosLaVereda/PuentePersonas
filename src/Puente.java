public class Puente {
    // Estado, objeto compartido entre los hilos
    private static final int PESO_MAXIMO = 400;
    private static final int MAX_PERSONAS = 4;
    private static final int MAX_PERSONAS_LADO = 3;

    private int peso = 0;
    private int numPersonas = 0;
    private int numPersonasIzq = 0;
    private int numPersonasDerecha = 0;

    synchronized public int getPeso() {
        return peso;
    }

    synchronized public int getNumPersonas() {
        return numPersonas;
    }

    synchronized public boolean autorizacionPasoIzquierdaDerecha(Persona persona) {
        boolean result;
        if (numPersonasIzq==3){
            System.out.println("No se puede pasar por exceso de lado");
        }
        if (this.peso + persona.getPeso() <= Puente.PESO_MAXIMO && this.numPersonas < Puente.MAX_PERSONAS && numPersonasIzq<MAX_PERSONAS_LADO) {
            this.numPersonasIzq++;
            this.numPersonas++;
            this.peso += persona.getPeso();
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    synchronized public boolean autorizacionPasoDerechaIzquierda(Persona persona) {
        boolean result;
        if (this.peso + persona.getPeso() <= Puente.PESO_MAXIMO && this.numPersonas < Puente.MAX_PERSONAS && numPersonasDerecha<MAX_PERSONAS_LADO) {
            this.numPersonasDerecha++;
            this.numPersonas++;
            this.peso += persona.getPeso();
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    synchronized public void terminaPaso(Persona persona, int lado) {
        this.peso -= persona.getPeso();
        this.numPersonas--;
        if (lado == 2){
            this.numPersonasDerecha--;
        } else {
            this.numPersonasIzq--;
        }
    }
}
