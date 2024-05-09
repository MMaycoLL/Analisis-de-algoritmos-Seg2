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
       int longitud = arr1.size() + arr2.size();
        ArrayList<BigInteger> resultado = new ArrayList<>();


        BigInteger acarreo = BigInteger.ZERO;
        for (int i = 0; i < longitud; i++) {
            resultado.add(BigInteger.ZERO);
        }

        if (arr1.size() > arr2.size()) {
            ArrayList<BigInteger> arrAux1 = arr1;
            arr1 = arr2;
            arr2 = arrAux1;
        }

        //Recorre el arreglo multiplicador desde la última posición
        for (int i = arr2.size() - 1; i >= 0; i--) {

            //Verifica a qué tan lejos está del borde derecho de resultado
            BigInteger  k = BigInteger.valueOf(resultado.size() - (arr2.size() - i));
            //Recorre el arreglo multiplicando desde la última posición
            for (int j = arr1.size() - 1; j >= 0; j--) {

                //Realiza la multiplicación y suma sobre el resultado anterior
                resultado.set(k.intValue(), arr1.get(j).multiply(arr2.get(i)).add(acarreo).add(resultado.get(k.intValue())));

                if (resultado.get(k.intValue()).compareTo(BigInteger.TEN) >= 0) {
                    acarreo = resultado.get(k.intValue()).divide(BigInteger.TEN);
                    resultado.set(k.intValue(), resultado.get(k.intValue()).mod(BigInteger.TEN));
                } else {
                    acarreo = BigInteger.ZERO;
                }
                k = k.subtract(BigInteger.ONE);
            }
            resultado.set(k.intValue(), acarreo);
            //resultado[k]=acarreo;
            acarreo = BigInteger.ZERO;
        }
    }
}