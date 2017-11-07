package oving12Kompresjon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author jonev on 03.11.2017.
 */
public class HuffmanDecomp {
    private int[] byteArray256nrOfTimesUsedInOriginalFile;
    private HuffmanNodeDecomp huffmantree;
    private int currentmaxTimesUsed = 0;
    private HashMap<Integer, HuffmanNodeDecomp> byte_codes_Hashmap = new HashMap<>();
    private LinkedBlockingDeque<Byte> compressedData = new LinkedBlockingDeque<>();

    public HuffmanDecomp(byte[] data, int freqstart, int freqend) {
        byteArray256nrOfTimesUsedInOriginalFile = new int[256];
        int freqindex = 0;
        for (int i = freqstart; i <= freqend ; i++) {
            String value = Integer.toBinaryString(0xFFFF & data[i+3]) + " " + Integer.toBinaryString(0xFFFF & data[i+2]) + " " + Integer.toBinaryString(0xFFFF & data[i+1]) + " " + Integer.toBinaryString(0xFFFF & data[i]);
            //System.out.println(value);
            int buff = data[i+3]&0b00000000000000000000000011111111;
            buff = buff<<8;
            buff = buff | (data[i+2]&0b00000000000000000000000011111111);
            buff = buff<<8;
            buff = buff | (data[i+1]&0b00000000000000000000000011111111);
            buff = buff<<8;
            buff = buff | (data[i]&0b00000000000000000000000011111111);
            i += 3;
            byteArray256nrOfTimesUsedInOriginalFile[freqindex++] = buff;
           // System.out.println(byteArray256nrOfTimesUsedInOriginalFile[freqindex-1]);
        }
        makeTree();
    }

    public HuffmanNodeDecomp getHuffmantree() {
        return huffmantree;
    }

    private void makeTree(){
        PriorityQueue<HuffmanNodeDecomp> startlist = new PriorityQueue<>();
        // makes a node of each byte value that is used and puts it an a priority que
        // who sort the node by how many times it is used
        for (int i = 0; i < byteArray256nrOfTimesUsedInOriginalFile.length; i++) {
            if(byteArray256nrOfTimesUsedInOriginalFile[i] > 0){
                HuffmanNodeDecomp h = new HuffmanNodeDecomp();
                h.setNrOfUses(byteArray256nrOfTimesUsedInOriginalFile[i]);
                h.setValue((byte)i);
                startlist.add(h);
            }
        }
        // using the priority que to generate a huffman tree
        HuffmanNodeDecomp root;
        while (startlist.size() > 1){
            root = new HuffmanNodeDecomp();
            HuffmanNodeDecomp l = startlist.poll();
            HuffmanNodeDecomp r = startlist.poll();
            root.setLeft(l);
            root.setRight(r);
            root.setValue(300);
            root.setNrOfUses(root.getLeft().getNrOfUses() + root.getRight().getNrOfUses());
            startlist.add(root);
        }
        // updates the code value, so that the node knows itselfs code
        huffmantree = startlist.poll();
        updateCode(huffmantree.getLeft(), (short)(0b0), 1);
        updateCode(huffmantree.getRight(), (short)(0b1), 1);
        huffmantree.print("", true);
        // puts the nodes in an hashmap, for easier access
        putToHashmap(huffmantree);
        for(Integer b: byte_codes_Hashmap.keySet()){
            String key = b.toString();
            HuffmanNodeDecomp node = byte_codes_Hashmap.get(b);
            String value = Integer.toBinaryString(0xFFFF & node.getCode());
            System.out.println("Byte value: " + key + " code: " + value + " nc: " + node.getNrOfBitsInCode());
        }
    }

    private void putToHashmap(HuffmanNodeDecomp n){
        if(n.getValue() != 300){ // ignore if the node is not a leafnode
            byte_codes_Hashmap.put(n.getValue(), n);
        }
        if(n.getLeft() != null){
            putToHashmap(n.getLeft());
        }
        if(n.getRight() != null){
            putToHashmap(n.getRight());
        }
    }

