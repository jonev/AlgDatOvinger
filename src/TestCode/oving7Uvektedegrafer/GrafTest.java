package oving7Uvektedegrafer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GrafTest {
    Graf instance;
    @Before
    public void setUp() throws Exception {
        instance = new Graf();
    }

    @After
    public void tearDown() throws Exception {
        instance = null;
    }

    @Test
    public void readGraphFile() throws Exception {
        instance.readGraphFile("src/oving7Uvektedegrafer/L7g1.txt");
        int[][] print = instance.getEdgeTbl();
        for (int i = 0; i < instance.getEdgeTbl().length; i++) {
            System.out.println(print[i][0] + " " + print[i][1]);
        }
        int[] node = instance.getIndexInEdgeTbl();
        for (int i = 0; i < node.length; i++) {
            System.out.println(node[i]);
        }
    }

}