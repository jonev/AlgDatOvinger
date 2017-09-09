package oving3Sortering;

import javax.print.DocFlavor;
import java.awt.image.AreaAveragingScaleFilter;
import java.io.PrintWriter;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by JoneSkole on 23.08.2017.
 */
public class o3 {
    public static void main(String[] args) {
        try (PrintWriter w1 = new PrintWriter("Result1.txt", "UTF-8");
        PrintWriter w2 = new PrintWriter("Result2.txt", "UTF-8");
        PrintWriter w3 = new PrintWriter("Result3.txt", "UTF-8");


        ){

        long start = 0L;
        long tidsforbruk = 0L;
        for (int antalforswitch = 2; antalforswitch < 500; antalforswitch += 40) {
            Misc.TimeTaking t1 = new Misc.TimeTaking(10);
            for (int j = 0; j < 10; j++) {

                int antalliarray = 100000;
                int[] ar1 = new int[antalliarray];
                for (int i = 0; i < ar1.length; i++) {
                    ar1[i] = ThreadLocalRandom.current().nextInt(antalliarray*-2, antalliarray*2 + 1);
                }

                int[] ar2 = new int[antalliarray];
                for (int i = 0; i < ar2.length; i++) {
                    ar2[i] = ThreadLocalRandom.current().nextInt(antalliarray*-2, antalliarray*2 + 1);
                }
//
                int[] ar3 = new int[antalliarray];
                for (int i = 0; i < ar3.length; i++) {
                    ar3[i] = ThreadLocalRandom.current().nextInt(antalliarray*-2, antalliarray*2 + 1);
                }

                t1.start();
                start = System.nanoTime();
                Algoritmene.quickSort(ar1, 0, ar1.length - 1, antalforswitch);
                t1.stop();
                tidsforbruk = System.nanoTime() - start;
                System.out.println("Resultat av Quicksort1: " + Algoritmene.checkIfSorted(ar1, 0, ar1.length - 1) + " switch algoritme:" + antalforswitch + " tidforbruk: " + tidsforbruk); // + (double) tidsforbruk / 1000000000.0);
                //w1.println(antalforswitch + ";" + (double) tidsforbruk / 1000.0);

                 start = System.nanoTime();
                 Algoritmene.quickSort(ar2, 0, ar2.length-1, antalforswitch);
                 tidsforbruk = System.nanoTime() - start;
                 System.out.println("Resultat av Quicksort2: " + Algoritmene.checkIfSorted(ar2, 0, ar2.length-1) + " switch algoritme:" + (antalforswitch) + " tidforbruk: " + tidsforbruk); // + (double)tidsforbruk / 1000000000.0);
                 //w2.println(antalforswitch + ";" + (double) tidsforbruk / 1000.0);
//
                 // start = System.nanoTime();
                 // Algoritmene.quickSort(ar3, 0, ar3.length-1, antalforswitch);
                 // tidsforbruk = System.nanoTime() - start;
                 // System.out.println("Resultat av Quicksort3: " + Algoritmene.checkIfSorted(ar3, 0, ar3.length-1) + " switch algoritme:" + (antalforswitch) + " tidforbruk: " + tidsforbruk); // + (double)tidsforbruk / 1000000000.0);
                 // w3.println(antalforswitch + ";" + (double) tidsforbruk / 1000.0);


                // start = System.nanoTime();
                // Algoritmene.insertionsort(ar2, 0, 9);
                // tidsforbruk = System.nanoTime() - start;
                // System.out.println("Resultat av Insert: " + Algoritmene.checkIfSorted(ar2, 0, 9) + " tidforbruk: " + tidsforbruk);
                // Algoritmene.printArray(ar1);

            }
            System.out.println(t1.finish());
        }
        } catch (Exception e){

        } finally {

        }
    }
}

class Algoritmene{
    public static void quickSort(int[] t, int v, int h, int switchAlgoritm){
        if(h - v > switchAlgoritm){
            int delepos = splitt(t, v, h);
            quickSort(t, v, delepos - 1, switchAlgoritm);
            quickSort(t, delepos + 1, h, switchAlgoritm);
        } else insertionsort(t, v, h);
    }

    public static void shellsort(int[] table){
        int s = table.length/2;
        while (s>0){
            for (int i = s; i < table.length; i++) {
                int j = i;
                int flytt = table[i];
                while (j>=s && flytt < table[j-s]){
                    table[j] = table[j-s];
                    j -=s;
                    //System.out.println("F " + (j-s) + " til " + j);
                    //printArray(table);
                }
                table[j] = flytt;
                //System.out.println("Setter " + flytt + " til " + j);
                // printArray(table);
            }
            s = (s == 2) ? 1 : (int)(s/2.2);
        }
    }

    public static void printArray(int[] array, int v, int h){
        String t = "";
        String s = "";
        for (int i = v; i < h; i++) {
            s += array[i] + " ";
            t += i + " ";
        }
        System.out.println(t);
        System.out.println(s);
    }

    public static int bubblesort(int[] array){
        int runder = 0;
        boolean switched = false;
        do{
            switched = false;
            for (int i = 1; i < array.length; i++) {
                runder++;
                if(array[i-1] > array[i]){
                    int help = array[i];
                    array[i] = array[i-1];
                    array[i-1] = help;
                    switched = true;
                }
            }
        } while (switched);
        return runder;
    }

    public static void insertionsort(int[] t, int v, int h){
        //printArray(t, v, h);
        for(int j = v+1; j< h+1; ++j){
            int bytt = t[j];
            int i = j-1;
            while(i >= v && t[i]>bytt){
                t[i+1] = t[i];
                i--;
            }
            t[i+1] = bytt;
        }
        // printArray(t, v, h);
    }

    public static boolean checkIfSorted(int[] table, int v, int h){
        for (int i = v; i < h; i++) {
            if(table[i] > table[i+1]) return false;
        }
        return true;
    }

    public static int splitt(int[] t, int v, int h){
        int iv;
        int ih;
        int m = median3sort(t, v, h);
        int dv = t[m];
        bytt(t, m, h-1);
        for (iv = v, ih = h-1;;){
            while (t[++iv] < dv);
            while (t[--ih] > dv);
            if(iv >= ih) break;
            bytt(t, iv, ih);
        }
        bytt(t, iv, h-1);
        return iv;
    }

    public static int median3sort(int[] t, int v, int h){
        int m = (v+h)/2;
        if(t[v]>t[m]) bytt(t, v, m);
        if(t[m]>t[h]){
            bytt(t, h, m);
            if(t[v]>t[m])bytt(t, v, m);
        }
        return m;
    }
    public static void bytt(int[] t, int i, int j){
        int k = t[j];
        t[j] = t[i];
        t[i] = k;
    }
}
