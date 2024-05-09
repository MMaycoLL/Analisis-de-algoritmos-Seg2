package metodos;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class _10_DivideVenceras1Estatico {
    public static void main(String[] args){


        //16x16
        BigInteger n3 = new BigInteger("9384938384738374");
        BigInteger num3 = new BigInteger("4321245679876543");


        List<BigInteger> lista1 = new ArrayList<>();

        lista1.add(n3);


        List<BigInteger> lista2 = new ArrayList<>();

        lista2.add(num3);
      
        for(int i=0; i<8; i++){
            System.out.println("----------");
            int[] arr1 = convertBigIntegerToArray(lista1.get(i));
            int [] arr2 = convertBigIntegerToArray(lista2.get(i));
            System.out.println(arr1.length+" X "+arr2.length);

            double startTime = System.nanoTime();
            BigInteger result = convertArrayToBigInteger(divideyVenceras1(arr1, arr2, arr1.length));
            double endTime = System.nanoTime();
            System.out.println("Tiempo: "+(endTime-startTime)/100_000);
            System.out.println("Resultado: "+result);
        }
    }


    //método de llamado dv1
    public static int[] divideyVenceras1(int vec1[],int vec2[],int n) {

        if(n==2) {//caso base
            int[] resultado = new int[n+1];
            resultado = AlgoritmoTradicional(vec1,vec2);
            return resultado;
        }
        else {//crea 4 arreglosunidimensionales tamaño n/2
            int[] x=new int[n/2];
            int[] y=new int[n/2];
            int[] z=new int[n/2];
            int[] w=new int[n/2];

            for(int i=0;i<n/2;i++) {
                w[i]=vec1[i];//recibe la izquierda del multiplicador[2,4]
                y[i]=vec2[i];//recibe la izquierda del multiplicando[1,3]
                x[i]=vec1[i+(n/2)];//recibe la derecha del multiplicador[1,3]
                z[i]=vec2[i+(n/2)];//recibe la derecha del multiplicando[2,1]
            }

            //estos reciben los resultados parciales de la parte izquierda
            //[0, 3, 1, 2]
            int[] r=new int[2*n];
            int[] auxr = new int[n];
            iniceros(r,2*n);
            auxr=divideyVenceras1(w,y,n/2);//

            for(int i=0;i<n;i++)
            {
                r[i]=auxr[i];
            }

            //estos reciben los resultados parciales de la parte derecha(derecha_izquierda)
            //[0, 0, 2, 6]
            int[] s = new int[n+(n/2)];
            int[] auxs = new int[n+(n/2)];
            iniceros(s,n+(n/2));
            auxs=divideyVenceras1(w,z,n/2);

            for(int i=0;i<n;i++)
            {
                s[i]=auxs[i];
            }

            // Resultado parcial de la parte izquierda (izquierda_derecha)
            int[] t=new int[(n/2)+n];
            int[] auxt=new int[n];
            iniceros(t,(n/2)+n);
            auxt=divideyVenceras1(x,y,n/2);

            for(int i=0;i<n;i++) {
                t[i]=auxt[i];
            }

            // Resultado parcial de la parte derecha (derecha_derecha)
            int[] u=new int[n];
            iniceros(u,n);
            u=divideyVenceras1(x,z,n/2);

            //Combinación de izquierda_izquierda y derecha_izquierda
            int[] res=new int[2*n];
            iniceros(res,2*n);
            res=suma(r,2*n,s,n+(n/2));

            //Combinación de izquierda_derecha y derecha_derecha:
            int[] res2=new int[(n/2)+n];
            iniceros(res2,(n/2)+n);
            res2=suma(t,n+(n/2),u,n);


            int[] res3=new int[2*n];
            iniceros(res3,2*n);

            //Combinación de los resultados anteriores:
            res3=suma(res,2*n,res2,(n/2)+n);

            //el resultado final se almacena en el arreglo res3 y se devuelve al final de la función divideyVenceras1
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

    //método para caso base
    public static int[] AlgoritmoTradicional (int arreglo1[], int arreglo2[]) {
        int resultado[]= new int [arreglo1.length+ arreglo2.length];
        int k;
        int acarreo = 0;

        for (int i = arreglo2.length -1; i>=0; i--){

            //Verifica a qué tan lejos está del borde derecho del arreglo resultado
            k  = resultado.length - (arreglo2.length - i);
            //Recorre el arreglo multiplicando desde la última posición
            for (int j = arreglo1.length - 1; j >= 0; j--) {

                //Realiza la multiplicación y suma sobre el resultado anterior
                resultado[k] += arreglo1[j] * arreglo2[i] + acarreo ;
                if (resultado[k] >= 10) {
                    acarreo = resultado[k]/10;
                    resultado[k] = resultado[k]%10;
                } else {
                    acarreo = 0;
                }

                k--;
            }
            resultado[k]=acarreo;
            acarreo = 0;
        }
        return resultado;
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


    public static int[] convertBigIntegerToArray(BigInteger number) {
        String numberString = number.toString();
        int[] array = new int[numberString.length()];

        for (int i = 0; i < numberString.length(); i++) {
            array[i] = Character.getNumericValue(numberString.charAt(i));
        }

        return array;
    }

    public static BigInteger convertArrayToBigInteger(int[] array) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < array.length; i++) {
            stringBuilder.append(array[i]);
        }

        return new BigInteger(stringBuilder.toString());
    }
}
