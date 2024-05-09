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

        int longitud = arrayList1.size() + arrayList2.size();
        ArrayList<BigInteger> resultado = new ArrayList<>(Collections.nCopies(longitud, null));

        int i = 0;
        int j = 0;
        int k = longitud - 1;

        for (int x = 0; x < resultado.size(); x++) {
            resultado.set(x, BigInteger.ZERO);
        }

        multiplicarRecursivo(arrayList1, arrayList2, resultado, i, j, k);
    }

    private static ArrayList<BigInteger> multiplicarRecursivo(ArrayList<BigInteger> arrayList1, ArrayList<BigInteger> arrayList2, ArrayList<BigInteger> resultado, int i, int j, int k) {

        if(i == arrayList2.size() - 1 && j == arrayList1.size()){

            if (k > 0){
                resultado.set(k - 1, resultado.get(k - 1).add(resultado.get(k).divide(BigInteger.TEN)));
                resultado.set(k, resultado.get(k).mod(BigInteger.TEN));
                k--;
                multiplicarRecursivo(arrayList1,arrayList2,resultado,i,j,k);
            } else {
                return resultado;
            }
        } else if (j == arrayList1.size() && i < arrayList2.size()-1) {

            j = 0;
            i++;

            multiplicarRecursivo(arrayList1,arrayList2,resultado,i,j,k);

        } else if (j < arrayList1.size()){
            resultado.set(i + j + 1, resultado.get(i + j + 1).add(arrayList1.get(j).multiply(arrayList2.get(i))));
            j++;
            multiplicarRecursivo(arrayList1,arrayList2,resultado,i,j,k);
        }

        return resultado;
    }
}


