package metodos;


import java.math.BigInteger;

public class _9_HinduIterativoEstatico implements AlgoritmoMultiplicacion {

    @Override
    public void multiplicar(BigInteger[] arr1, BigInteger[] arr2) {

        // Crea un arreglo para almacenar el resultado de la multiplicación
        BigInteger[] resultado = new BigInteger[arr1.length + arr2.length];

        // Crea un arreglo para almacenar los acarreos
        BigInteger[] acarreo = new BigInteger[resultado.length];

        // Inicializa todos los elementos de resultado y acarreo a cero
        for (int i = 0; i < resultado.length; i++) {
            resultado[i] = BigInteger.ZERO;
            acarreo[i] = BigInteger.ZERO;
        }

        // Recorre el arreglo multiplicador desde la última posición
        for (int i = arr2.length - 1; i >= 0; i--) {
            // Calcula la posición inicial del resultado para el dígito actual de arr2
            int k = resultado.length - (arr2.length - i);

            // Recorre el arreglo multiplicando desde la última posición
            for (int j = arr1.length - 1; j >= 0; j--) {
                // Realiza la multiplicación y suma sobre el resultado anterior
                resultado[k] = resultado[k].add(arr1[j].multiply(arr2[i]));

                // Verifica si hay un acarreo al siguiente dígito
                if (resultado[k].compareTo(BigInteger.TEN) >= 0) {
                    acarreo[k - 1] = acarreo[k - 1].add(resultado[k].divide(BigInteger.TEN));
                    resultado[k] = resultado[k].mod(BigInteger.TEN);
                } else {
                    acarreo[k - 1] = BigInteger.ZERO;
                }

                k--;
            }
        }

        // Realiza el proceso final de acarreo
        for (int i = resultado.length - 1; i >= 0; i--) {
            resultado[i] = resultado[i].add(acarreo[i]);

            // Verifica si hay un acarreo al siguiente dígito
            if (i - 1 >= 0) {
                if (resultado[i].compareTo(BigInteger.TEN) >= 0) {
                    acarreo[i - 1] = acarreo[i - 1].add(resultado[i].divide(BigInteger.TEN));
                    resultado[i] = resultado[i].mod(BigInteger.TEN);
                }
            }
        }
    }
}




