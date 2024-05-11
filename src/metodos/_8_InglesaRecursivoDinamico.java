package metodos;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class _8_InglesaRecursivoDinamico implements AlgoritmoMultiplicacion {

    @Override
    public void multiplicar(BigInteger[] arr1, BigInteger[] arr2) {
        // Convertir los arrays a ArrayList
        ArrayList<BigInteger> arrayList1 = new ArrayList<>(arr1.length);
        ArrayList<BigInteger> arrayList2 = new ArrayList<>(arr2.length);
        arrayList1.addAll(Arrays.asList(arr1));
        arrayList2.addAll(Arrays.asList(arr2));

        // Llamar al método existente con ArrayList
        multiplicarArrayListAmericanoRecursivo(arrayList1, arrayList2);
    }

    private static void multiplicarArrayListAmericanoRecursivo(ArrayList<BigInteger> arrayList1, ArrayList<BigInteger> arrayList2) {

        // Calcula la longitud del resultado
        int longitud = arrayList1.size() + arrayList2.size();

        // Crea un ArrayList para almacenar el resultado de la multiplicación
        ArrayList<BigInteger> resultado = new ArrayList<>(Collections.nCopies(longitud, null));

        // Inicializa los elementos del resultado a cero
        for (int x = 0; x < resultado.size(); x++) {
            resultado.set(x, BigInteger.ZERO);
        }

        // Define los índices iniciales para la multiplicación recursiva
        int i = 0;
        int j = 0;
        int k = longitud - 1;

        // Inicia la multiplicación recursiva
        multiplicarRecursivo(arrayList1, arrayList2, resultado, i, j, k);
    }

    private static ArrayList<BigInteger> multiplicarRecursivo(ArrayList<BigInteger> arrayList1, ArrayList<BigInteger> arrayList2, ArrayList<BigInteger> resultado, int i, int j, int k) {

        // Caso base: cuando i es el último índice de arrayList2 y j es igual a la longitud de arrayList1
        if (i == arrayList2.size() - 1 && j == arrayList1.size()) {
            // Realiza el proceso de acarreo si es necesario
            if (k > 0) {
                resultado.set(k - 1, resultado.get(k - 1).add(resultado.get(k).divide(BigInteger.TEN)));
                resultado.set(k, resultado.get(k).mod(BigInteger.TEN));
                k--;
                multiplicarRecursivo(arrayList1, arrayList2, resultado, i, j, k);
            } else {
                return resultado;
            }
        } else if (j == arrayList1.size() && i < arrayList2.size() - 1) {
            // Si j alcanza la longitud de arrayList1 y hay elementos restantes en arrayList2, reinicia j y avanza a la siguiente posición de arrayList2
            j = 0;
            i++;
            multiplicarRecursivo(arrayList1, arrayList2, resultado, i, j, k);
        } else if (j < arrayList1.size()) {
            // Realiza la multiplicación y avanza a la siguiente posición de arrayList1
            resultado.set(i + j + 1, resultado.get(i + j + 1).add(arrayList1.get(j).multiply(arrayList2.get(i))));
            j++;
            multiplicarRecursivo(arrayList1, arrayList2, resultado, i, j, k);
        }

        return resultado;
    }
}



