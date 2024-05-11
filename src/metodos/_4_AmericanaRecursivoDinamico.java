package metodos;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class _4_AmericanaRecursivoDinamico implements AlgoritmoMultiplicacion {

    @Override
    public void multiplicar(BigInteger[] arrayList1, BigInteger[] arrayList2) {
        ArrayList<BigInteger> array1 = new ArrayList<>(arrayList1.length);
        ArrayList<BigInteger> array2 = new ArrayList<>(arrayList2.length);

        array1.addAll(Arrays.asList(arrayList1));
        array2.addAll(Arrays.asList(arrayList2));

        multiplicarArrayListRecursivo(array1, array2);

    }

    private static void multiplicarArrayListRecursivo(ArrayList<BigInteger> arrayList1, ArrayList<BigInteger> arrayList2) {

        int longitud = arrayList1.size() + arrayList2.size();
        ArrayList<BigInteger> resultado = new ArrayList<>(Collections.nCopies(longitud, null));

        if (arrayList1.size() > arrayList2.size()) {
            ArrayList <BigInteger> arrAux1 = arrayList1;
            arrayList1 = arrayList2;
            arrayList2 = arrAux1;
        }

        BigInteger acarreo = BigInteger.ZERO;
        int i = arrayList2.size() - 1;
        int j = arrayList1.size() - 1;
        int k = resultado.size() - 1;

        for (int index = 0; index < longitud; index++) {
            resultado.set(index, BigInteger.ZERO);
        }

        multiplicacionAmericanoRecursivo(arrayList1, arrayList2, resultado, acarreo, i, j, k);
    }

    private static void multiplicacionAmericanoRecursivo(ArrayList<BigInteger> arrayList1, ArrayList<BigInteger> arrayList2,
                                                         ArrayList<BigInteger> resultado, BigInteger acarreo, int i, int j, int k) {

        // Caso base: cuando i y j son ambos cero, la multiplicación recursiva termina
        if (i == 0 && j == 0) {
            // Realiza la multiplicación y suma el acarreo
            resultado.set(k, resultado.get(k).add(arrayList1.get(j).multiply(arrayList2.get(i))).add(acarreo));

            // Verifica si hay un acarreo al siguiente dígito
            if (resultado.get(k).compareTo(BigInteger.TEN) >= 0) {
                acarreo = resultado.get(k).divide(BigInteger.TEN);
                resultado.set(k, resultado.get(k).mod(BigInteger.TEN));
            } else {
                acarreo = BigInteger.ZERO;
            }
            k--;

            // Guarda cualquier acarreo restante
            resultado.set(k, acarreo);

        } else if (j == 0) {
            // Está en la posición j=0 e i= cualquier valor del arrayList1 (For anidado)
            resultado.set(k, resultado.get(k).add(arrayList1.get(j).multiply(arrayList2.get(i))).add(acarreo));

            // Verifica si hay un acarreo al siguiente dígito
            if (resultado.get(k).compareTo(BigInteger.TEN) >= 0) {
                acarreo = resultado.get(k).divide(BigInteger.TEN);
                resultado.set(k, resultado.get(k).mod(BigInteger.TEN));
            } else {
                acarreo = BigInteger.ZERO;
            }
            k--;

            // Reinicia j al final del arrayList1 y decrementa i para iterar arrayList2
            resultado.set(k, acarreo);
            acarreo = BigInteger.ZERO;
            i--;
            j = arrayList1.size() - 1;
            k = resultado.size() - (arrayList2.size() - i);

            // Llama recursivamente con los nuevos índices
            multiplicacionAmericanoRecursivo(arrayList1, arrayList2, resultado, acarreo, i, j, k);
        } else {
            // Realiza la multiplicación y suma el acarreo
            resultado.set(k, resultado.get(k).add(arrayList1.get(j).multiply(arrayList2.get(i))).add(acarreo));

            // Verifica si hay un acarreo al siguiente dígito
            if (resultado.get(k).compareTo(BigInteger.TEN) >= 0) {
                acarreo = resultado.get(k).divide(BigInteger.TEN);
                resultado.set(k, resultado.get(k).mod(BigInteger.TEN));
            } else {
                acarreo = BigInteger.ZERO;
            }
            k--;

            // Decrementa los índices para la siguiente iteración recursiva
            j--;

            // Llama recursivamente con los nuevos índices
            multiplicacionAmericanoRecursivo(arrayList1, arrayList2, resultado, acarreo, i, j, k);

        }
    }
}
