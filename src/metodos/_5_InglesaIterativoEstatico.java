package metodos;

import java.math.BigInteger;

public class _5_InglesaIterativoEstatico {

    public static void main(String[] args){
        BigInteger[] arr1 = {BigInteger.valueOf(9),
                BigInteger.valueOf(9), BigInteger.valueOf(9),
                BigInteger.valueOf(9), BigInteger.valueOf(9),
                BigInteger.valueOf(9), BigInteger.valueOf(9)};

        BigInteger[] arr2 = {BigInteger.valueOf(9),
                BigInteger.valueOf(9), BigInteger.valueOf(9),
                BigInteger.valueOf(9), BigInteger.valueOf(9),
                BigInteger.valueOf(9), BigInteger.valueOf(9)};

        System.out.println("Arreglo multiplicando");
        for(BigInteger l: arr1)
            System.out.print(l + " ");

        System.out.println("\nArreglo multiplicador");
        for (BigInteger h : arr2)
            System.out.print(h + " ");
        System.out.println();

        multiplicaInglesa(arr1, arr2);

    }

    public static void multiplicaInglesa (BigInteger[] arreglo1, BigInteger[] arreglo2) {

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

        imprimirResultado(resultado);

    }

    private static void imprimirResultado(BigInteger[] resultado) {
        System.out.println("Resultado");
        for (int i = 0; i< resultado.length; i++){
            System.out.print(resultado[i] + " ");
        }
    }
}
