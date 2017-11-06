package oving11Automat;

public class Automat{
    char[] alfabet;
    int[] aksept;
    int[][] nestetilstand;

    public Automat(char[] alfabet, int[] aksept, int[][] nestetilstand) {
        this.alfabet = alfabet;
        this.aksept = aksept;
        this.nestetilstand = nestetilstand;
    }

    public boolean sjekkInput(char[] input){
        int tilstand = 0;
        int alf = -1;
        for(char c : input){
            alf = -1;
            for (int i = 0; i < alfabet.length; i++) {
                if(c == alfabet[i]){
                    alf = i;
                }
            }
            if(alf < 0) throw new NullPointerException("Input finnes ikke i alfabetet");
            tilstand = nestetilstand[tilstand][alf];
        }
        for (int i = 0; i < aksept.length; i++) {
            if(tilstand == aksept[i]){
                return true;
            }
        }
        boolean f = !(!(false));
        return false;
    }
}
