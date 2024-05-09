package metodos;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class _6_InglesaIterativoDinamico implements AlgoritmoMultiplicacion {

    @Override
    public void multiplicar(BigInteger[] arr1, BigInteger[] arr2) {
        // Convertir los arrays a ArrayList
        ArrayList<BigInteger> arrayList1 = new ArrayList<>(arr1.length);
        ArrayList<BigInteger> arrayList2 = new ArrayList<>(arr2.length);
        arrayList1.addAll(Arrays.asList(arr1));
        arrayList2.addAll(Arrays.asList(arr2));

        // Llamar al método existente con ArrayList
        multiplicarInglesArrayList(arrayList1, arrayList2);
    }

    public static void multiplicarInglesArrayList (ArrayList<BigInteger> arrayList1, ArrayList<BigInteger> arrayList2) {

        int longitud = arrayList1.size() + arrayList2.size();
        ArrayList<BigInteger> resultado = new ArrayList<>(Collections.nCopies(longitud, null));

        for (int x = 0; x < longitud; x++) {
            resultado.set(x, BigInteger.ZERO);
        }

        for (int i = 0; i < arrayList2.size(); i++) {
            for(int j = 0; j < arrayList1.size(); j++) {
                resultado.set(i + j + 1, resultado.get(i + j + 1).add(arrayList1.get(j).multiply(arrayList2.get(i))));

            }

        }
        for (int k = longitud-1; k > 0; k--) {
            resultado.set(k - 1, resultado.get(k - 1).add(resultado.get(k).divide(BigInteger.TEN)));
            resultado.set(k, resultado.get(k).mod(BigInteger.TEN));

        }
    }
}
