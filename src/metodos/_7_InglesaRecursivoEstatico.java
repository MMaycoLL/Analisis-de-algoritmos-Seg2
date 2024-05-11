package metodos;

import java.math.BigInteger;

public class _7_InglesaRecursivoEstatico implements AlgoritmoMultiplicacion {

    @Override
    public void multiplicar(BigInteger[] arreglo1, BigInteger[] arreglo2) {

        int tam = arreglo1.length + arreglo2.length;
        BigInteger[] resultado = new BigInteger [tam];
        int i = 0;
        int j = 0;
        int k = tam - 1;

        for (int x = 0; x < resultado.length; x++) {
            resultado[x] = BigInteger.ZERO;
        }

        multiplicarRecursivo(arreglo1, arreglo2, resultado, i, j, k);
    }

    private static BigInteger[] multiplicarRecursivo(BigInteger[] arreglo1, BigInteger[] arreglo2, BigInteger[] resultado, int i, int j, int k) {

        // Caso base: cuando i es el último índice de arreglo2 y j es igual a la longitud de arreglo1
        if (i == arreglo2.length - 1 && j == arreglo1.length) {
            // Realiza el proceso de acarreo si es necesario
            if (k > 0) {
                resultado[k - 1] = resultado[k - 1].add(resultado[k].divide(BigInteger.TEN));
                resultado[k] = resultado[k].mod(BigInteger.TEN);
                k--;
                multiplicarRecursivo(arreglo1, arreglo2, resultado, i, j, k);
            } else {
                return resultado;
            }
        } else if (j == arreglo1.length && i < arreglo2.length - 1) {
            // Si j alcanza la longitud de arreglo1 y hay elementos restantes en arreglo2, reinicia j y avanza a la siguiente posición de arreglo2
            j = 0;
            i++;
            multiplicarRecursivo(arreglo1, arreglo2, resultado, i, j, k);
        } else if (j < arreglo1.length) {
            // Realiza la multiplicación y avanza a la siguiente posición de arreglo1
            resultado[i + j + 1] = resultado[i + j + 1].add(arreglo1[j].multiply(arreglo2[i]));
            j++;
            multiplicarRecursivo(arreglo1, arreglo2, resultado, i, j, k);
        }

        return resultado;
    }
}

