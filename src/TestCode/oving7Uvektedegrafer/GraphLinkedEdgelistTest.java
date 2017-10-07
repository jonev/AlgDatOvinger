package oving7Uvektedegrafer;

import Misc.TimeTaking;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author jonev on 06.10.2017.
 */
public class GraphLinkedEdgelistTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void readGraphFileToNeighborList1() throws Exception {
        GraphLinkedEdgelist L7g5 = new GraphLinkedEdgelist();
        L7g5.readGraphFileToNeighborList1("src/oving7Uvektedegrafer/L7Skandinavia.txt", false);

    }

    @Test
    public void topologisksorteringTest() throws Exception {
        GraphLinkedEdgelist instance= new GraphLinkedEdgelist();
        instance.readGraphFileToNeighborList1("src/oving7Uvektedegrafer/L7g5.txt", true);

        Node n = o7.topologisksortering(instance);
        System.out.println(n.getNodeList());
    }

    @Test
    public void L5SkandinaviaTest() throws Exception {
        System.out.println("");
        System.out.println("");
        TimeTaking t = new TimeTaking(1);
        t.start();
        GraphLinkedEdgelist instance = new GraphLinkedEdgelist();
        System.out.println("Leser inn alle veistrekninger i skandinavia fra tekstfil");
        instance.readGraphFileToNeighborList1("src/oving7Uvektedegrafer/L7Skandinavia.txt", true);
        System.out.println("Antall steder " + instance.getNodecount());
        System.out.println("Antall veistrekkninger " + instance.getEdgecount());
        int drammenindex = 65205;
        System.out.println("Finner ant veistrekk fra Drammen til alle steder i skandinavia");
        Node[] nodes = o7.breddeforstsok(instance, drammenindex);
        Node drammen = instance.getNodes()[65205];
        Node helsinki = instance.getNodes()[3378527];
        System.out.println("Henter ut hvor mange veistrekninger det er fra Drammen til Helsinki: " + helsinki.getDist() + " det vil si: " + (helsinki.getDist()-1) + " veikryss");
        // System.out.println("Finner ant veistrekk fra Kalvskinnet(Trondheim) til alle steder i skandinavia");
        // nodes = o7.breddeforstsok(instance, 37774);
        // System.out.println("Henter ut hvor mange veistrekninger det er fra Kalvskinnet til Moholt: " + nodes[18058].getDist() + " det vil si: " + (nodes[18058].getDist()-1) + " veikryss");
        System.out.println("Utført på " + (double)t.finish()/1000000000 + " sekunder");
        System.out.println("");
        System.out.println("");
    }
}