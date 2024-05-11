package metodos;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

public class _2_AmericanaIterativoDinamico implements AlgoritmoMultiplicacion {

    @Override
    public void multiplicar(BigInteger[] arr1, BigInteger[] arr2) {
        // Convertir los arrays a ArrayList
        ArrayList<BigInteger> arrayList1 = new ArrayList<>(arr1.length);
        ArrayList<BigInteger> arrayList2 = new ArrayList<>(arr2.length);
        arrayList1.addAll(Arrays.asList(arr1));
        arrayList2.addAll(Arrays.asList(arr2));

        // Llamar al método existente con ArrayList
        multiplicarAmericanoArrayList(arrayList1, arrayList2);
    }

    public static void multiplicarAmericanoArrayList(ArrayList<BigInteger> arr1, ArrayList<BigInteger> arr2) {

        // Calcula la longitud total del resultado
        int longitud = arr1.size() + arr2.size();

        // Inicializa el ArrayList resultado
        ArrayList<BigInteger> resultado = new ArrayList<>();

        // Inicializa la variable de acarreo
        BigInteger acarreo = BigInteger.ZERO;

        // Inicializa el ArrayList resultado con ceros
        for (int i = 0; i < longitud; i++) {
            resultado.add(BigInteger.ZERO);
        }

        // Verifica cuál ArrayList es más corto para optimizar la multiplicación
        if (arr1.size() > arr2.size()) {
            ArrayList<BigInteger> arrAux1 = arr1;
            arr1 = arr2;
            arr2 = arrAux1;
        }

        // Recorre el ArrayList multiplicador desde la última posición
        for (int i = arr2.size() - 1; i >= 0; i--) {

            // Calcula a qué tan lejos está del borde derecho del ArrayList resultado
            BigInteger k = BigInteger.valueOf(resultado.size() - (arr2.size() - i));

            // Recorre el ArrayList multiplicando desde la última posición
            for (int j = arr1.size() - 1; j >= 0; j--) {

                // Realiza la multiplicación y suma sobre el resultado anterior
                resultado.set(k.intValue(), arr1.get(j).multiply(arr2.get(i)).add(acarreo).add(resultado.get(k.intValue())));

                // Verifica si hay acarreo y ajusta el resultado
                if (resultado.get(k.intValue()).compareTo(BigInteger.TEN) >= 0) {
                    acarreo = resultado.get(k.intValue()).divide(BigInteger.TEN);
                    resultado.set(k.intValue(), resultado.get(k.intValue()).mod(BigInteger.TEN));
                } else {
                    acarreo = BigInteger.ZERO;
                }

                // Disminuye k para la próxima iteración
                k = k.subtract(BigInteger.ONE);
            }

            // Guarda el acarreo en el resultado
            resultado.set(k.intValue(), acarreo);
            acarreo = BigInteger.ZERO;
        }
    }
}
