package oving11Automat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AutomatTest {
    private Automat instance;
    char[] a1 = {'0', '1'};
    char[] a2 = {'a', 'b'};

    int[] ak1 = {2};
    int[] ak2 = {3};

    int[][] n1 = {
            {1, 3},
            {1, 2},
            {2, 3},
            {3, 3}
    };

    int[][] n2 = {
            {1, 3},
            {1, 2},
            {2, 3},
            {3, 3}
    };

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
        instance = null;
    }

    @Test
    public void sjekkInput1() throws Exception {
        instance = new Automat(a1, ak1, n1);
        assertEquals(true, instance.sjekkInput(new char[]{'0', '1', '0'}));
        assertEquals(false, instance.sjekkInput(new char[]{'1', '1', '1'}));
        assertEquals(false, instance.sjekkInput(new char[]{'0', '1', '0', '1', '1', '0'}));
        assertEquals(true, instance.sjekkInput(new char[]{'0', '0', '1', '0', '0', '0'}));
    }
    @Test
    public void sjekkInput2() throws Exception {
        instance = new Automat(a2, ak2, n2);
        assertEquals(true, instance.sjekkInput(new char[]{'a', 'b', 'b', 'b'}));
        assertEquals(false, instance.sjekkInput(new char[]{'a', 'a', 'a', 'b'}));
        assertEquals(true, instance.sjekkInput(new char[]{'b', 'a', 'b', 'a', 'b'}));
    }

}