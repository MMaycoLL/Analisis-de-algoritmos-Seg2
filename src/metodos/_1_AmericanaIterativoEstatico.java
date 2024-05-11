package metodos;

import java.math.BigInteger;

public class _1_AmericanaIterativoEstatico implements AlgoritmoMultiplicacion {

    @Override
    public void multiplicar(BigInteger[] arr1, BigInteger[] arr2) {

        // Inicialización del arreglo resultado y otras variables
        BigInteger[] resultado = new BigInteger[arr1.length + arr2.length];
        int k;
        BigInteger acarreo = BigInteger.ZERO;

        // Inicialización del arreglo resultado con ceros
        for (int i = 0; i < resultado.length; i++) {
            resultado[i] = BigInteger.ZERO;
        }

        // Determina el arreglo de menor longitud para optimizar la multiplicación
        if (arr1.length > arr2.length) {
            BigInteger[] arrAux1 = arr1;
            arr1 = arr2;
            arr2 = arrAux1;
        }

        // Itera sobre el arreglo de multiplicación, comenzando desde la última posición
        for (int i = arr2.length - 1; i >= 0; i--) {

            // Calcula la posición relativa en el arreglo resultado
            k = resultado.length - (arr2.length - i);

            // Itera sobre el arreglo multiplicador, desde la última posición
            for (int j = arr1.length - 1; j >= 0; j--) {

                // Realiza la multiplicación y suma sobre el resultado anterior
                resultado[k] = resultado[k].add(arr1[j].multiply(arr2[i])).add(acarreo);

                // Gestiona el acarreo, si es necesario
                if (resultado[k].compareTo(BigInteger.TEN) >= 0) {
                    acarreo = resultado[k].divide(BigInteger.TEN);
                    resultado[k] = resultado[k].mod(BigInteger.TEN);
                } else {
                    acarreo = BigInteger.ZERO;
                }

                k--;
            }
            resultado[k] = acarreo;
            acarreo = BigInteger.ZERO;
        }
    }
}

