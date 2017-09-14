package oving5Hash;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by JoneSkole on 13.09.2017.
 */
public class HashTableTest {
    private HashTable instance;
    @Before
    public void setUp() throws Exception {
        instance = new HashTable(4);
    }

    @After
    public void tearDown() throws Exception {
        instance = null;
    }

    @Test
    public void divhash() throws Exception {
        int a = instance.divhash(50);
    }

    @Test
    public void divhash1() throws Exception {

    }

}