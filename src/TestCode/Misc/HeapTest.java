package Misc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

/**
 * @author jonev on 07.10.2017.
 */
public class HeapTest {
    private Heap instanceMin;
    private Heap instanceMax;
    @Before
    public void setUp() throws Exception {
        instanceMin = new Heap(20, true);
        instanceMax = new Heap(20, false);
        addTestNodes();
        instanceMin.makeHeap();
        instanceMax.makeHeap();
    }

    @After
    public void tearDown() throws Exception {
        instanceMin = null;
        instanceMax = null;
    }

    @Test
    public void addNode() throws Exception {
        assertEquals(7, instanceMin.getNrOfNodeIn());
        assertEquals(7, instanceMax.getNrOfNodeIn());
        System.out.println(instanceMin.toString());
        System.out.println(instanceMax.toString());
    }

    private void addTestNodes(){
        instanceMin.addNodeBeforeMake(0, 1);
        instanceMax.addNodeBeforeMake(0, 1);

        instanceMin.addNodeBeforeMake(1, 3);
        instanceMax.addNodeBeforeMake(1, 3);

        instanceMin.addNodeBeforeMake(2, 7);
        instanceMax.addNodeBeforeMake(2, 7);

        instanceMin.addNodeBeforeMake(3, 2);
        instanceMax.addNodeBeforeMake(3, 2);

        instanceMin.addNodeBeforeMake(4, 9);
        instanceMax.addNodeBeforeMake(4, 9);

        instanceMin.addNodeBeforeMake(5, 8);
        instanceMax.addNodeBeforeMake(5, 8);

        instanceMin.addNodeBeforeMake(6, 6);
        instanceMax.addNodeBeforeMake(6, 6);
    }

    @Test
    public void makeHeapTest() throws Exception {
        System.out.println(instanceMin.toString());
        System.out.println(instanceMax.toString());
        assertEquals(1, instanceMin.getPri(0));
        assertEquals(3, instanceMin.getPri(3));
        assertEquals(7, instanceMin.getPri(6));
        assertEquals(9, instanceMax.getPri(0));
        assertEquals(2, instanceMax.getPri(3));
        assertEquals(6, instanceMax.getPri(6));
        checkHeapPriority(instanceMin, true);
        checkHeapPriority(instanceMax, false);
    }

    @Test
    public void getRootTest() throws Exception {

        assertEquals(1, instanceMin.getRoot().getIntPri());
        assertEquals(9, instanceMax.getRoot().getIntPri());

        System.out.println(instanceMin.toString());
        System.out.println(instanceMax.toString());

    }

    @Test
    public void changePriorityTest() throws Exception {
        // System.out.println(instanceMax.toString());
        instanceMax.changePriority(0, 5);
        instanceMax.changePriority(4, 88);
        instanceMax.changePriority(2, 2);
        instanceMax.changePriority(0, 4);
        // System.out.println(instanceMax.toString());
        // System.out.println(instanceMax.toString());
        instanceMax.changePriority(3, 9);
        instanceMax.changePriority(2, 1);
        instanceMax.changePriority(5, 11);
        instanceMax.changePriority(6, 3);
        // System.out.println(instanceMax.toString());



        checkHeapPriority(instanceMin, true);
        checkHeapPriority(instanceMax, false);
    }

    @Test
    public void addNodeTest() throws Exception {
        instanceMax.addNode(100, 7);
        System.out.println(instanceMax.toString());
        instanceMax.addNode(101, 11);
        System.out.println(instanceMax.toString());

        instanceMax.addNode(100, 4);
        System.out.println(instanceMax.toString());
        checkHeapPriority(instanceMax, false);

    }

    private void checkHeapPriority(Heap h, boolean typeMin){
        PriHeapNode root = h.getRoot();
        PriHeapNode last = root;
        while (root != null){
            if(typeMin){
                if(root.getIntPri() < last.getIntPri()){
                    assertTrue(false);
                }
            } else {
                if(root.getIntPri() > last.getIntPri()){
                    assertTrue(false);
                }
            }
            last = root;
            root = h.getRoot();
        }
    }
}