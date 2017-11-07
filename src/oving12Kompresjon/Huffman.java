package oving12Kompresjon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author jonev on 03.11.2017.
 */
public class Huffman {
    private int[] byteArray256nrOfTimesUsedInOriginalFile;
    private HuffmanNode huffmantree;
    private int currentmaxTimesUsed = 0;
    private HashMap<Integer, HuffmanNode> byte_codes_Hashmap = new HashMap<>();
    private LinkedBlockingDeque<Byte> compressedData = new LinkedBlockingDeque<>();

    public Huffman() {
        byteArray256nrOfTimesUsedInOriginalFile = new int[256];
    }

    public HuffmanNode getHuffmantree() {
        return huffmantree;
    }

    public void initHuffmantree(byte[] lstb){
        for(byte b:lstb){
            initHuffmantree(b);
        }
        makeTree();
    }
    private void initHuffmantree(byte b){
        int i = b;
        i = i & 0b00000000000000000000000011111111 ; // converts negative byte value to positive integer value
        byteArray256nrOfTimesUsedInOriginalFile[i]++;
        if(byteArray256nrOfTimesUsedInOriginalFile[i] > currentmaxTimesUsed){
            currentmaxTimesUsed = byteArray256nrOfTimesUsedInOriginalFile[i];
        }
    }

    private void makeTree(){
        PriorityQueue<HuffmanNode> startlist = new PriorityQueue<>();
        // makes a node of each byte value that is used and puts it an a priority que
        // who sort the node by how many times it is used
        for (int i = 0; i < byteArray256nrOfTimesUsedInOriginalFile.length; i++) {
            if(byteArray256nrOfTimesUsedInOriginalFile[i] > 0){
                HuffmanNode h = new HuffmanNode();
                h.setNrOfUses(byteArray256nrOfTimesUsedInOriginalFile[i]);
                h.setValue((byte)i);
                startlist.add(h);
            }
        }
        // using the priority que to generate a huffman tree
        HuffmanNode root;
        while (startlist.size() > 1){
            root = new HuffmanNode();
            HuffmanNode l = startlist.poll();
            HuffmanNode r = startlist.poll();
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
            HuffmanNode node = byte_codes_Hashmap.get(b);
            String value = Integer.toBinaryString(0xFFFF & node.getCode());
            System.out.println("Byte value: " + key + " code: " + value + " nc: " + node.getNrOfBitsInCode());
        }
    }

    private void putToHashmap(HuffmanNode n){
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

    public HashMap<Integer, HuffmanNode> getByte_codes_Hashmap() {
        return byte_codes_Hashmap;
    }

    // reqursiv update of code
    private void updateCode(HuffmanNode h, short code, int nrOfBitsInUseInCode){
        h.setCode(code);
        h.setNrOfBitsInCode(nrOfBitsInUseInCode);
        if(h.getLeft() != null){
            updateCode(h.getLeft(), (short)(code<<1), nrOfBitsInUseInCode+1);
        }
        if(h.getRight() != null){
            updateCode(h.getRight(), (short)((code<<1)+0b1), nrOfBitsInUseInCode+1);
        }
    }


    // compress char array
    public void compress(char[] lstc){
        byte[] b = new byte[lstc.length*2];
        int index = 0;
        for(char c:lstc){
            int s = (short)c;
            b[index] = (byte)(s & 0b11111111);
            b[index+1] = (byte)(s >> 8);
            index += 2;
        }
        compress(b);

    }

    // compress byte array
    public void compress(byte[] lstb){
        for(int i: byteArray256nrOfTimesUsedInOriginalFile){
            //System.out.println(i);
            byte b = (byte)(i&0b11111111);
            compressedData.add(b);
            //System.out.println((byte)(i&0b11111111));
            b = (byte)((i>>8)&0b11111111);
            compressedData.add(b);
            //System.out.println((byte)((i>>8)&0b11111111));
            b = (byte)((i>>16)&0b11111111);
            compressedData.add(b);
            //System.out.println((byte)((i>>16)&0b11111111));
            b = (byte)((i>>24)&0b11111111);
            compressedData.add(b);
            //System.out.println((byte)((i>>24)&0b11111111));
        }
        HuffmanNode node;
        int codeForByte;
        byte nrOfBitsInUse;
        byte buffer = 0;
        byte usedInBuffer = 0;
        byte overloadbytes = 0;
        for(byte b:lstb){
            node = byte_codes_Hashmap.get((int)b);
            if(node == null){
                System.out.println("");
            }
            codeForByte = node.getCode();
            nrOfBitsInUse = (byte)node.getNrOfBitsInCode();
            byte switchvalue = (byte)(8-usedInBuffer-nrOfBitsInUse);
            if(switchvalue < 0){
                // finner ut hvor mange bit man trenger i neste byte
                overloadbytes = (byte)(switchvalue*-1);
                // tar vekk de bittene det ikke er plass til >>
                buffer = (byte)(buffer | ((codeForByte>>overloadbytes)));
                compressedData.add(buffer); // legger til i outputten
                // viss man har en kode som enda er over 8 bit, legges de til
                while (overloadbytes > 8){
                    buffer = (byte)((codeForByte>>(overloadbytes-8) & 0b11111111));
                    compressedData.add(buffer); // legger til i outputten
                    usedInBuffer = 0;
                    overloadbytes -= 8;
                }
                if(overloadbytes == 0) continue;
                // legger til de bittene det ikke var plass til
                buffer = (byte)(codeForByte<<8-overloadbytes);
                usedInBuffer = overloadbytes;
                continue;
            }
            buffer = (byte)(buffer | (codeForByte<<switchvalue));
            usedInBuffer +=nrOfBitsInUse;
            if(nrOfBitsInUse >= 8){
                compressedData.add(buffer);
                buffer = 0;
            }
        }
        compressedData.add(buffer);

    }

    // public ArrayList<Byte> decompress(byte[] lstb){
    //     ArrayList<Byte> out = new ArrayList<>();
    //     HuffmanNode h = huffmantree;
    //     for (int i = 1024; i < lstb.length; i++) {
    //         byte currentInByte = (byte)(lstb[i]&0xFF);
    //         for (byte j = 0; j < 8; j++) {
    //             byte bitStatus = (byte)(0b10000000 & (currentInByte<<j));
    //             if(bitStatus == 0){
    //                 h = h.getLeft();
    //             } else {
    //                 h = h.getRight();
    //             }
    //             if(h.getLeft() == null && h.getRight() == null){
    //                 out.add((byte)h.getValue());
    //                 h = huffmantree;
    //             }
    //         }
    //     }
    //     return out;
    // }



    public LinkedBlockingDeque<Byte> getCompressedData() {
        return compressedData;
    }
}

class HuffmanNode implements Comparable<HuffmanNode>{
    private int value;
    private int nrOfUses;
    private int code;
    private int nrOfBitsInCode;
    private HuffmanNode left;
    private HuffmanNode right;

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

    public HuffmanNode getLeft() {
        return left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setLeft(HuffmanNode left) {
        this.left = left;
    }

    public void setRight(HuffmanNode right) {
        this.right = right;
    }

    public int getNrOfUses() {
        return nrOfUses;
    }



    public void setNrOfUses(int nrOfUses) {
        this.nrOfUses = nrOfUses;
    }

    @Override
    public int compareTo(HuffmanNode o) {
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