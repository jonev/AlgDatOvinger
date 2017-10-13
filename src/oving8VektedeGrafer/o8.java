package oving8VektedeGrafer;

import Misc.*;

/**
 * @author jonev on 12.10.2017.
 */
public class o8 {
    public static Node[] nodes;
    public static void main(String[] args) {
        GraphLinkedEdgelist g = new GraphLinkedEdgelist();
        g.readGraphFileToNeighborList1("src/oving8VektedeGrafer/vg4.txt", true);

        Heap h = new Heap(g.getNodecount(), true);
        nodes = g.getNodes();
        nodes[1].setDist(0);
        Node[] hnodes = new Node[g.getNodecount()];
        for (int i = 0; i < hnodes.length; i++) {
            hnodes[i] = nodes[i];
        }
        h.setNodes(hnodes);
        h.makeHeap();

        dijkstas(h);
        for (int i = 0; i < nodes.length; i++) {
            System.out.println("Node: " + nodes[i].getNr() + " " + nodes[i].getPredecessor() + " " + nodes[i].getDist());
        }
    }



    private static void dijkstas(Heap h){
        TimeTaking t = new TimeTaking(1);
        t.start();
        Node node = h.getRoot();
        node.setIntPri(0);
        node.setDist(0);
        while (node != null){
            while (node.gotEdges()){
                Edge e = node.getEdge();
                // if(nodes[e.getTo()].getDist() == Integer.MAX_VALUE) {
                //     nodes[e.getTo()].setDist(node.getDist() + e.getWeight());
                //     nodes[e.getTo()].setPredecessor(node.getNr());
                // } else { // TODO får rolover på denne slik at den blir -2 milliarder
                    if(nodes[e.getTo()].getDist() > (node.getDist() + e.getWeight())){
                        nodes[e.getTo()].setDist(node.getDist() + e.getWeight());
                        nodes[e.getTo()].setPredecessor(node.getNr());
                    }
                //}
            }
            h.fixHeap(node.getIndex());
            node = h.getRoot();
            System.out.println("Ant fix: " + h.getAntfix());
        }
        System.out.println("Utført på " + (double)t.finish()/1000000000 + " sekunder");
    }

}
