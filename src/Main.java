import metodos.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.math.BigInteger;
import java.util.Arrays;

public class Main extends JFrame {

    private static final long serialVersionUID = 1L;

    public Main(BigInteger[] arreglo1, BigInteger[] arreglo2) {
        super("Tiempos de Ejecución");

        // Arreglo que contendrá todos los tiempos de ejecución y nombres de algoritmos
        metodos.AlgoritmoTiempo[] arregloDeTiempos = new metodos.AlgoritmoTiempo[9];

        // Ejecutar y medir el tiempo de ejecución de cada algoritmo
        AlgoritmoMultiplicacion algoritmo1 = new _1_AmericanaIterativoEstatico();
        double tiempoAlgoritmo1 = medirTiempo(algoritmo1, arreglo1, arreglo2);
        arregloDeTiempos[0] = new metodos.AlgoritmoTiempo("AmericanaIterativoEstatico", tiempoAlgoritmo1);

        AlgoritmoMultiplicacion algoritmo2 = new _2_AmericanaIterativoDinamico();
        double tiempoAlgoritmo2 = medirTiempo(algoritmo2, arreglo1, arreglo2);
        arregloDeTiempos[1] = new metodos.AlgoritmoTiempo("AmericanaIterativoDinamico", tiempoAlgoritmo2);

        AlgoritmoMultiplicacion algoritmo3 = new _3_AmericanaRecursivoEstatico();
        double tiempoAlgoritmo3 = medirTiempo(algoritmo3, arreglo1, arreglo2);
        arregloDeTiempos[2] = new metodos.AlgoritmoTiempo("AmericanaRecursivoEstatico", tiempoAlgoritmo3);

        AlgoritmoMultiplicacion algoritmo4 = new _4_AmericanaRecursivoDinamico();
        double tiempoAlgoritmo4 = medirTiempo(algoritmo4, arreglo1, arreglo2);
        arregloDeTiempos[3] = new metodos.AlgoritmoTiempo("AmericanaRecursivoDinamico", tiempoAlgoritmo4);

        AlgoritmoMultiplicacion algoritmo5 = new _5_InglesaIterativoEstatico();
        double tiempoAlgoritmo5 = medirTiempo(algoritmo5, arreglo1, arreglo2);
        arregloDeTiempos[4] = new metodos.AlgoritmoTiempo("InglesaIterativoEstatico", tiempoAlgoritmo5);

        AlgoritmoMultiplicacion algoritmo6 = new _6_InglesaIterativoDinamico();
        double tiempoAlgoritmo6 = medirTiempo(algoritmo6, arreglo1, arreglo2);
        arregloDeTiempos[5] = new metodos.AlgoritmoTiempo("InglesaIterativoDinamico", tiempoAlgoritmo6);

        AlgoritmoMultiplicacion algoritmo7 = new _7_InglesaRecursivoEstatico();
        double tiempoAlgoritmo7 = medirTiempo(algoritmo7, arreglo1, arreglo2);
        arregloDeTiempos[6] = new metodos.AlgoritmoTiempo("InglesaRecursivoEstatico", tiempoAlgoritmo7);

        AlgoritmoMultiplicacion algoritmo8 = new _8_InglesaRecursivoDinamico();
        double tiempoAlgoritmo8 = medirTiempo(algoritmo8, arreglo1, arreglo2);
        arregloDeTiempos[7] = new metodos.AlgoritmoTiempo("InglesaRecursivoDinamico", tiempoAlgoritmo8);

        AlgoritmoMultiplicacion algoritmo9 = new _9_HinduIterativoEstatico();
        double tiempoAlgoritmo9 = medirTiempo(algoritmo9, arreglo1, arreglo2);
        arregloDeTiempos[8] = new metodos.AlgoritmoTiempo("HinduIterativoEstatico", tiempoAlgoritmo9);






        // Ordenar el arreglo de tiempos y nombres de algoritmos
        Arrays.sort(arregloDeTiempos);

        // Imprimir los tiempos de ejecución
        for (metodos.AlgoritmoTiempo algoritmoTiempo : arregloDeTiempos) {
            System.out.println("Tiempo de ejecución de " + algoritmoTiempo.getNombre() + ": " + algoritmoTiempo.getTiempo() + " ms");
        }

        // Una vez están ordenados los tiempos de ejecución los mandamos a la gráfica
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (metodos.AlgoritmoTiempo algoritmoTiempo : arregloDeTiempos) {
            dataset.addValue(algoritmoTiempo.getTiempo(), "Tiempo de Ejecución", algoritmoTiempo.getNombre());
        }

        // Crear el gráfico
        JFreeChart chart = ChartFactory.createBarChart("Comparación de Tiempos de Ejecución", // Título del gráfico
                "Algoritmos", // Etiqueta del eje de las X
                "Tiempo de Ejecución (ms)", // Etiqueta del eje de las Y
                dataset // Datos
        );

        // Mostrar el gráfico en un panel
        ChartPanel chartPanel = new ChartPanel(chart);
        setContentPane(chartPanel);
    }

    public static void main(String[] args) {
        // Definir los arreglos de ejemplo
        BigInteger[] arreglo1 = {BigInteger.valueOf(123), BigInteger.valueOf(456), BigInteger.valueOf(789)};
        BigInteger[] arreglo2 = {BigInteger.valueOf(321), BigInteger.valueOf(654), BigInteger.valueOf(987)};

        Main main = new Main(arreglo1, arreglo2);
        main.pack();
        main.setVisible(true);
    }

    private double medirTiempo(AlgoritmoMultiplicacion algoritmo, BigInteger[] arreglo1, BigInteger[] arreglo2) {
        long startTime = System.nanoTime();
        algoritmo.multiplicar(arreglo1, arreglo2);
        long endTime = System.nanoTime();
        return (double) (endTime - startTime) / 1000000; // Convertir de nanosegundos a milisegundos
    }
}

