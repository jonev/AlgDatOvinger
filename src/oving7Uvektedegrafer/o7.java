package oving7Uvektedegrafer;

import Misc.PriHeapNode;
import jdk.nashorn.internal.runtime.FindProperty;
import Misc.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

public class o7 {
    public static void main(String[] args) throws Exception{

    }


    public static Node[] breddeforstsok(GraphIndexEdgeTable graph, int startnode) throws Exception{
        Node[] nodetbl = graph.getIndexInEdgeTbl();
        int[] edges = graph.getEdgeTbl();
        Node s = nodetbl[startnode];
        s.setDist(0);
        s.setPredecessor(0);
        LinkedList<Node> que = new LinkedList<Node>();
        que.push(s);

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

    public static Node[] breddeforstsok(GraphLinkedEdgelist graph, int startnode) throws Exception{
        Node[] nodetbl = graph.getNodes();
        Node s = nodetbl[startnode];
        s.setDist(0);
        s.setPredecessor(0);
        LinkedList<Node> que = new LinkedList<Node>();
        que.push(s);

        while (!que.isEmpty()){
            Node current = que.pollLast();
            while (current.gotEdges()){
                Integer edge = current.getEdge().getTo();
                if(nodetbl[edge].getDist() == Integer.MAX_VALUE){
                   nodetbl[edge].setDist(current.getDist() + 1);
                   nodetbl[edge].setPredecessor(current.getNr());
                   que.push(nodetbl[edge]);
                }
            }
        }
        return nodetbl;
    }


    public static Node topologisksortering(GraphLinkedEdgelist graph){
        Node[] nodes = graph.getNodes();
        Node l = null;
        for (int i = graph.getNodecount()-1; i >= 0; i--) {
            l = df_topo(nodes, nodes[i], l);
        }

        return l;
    }

    private static Node df_topo(Node[] nodes, Node n, Node l){
        if(n.isFound()) return l;
        n.setFound(true);
        // check each edge
        if(n.gotEdges()) {
            while (n.gotEdges()){
                Integer edge = n.getEdge().getTo();
                l = df_topo(nodes, nodes[edge], l);
            }
        }
        //
        n.setNext(l);
        return n;
    }


}


class GraphIndexEdgeTable {
    private Node[] indexInEdgeTbl;
    private int[] edgeTbl;
    private int nodecount;
    private int edgecount;

    public void readGraphFileToNeighborList2(String filename, boolean direct){
        BufferedReader rd = null;
        FileReader fr = null;
        try{
            fr = new FileReader(filename);
            rd = new BufferedReader(fr);

            String line = rd.readLine().trim();
            String[] heading = line.split(" +");
            nodecount = Integer.parseInt(heading[0].trim());
            edgecount = Integer.parseInt(heading[1].trim())*(direct ? 1 : 2);
            indexInEdgeTbl = new Node[nodecount+1];
            int[][] unsortedEdgTbl = new int[edgecount][2];

            int node = -1;
            int from = -1;
            int to = -1;
            int index = 0;

            // read from file
            while ((line = rd.readLine()) != null){
                String splitt[] = line.trim().split(" +|\t+");
                from = Integer.parseInt(splitt[0].trim());
                to = Integer.parseInt(splitt[1].trim());

                unsortedEdgTbl[index][0] = from;
                unsortedEdgTbl[index++][1] = to;
                if(!direct){
                    unsortedEdgTbl[index][0] = to;
                    unsortedEdgTbl[index++][1] = from;
                }
            }

            // sort edges
            boolean hasEdge = false;
            edgeTbl = new int[unsortedEdgTbl.length];
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
            indexInEdgeTbl[nodecount] = new Node(-1);
            indexInEdgeTbl[nodecount].setDist(-1);
            indexInEdgeTbl[nodecount].setIndex(edgecount);


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
        for (int i = 0; i < nodecount; i++) {
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




