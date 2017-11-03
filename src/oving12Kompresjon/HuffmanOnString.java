package oving12Kompresjon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * @author jonev on 03.11.2017.
 */
public class HuffmanOnString {
    private int[] nrOfTimesUsed;
    private HuffmanOnStringNode huffmantree;
    private int currentmaxTimesUsed = 0;

    public HuffmanOnString() {
        nrOfTimesUsed = new int[256];
    }

    public void read(char c){
        short s = (short)c;
        byte b1 = (byte)(s & 0b11111111);
        byte b2 = (byte)(s >> 8);
        nrOfTimesUsed[b1]++;
        nrOfTimesUsed[b2]++;
        if(nrOfTimesUsed[b1] > currentmaxTimesUsed){
            currentmaxTimesUsed = nrOfTimesUsed[b1];
        }
        if(nrOfTimesUsed[b2] > currentmaxTimesUsed){
            currentmaxTimesUsed = nrOfTimesUsed[b2];
        }
    }

    public void makeTree(){
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
        System.out.println("");
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

    public void compress(char c){
        short s = (short)c;
        byte b1 = (byte)(s & 0b11111111);
        byte b2 = (byte)(s >> 8);
    }



}

class HuffmanOnStringNode implements Comparable<HuffmanOnStringNode>{
    private byte value;
    private int nrOfUses;
    private short code;
    private HuffmanOnStringNode left;
    private HuffmanOnStringNode right;


    public void setCode(short code) {
        this.code = code;
    }

    public short getCode() {

        return code;
    }

    public byte getValue() {
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
            return -1;
        }

    }

    @Override
    public String toString(){
        return nrOfUses + " ";
    }
}