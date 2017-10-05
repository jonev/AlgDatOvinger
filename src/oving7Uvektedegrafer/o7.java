package oving7Uvektedegrafer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutionException;

public class o7 {
    public static void main(String[] args) throws Exception{

    }


    public static Node[] breddeforstsok(Graph graph, int startnode) throws Exception{
        Node[] nodetbl = graph.getIndexInEdgeTbl();
        int[] edges = graph.getEdgeTbl();
        Node s = nodetbl[startnode];
        s.setDist(0);
        s.setPredecessor(0);
        LinkedList<Node> que = new LinkedList<Node>();
        que.push(s);
        int[][] result = new int[nodetbl.length][3]; // Node/Forgjenger/Dist

        while (!que.isEmpty()){
            Node current = que.pollLast();
            for (int nabonr = current.getIndex(); nabonr < nodetbl[current.getNr()+1].getIndex(); nabonr++) {
                if(nodetbl[edges[nabonr]].getDist() == Integer.MAX_VALUE){
                    nodetbl[edges[nabonr]].setDist(current.getDist() + 1);
                    nodetbl[edges[nabonr]].setPredecessor(current.getNr());
                    que.push(nodetbl[edges[nabonr]]);
                }
            }
        }
        return nodetbl;
    }
}


class Graph{
    private Node[] indexInEdgeTbl;
    private int[] edgeTbl;
    private int nodecount;
    private int edgecount;

    public Graph(){
    }



    public void readGraphFile(String filename){
        BufferedReader rd = null;
        FileReader fr = null;
        try{
            fr = new FileReader(filename);
            rd = new BufferedReader(fr);

            String line = rd.readLine().trim();
            String[] heading = line.split(",");
            nodecount = Integer.parseInt(heading[0].trim());
            edgecount = Integer.parseInt(heading[1].trim());
            indexInEdgeTbl = new Node[nodecount+1];
            int[][] unsortedEdgTbl = new int[edgecount][2];

            int node = -1;
            int from = -1;
            int to = -1;
            int index = 0;

            // read from file
            while ((line = rd.readLine()) != null){
                String splitt[] = line.trim().split(",");
                from = Integer.parseInt(splitt[0].trim());
                to = Integer.parseInt(splitt[1].trim());

                unsortedEdgTbl[index][0] = from;
                unsortedEdgTbl[index++][1] = to;
            }

            // sort edges
            boolean hasEdge = false;
            edgeTbl = new int[edgecount];
            int edgetblindex = 0;
            for (int nodenrindex = 0; nodenrindex < nodecount; nodenrindex++) { // for each node
                indexInEdgeTbl[nodenrindex] = new Node(nodenrindex);
                indexInEdgeTbl[nodenrindex].setIndex(edgetblindex);
                hasEdge = false;
                for (int edgeindex = 0; edgeindex < edgecount; edgeindex++) { // collection all edges for that node
                    if(unsortedEdgTbl[edgeindex][0] == nodenrindex){
                        try {
                            edgeTbl[edgetblindex] = unsortedEdgTbl[edgeindex][1];
                            edgetblindex++;
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
            // adding dummynode
            indexInEdgeTbl[nodecount] = new Node(nodecount);


        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                fr.close();
                rd.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public Node[] getIndexInEdgeTbl() {
        return indexInEdgeTbl;
    }

    public int[] getEdgeTbl() {
        return edgeTbl;
    }

    public String toString(){
        String s = "Nodes: " + nodecount + " edges " + edgecount;
        for (int i = 0; i < indexInEdgeTbl.length-1; i++) {
            for (int j = indexInEdgeTbl[i].getIndex(); j < indexInEdgeTbl[i+1].getIndex(); j++) {
                s += "\nNode " + i + " kant til " + edgeTbl[j];
            }
        }
        return s;
    }


    public int getNodecount() {
        return nodecount;
    }

    public int getEdgecount() {
        return edgecount;
    }
}

class Node{
    private int dist = Integer.MAX_VALUE;
    private int nr;
    private int index = -1;
    int predecessor = -1;

    public Node(int nr){
        this.nr = nr;
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



}