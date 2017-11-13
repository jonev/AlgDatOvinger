package oving12Kompresjon;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author jonev on 03.11.2017.
 */
public class HuffmanOnStringTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    static byte[] data;
    @Test
    public void fileTest() throws Exception {
        String file = "src/oving12Kompresjon/org1.txt";
        String output = "src/oving12Kompresjon/org1compressed.huff";
        File f = new File(file);
        DataInputStream innfil = new DataInputStream(new BufferedInputStream(new FileInputStream(f)));
        int length = (int) f.length();
        data = new byte[length];
        innfil.readFully(data,0,length);
        //for(byte b : data){
        //    System.out.print(b+ " " + (char)(b&0xFF) + ", ");
        //}
        System.out.println("Lest fra orginalfil størrelse: " + data.length);
        for(byte b : data){
            String s1 = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            System.out.println(s1);
        }
        System.out.println();
        Huffman h = new Huffman();
        h.initHuffmantree(data);
        //h.getHuffmantree().print("", true);
        HashMap<Integer, HuffmanNode> byteVaulesCodes = h.getByte_codes_Hashmap();

        for(Integer b: byteVaulesCodes.keySet()){
            String key = b.toString();
            String value = Integer.toBinaryString(0xFFFF & byteVaulesCodes.get(b).getCode());
            System.out.println("Byte value: " + key + " code: " + value);
        }
        h.compress(data);
        LinkedBlockingDeque<Byte> que = h.getCompressedData();
        byte[] lstb = new byte[que.size()];
        int index = 0;
        System.out.println("Skrev: " + que.size());
        for(byte b : que){
            lstb[index] = b;
            String s1 = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            System.out.println(s1);
            index++;
        }
        DataOutputStream utfil = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(output)));
        utfil.write(lstb,0, que.size());
        for(byte b : data){
        }
        //for(byte b : que){
        //    System.out.print(b+ " " + (char)(b&0xFF) + ", ");
        //}
        //System.out.println();
        utfil.close();
        innfil.close();

        File f2 = new File(output);
        DataInputStream innfil2 = new DataInputStream(new BufferedInputStream(new FileInputStream(f2)));
        length = (int) f2.length();
        data = new byte[length];
        innfil2.readFully(data,0,length);
        System.out.println("Leste: ");
       // for(byte b : data){
       //     System.out.print(b+ " " + (char)(b&0xFF) + ", ");
       // }
        // System.out.println();
        for(byte b : data){
            String s1 = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            System.out.println(s1);
        }
        System.out.println();
        innfil2.close();

        // ArrayList<Byte> decomp = h.decompress(data);
        // for(Byte b : decomp){
        //     System.out.print((char)(b&0xFF));
        // }
        // System.out.println();
    }

    @Test
    public void fileTest2() throws Exception {
        String file = "src/oving12Kompresjon/org2.txt";
        String output = "src/oving12Kompresjon/org2compressed.huff";
        String outputdecomp = "src/oving12Kompresjon/org2decomp.txt";
        File f = new File(file);
        DataInputStream innfil = new DataInputStream(new BufferedInputStream(new FileInputStream(f)));
        int length = (int) f.length();
        data = new byte[length];
        innfil.readFully(data,0,length);
        System.out.println("Lest fra orginalfil størrelse: " + data.length);
        System.out.println();
        Huffman h = new Huffman();
        h.initHuffmantree(data);
        HashMap<Integer, HuffmanNode> byteVaulesCodes = h.getByte_codes_Hashmap();
        h.compress(data);
        LinkedBlockingDeque<Byte> que = h.getCompressedData();
        System.out.println("Komp str: " + que.size());
        byte[] lstb = new byte[que.size()];
        int index = 0;
        for(byte b : que){
            lstb[index] = b;
            index++;
        }
        DataOutputStream utfil = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(output)));
        utfil.write(lstb,0, que.size());
        utfil.close();
        innfil.close();

        File f2 = new File(output);
        DataInputStream innfil2 = new DataInputStream(new BufferedInputStream(new FileInputStream(f2)));
        length = (int) f2.length();
        data = new byte[length];
        innfil2.readFully(data,0,length);
        System.out.println();
        innfil2.close();
    }

    @Test
    public void fileTestDekompEgetProgram() throws Exception {
        String output = "src/oving12Kompresjon/org2compressed.huff";
        String outputdecomp = "src/oving12Kompresjon/org2decomp.txt";
        File f2 = new File(output);
        DataInputStream innfil2 = new DataInputStream(new BufferedInputStream(new FileInputStream(f2)));
        int length = (int) f2.length();
        data = new byte[length];
        innfil2.readFully(data,0,length);

        System.out.println();
        innfil2.close();
        HuffmanDecomp h = new HuffmanDecomp(data, 0, 1023);

        ArrayList<Byte> decomp = h.decompress(data);
        byte[] lstb2 = new byte[decomp.size()];
        int index2 = 0;
        for(Byte b : decomp){
            lstb2[index2] = b;
            index2++;
        }
        //System.out.println();
        DataOutputStream utfildecomp = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(outputdecomp)));
        utfildecomp.write(lstb2,0, lstb2.length);
        utfildecomp.close();

    }


    @Test
    public void fileTest4() throws Exception {
        String file = "src/oving12Kompresjon/opg12.txt";
        String output = "src/oving12Kompresjon/opg12compressed.huff";
        String outputdecomp = "src/oving12Kompresjon/opg12decomp.txt";
        File f = new File(file);
        DataInputStream innfil = new DataInputStream(new BufferedInputStream(new FileInputStream(f)));
        int length = (int) f.length();
        data = new byte[length];
        innfil.readFully(data,0,length);
        System.out.println("Lest fra orginalfil størrelse: " + data.length);

        //for(byte b : data){
        //    System.out.print(b+", ");
        //}
        //System.out.println();
        //for(byte b : data){
        //    System.out.print((char)b);
        //}
        System.out.println();
        Huffman h = new Huffman();
        h.initHuffmantree(data);
        HashMap<Integer, HuffmanNode> byteVaulesCodes = h.getByte_codes_Hashmap();

        //for(Integer b: byteVaulesCodes.keySet()){
        //    String key = b.toString();
        //    String value = Integer.toBinaryString(0xFFFF & byteVaulesCodes.get(b).getCode());
        //    System.out.println("Byte value: " + key + " code: " + value);
        //}
        h.compress(data);
        LinkedBlockingDeque<Byte> que = h.getCompressedData();
        System.out.println("Komp str: " + que.size());
        byte[] lstb = new byte[que.size()];
        int index = 0;
        for(byte b : que){
            lstb[index] = b;
            index++;
        }
        DataOutputStream utfil = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(output)));
        utfil.write(lstb,0, que.size());
        //System.out.println("Skrev: ");
        //for(byte b : que){
        //    System.out.print(b+", ");
        //}
        //System.out.println();
        utfil.close();
        innfil.close();

        File f2 = new File(output);
        DataInputStream innfil2 = new DataInputStream(new BufferedInputStream(new FileInputStream(f2)));
        length = (int) f2.length();
        data = new byte[length];
        innfil2.readFully(data,0,length);
        //for(byte b : data){
        //    String s1 = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
        //    System.out.println(s1);
        //}
        //for(byte b : data){
        //    System.out.print(b+", ");
        //}
        System.out.println();
        innfil2.close();

        //ArrayList<Byte> decomp = h.decompress(data);
        //for(Byte b : decomp){
        //    System.out.print((char)(b&0xFF));
        //}
        //byte[] lstb2 = new byte[decomp.size()];
        //int index2 = 0;
        //for(Byte b : decomp){
        //    lstb2[index2] = b;
        //    index2++;
        //}
        ////System.out.println();
        //DataOutputStream utfildecomp = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(outputdecomp)));
        //utfildecomp.write(lstb2,0, lstb2.length);
        //utfildecomp.close();

    }


}