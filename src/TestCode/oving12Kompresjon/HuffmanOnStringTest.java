package oving12Kompresjon;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
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

    @Test
    public void testReadSequenceTable() throws Exception {
        HuffmanOnString h = new HuffmanOnString();
        String s = "spennende";
        for(char c:s.toCharArray()){
            short ss = (short)c;
            byte b1 = (byte)(ss & 0b11111111);
            String value1 = Integer.toBinaryString(0xFFFF & b1);
            byte b2 = (byte)(ss >> 8);
            String value2 = Integer.toBinaryString(0xFFFF & b2);
            System.out.println("Char: " + c + " int: " + (short)c + " byte1: " + value1 + " byte2: " + value2);
        }
        h.initHuffmantree(s.toCharArray());
        String s1 = h.getHuffmantree().toString();
        System.out.println(s1);
        HashMap<Integer, Integer> byteVaulesCodes = h.getByteVaulesCodes();

        for(Integer b: byteVaulesCodes.keySet()){
            String key = b.toString();
            String value = Integer.toBinaryString(0xFFFF & byteVaulesCodes.get(b));
            System.out.println("Byte value: " + key + " code: " + value);
        }

        h.compress(s.toCharArray());
        LinkedBlockingDeque<Byte> que = h.getQue();
        System.out.println("Compressed: ");
        for(Byte b:que){
            String sout = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            System.out.println(sout);
        }
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
        for(byte b : data){
            System.out.print(b+", ");
        }
        System.out.println();
        for(byte b : data){
            System.out.print((char)b);
        }
        System.out.println();
        HuffmanOnString h = new HuffmanOnString();
        h.initHuffmantree(data);
        HashMap<Integer, Integer> byteVaulesCodes = h.getByteVaulesCodes();

        for(Integer b: byteVaulesCodes.keySet()){
            String key = b.toString();
            String value = Integer.toBinaryString(0xFFFF & byteVaulesCodes.get(b));
            System.out.println("Byte value: " + key + " code: " + value);
        }
        h.compress(data);
        LinkedBlockingDeque<Byte> que = h.getQue();
        byte[] lstb = new byte[que.size()];
        int index = 0;
        for(byte b : que){
            lstb[index] = b;
            index++;
        }
        DataOutputStream utfil = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(output)));
        utfil.write(lstb,0, que.size());
        System.out.println("Skrev: ");
        for(byte b : que){
            System.out.print(b+", ");
        }
        System.out.println();
        utfil.close();
        innfil.close();

        File f2 = new File(output);
        DataInputStream innfil2 = new DataInputStream(new BufferedInputStream(new FileInputStream(f2)));
        length = (int) f2.length();
        data = new byte[length];
        innfil2.readFully(data,0,length);
        for(byte b : data){
            String s1 = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            System.out.println(s1);
        }
        for(byte b : data){
            System.out.print(b+", ");
        }
        System.out.println();
        innfil2.close();

    }

    @Test
    public void fileTest2() throws Exception {
        String file = "src/oving12Kompresjon/org2.txt";
        String output = "src/oving12Kompresjon/org2compressed.huff";
        File f = new File(file);
        DataInputStream innfil = new DataInputStream(new BufferedInputStream(new FileInputStream(f)));
        int length = (int) f.length();
        data = new byte[length];
        innfil.readFully(data,0,length);
        //for(byte b : data){
        //    System.out.print(b+", ");
        //}
        //System.out.println();
        //for(byte b : data){
        //    System.out.print((char)b);
        //}
        System.out.println();
        HuffmanOnString h = new HuffmanOnString();
        h.initHuffmantree(data);
        HashMap<Integer, Integer> byteVaulesCodes = h.getByteVaulesCodes();

        for(Integer b: byteVaulesCodes.keySet()){
            String key = b.toString();
            String value = Integer.toBinaryString(0xFFFF & byteVaulesCodes.get(b));
            System.out.println("Byte value: " + key + " code: " + value);
        }
        h.compress(data);
        LinkedBlockingDeque<Byte> que = h.getQue();
        byte[] lstb = new byte[que.size()];
        int index = 0;
        for(byte b : que){
            lstb[index] = b;
            index++;
        }
        DataOutputStream utfil = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(output)));
        utfil.write(lstb,0, que.size());
        System.out.println("Skrev: ");
        for(byte b : que){
            System.out.print(b+", ");
        }
        System.out.println();
        utfil.close();
        innfil.close();

        File f2 = new File(output);
        DataInputStream innfil2 = new DataInputStream(new BufferedInputStream(new FileInputStream(f2)));
        length = (int) f2.length();
        data = new byte[length];
        innfil2.readFully(data,0,length);
        for(byte b : data){
            String s1 = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            System.out.println(s1);
        }
        for(byte b : data){
            System.out.print(b+", ");
        }
        System.out.println();
        innfil2.close();

    }
}