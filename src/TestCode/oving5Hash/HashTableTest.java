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
        instance = new HashTable(17);
    }

    @After
    public void tearDown() throws Exception {
        instance = null;
    }

    @Test
    public void h1() throws Exception {
        long i = instance.h1(233);
        System.out.println(i);
    }

    @Test
    public void h2() throws Exception {
        long i = instance.h2(233);
        System.out.println(i);

    }

    @Test
    public void hash() throws Exception {
        long i = instance.hash(233, 0);
        System.out.println(i);

    }

    @Test
    public void hash1() throws Exception {
        long i = instance.hash("Jone");
        System.out.println(i);

    }

    @Test
    public void getFromTable() throws Exception {
       hash1();
       int i = instance.getFromTable("Jone");
        System.out.println(i);
    }

    @Test
    public void stringToInt() throws Exception {

    }







}