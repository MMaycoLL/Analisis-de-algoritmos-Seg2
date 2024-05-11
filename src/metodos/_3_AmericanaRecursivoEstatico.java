package metodos;

import java.math.BigInteger;

public class _3_AmericanaRecursivoEstatico implements AlgoritmoMultiplicacion {

    @Override
    public void multiplicar(BigInteger[] arr1, BigInteger[] arr2) {

        BigInteger[] resultado = new BigInteger[arr1.length + arr2.length];

        BigInteger acarreo = BigInteger.ZERO;
        int i = arr2.length - 1;
        int j = arr1.length - 1;
        int k = resultado.length - 1;

        if (arr1.length > arr2.length) {
            BigInteger[] arrAux1 = arr1;
            arr1 = arr2;
            arr2 = arrAux1;
        }

        for (int index = 0; index < resultado.length; index++) {
            resultado[index] = BigInteger.ZERO;
        }

        multiplicacionAmericanoRecursivo(arr1, arr2, resultado, acarreo, i, j, k);

    }

    public static void multiplicacionAmericanoRecursivo(BigInteger[] arr1, BigInteger[] arr2, BigInteger[] resultado,
                                                        BigInteger acarreo, int i, int j, int k) {

        // Caso base: cuando i y j son ambos cero, la multiplicación recursiva termina
        if (i == 0 && j == 0) {
            // Realiza la multiplicación y suma el acarreo
            resultado[k] = resultado[k].add(arr1[j].multiply(arr2[i])).add(acarreo);

            // Verifica si hay un acarreo al siguiente dígito
            if (resultado[k].compareTo(BigInteger.TEN) >= 0) {
                acarreo = resultado[k].divide(BigInteger.TEN);
                resultado[k] = resultado[k].mod(BigInteger.TEN);
            } else {
                acarreo = BigInteger.ZERO;
            }
            k--;

            // Guarda cualquier acarreo restante
            resultado[k] = acarreo;
        } else if (j == 0) {
            // Está en la posición j=0 e i= cualquier valor del arr1 (For anidado)
            resultado[k] = resultado[k].add(arr1[j].multiply(arr2[i])).add(acarreo);

            // Verifica si hay un acarreo al siguiente dígito
            if(resultado[k].compareTo(BigInteger.TEN) >= 0){
                acarreo = resultado[k].divide(BigInteger.TEN);
                resultado[k] = resultado[k].mod(BigInteger.TEN);
            } else {
                acarreo = BigInteger.ZERO;
            }
            k--;

            // Reinicia j al final del arreglo arr1 y decrementa i para iterar arr2
            resultado[k] = acarreo;
            acarreo = BigInteger.ZERO;
            i--;
            j = arr1.length - 1;
            k = resultado.length - (arr2.length - i);

            // Llama recursivamente con los nuevos índices
            multiplicacionAmericanoRecursivo(arr1, arr2, resultado, acarreo, i, j, k);
        } else {
            // Realiza la multiplicación y suma el acarreo
            resultado[k] = resultado[k].add(arr1[j].multiply(arr2[i])).add(acarreo);

            // Verifica si hay un acarreo al siguiente dígito
            if(resultado[k].compareTo(BigInteger.TEN) >= 0){
                acarreo = resultado[k].divide(BigInteger.TEN);
                resultado[k] = resultado[k].mod(BigInteger.TEN);
            } else {
                acarreo = BigInteger.ZERO;
            }
            k--;

            // Decrementa los índices para la siguiente iteración recursiva
            j--;

            // Llama recursivamente con los nuevos índices
            multiplicacionAmericanoRecursivo(arr1, arr2, resultado, acarreo, i, j, k);
        }
    }
}