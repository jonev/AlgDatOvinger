package oving12Kompresjon;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author jonev on 03.11.2017.
 */
public class HuffmanOnString {
    private int[] nrOfTimesUsed;
    private HuffmanOnStringNode huffmantree;
    private int currentmaxTimesUsed = 0;
    private HashMap<Integer, Integer> byteVaulesCodes = new HashMap<>();
    private HashMap<Integer, Integer> codeVaulesBytes= new HashMap<>();
    private LinkedBlockingDeque<Byte> que = new LinkedBlockingDeque<>();

    public HuffmanOnString() {
        nrOfTimesUsed = new int[256];
    }

    public HuffmanOnStringNode getHuffmantree() {
        return huffmantree;
    }

    public void initHuffmantree(char[] lstc){
        for(char c:lstc){
            initHuffmantree(c);
        }
        makeTree();
    }

    private void initHuffmantree(char c){
        short s = (short)c;
        byte b1 = (byte)(s & 0b11111111);
        //String value = Integer.toBinaryString(0xFFFF & b1);
        //System.out.println("Byte value: " + c + " code1: " + value);
        byte b2 = (byte)(s >> 8);
        //value = Integer.toBinaryString(0xFFFF & b2);
        //System.out.println("Byte value: " + c + " code2: " + value);
        nrOfTimesUsed[b1]++;
        nrOfTimesUsed[b2]++;
        if(nrOfTimesUsed[b1] > currentmaxTimesUsed){
            currentmaxTimesUsed = nrOfTimesUsed[b1];
        }
        if(nrOfTimesUsed[b2] > currentmaxTimesUsed){
            currentmaxTimesUsed = nrOfTimesUsed[b2];
        }
    }

    public void initHuffmantree(byte[] lstb){
        for(byte b:lstb){
            initHuffmantree(b);
        }
        makeTree();
    }
    private void initHuffmantree(byte b){
        int i = b;
        i = i & 0b00000000000000000000000011111111 ;
        System.out.println(i);
        nrOfTimesUsed[i]++;
        if(nrOfTimesUsed[i] > currentmaxTimesUsed){
            currentmaxTimesUsed = nrOfTimesUsed[i];
        }
    }

    private void makeTree(){
        PriorityQueue<HuffmanOnStringNode> startlist = new PriorityQueue<>();
        for (int i = 0; i < nrOfTimesUsed.length; i++) {
            if(nrOfTimesUsed[i] > 0){
                HuffmanOnStringNode h = new HuffmanOnStringNode();
                h.setNrOfUses(nrOfTimesUsed[i]);
                h.setValue((byte)i);
                startlist.add(h);
            }
        }
        HuffmanOnStringNode root;
        while (startlist.size() > 1){
            root = new HuffmanOnStringNode();
            HuffmanOnStringNode l = startlist.poll();
            HuffmanOnStringNode r = startlist.poll();
            root.setLeft(l);
            root.setRight(r);
            root.setValue((byte)-1);
            root.setNrOfUses(root.getLeft().getNrOfUses() + root.getRight().getNrOfUses());
            startlist.add(root);
        }
        huffmantree = startlist.poll();
        updateCode(huffmantree.getLeft(), (short)(0b0));
        updateCode(huffmantree.getRight(), (short)(0b1));

        putToHashmap(huffmantree);

    }

    private void putToHashmap(HuffmanOnStringNode n){
        if(n.getValue() != -1){
            byteVaulesCodes.put(n.getValue(), n.getCode());
        }
        if(n.getLeft() != null){
            putToHashmap(n.getLeft());
        }
        if(n.getRight() != null){
            putToHashmap(n.getRight());
        }
    }

    public HashMap<Integer, Integer> getByteVaulesCodes() {
        return byteVaulesCodes;
    }

    private void updateCode(HuffmanOnStringNode h, short code){
        h.setCode(code);
        if(h.getLeft() != null){
            updateCode(h.getLeft(), (short)(code<<1));
        }
        if(h.getRight() != null){
            updateCode(h.getRight(), (short)((code<<1)+0b1));
        }
    }


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

    public void compress(byte[] lstb){
        int codeForByte;
        byte nrOfBitsInUse;
        byte buffer = 0;
        byte usedInBuffer = 0;
        byte overloadbytes = 0;
        for(byte b:lstb){
            codeForByte = byteVaulesCodes.get((int)b);
            nrOfBitsInUse = (byte)(32 - Integer.numberOfLeadingZeros(codeForByte));
            if(codeForByte == 0){
                usedInBuffer++;
                continue;
            }
            byte switchvalue = (byte)(8-usedInBuffer-nrOfBitsInUse);
            if(switchvalue < 0){
                // finner ut hvor mange bit man trenger i neste byte
                overloadbytes = (byte)(switchvalue*-1);
                // tar vekk de bittene det ikke er plass til >>
                buffer = (byte)(buffer | ((codeForByte>>overloadbytes)));
                que.add(buffer); // legger til i outputten
                // legger til de bittene det ikke var plass til
                buffer = (byte)(codeForByte<<8-overloadbytes);
                usedInBuffer = overloadbytes;
                continue;
            }
            buffer = (byte)(buffer | (codeForByte<<switchvalue));
            usedInBuffer +=nrOfBitsInUse;
            if(nrOfBitsInUse >= 8){
                que.add(buffer);
                buffer = 0;
                nrOfBitsInUse = 0;
            }
            String s1 = String.format("%8s", Integer.toBinaryString(buffer & 0xFF)).replace(' ', '0');
            System.out.println(s1);
        }
        que.add(buffer);

    }

    public LinkedBlockingDeque<Byte> getQue() {
        return que;
    }
}

class HuffmanOnStringNode implements Comparable<HuffmanOnStringNode>{
    private int value;
    private int nrOfUses;
    private int code;
    private HuffmanOnStringNode left;
    private HuffmanOnStringNode right;


    public void setCode(short code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public int getValue() {
        return value;
    }

    public HuffmanOnStringNode getLeft() {
        return left;
    }

    public HuffmanOnStringNode getRight() {
        return right;
    }

    public void setValue(byte value) {
        this.value = value;
    }

    public void setLeft(HuffmanOnStringNode left) {
        this.left = left;
    }

    public void setRight(HuffmanOnStringNode right) {
        this.right = right;
    }

    public int getNrOfUses() {
        return nrOfUses;
    }



    public void setNrOfUses(int nrOfUses) {
        this.nrOfUses = nrOfUses;
    }

    @Override
    public int compareTo(HuffmanOnStringNode o) {
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
        String s = "NrOfUse:" + nrOfUses + " code: " + code;
        if(left != null){
            s += ", " + left.toString();
        }
        if(right != null){
            s += ", " + right.toString();
        }
        return s;
    }
}