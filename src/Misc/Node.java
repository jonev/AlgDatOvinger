package Misc;

import java.util.LinkedList;

/**
 * @author jonev on 14.10.2017.
 */
public class Node implements Comparable<Node>{
    private int dist = 1000000;
    private String nodename;
    private int index = -1;
    private String predecessor = "No one";
    private LinkedList<Edge> edges;
    private Node next;
    private boolean found;

    public Node(String nodename, int dist){
        this.nodename = nodename;
        edges = new LinkedList<>();
        this.dist = dist;
    }
    public Node(String nodename){
        this.nodename = nodename;
        edges = new LinkedList<>();
    }
    public void addEdge(Edge edge){
        edges.push(edge);
    }

    public LinkedList<Edge> getEdges() {
        return edges;
    }

    public boolean gotEdges(){
        return (edges.size() > 0);
    }

    public Edge getEdge(){
        return edges.pop();
    }

    public String getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(String predecessor) {
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

    public String getNodename() {
        return nodename;
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

    public String getNodeList(){
        if(next == null) return nodename;
        return nodename + " - " + next.getNodeList();
    }

    @Override
    public int compareTo(Node o) {
        if(o == null) return 1;
        if(dist > o.dist){
            return 1;
        } else if(dist < o.dist){
            return -1;
        } else return 0;
    }
}
