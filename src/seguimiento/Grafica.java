package seguimiento;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class Grafica extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String FILE_PATH = "D:\\Code\\seguimiento2\\src\\generador\\datos_generados_8.txt";

	 public Grafica(int[] arreglo) {
	        super("Tiempos de Ejecución");

	        // Arreglo que contendrá todos los tiempos de ejecución y nombres de algoritmos
	        AlgoritmoTiempo[] arregloDeTiempos = new AlgoritmoTiempo[7];
	        

	        // Ejecutar y medir el tiempo de ejecución de cada algoritmo,
	        // para cada algoritmo se manda una copia del arreglo original
	        // ya que de lo contrario se estaría mandando el arreglo ordenado

	        int[] arreglo1 = Arrays.copyOf(arreglo, arreglo.length);
	        double tiempoTimSort = medirTiempo(new TimSort(), arreglo1);
	        arregloDeTiempos[0] = new AlgoritmoTiempo("TimSort", tiempoTimSort);

	        int[] arreglo2 = Arrays.copyOf(arreglo, arreglo.length);
	        double tiempoCombSort = medirTiempo(new CombSort(), arreglo2);
	        arregloDeTiempos[1] = new AlgoritmoTiempo("CombSort", tiempoCombSort);

	        int[] arreglo3 = Arrays.copyOf(arreglo, arreglo.length);
	        double tiempoTreeSort = medirTiempoTreeSort(new TreeSort(), arreglo3);
	        arregloDeTiempos[2] = new AlgoritmoTiempo("TreeSort", tiempoTreeSort);

	        int[] arreglo4 = Arrays.copyOf(arreglo, arreglo.length);
	        double tiempoPigeonholeSort = medirTiempo(new PigeonholeSort(), arreglo4);
	        arregloDeTiempos[3] = new AlgoritmoTiempo("PigeonholeSort", tiempoPigeonholeSort);

	        int[] arreglo5 = Arrays.copyOf(arreglo, arreglo.length);
	        double tiempoHeapSort = medirTiempo(new HeapSort(), arreglo5);
	        arregloDeTiempos[4] = new AlgoritmoTiempo("HeapSort", tiempoHeapSort);

	        int[] arreglo6 = Arrays.copyOf(arreglo, arreglo.length);
	        double tiempoBitonicSort = medirTiempo(new BitonicSort(), arreglo6);
	        arregloDeTiempos[5] = new AlgoritmoTiempo("BitonicSort", tiempoBitonicSort);

	        int[] arreglo7 = Arrays.copyOf(arreglo, arreglo.length);
	        double tiempoGnomeSort = medirTiempo(new GnomeSort(), arreglo7);
	        arregloDeTiempos[6] = new AlgoritmoTiempo("GnomeSort", tiempoGnomeSort);

	     // Ordenar el arreglo de tiempos y nombres de algoritmos
	        Arrays.sort(arregloDeTiempos);

	        // Imprimir los tiempos de ejecución
	        for (AlgoritmoTiempo algoritmoTiempo : arregloDeTiempos) {
	            System.out.println("Tiempo de ejecución de " + algoritmoTiempo.getNombre() + ": " + algoritmoTiempo.getTiempo() + " ms");
	        }

	        // Una vez están ordenados los tiempos de ejecución los mandamos a la gráfica
	        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	        for (AlgoritmoTiempo algoritmoTiempo : arregloDeTiempos) {
	            dataset.addValue(algoritmoTiempo.getTiempo(), "Tiempo de Ejecución", algoritmoTiempo.getNombre());
	        }

		// Crear el gráfico
		JFreeChart chart = ChartFactory.createBarChart("Comparación de Tiempos de Ejecución", // Título del gráfico
				"Algoritmo", // Etiqueta del eje de las X
				"Tiempo de Ejecución (ms)", // Etiqueta del eje de las Y
				dataset // Datos
		);

		// Mostrar el gráfico en un panel
		ChartPanel chartPanel = new ChartPanel(chart);
		setContentPane(chartPanel);
	}

	
	private double medirTiempoTreeSort(TreeSort algoritmo, int[] arreglo) {
		long startTime = System.nanoTime();
		algoritmo.treeins(arreglo);
		algoritmo.inorderRec(algoritmo.root);
		long endTime = System.nanoTime();
		return (double) (endTime - startTime) / 1000000; // Convertir de nanosegundos a milisegundos
	}

	private double medirTiempo(AlgoritmoOrdenamiento algoritmo, int[] arreglo) {
		long startTime = System.nanoTime();
		algoritmo.ordenar(arreglo, arreglo.length);
		long endTime = System.nanoTime();
		return (double) (endTime - startTime) / 1000000; // Convertir de nanosegundos a milisegundos
	}

	
	private void ordenar(double[] arregloDeTiempos) {
		double temp = 0;

		for (int j = 1; j < arregloDeTiempos.length; j++) {

			for (int i = 0; i < arregloDeTiempos.length - 1; i++) {
				if (arregloDeTiempos[i] < arregloDeTiempos[i + 1]) {
					temp = arregloDeTiempos[i];
					arregloDeTiempos[i] = arregloDeTiempos[i + 1];
					arregloDeTiempos[i + 1] = temp;
				}
			}
		}
	}

	private static int[] readNumbersFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            return br.lines().mapToInt(Integer::parseInt).toArray();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return new int[0];
        }
    }

    public static void main(String[] args) {
        // Change this line to read numbers from a text file
        int[] arreglo = readNumbersFromFile(FILE_PATH);

        Grafica grafica = new Grafica(arreglo);
        grafica.pack();
        grafica.setVisible(true);
    }
}

