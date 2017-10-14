package Misc;

/**
 * @author jonev on 07.10.2017.
 */
public class Heap {
    private Node[] nodes;
    private boolean typeMin;
    private int nrOfNodeIn = 0;
    private int antfix = 0;

    public Heap(int nrOfNodes, boolean typeMin){
        nodes = new Node[nrOfNodes];
        this.typeMin = typeMin;

    }

    public Node[] getNodes() {
        return nodes;
    }

    public void setNodes(Node[] nodes) {
        this.nodes = nodes;
        this.nrOfNodeIn = nodes.length;
    }

    public boolean isEmpty(){
        return (nrOfNodeIn < 1);
    }

    public Node addNodeBeforeMake(String nodename, int dist){
        nodes[nrOfNodeIn++] = new Node(nodename, dist);
        return nodes[nrOfNodeIn-1];
    }

    public void addNode(String nodename, int dist){
        nodes[nrOfNodeIn++] = new Node(nodename, dist);
        fixHeap(nrOfNodeIn-1);
    }

    public void addNode(Node node){
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

    public void setDist(int index, int dist){
        nodes[index].setDist(dist);
    }


    public void setPredecessor(int index, String pre){
        nodes[index].setPredecessor(pre);
    }
    public String getPredecessor(int index){
        return nodes[index].getPredecessor();
    }

    public int getDist(int i){
        if(typeMin){
            return (i < nrOfNodeIn) ? nodes[i].getDist() : Integer.MAX_VALUE;
        }else {
            return (i < nrOfNodeIn) ? nodes[i].getDist() : Integer.MIN_VALUE;
        }
    }

    public void fixHeap(int i){ // todo oppdatere nr til noden til å matche indexen i tabellen, hver gang en node flyttes
        antfix++;
        if(typeMin){ // min heap
            if(getDist(getParentIndex(i)) > getDist(i)){
                switchPlace(i, getParentIndex(i));
                fixHeap(getParentIndex(i));
            }
            if(getDist(i) > getDist(getFirstChildIndex(i))){
                switchPlace(i, getFirstChildIndex(i));
                fixHeap(getFirstChildIndex(i));
            }
            if(getDist(i) > getDist(getSecondChildIndex(i))){
                switchPlace(i, getSecondChildIndex(i));
                fixHeap(getSecondChildIndex(i));
            }
        }else{ // max heap
            if(getDist(getParentIndex(i)) < getDist(i)){
                switchPlace(i, getParentIndex(i));
                fixHeap(getParentIndex(i));
            }
            if(getDist(i) < getDist(getFirstChildIndex(i))){
                switchPlace(i, getFirstChildIndex(i));
                fixHeap(getFirstChildIndex(i));
            }
            if(getDist(i) < getDist(getSecondChildIndex(i))){
                switchPlace(i, getSecondChildIndex(i));
                fixHeap(getSecondChildIndex(i));
            }
        }
    }

    public int getAntfix() {
        return antfix;
    }

    public Node getRoot(){
        if(nrOfNodeIn < 1) return null;
        Node root = nodes[0];
        nodes[0] = nodes[nrOfNodeIn-1];
        nodes[0].setIndex(0);
        nodes[nrOfNodeIn-1] = null; // no need, just for view
        nrOfNodeIn--;
        fixHeap(0);
        return root;
    }

    public void changeDistance(int i, int newDist){
        if(i >= nrOfNodeIn) return;
        nodes[i].setDist(newDist);
        fixHeap(getParentIndex(i));
    }

    private void switchPlace(int i, int j){
        Node n = nodes[i];
        // System.out.println("Bytter, setter fra: " + i + " pri: " + n.getIntPri() + " til: " + j);
        // System.out.println("Bytter, setter fra: " + j + " pri: " + nodes[j].getIntPri() + " til: " + i);
        nodes[i] = nodes[j];
        nodes[j] = n;
        nodes[i].setIndex(i);
        nodes[j].setIndex(j);
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
            s += nodes[i].getDist() + " ; ";
        }
        return s;
    }
}
