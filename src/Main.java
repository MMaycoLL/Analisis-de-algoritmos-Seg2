import metodos.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;

public class Main extends JFrame {

    private static final String FILE_PATH1 = "src/generador/datos_generados1.txt";
    private static final String FILE_PATH2 = "src/generador/datos_generados2.txt";

    public Main(BigInteger[] arreglo1, BigInteger[] arreglo2) {
        super("Tiempos de Ejecución");

        // Arreglo que contendrá todos los tiempos de ejecución y nombres de algoritmos
        AlgoritmoTiempo[] arregloDeTiempos = new AlgoritmoTiempo[10];

        // Ejecutar y medir el tiempo de ejecución de cada algoritmo
        AlgoritmoMultiplicacion algoritmo1 = new _1_AmericanaIterativoEstatico();
        double tiempoAlgoritmo1 = medirTiempo(algoritmo1, arreglo1, arreglo2);
        arregloDeTiempos[0] = new metodos.AlgoritmoTiempo("AIEstatico", tiempoAlgoritmo1);

        AlgoritmoMultiplicacion algoritmo2 = new _2_AmericanaIterativoDinamico();
        double tiempoAlgoritmo2 = medirTiempo(algoritmo2, arreglo1, arreglo2);
        arregloDeTiempos[1] = new metodos.AlgoritmoTiempo("AIDinamico", tiempoAlgoritmo2);

        AlgoritmoMultiplicacion algoritmo3 = new _3_AmericanaRecursivoEstatico();
        double tiempoAlgoritmo3 = medirTiempo(algoritmo3, arreglo1, arreglo2);
        arregloDeTiempos[2] = new metodos.AlgoritmoTiempo("AREstatico", tiempoAlgoritmo3);

        AlgoritmoMultiplicacion algoritmo4 = new _4_AmericanaRecursivoDinamico();
        double tiempoAlgoritmo4 = medirTiempo(algoritmo4, arreglo1, arreglo2);
        arregloDeTiempos[3] = new metodos.AlgoritmoTiempo("ARDinamico", tiempoAlgoritmo4);

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

        AlgoritmoMultiplicacion algoritmo10 = new _10_DivideVenceras1Estatico();
        double tiempoAlgoritmo10 = medirTiempo(algoritmo10, arreglo1, arreglo2);
        arregloDeTiempos[9] = new metodos.AlgoritmoTiempo("HinduIterativoEstatico", tiempoAlgoritmo10);


        // Ordenar el arreglo de tiempos y nombres de algoritmos
        Arrays.sort(arregloDeTiempos, Comparator.comparing(AlgoritmoTiempo::getTiempo));

        guardarTiemposEnArchivo(arregloDeTiempos, "src/generador/datos_generados3.txt");

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
        int[] arreglo11 = readNumbersFromFile(FILE_PATH1);
        int[] arreglo22 = readNumbersFromFile(FILE_PATH2);

        // Convertir los arreglos de enteros a BigInteger[]
        BigInteger[] arreglo1 = convertirABigInteger(arreglo11);
        BigInteger[] arreglo2 = convertirABigInteger(arreglo22);



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

    private static int[] readNumbersFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            return br.lines().mapToInt(Integer::parseInt).toArray();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return new int[0];
        }
    }

    private static BigInteger[] convertirABigInteger(int[] datos) {
        BigInteger[] result = new BigInteger[datos.length];
        for (int i = 0; i < datos.length; i++) {
            result[i] = BigInteger.valueOf(datos[i]);
        }
        return result;
    }

    private static void guardarTiemposEnArchivo(AlgoritmoTiempo[] tiempos, String nombreArchivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (metodos.AlgoritmoTiempo tiempo : tiempos) {
                bw.write(tiempo.getNombre() + ": " + tiempo.getTiempo() + " ms");
                bw.newLine(); // Agregar una nueva línea para cada tiempo
            }
            System.out.println("Tiempos de ejecución guardados en el archivo '" + nombreArchivo + "'.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

