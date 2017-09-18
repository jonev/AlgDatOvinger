package oving5Hash;

import Misc.TimeTaking;

import java.io.BufferedReader;
import java.io.CharConversionException;
import java.io.FileReader;
import java.sql.Time;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by JoneSkole on 13.09.2017.
 */
public class o5 {
    public static void main(String[] args) throws Exception{

        HashTable hash = new HashTable(79);
        try(BufferedReader in = new BufferedReader(new FileReader("src/oving5Hash/navn.txt"))){
            String line;
            while((line = in.readLine()) != null)
            {
                hash.hash(line);
            }
            System.out.println(hash.getSummary());

            System.out.println(("Kimia Abtahi".equals(hash.getNameFromTable("Kimia Abtahi"))) ? "Korrekt" : "Feil");
            System.out.println(("Kristoffer Arntzen".equals(hash.getNameFromTable("Kristoffer Arntzen"))) ? "Korrekt" : "Feil");
            System.out.println(("Jostein Johansen Aune".equals(hash.getNameFromTable("Jostein Johansen Aune"))) ? "Korrekt" : "Feil");
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            int[] ar1 = new int[5000000];
            for (int i = 0; i < ar1.length; i++) {
                ar1[i] = ThreadLocalRandom.current().nextInt(0, 5000000*5 + 1);
            }
            TimeTaking t1 = new TimeTaking(10);
            HashTable integerHash = null;


            for (int i = 0; i < 5; i++) {
                integerHash = new HashTable(6000011);
                t1.start();
                for (int j = 0; j < ar1.length; j++) {
                    integerHash.hash(ar1[j]);
                }
                t1.stop();
            }
            t1.stop();

            TimeTaking t2 = new TimeTaking(10);

            HashMap<Integer, Integer> hm = null;
            for (int i = 0; i < 5; i++) {
                hm = new HashMap<Integer, Integer>();
                t2.start();
                for (int j = 0; j < ar1.length; j++) {
                    hm.put(ar1[j], ar1[j]);
                }
                t2.stop();
            }

            System.out.println("Tid selvlagd hash: " + (double)t1.finish()/1000000000 + " oppsumering: " + integerHash.getSummary() + " tid på HashMap: " + (double)t2.finish()/1000000000);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

class HashTable{
    private Object[] tbl;
    private int tblsize;
    private long sizeInPow2;
    private long collisioncount = 0;
    private long elementscount = 0;

    public HashTable(int sizeAsPrimeNumber) {
        tblsize =  sizeAsPrimeNumber;
        tbl = new Object[tblsize];
    }

    public long h1(long key){
        return (key%tblsize);
    }

    public long h2(long key){
        return (key%(tblsize-1)+1);
    }

    public long hash(long key, long tries){
        return (h1(key) + (tries*h2(key)))%tblsize;
    }

    public long hash(String name){
        long key = stringToInt(name);
        long tries = 0;
        while(tries<tblsize){
            int h = (int)hash(key, tries);
            if(tbl[h] == null){
                tbl[h] = name;
                elementscount++;
                return h;
            }else {
                System.out.println("Krasj: " + tbl[h] + " - " + name);
                collisioncount++;
            }
            tries++;
        }
        return -1;
    }

    public int hash(long key){
        int tries = 0;
        while(tries<tblsize){
            int h = (int)hash(key, tries);
            if(tbl[h] == null){
                tbl[h] = key;
                elementscount++;
                return h;
            }else {
                // System.out.println("Krasj: " + tbl[h] + " - " + key);
                collisioncount++;
            }
            tries++;
        }
        return -1;
    }

    public int getFromTable(String name){
        int key = stringToInt(name);
        int tries = 0;
        while(tries<tblsize){
            int h = (int)hash(key, tries);
            if(tbl[h] != null && tbl[h].equals(name)){
                return h;
            }
            tries++;
        }
        return -1;
    }

    public String getNameFromTable(String name){
        int key = stringToInt(name);
        int tries = 0;
        while(tries<tblsize){
            int h = (int)hash(key, tries);
            if(tbl[h] != null && tbl[h].equals(name)){
                return (String)tbl[h];
            }
            tries++;
        }
        return null;
    }

    public int stringToInt(String key){
        int intvalue = 0;
        for (int i = 0; i < key.length(); i++) {
            intvalue += ((int)key.charAt(i))*Math.pow(7.0, i);
        }
        return intvalue;
    }

    public String getSummary(){
        String s = "Lastfaktor: " + elementscount;
        s += "/" + tblsize + " = " + (double)((double)elementscount/(double)tblsize);
        s += " antall kolisjoner: " + collisioncount;
        return s;
    }
}


