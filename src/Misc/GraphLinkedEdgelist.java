package Misc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * @author jonev on 12.10.2017.
 */
public class GraphLinkedEdgelist{
    private Node[] nodes;
    private ArrayList<Node> nodelst;
    private int nodecount;
    private int edgecount;


    public void readGraphFileToNeighborList1ToArray(String filename, boolean direct){
        BufferedReader rd = null;
        FileReader fr = null;
        try{
            fr = new FileReader(filename);
            rd = new BufferedReader(fr);

            String line = rd.readLine().trim();
            String[] heading = line.split(" +");
            nodecount = Integer.parseInt(heading[0].trim());
            edgecount = Integer.parseInt(heading[1].trim())*(direct ? 1 : 2);
            nodes = new Node[nodecount];
            for (int i = 0; i < nodes.length; i++) {
                nodes[i] = new Node("N:" + i);
                nodes[i].setIndex(i);
            }

            // initHuffmantree from file
            while ((line = rd.readLine()) != null){
                String splitt[] = line.trim().split(" +|\t+");
                int from = Integer.parseInt(splitt[0]);
                int to = Integer.parseInt(splitt[1]);
                int weight = (splitt.length > 2) ? Integer.parseInt(splitt[2]) : 0;
                nodes[from].addEdge(new Edge(to, weight));
                if(!direct){
                    nodes[to].addEdge(new Edge(to, weight));
                }
            }


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

    public void readGraphFileToNeighborList1ToArrayList(String filename, boolean direct){
        BufferedReader rd = null;
        FileReader fr = null;
        try{
            fr = new FileReader(filename);
            rd = new BufferedReader(fr);

            String line = rd.readLine().trim();
            String[] heading = line.split(" +");
            nodecount = Integer.parseInt(heading[0].trim());
            edgecount = Integer.parseInt(heading[1].trim())*(direct ? 1 : 2);
            nodelst = new ArrayList<Node>();
            for (int i = 0; i < nodecount; i++) {
                nodelst.add(new Node("N:" + i));
                nodelst.get(i).setIndex(i);
            }

            // initHuffmantree from file
            while ((line = rd.readLine()) != null){
                String splitt[] = line.trim().split(" +|\t+");
                int from = Integer.parseInt(splitt[0]);
                int to = Integer.parseInt(splitt[1]);
                int weight = (splitt.length > 2) ? Integer.parseInt(splitt[2]) : 0;
                nodelst.get(from).addEdge(new Edge(to, weight));
                if(!direct){
                    nodelst.get(to).addEdge(new Edge(to, weight));
                }
            }


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

    public void readGraphFileToNeighborList1ToArrayListAstar(String filename){
        BufferedReader rd = null;
        FileReader fr = null;
        try{
            fr = new FileReader(filename);
            rd = new BufferedReader(fr);

            String line = rd.readLine().trim();
            String[] heading = line.split(" +");
            nodecount = Integer.parseInt(heading[0].trim());
            edgecount = Integer.parseInt(heading[1].trim());
            nodelst = new ArrayList<Node>();
            for (int i = 0; i < nodecount; i++) {
                nodelst.add(new Node("N:" + i));
                nodelst.get(i).setIndex(i);
            }

            // initHuffmantree from file
            while ((line = rd.readLine()) != null){
                String splitt[] = line.trim().split(" +|\t+");
                int from = Integer.parseInt(splitt[0]);
                int to = Integer.parseInt(splitt[1]);
                int drivetime = Integer.parseInt(splitt[2]);
                int lenght = Integer.parseInt(splitt[3]);
                int speedlimit = Integer.parseInt(splitt[4]);
                nodelst.get(from).addEdge(new Edge(to, drivetime, lenght, speedlimit));
            }


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

    public void setLongLat(int nodenr, double longitude, double latitude){
        nodelst.get(nodenr).setLongLat(longitude, latitude);
    }

    public ArrayList<Node> getNodelst() {
        return nodelst;
    }

    public void setNodes(Node[] nodes) {
        this.nodes = nodes;
    }

    public void setNodecount(int nodecount) {
        this.nodecount = nodecount;
    }

    public void setEdgecount(int edgecount) {
        this.edgecount = edgecount;
    }

    public Node[] getNodes() {
        return nodes;
    }

    public int getNodecount() {
        return nodecount;
    }

    public int getEdgecount() {
        return edgecount;
    }

}
