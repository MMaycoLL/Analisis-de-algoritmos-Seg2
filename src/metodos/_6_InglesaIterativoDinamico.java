package metodos;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

public class _6_InglesaIterativoDinamico {

    public static void main(String[] args){
        ArrayList<BigInteger> arr1 = new ArrayList<>();
        arr1.add(BigInteger.valueOf(9));
        arr1.add(BigInteger.valueOf(9));
        arr1.add(BigInteger.valueOf(9));
        arr1.add(BigInteger.valueOf(9));
        arr1.add(BigInteger.valueOf(9));
        arr1.add(BigInteger.valueOf(9));
        arr1.add(BigInteger.valueOf(9));

        ArrayList<BigInteger> arr2 = new ArrayList<>();
        arr2.add(BigInteger.valueOf(9));
        arr2.add(BigInteger.valueOf(9));
        arr2.add(BigInteger.valueOf(9));
        arr2.add(BigInteger.valueOf(9));
        arr2.add(BigInteger.valueOf(9));
        arr2.add(BigInteger.valueOf(9));
        arr2.add(BigInteger.valueOf(9));


        System.out.println("Arreglo multiplicando");
        for(BigInteger l: arr1)
            System.out.print(l + " ");

        System.out.println("\nArreglo multiplicador");
        for (BigInteger h : arr2)
            System.out.print(h + " ");
        System.out.println();

        multiplicarInglesArrayList(arr1, arr2);

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

        imprimirResultadoArrayList(resultado);

    }

    private static void imprimirResultadoArrayList(ArrayList<BigInteger> resultado) {
        System.out.println("Resultado");
        for (int i = 0; i< resultado.size(); i++){
            System.out.print(resultado.get(i) + " ");
        }
    }
}
