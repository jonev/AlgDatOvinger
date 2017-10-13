package Misc;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * @author jonev on 12.10.2017.
 */
public class GraphLinkedEdgelist{
    private Node[] nodes;
    private int nodecount;
    private int edgecount;


    public void readGraphFileToNeighborList1(String filename, boolean direct){
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
                nodes[i] = new Node(i);
                nodes[i].setIndex(i);
            }

            // read from file
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
