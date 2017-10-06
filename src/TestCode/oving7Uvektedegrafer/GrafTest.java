package oving7Uvektedegrafer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GrafTest {
    GraphIndexEdgeTable instance;
    @Before
    public void setUp() throws Exception {
        instance = new GraphIndexEdgeTable();
    }

    @After
    public void tearDown() throws Exception {
        instance = null;
    }

    @Test
    public void breddeforstsok() throws Exception {
        instance.readGraphFileToNeighborList2("src/oving7Uvektedegrafer/testgraph.txt", false);
        Node[] nodetbl = o7.breddeforstsok(instance, 5);
        for (int i = 0; i < nodetbl.length; i++) {
            System.out.println("Node " + nodetbl[i].getNr() + " forgjenger " + nodetbl[i].getPredecessor() + " dist " + nodetbl[i].getDist());
        }

        assertEquals(2, nodetbl[0].getPredecessor());
        assertEquals(2, nodetbl[0].getDist());
    }

    @Test
    public void readGraphFile() throws Exception {
        instance.readGraphFileToNeighborList2("src/oving7Uvektedegrafer/testgraph.txt", true);
        System.out.println(instance.toString());
        assertEquals(17, instance.getEdgeTbl().length);
        assertEquals(2, instance.getEdgeTbl()[0]);
        assertEquals(4, instance.getEdgeTbl()[16]);
        assertEquals(8, instance.getIndexInEdgeTbl().length);
        assertEquals(0, instance.getIndexInEdgeTbl()[0].getIndex());
        assertEquals(2, instance.getIndexInEdgeTbl()[1].getIndex());
        assertEquals(2, instance.getIndexInEdgeTbl()[2].getIndex());
        assertEquals(5, instance.getIndexInEdgeTbl()[3].getIndex());
    }


    @Test
    public void bfsL7g1() throws Exception {
        GraphIndexEdgeTable L7g1 = new GraphIndexEdgeTable();
        L7g1.readGraphFileToNeighborList2("src/oving7Uvektedegrafer/L7g1.txt", false);
        Node[] nodetbl = o7.breddeforstsok(L7g1, 5);
        System.out.println(L7g1.toString());
        System.out.println("Resultat av L7g1.txt ------------");
        for (int i = 0; i < nodetbl.length-1; i++) {
            System.out.println("Node " + nodetbl[i].getNr() + " forgjenger " + nodetbl[i].getPredecessor() + " dist " + nodetbl[i].getDist());
        }
        System.out.println("------------------------------------");

    }

    @Test
    public void bfsL7g2() throws Exception {
        GraphIndexEdgeTable L7g2 = new GraphIndexEdgeTable();
        L7g2.readGraphFileToNeighborList2("src/oving7Uvektedegrafer/L7g2.txt", false);
        Node[] nodetbl = o7.breddeforstsok(L7g2, 5);
        System.out.println(L7g2.toString());
        System.out.println("Resultat av L7g2.txt ------------");
        System.out.println("Noder: " + L7g2.getNodecount() + " kanter: " + L7g2.getEdgecount());
        for (int i = 0; i < nodetbl.length; i++) {
            System.out.println("Node " + nodetbl[i].getNr() + " forgjenger " + nodetbl[i].getPredecessor() + " dist " + nodetbl[i].getDist());
        }
        System.out.println("------------------------------------");

    }

    @Test
    public void bfsL7g3() throws Exception {
        GraphIndexEdgeTable L7g3 = new GraphIndexEdgeTable();
        L7g3.readGraphFileToNeighborList2("src/oving7Uvektedegrafer/L7g3.txt", false);
        System.out.println(L7g3.toString());
        Node[] nodetbl = o7.breddeforstsok(L7g3, 5);
        System.out.println("Resultat av L7g3.txt ------------");
        System.out.println("Noder: " + L7g3.getNodecount() + " kanter: " + L7g3.getEdgecount());
        for (int i = 0; i < nodetbl.length-1; i++) {
            System.out.println("Node " + nodetbl[i].getNr() + " forgjenger " + nodetbl[i].getPredecessor() + " dist " + nodetbl[i].getDist());
        }
        System.out.println("------------------------------------");

    }

    @Test
    public void bfsL7g5() throws Exception {
        GraphIndexEdgeTable L7g5 = new GraphIndexEdgeTable();
        L7g5.readGraphFileToNeighborList2("src/oving7Uvektedegrafer/L7g5.txt", false);
        System.out.println(L7g5.toString());
        Node[] nodetbl = o7.breddeforstsok(L7g5, 5);
        System.out.println("Resultat av L7g5.txt ------------");
        System.out.println("Noder: " + L7g5.getNodecount() + " kanter: " + L7g5.getEdgecount());
        for (int i = 0; i < nodetbl.length-1; i++) {
            System.out.println("Node " + nodetbl[i].getNr() + " forgjenger " + nodetbl[i].getPredecessor() + " dist " + nodetbl[i].getDist());
        }
        System.out.println("------------------------------------");

    }

    @Test
    public void bfsL7Skandinavia() throws Exception {
        GraphIndexEdgeTable L7S = new GraphIndexEdgeTable();
        L7S.readGraphFileToNeighborList2("src/oving7Uvektedegrafer/L7Skandinavia.txt", false);
        Node[] nodetbl = o7.breddeforstsok(L7S, 5);
        System.out.println("Resultat av L7Skandinavia.txt ------------");
        System.out.println("Noder: " + L7S.getNodecount() + " kanter: " + L7S.getEdgecount());
        for (int i = 0; i < nodetbl.length-1; i++) {
            System.out.println("Node " + nodetbl[i].getNr() + " forgjenger " + nodetbl[i].getPredecessor() + " dist " + nodetbl[i].getDist());
        }
        System.out.println("------------------------------------");

    }
}