package metodos;

import java.math.BigInteger;

public class _5_InglesaIterativoEstatico implements AlgoritmoMultiplicacion {

    @Override
    public void multiplicar(BigInteger[] arreglo1, BigInteger[] arreglo2) {

        // Calcula el tamaño del resultado
        int tam = arreglo1.length + arreglo2.length;

        // Crea un arreglo para almacenar el resultado de la multiplicación
        BigInteger[] resultado = new BigInteger[tam];

        // Inicializa todos los elementos del resultado a cero
        for (int x = 0; x < resultado.length; x++) {
            resultado[x] = BigInteger.ZERO;
        }

        // Realiza la multiplicación convencional de los arreglos
        for (int i = 0; i < arreglo2.length; i++) {
            for (int j = 0; j < arreglo1.length; j++) {
                resultado[i + j + 1] = resultado[i + j + 1].add(arreglo1[j].multiply(arreglo2[i]));
            }
        }

        // Realiza el proceso de "carry" o acarreo
        for (int k = tam - 1; k > 0; k--) {
            resultado[k - 1] = resultado[k - 1].add(resultado[k].divide(BigInteger.TEN));
            resultado[k] = resultado[k].mod(BigInteger.TEN);
        }
    }
}