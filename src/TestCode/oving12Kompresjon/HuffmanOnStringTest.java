package oving12Kompresjon;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

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
        //HuffmanOnString h = new HuffmanOnString("spennende pennevenner");
        HuffmanOnString h = new HuffmanOnString();
        String s = "spennende pennevenner";
        for(char c:s.toCharArray()){
            h.read(c);
        }
        h.makeTree();
        String s1 = h.getHuffmantree().toString();
        System.out.println(s1);
        HashMap<Byte, Short> byteVaulesCodes = h.getByteVaulesCodes();
        for(Byte b: byteVaulesCodes.keySet()){
            String key = b.toString();
            String value = Integer.toBinaryString(0xFFFF & byteVaulesCodes.get(b));
            System.out.println("Byte value: " + key + " code: " + value);
        }
    }
}