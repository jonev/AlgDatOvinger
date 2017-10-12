package Misc;

import com.sun.org.apache.xml.internal.security.encryption.CipherReference;

/**
 * @author jonev on 07.10.2017.
 */
public class Heap {
    private PriHeapNode[] nodes;
    private boolean typeMin;
    private int nrOfNodeIn = 0;

    public Heap(int nrOfNodes, boolean typeMin){
        nodes = new PriHeapNode[nrOfNodes];
        this.typeMin = typeMin;

    }

    public boolean isEmpty(){
        return (nrOfNodeIn < 1);
    }

    public PriHeapNode addNodeBeforeMake(int nr, int pri){
        nodes[nrOfNodeIn++] = new Node(nr, pri);
        return nodes[nrOfNodeIn-1];
    }

    public void addNode(int nr, int pri){
        nodes[nrOfNodeIn++] = new Node(nr, pri);
        fixHeap(nrOfNodeIn-1);
    }

    public void addNode(PriHeapNode node){
        try {
            nodes[nrOfNodeIn++] = node;
            fixHeap(nrOfNodeIn - 1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void makeHeap(){
        int i = nrOfNodeIn/2;
        while (i-- > 0){
            fixHeap(i);
        }
    }

    public int getPri(int i){
        if(typeMin){
            return (i < nrOfNodeIn) ? nodes[i].getIntPri() : Integer.MAX_VALUE;
        }else {
            return (i < nrOfNodeIn) ? nodes[i].getIntPri() : Integer.MIN_VALUE;
        }
    }

    private void fixHeap(int i){
        if(typeMin){ // min heap
            if(getPri(getParentIndex(i)) > getPri(i)){
                switchPlace(i, getParentIndex(i));
                fixHeap(getParentIndex(i));
            }
            if(getPri(i) > getPri(getFirstChildIndex(i))){
                switchPlace(i, getFirstChildIndex(i));
                fixHeap(getFirstChildIndex(i));
            }
            if(getPri(i) > getPri(getSecondChildIndex(i))){
                switchPlace(i, getSecondChildIndex(i));
                fixHeap(getSecondChildIndex(i));
            }
        }else{ // max heap
            if(getPri(getParentIndex(i)) < getPri(i)){
                switchPlace(i, getParentIndex(i));
                fixHeap(getParentIndex(i));
            }
            if(getPri(i) < getPri(getFirstChildIndex(i))){
                switchPlace(i, getFirstChildIndex(i));
                fixHeap(getFirstChildIndex(i));
            }
            if(getPri(i) < getPri(getSecondChildIndex(i))){
                switchPlace(i, getSecondChildIndex(i));
                fixHeap(getSecondChildIndex(i));
            }
        }
    }

    public PriHeapNode getRoot(){
        if(nrOfNodeIn < 1) return null;
        PriHeapNode root = nodes[0];
        nodes[0] = nodes[nrOfNodeIn-1];
        nrOfNodeIn--;
        fixHeap(0);
        return root;
    }

    public void changePriority(int i, int newPriority){
        if(i >= nrOfNodeIn) return;
        nodes[i].setIntPri(newPriority);
        fixHeap(getParentIndex(i));
    }

    private void switchPlace(int i, int j){
        PriHeapNode n = nodes[i];
        // System.out.println("Bytter, setter fra: " + i + " pri: " + n.getIntPri() + " til: " + j);
        // System.out.println("Bytter, setter fra: " + j + " pri: " + nodes[j].getIntPri() + " til: " + i);
        nodes[i] = nodes[j];
        nodes[j] = n;
    }

    private int getParentIndex(int child){
        if(child <= 0) return 0;
        return ((child-1)/2);
    }

    private int getFirstChildIndex(int parent){
        return (2*parent) + 1;
    }
    private int getSecondChildIndex(int parent){
        return (2*parent) + 2;
    }


    public int getNrOfNodeIn() {
        return nrOfNodeIn;
    }

    public String toString(){
        // String temp = "";
        // for (int i = 0; i < (nrOfNodeIn/2); i++) {
        //     temp += "\t";
        // }
        String s = (typeMin) ? "Min: " : "Max: ";
        for (int i = 0; i < nrOfNodeIn; i++) {
            s += nodes[i].getIntPri() + " ; ";
        }
        return s;
    }
}
