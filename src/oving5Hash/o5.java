package oving5Hash;

import java.io.CharConversionException;

/**
 * Created by JoneSkole on 13.09.2017.
 */
public class o5 {
}

class HashTable{
    int A = 1327217885;
    private String[] tbl;
    private int tblsize;
    private int sizeInPow2;

    public HashTable(int sizeInPow2) {
        this.sizeInPow2 = sizeInPow2;
        tblsize = (int)Math.pow(2.0, sizeInPow2);
        tbl = new String[tblsize];
    }

    public int h1(int key){
        return key*A>>(31-sizeInPow2);
    }

    public int h2(int key){
        return (2 * Math.abs(key) + 1)%tblsize;
    }

    public int hash(int key, int tries){
        return (h1(key) + (tries*h2(key)))%tblsize;
    }

    public void hash(String name){
        int key = 0; //TODO legge inn fuksjon for å hente ut key fra string
        int tries = 0;
        while(true){
            int h = hash(key, tries);
            if(tbl[h] == null){
                tbl[h] = ""; // TODO fiks denne
            }
        }
    }
}