    public HashMap<Integer, HuffmanNodeDecomp> getByte_codes_Hashmap() {
        return byte_codes_Hashmap;
    }

    // reqursiv update of code
    private void updateCode(HuffmanNodeDecomp h, short code, int nrOfBitsInUseInCode){
        h.setCode(code);
        h.setNrOfBitsInCode(nrOfBitsInUseInCode);
        if(h.getLeft() != null){
            updateCode(h.getLeft(), (short)(code<<1), nrOfBitsInUseInCode+1);
        }
        if(h.getRight() != null){
            updateCode(h.getRight(), (short)((code<<1)+0b1), nrOfBitsInUseInCode+1);
        }
    }


    public ArrayList<Byte> decompress(byte[] lstb){
        ArrayList<Byte> out = new ArrayList<>();
        HuffmanNodeDecomp h = huffmantree;
        for (int i = 1024; i < lstb.length; i++) {
            byte currentInByte = (byte)(lstb[i]&0xFF);
            for (byte j = 0; j < 8; j++) {
                byte bitStatus = (byte)(0b10000000 & (currentInByte<<j));
                if(bitStatus == 0){
                    h = h.getLeft();
                } else {
                    h = h.getRight();
                }
                if(h.getLeft() == null && h.getRight() == null){
                    out.add((byte)h.getValue());
                    h = huffmantree;
                }
            }
        }
        return out;
    }



    public LinkedBlockingDeque<Byte> getCompressedData() {
        return compressedData;
    }
}

class HuffmanNodeDecomp implements Comparable<HuffmanNodeDecomp>{
    private int value;
    private int nrOfUses;
    private int code;
    private int nrOfBitsInCode;
    private HuffmanNodeDecomp left;
    private HuffmanNodeDecomp right;

    public void setNrOfBitsInCode(int nrOfBitsInCode) {
        this.nrOfBitsInCode = nrOfBitsInCode;
    }

    public int getNrOfBitsInCode() {
        return nrOfBitsInCode;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public int getValue() {
        return value;
    }

    public HuffmanNodeDecomp getLeft() {
        return left;
    }

    public HuffmanNodeDecomp getRight() {
        return right;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setLeft(HuffmanNodeDecomp left) {
        this.left = left;
    }

    public void setRight(HuffmanNodeDecomp right) {
        this.right = right;
    }

    public int getNrOfUses() {
        return nrOfUses;
    }



    public void setNrOfUses(int nrOfUses) {
        this.nrOfUses = nrOfUses;
    }

    @Override
    public int compareTo(HuffmanNodeDecomp o) {
        if(nrOfUses < o.nrOfUses){
            return -1;
        } else if(nrOfUses > o.nrOfUses){
            return 1;
        } else {
            if(value > o.value){
                return -1;
            } else if(value < o.value){
                return 1;
            } else return 0;
        }

    }

    @Override
    public String toString(){
        String s1 = String.format("%8s", Integer.toBinaryString(code & 0xFF)).replace(' ', '0');
        String s2 = String.format("%8s", Integer.toBinaryString(value & 0xFF)).replace(' ', '0');
        String s = "(Nr:" + nrOfUses + "c:" + s1 + "v:" + s2 + "nru:" + nrOfBitsInCode + "ch:" + (char)(value&0xFF) +")";
        if(left != null){
            s += ", L" + left.toString();
        }
        if(right != null){
            s += ", R" + right.toString();
        }
        return s;
    }
    public void print(String prefix, boolean isTail){
        String s1 = String.format("%8s", Integer.toBinaryString(code & 0xFF)).replace(' ', '0');
        String s2 = String.format("%8s", Integer.toBinaryString(value & 0xFF)).replace(' ', '0');
        String s = "(Nr:" + nrOfUses + "c:" + s1 + "v:" + s2 + "nru:" + nrOfBitsInCode + "ch:" + (char)(value&0xFF) +")";
        System.out.println(prefix + (isTail ? "└── " : "├── ") + s);
        if(right != null){
            right.print(prefix + (isTail ? "    " : "│   "), false);
        }
        if(left != null){
            left.print(prefix + (isTail ? "    " : "│   "), true);
        }

    }
}