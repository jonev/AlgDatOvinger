package oving7Uvektedegrafer;

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


}