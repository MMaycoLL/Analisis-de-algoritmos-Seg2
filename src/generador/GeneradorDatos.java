package generador;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.StringJoiner;

public class GeneradorDatos {

    private static final int LONGITUD_ARREGLO = 10; // Longitud del arreglo

    private static void guardarDatosEnArchivo(int[] datos, String nombreArchivo) throws IOException {
        StringJoiner joiner = new StringJoiner("\n");
        for (int dato : datos) {
            joiner.add(Integer.toString(dato));
        }
        
        BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo));
        bw.write(joiner.toString());
        bw.close();
        System.out.println("Datos generados y guardados en el archivo '" + nombreArchivo + "'.");
    }

    private static int generarNumeroAleatorioDeOchoDigitos() {
        Random rand = new Random();
        return rand.nextInt(90000000) + 10000000; // Genera un número aleatorio de 8 dígitos
    }

    public static int[] generarArregloAleatorio() {
        int[] arreglo = new int[LONGITUD_ARREGLO];
        for (int i = 0; i < LONGITUD_ARREGLO; i++) {
            arreglo[i] = generarNumeroAleatorioDeOchoDigitos();
        }
        return arreglo;
    }

    public static void main(String[] args) {
        try {
            // Generar arreglo con 100,000 valores aleatorios de 8 dígitos
            int[] datosAleatorios = generarArregloAleatorio();

            // Guardar los datos en un archivo de texto
            guardarDatosEnArchivo(datosAleatorios, "D:\\Code\\seguimiento2\\src\\generador\\datos_generados_8.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

