package metodos;

import java.math.BigInteger;

public class _5_InglesaIterativoEstatico implements AlgoritmoMultiplicacion {


    @Override
    public void multiplicar(BigInteger[] arreglo1, BigInteger[] arreglo2) {

        int tam = arreglo1.length + arreglo2.length;
        BigInteger[] resultado = new BigInteger [tam];

        for (int x = 0; x < resultado.length; x++) {
            resultado[x] = BigInteger.ZERO;
        }

        for (int i = 0; i < arreglo2.length; i++) {
            for(int j = 0; j < arreglo1.length; j++) {
                resultado[i + j + 1] = resultado[i + j + 1].add(arreglo1[j].multiply(arreglo2[i]));

            }

        }
        for (int k=tam-1; k>0; k--) {
            resultado[k - 1] = resultado[k - 1].add(resultado[k].divide(BigInteger.TEN));
            resultado[k] = resultado[k].mod(BigInteger.TEN);

        }
    }
}
