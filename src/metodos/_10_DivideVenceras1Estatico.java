package metodos;

import java.math.BigInteger;

public class _10_DivideVenceras1Estatico implements AlgoritmoMultiplicacion {


    @Override
    public void multiplicar(BigInteger[] arr1, BigInteger[] arr2) {

        int[] intArr1 = convertBigIntegerArrayToIntArray(arr1);
        int[] intArr2 = convertBigIntegerArrayToIntArray(arr2);

        // Llamar a divideyVenceras1 con los arreglos enteros
        int[] resultIntArray = divideyVenceras1(intArr1, intArr2, intArr1.length);

        // Convertir el resultado de int[] a BigInteger
        convertIntArrayToBigInteger(resultIntArray);

    }

    // Este método implementa el algoritmo de multiplicación utilizando el enfoque de divide y vencerás.
// Divide recursivamente los arreglos de entrada en subarreglos más pequeños y luego combina los resultados parciales.
    public static int[] divideyVenceras1(int vec1[], int vec2[], int n) {

        // Si el tamaño del problema es lo suficientemente pequeño (n=2), utiliza el algoritmo tradicional.
        if (n == 2) {
            int[] resultado;
            resultado = AlgoritmoTradicional(vec1, vec2); // Llama al algoritmo tradicional.
            return resultado;
        } else {
            // Divide los arreglos de entrada en cuatro subarreglos de tamaño n/2.
            int[] x = new int[n / 2];
            int[] y = new int[n / 2];
            int[] z = new int[n / 2];
            int[] w = new int[n / 2];

            // Asigna los elementos de los arreglos originales a los subarreglos.
            for (int i = 0; i < n / 2; i++) {
                w[i] = vec1[i]; // Asigna la parte izquierda del multiplicador.
                y[i] = vec2[i]; // Asigna la parte izquierda del multiplicando.
                x[i] = vec1[i + (n / 2)]; // Asigna la parte derecha del multiplicador.
                z[i] = vec2[i + (n / 2)]; // Asigna la parte derecha del multiplicando.
            }

            // Calcula los resultados parciales de la parte izquierda del arreglo.
            int[] r = new int[2 * n];
            int[] auxr;
            iniceros(r, 2 * n); // Inicializa con ceros.
            auxr = divideyVenceras1(w, y, n / 2); // Llama recursivamente.

            // Asigna los resultados parciales al arreglo r.
            for (int i = 0; i < n; i++) {
                r[i] = auxr[i];
            }

            // Calcula los resultados parciales de la parte derecha izquierda del arreglo.
            int[] s = new int[n + (n / 2)];
            int[] auxs;
            iniceros(s, n + (n / 2)); // Inicializa con ceros.
            auxs = divideyVenceras1(w, z, n / 2); // Llama recursivamente.

            // Asigna los resultados parciales al arreglo s.
            for (int i = 0; i < n; i++) {
                s[i] = auxs[i];
            }

            // Calcula los resultados parciales de la parte izquierda derecha del arreglo.
            int[] t = new int[(n / 2) + n];
            int[] auxt;
            iniceros(t, (n / 2) + n); // Inicializa con ceros.
            auxt = divideyVenceras1(x, y, n / 2); // Llama recursivamente.

            // Asigna los resultados parciales al arreglo t.
            for (int i = 0; i < n; i++) {
                t[i] = auxt[i];
            }

            // Calcula los resultados parciales de la parte derecha derecha del arreglo.
            int[] u = new int[n];
            iniceros(u, n); // Inicializa con ceros.
            u = divideyVenceras1(x, z, n / 2); // Llama recursivamente.

            // Combina los resultados parciales en un solo arreglo.
            int[] res = new int[2 * n];
            iniceros(res, 2 * n); // Inicializa con ceros.
            res = suma(r, 2 * n, s, n + (n / 2)); // Combinación de izquierda_izquierda y derecha_izquierda.
            int[] res2 = new int[(n / 2) + n];
            iniceros(res2, (n / 2) + n); // Inicializa con ceros.
            res2 = suma(t, n + (n / 2), u, n); // Combinación de izquierda_derecha y derecha_derecha.
            int[] res3 = new int[2 * n];
            iniceros(res3, 2 * n); // Inicializa con ceros.
            res3 = suma(res, 2 * n, res2, (n / 2) + n); // Combinación de los resultados anteriores.

            // Devuelve el resultado final.
            return res3;
        }
    }


