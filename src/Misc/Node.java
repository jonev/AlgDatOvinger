package Misc;

import java.util.LinkedList;

/**
 * @author jonev on 07.10.2017.
 */
public class Node implements PriHeapNode{
    private int dist = Integer.MAX_VALUE;
    private int nr;
    private int index = -1;
    private int predecessor = -1;
    private Node next;
    private boolean found;
    private LinkedList<Edge> edges;
    private int pri;


    public Node(int nr){
        this.nr = nr;
        edges = new LinkedList<>();
    }

    public Node(int nr, int pri){
        this.nr = nr;
        edges = new LinkedList<>();
        this.pri = pri;
    }
    public void addEdge(Edge edge){
        edges.push(edge);
    }

    public boolean gotEdges(){
        return (edges.size() > 0);
    }

    public Edge getEdge(){
        return edges.pop();
    }

    public int getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(int predecessor) {
        this.predecessor = predecessor;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getDist() {
        return dist;
    }

    public int getIndex() {
        return index;
    }

    public int getNr() {
        return nr;
    }

    public Node getNext() {
        return next;
    }

    public boolean isFound() {
        return found;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    @Override
    public int getIntPri() {
        return pri;
    }

    @Override
    public void setIntPri(int priority) {
        pri = priority;
    }

    public String getNodeList(){
        if(next == null) return nr + " end";
        return nr + " - " + next.getNodeList();
    }
}
