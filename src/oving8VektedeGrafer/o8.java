package oving8VektedeGrafer;

import Misc.*;

/**
 * @author jonev on 12.10.2017.
 */
public class o8 {
    public static void main(String[] args) {
        GraphLinkedEdgelist g = new GraphLinkedEdgelist();
        g.readGraphFileToNeighborList1("src/oving8VektedeGrafer/vg4.txt", true);

        Node[] nodes = dijkstas(g, 1);
        for (int i = 0; i < nodes.length; i++) {
            System.out.println(nodes[i].getNr() + " " + nodes[i].getPredecessor() + " " + nodes[i].getDist());
        }
    }



    private static Node[] dijkstas(GraphLinkedEdgelist g, int startIndex){
        Node[] nodes = g.getNodes();
        Node startNode = nodes[startIndex];
        Heap heap = new Heap(g.getNodecount(), true);
        startNode.setIntPri(0);
        startNode.setDist(0);
        heap.addNode(startNode);
        while (!heap.isEmpty()){
            Node node = (Node)heap.getRoot();
            while (node.gotEdges()){
                Edge e = node.getEdge();
                if(nodes[e.getTo()].getDist() == Integer.MAX_VALUE) {
                    nodes[e.getTo()].setDist(node.getDist() + e.getWeight());
                    nodes[e.getTo()].setPredecessor(node.getNr());
                } else {
                    if(nodes[e.getTo()].getDist() > (node.getDist() + e.getWeight())){
                        nodes[e.getTo()].setDist(node.getDist() + e.getWeight());
                        nodes[e.getTo()].setPredecessor(node.getNr());
                    }
                }
                heap.addNode(nodes[e.getTo()]);
            }
        }
        return nodes;
    }

}
