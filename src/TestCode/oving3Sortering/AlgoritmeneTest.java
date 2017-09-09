package oving3Sortering;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by JoneSkole on 02.09.2017.
 */
public class AlgoritmeneTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void quickSort() throws Exception {
        int[] i = {1, 5, 2, 4, 9, 8, 7, 3, 6, 0, -2, -1, -9, -4, -6, -5, -7, -8, -3};
        int[] i2 = {-9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Algoritmene.quickSort(i, 0, 18, 5);
        assertArrayEquals(i2, i);

    }

    @Test
    public void insertionsort() throws Exception {

    }

    @Test
    public void splitt() throws Exception {
        int[] i = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        int s = Algoritmene.splitt(i, 0, 9);
    }

    @Test
    public void median3sort() throws Exception {
        int[] i = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] i2 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int j = Algoritmene.median3sort(i, 0, 9);
        assertEquals(j, 4);
        assertArrayEquals(i, i2);

        int[] i3 = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        int[] i4 = {0, 8, 7, 6, 5, 4, 3, 2, 1, 9};
        int j2 = Algoritmene.median3sort(i3, 0, 9);
        assertEquals(j2, 4);
        assertArrayEquals(i3, i4);
    }

    @Test
    public void bytt() throws Exception {
        int[] i = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] j = {0, 7, 2, 3, 4, 5, 6, 1, 8, 9};
        Algoritmene.bytt(i, 1, 7);
        assertArrayEquals(i, j);
    }

}