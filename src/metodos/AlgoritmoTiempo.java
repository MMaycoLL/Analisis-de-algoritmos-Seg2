package metodos;


public class AlgoritmoTiempo implements Comparable<AlgoritmoTiempo> {
    private String nombre;
    private double tiempo;

    public AlgoritmoTiempo(String nombre, double tiempo) {
        this.nombre = nombre;
        this.tiempo = tiempo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getTiempo() {
        return tiempo;
    }

    @Override
    public int compareTo(AlgoritmoTiempo otro) {
        return Double.compare(otro.tiempo, this.tiempo);
    }
}