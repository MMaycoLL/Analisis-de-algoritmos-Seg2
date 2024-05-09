package metodos;


import java.math.BigInteger;
import java.util.Random;

public class _9_HinduIterativoEstatico {

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

        multiplicarIndu(arr1, arr2);

    }


    private static void multiplicarIndu(BigInteger[] arr1, BigInteger[] arr2) {

        BigInteger[] resultado = new BigInteger[arr1.length+ arr2.length];
        int k;
        BigInteger[] acarreo = new BigInteger[resultado.length];

        System.out.print("\n");

        for (int i = 0; i < resultado.length; i++) {
            resultado[i] = BigInteger.ZERO;
            acarreo[i] = BigInteger.ZERO;
        }


        //Recorre el arreglo multiplicador desde la última posición
        for (int i = arr2.length -1; i>=0; i--){

            //Verifica a qué tan lejos está del borde derecho del arreglo resultado
            k  = resultado.length - (arr2.length - i);
            //Recorre el arreglo multiplicando desde la última posición
            for (int j = arr1.length - 1; j >= 0; j--) {

                //Realiza la multiplicación y suma sobre el resultado anterior
                resultado[k] = resultado[k].add(arr1[j].multiply(arr2[i]));
                //resultado[k] += arr1[j] * arr2[i];

                /**Condición que verifica si el resultado es igual o mayor a 10
                 * Para indicar que se acumula en el acarreo y que queda almacenado en la posicion [k]
                 * Ejem: 24/10 = 2--->acarreo(lo que se va a sumar en la siguiente multiplicación)
                 * 24%10 = 4 ---> resultado[k]
                 */
                if(resultado[k].compareTo(BigInteger.TEN) >= 0){
                    acarreo[k-1] = acarreo[k-1].add(resultado[k].divide(BigInteger.TEN));
                    resultado[k] = resultado[k].mod(BigInteger.TEN);
                    //acarreo[k-1] += resultado[k]/10;
                    //resultado[k] = resultado[k]%10;
                } else {
                    acarreo[k-1] = BigInteger.ZERO;
                }

                k--;
            }
        }
        for (int i = resultado.length - 1; i>=0; i--){
            resultado[i] = resultado[i].add(acarreo[i]);
            //resultado[i] += acarreo[i];

            if(resultado[i].compareTo(BigInteger.TEN) >= 0){
                acarreo[i-1] = acarreo[i-1].add(resultado[i].divide(BigInteger.TEN));
                resultado[i] = resultado[i].mod(BigInteger.TEN);
            }
        }
        imprimirResultado(resultado);
    }

    private static void imprimirResultado(BigInteger[] resultado) {
        System.out.println("Resultado");
        for (int i = 0; i< resultado.length; i++){
            System.out.print(resultado[i] + " ");
        }
    }

    public static BigInteger[] generateRandomBigIntegerArray(int rows) {
        // Create a new one-dimensional array of BigInteger objects of size rows x columns.
        BigInteger[] array = new BigInteger[rows];

        // Create a new Random object to generate random values.
        Random random = new Random();

        // Iterate over each element of the array and assign a random BigInteger value between 1000 and 9000.
        for (int i = 0; i < rows; i++) {
                BigInteger value = BigInteger.valueOf(random.nextInt(8001) + 1000);
                array[i] = value;

        }

        // Return the array with random values.
        return array;
    }


}



