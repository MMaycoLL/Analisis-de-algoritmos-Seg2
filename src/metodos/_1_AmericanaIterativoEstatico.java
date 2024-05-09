package metodos;

import java.math.BigInteger;

public class _1_AmericanaIterativoEstatico {

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

        multiplicarAmericano(arr1, arr2);

    }

    private static void multiplicarAmericano(BigInteger[] arr1, BigInteger[] arr2) {

        BigInteger[] resultado = new BigInteger[arr1.length+ arr2.length];
        int k;
        BigInteger acarreo = BigInteger.ZERO;


        for (int i = 0; i < resultado.length; i++) {
            resultado[i] = BigInteger.ZERO;
        }

        System.out.print("\n");

        if(arr1.length > arr2.length){
            BigInteger[] arrAux1 = arr1;
            arr1 = arr2;
            arr2 = arrAux1;
        }
        //Recorre el arreglo multiplicador desde la última posición
        for (int i = arr2.length -1; i>=0; i--){

            //Verifica a qué tan lejos está del borde derecho del arreglo resultado
            k  = resultado.length - (arr2.length - i);
            //Recorre el arreglo multiplicando desde la última posición
            for (int j = arr1.length - 1; j >= 0; j--) {

                //Realiza la multiplicación y suma sobre el resultado anterior
                resultado[k] = resultado[k].add(arr1[j].multiply(arr2[i])).add(acarreo);
                /**Condición que verifica si el resultado es igual o mayor a 10
                 * Para indicar que se acumula en el acarreo y que queda almacenado en la posicion [k]
                 * Ejem: 24/10 = 2--->acarreo(lo que se va a sumar en la siguiente multiplicación)
                 * 24%10 = 4 ---> resultado[k]
                 */
                if (resultado[k].compareTo(BigInteger.TEN) >= 0) {
                    acarreo = resultado[k].divide(BigInteger.TEN);
                    resultado[k] = resultado[k].mod(BigInteger.TEN);
                } else {
                    acarreo = BigInteger.ZERO;
                }

                k--;
            }
            resultado[k]=acarreo;
            acarreo = BigInteger.ZERO;
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
