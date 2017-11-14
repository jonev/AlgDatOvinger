package oving13Astar;

import org.junit.Test;

import static org.junit.Assert.*;

public class o13Test {

    @Test
    public void test1() throws Exception {
        long i = 2214984;
        String s = o13.sekToHMS(i);
        System.out.println(s);

        /*
        22149 sek
        6 t

         */
    }
}