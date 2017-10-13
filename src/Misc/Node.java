package Misc;

import java.util.LinkedList;

/**
 * @author jonev on 07.10.2017.
 */
public class Node{
    private int dist = 2000000;
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

    public Node(int nr, int dist){
        this.nr = nr;
        edges = new LinkedList<>();
        this.dist = dist;
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


    public int getIntPri() {
        return pri;
    }


    public void setIntPri(int priority) {
        pri = priority;
    }

    public String getNodeList(){
        if(next == null) return nr + " end";
        return nr + " - " + next.getNodeList();
    }
}
