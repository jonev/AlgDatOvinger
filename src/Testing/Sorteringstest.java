package Testing;

/**
 * Created by JoneSkole on 28.08.2017.
 */
public class Sorteringstest {
    public static void main(String[] args) {
        int[] testtbl = {4, 2, 3, 6, 1, 8, 2, 4};
        //insertSort(testtbl);
        bubblesort(testtbl);
    }

    private static void bytt(int[] tbl, int from, int to){
        int temp = tbl[from];
        tbl[from] = tbl[to];
        tbl[to] = temp;
    }

    private static void insertSort(int[] tbl){
        for (int j = 1; j < tbl.length; j++) {
            int bytt = tbl[j];
            int i = j-1;
            while (i >= 0 && tbl[i]>bytt){
                tbl[i+1] = tbl[i];
                i--;
            }
            tbl[i+1] = bytt;
        }
    }

    private static void bubblesort(int[] t){
        for (int i = t.length-1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if(t[j]>t[j+1]){
                    bytt(t, j, j+1);
                }
            }
        }
    }
}
