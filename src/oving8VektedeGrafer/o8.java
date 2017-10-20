package oving8VektedeGrafer;

import Misc.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author jonev on 12.10.2017.
 */
public class o8 {
    public static void main(String[] args) {
        GraphLinkedEdgelist g = new GraphLinkedEdgelist();
        g.readGraphFileToNeighborList1ToArrayList("src/oving8VektedeGrafer/vg4.txt", true);

        TimeTaking t = new TimeTaking(1);
        t.start();
        dijkstas(g.getNodelst(), 1);
        System.out.println("Utført på " + (double)t.finish()/1000000000 + " sekunder");

        ArrayList<Node> nodelst = g.getNodelst();
        for (Node n : nodelst) {
            System.out.println(n.getNodename() + " " + n.getPredecessor() + "  " + n.getDist());
        }

    }



    private static void dijkstas(ArrayList<Node> nodes, int startnode){
        nodes.get(startnode).setDist(0);
        nodes.get(startnode).setPredecessor("Start");
        PriorityQueue<Node> queue = new PriorityQueue<Node>(nodes);

        Node node = queue.poll();
        while (node != null){
            Edge[] edges = node.getEdges().toArray(new Edge[node.getEdges().size()]);
            for (Edge e : edges){
                // TODO får rolover på denne slik at den blir -2 milliarder
                Node change = nodes.get(e.getTo());
                if(change.getDist() > (node.getDist() + e.getWeight())){
                    change.setDist(node.getDist() + e.getWeight());
                    change.setPredecessor(node.getNodename());
                    queue.remove(change);
                    queue.add(change);
                }
            }
            node = queue.poll();
        }
    }

}