    //completa de ceros los arreglos hasta la potencia de 2 más cercana
    static void iniceros(int arreglo[],int tamano)
    {
        for(int i=0; i<tamano; i++)
        {
            arreglo[i]=0;
        }
    }

    // Este método implementa el algoritmo de multiplicación tradicional para el caso base.
// Recibe dos arreglos de enteros y devuelve un arreglo que contiene el resultado de la multiplicación.
    public static int[] AlgoritmoTradicional(int arreglo1[], int arreglo2[]) {
        // Calcula el tamaño del resultado sumando las longitudes de los dos arreglos.
        int resultado[] = new int[arreglo1.length + arreglo2.length];
        int k; // Índice para controlar la posición en el arreglo resultado.
        int acarreo = 0; // Almacena el acarreo durante las multiplicaciones.

        // Recorre el segundo arreglo de derecha a izquierda.
        for (int i = arreglo2.length - 1; i >= 0; i--) {
            // Calcula la posición inicial en el arreglo resultado.
            k = resultado.length - (arreglo2.length - i);
            // Recorre el primer arreglo de derecha a izquierda.
            for (int j = arreglo1.length - 1; j >= 0; j--) {
                // Realiza la multiplicación y suma el acarreo.
                resultado[k] += arreglo1[j] * arreglo2[i] + acarreo;
                // Verifica si hay acarreo y ajusta el resultado y el acarreo.
                if (resultado[k] >= 10) {
                    acarreo = resultado[k] / 10;
                    resultado[k] = resultado[k] % 10;
                } else {
                    acarreo = 0;
                }
                k--; // Mueve el índice al siguiente elemento del arreglo resultado.
            }
            // Asigna el acarreo restante a la posición final del resultado.
            resultado[k] = acarreo;
            // Reinicia el acarreo para el siguiente ciclo.
            acarreo = 0;
        }
        return resultado; // Devuelve el resultado de la multiplicación.
    }

    //método para la suma de los arrays resultantes, controlando los acarreos
    public static int[] suma(int[] vec1, int tam1, int[] vec2, int tam2) {
        int[] res = new int[Math.max(tam1, tam2)];
        int carry = 0;
        int i = tam1 - 1;
        int j = tam2 - 1;
        int k = res.length - 1;

        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (i >= 0) {
                sum += vec1[i];
                i--;
            }
            if (j >= 0) {
                sum += vec2[j];
                j--;
            }
            res[k] = sum % 10;
            carry = sum / 10;
            k--;
        }

        if (carry > 0) {
            int[] newRes = new int[res.length + 1];
            System.arraycopy(res, 0, newRes, 1, res.length);
            newRes[0] = carry;
            return newRes;
        }
        return res;
    }

    // Método para convertir BigInteger[] a int[]
    private int[] convertBigIntegerArrayToIntArray(BigInteger[] bigIntArray) {
        int[] intArray = new int[bigIntArray.length];
        for (int i = 0; i < bigIntArray.length; i++) {
            intArray[i] = bigIntArray[i].intValue();
        }
        return intArray;
    }

    // Método para convertir int[] a BigInteger
    private BigInteger convertIntArrayToBigInteger(int[] intArray) {
        StringBuilder sb = new StringBuilder();
        for (int value : intArray) {
            sb.append(value);
        }
        // Utiliza el constructor de BigInteger que toma una cadena como argumento
        return new BigInteger(sb.toString());
    }
}