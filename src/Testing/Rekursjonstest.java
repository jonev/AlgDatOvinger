package Testing;

import org.omg.CORBA.Current;
import sun.util.resources.cldr.ebu.CurrencyNames_ebu;

/**
 * Created by JoneSkole on 21.08.2017.
 */
public class Rekursjonstest {
    public static void main(String[] args) {
        //ned(20);
        // System.out.println(rekkesum(10));

        System.out.println(palidron("tut"));
        System.out.println(palidron("tute"));
        System.out.println(palidron("tutetr"));
        System.out.println(palidron("madam"));
        System.out.println(palidron("redder"));
        System.out.println(palidron("reder"));
    }

    public static void ned(int n){
        if(n == 0) return;
        System.out.println(n);
        ned(n-1);
    }

    public static int rekkesum(int n){
        if(n == 1) return 1;
        return rekkesum(n-1) + n;
    }

    public static boolean palidron(String word){
        if(word == null) return false;
        return palidron(word, 0);
    }

    private static boolean palidron(String word, int currentposition){
        if(currentposition > word.length()/2) return true;
        if(word.charAt(currentposition) != word.charAt(word.length() - 1 - currentposition)) return false;
        return palidron(word, currentposition + 1);
    }

    public static int combinationsof100(int amount, int typesOfCoins, int[] valuesOfcoins){
        if(typesOfCoins <= 0) return 0;
        if(amount < 0) return 0;
        if(amount == 0) return 1;
        return 0;

    }

    public static void printFlis(int dir){
        String s = "";
        switch (dir){
            case 1:
                s =  "|,";
                break;
            case 2:
                s =  "|'";
                break;
            case 3:
                s =  ",|";
                break;
            case 4:
                s =  "'|";
                break;
            default:
                s =  "  ";
        }
        System.out.print(s);
    }



}

enum Direction {
    RightTop, RightBottom, LeftTop, LeftBottom
}

class Flis {
    public int[][] tab;

    public Flis(int størrelse) {
        tab = new int[størrelse][størrelse];
    }

    public String toString() {
        String res = "";
        for (int i = 0; i < tab.length; ++i) {
            for (int j = 0; j < tab.length; ++j) {
                res += tab[i][j];
            }
            res += '\n';
        }
        return res;
    }

    public void leggFlis(int n, int startX, int startY, int slukX, int slukY) {
        if (n == 1) return;
        int midt = n / 2;
        int midtX = startX + midt;
        int midtY = startY + midt;

        if (slukX < midtX && slukY < midtY) { // sluk i øvre venstre del
            leggFlis(n/2, startX, startY, slukX, slukY); // øvre venstre
            leggFlis(n/2, midtX, startY, midtX, midtY - 1); // øvre høyre
            leggFlis(n/2, startX, midtY, midtX - 1, midtY); // nedre venstre
            leggFlis(n/2, midtX, midtY, midtX, midtY); // nedre høyre
            tab[midtX][midtY-1] = tab[midtX-1][midtY] = tab[midtX][midtY] = 4;
        }
        else if (slukX < midtX) { // sluk i nedre venstre del
            leggFlis(n/2, startX, startY, midtX-1, midtY-1);
            leggFlis(n/2, midtX, startY, midtX, midtY-1);
            leggFlis(n/2, startX, midtY, slukX, slukY);
            leggFlis(n/2, midtX, midtY, midtX, midtY);
            tab[midtX-1][midtY-1] = tab[midtX][midtY-1] = tab[midtX][midtY] = 3;
        }
        else if (slukY < midtY) { // sluk i øvre høyre del
            leggFlis(n/2, startX, startY, midtX-1, midtY-1);
            leggFlis(n/2, midtX, startY, slukX, slukY);
            leggFlis(n/2, startX, midtY, midtX-1, midtY);
            leggFlis(n/2, midtX, midtY, midtX, midtY);
            tab[midtX-1][midtY-1] = tab[midtX-1][midtY] = tab[midtX][midtY] = 1;
        }
        else { // sluk i nedre høyre del
            leggFlis(n/2, startX, startY, midtX-1, midtY-1);
            leggFlis(n/2, midtX, startY, midtX, midtY-1);
            leggFlis(n/2, startX, midtY, midtX-1, midtY);
            leggFlis(n/2, midtX, midtY, slukX, slukY);
            tab[midtX-1][midtY-1] = tab[midtX][midtY-1] = tab[midtX-1][midtY] = 2;
        }
    }

    public static void main(String[] args) {
        int m = 2;
        int n = (int)Math.pow(2, m);
        Flis golv = new Flis(n);
        int slukX = 2;
        int slukY = 2;
        golv.leggFlis(n, 0, 0, slukX, slukY);
        for (int i = 0; i < golv.tab.length; i++) {
            for (int j = 0; j < golv.tab[i].length; j++) {
                Rekursjonstest.printFlis(golv.tab[i][j]);
            }
            System.out.print("\n");
        }
        System.out. println(golv);
    }
}