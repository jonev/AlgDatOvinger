package Misc;

import java.util.LinkedList;

/**
 * @author jonev on 14.10.2017.
 */
public class Node implements Comparable<Node>{
    private long dist = Long.MAX_VALUE;
    private String nodename;
    private int index = -1;
    private String predecessor = "No one";
    private int intPredecessor = -1;
    private LinkedList<Edge> edges;
    private Node next;
    private boolean found;
    private double radlongitude;
    private double radlatitude;
    private double cosLat;
    private long distanceToEnd;

    public Node(Node n) {
        this.dist = n.dist;
        this.nodename = n.nodename;
        this.index = n.index;
        this.predecessor = n.predecessor;
        this.intPredecessor = n.intPredecessor;
        this.edges = n.edges;
        this.next = n.next;
        this.found = n.found;
        this.radlongitude = n.radlongitude;
        this.radlatitude = n.radlatitude;
        this.cosLat = n.cosLat;
        this.distanceToEnd = n.distanceToEnd;
    }

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

    public void setPredecessor(String predecessor, int pred) {
        this.predecessor = predecessor;
        this.intPredecessor = pred;
    }

    public void setPredecessor(String predecessor) {
        this.predecessor = predecessor;
    }

    public int getIntPredecessor() {
        return intPredecessor;
    }

    public void setDist(long dist) {
        this.dist = dist;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public long getDist() {
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
    public void setLongLat(double longitude, double latitude){
        this.radlongitude = longitude;
        this.radlatitude = latitude;
        cosLat = Math.cos(latitude);
    }

    public long getDistanceToEnd() {
        return distanceToEnd;
    }

    public void setDistanceToEnd(long distanceToEnd) {
        this.distanceToEnd = distanceToEnd;
    }

    public double getCosLat() {
        return cosLat;
    }

    public double getRadlongitude() {
        return radlongitude;
    }

    public void setRadlongitude(double radlongitude) {
        this.radlongitude = radlongitude;
    }

    public void setRadlatitude(double radlatitude) {
        this.radlatitude = radlatitude;
    }

    public double getRadlatitude() {
        return radlatitude;
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
