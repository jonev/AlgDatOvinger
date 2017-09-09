package oving4Trer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by JoneSkole on 08.09.2017.
 */
public class SimpleCompilerTest {
    SimpleCompiler instance;

    @Before
    public void setUp() throws Exception {
        instance = new SimpleCompiler();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void compile() throws Exception {
        ArrayList<String> s = instance.compile("src/TestCode/oving4Trer/TestCompileFile.txt");
        for (String st: s) {
            System.out.println(st);
        }
    }

    @Test
    public void nextSymbol() throws Exception {
        assertTrue(instance.nextSymbol('}'));
        assertFalse(instance.nextSymbol('{'));
        assertFalse(instance.nextSymbol('}'));
        assertFalse(instance.nextSymbol('{'));
        assertFalse(instance.nextSymbol('{'));
        assertFalse(instance.nextSymbol('}'));
        assertFalse(instance.nextSymbol('}'));

        assertFalse(instance.nextSymbol('{'));
        assertFalse(instance.nextSymbol('}'));

        assertFalse(instance.nextSymbol('{'));
        assertFalse(instance.nextSymbol('{'));

        assertFalse(instance.nextSymbol('('));
        assertFalse(instance.nextSymbol('('));

        assertFalse(instance.nextSymbol('['));
        assertFalse(instance.nextSymbol('('));
        assertFalse(instance.nextSymbol(')'));
        assertTrue(instance.nextSymbol(')'));
        assertFalse(instance.nextSymbol(']'));

        assertFalse(instance.nextSymbol(')'));
        assertFalse(instance.nextSymbol(')'));


        assertFalse(instance.nextSymbol('}'));
        assertFalse(instance.nextSymbol('}'));
    }

}