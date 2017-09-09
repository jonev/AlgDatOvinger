package oving2Rekursjon;

/**
 * Created by JoneSkole on 23.08.2017.
 */
public class o2 {
    public static void main(String[] args) {


        double x = 1.00013;
        int n = 10000;

        for (int i = 0; i < 10; i++) {
            for (int antallforsok = 0; antallforsok < 10; antallforsok++) { // tidsmålingsløkke

                long start = 0L;
                long tidsforbruk = 0L;
                double ans = 0;
                start = System.nanoTime();
                // kode for algoritmen
                ans = Algoritmene.rekursjon( n, x);
                tidsforbruk = System.nanoTime() - start;
                System.out.println("-Nr: " + antallforsok + " n: " + n + " x:" + x + " svar: " + ans + " tidsforbruk: " + tidsforbruk);

                start = System.nanoTime();
                // kode for algoritmen
                ans = Algoritmene.rekursjon2(n, x);
                tidsforbruk = System.nanoTime() - start;
                System.out.println("--Nr: " + antallforsok + " n: " + n + " x:" + x + " svar: " + ans + " tidsforbruk: " + tidsforbruk);

                start = System.nanoTime();
                // kode for algoritmen
                ans = Math.pow(x, n);
                tidsforbruk = System.nanoTime() - start;
                System.out.println("Nr: " + antallforsok + " n: " + n + " x:" + x + " svar: " + ans + " tidsforbruk: " + tidsforbruk);


            }
        }
    }
}

class Algoritmene{
    public static double rekursjon(int n, double x){
        if(n < 0) return -1;
        if(n == 0) return 1;
        return rekursjon(n-1, x)*x;
    }

    public static double rekursjon2(int n, double x){
        if(n == 0) return 1;
        if(n%2 == 0) return rekursjon2((n/2), x*x); // partall
        return rekursjon2((n-1)/2, x*x)*x; // oddetall
    }
}
