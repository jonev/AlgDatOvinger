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
        instanceMin.addNodeBeforeMake(0 + "", 1);
        instanceMax.addNodeBeforeMake(0 + "", 1);

        instanceMin.addNodeBeforeMake(1 + "", 3);
        instanceMax.addNodeBeforeMake(1 + "", 3);

        instanceMin.addNodeBeforeMake(2 + "", 7);
        instanceMax.addNodeBeforeMake(2 + "", 7);

        instanceMin.addNodeBeforeMake(3 + "", 2);
        instanceMax.addNodeBeforeMake(3 + "", 2);

        instanceMin.addNodeBeforeMake(4 + "", 9);
        instanceMax.addNodeBeforeMake(4 + "", 9);

        instanceMin.addNodeBeforeMake(5 + "", 8);
        instanceMax.addNodeBeforeMake(5 + "", 8);

        instanceMin.addNodeBeforeMake(6 + "", 6);
        instanceMax.addNodeBeforeMake(6 + "", 6);
    }

    @Test
    public void makeHeapTest() throws Exception {
        System.out.println(instanceMin.toString());
        System.out.println(instanceMax.toString());
        assertEquals(1, instanceMin.getDist(0));
        assertEquals(3, instanceMin.getDist(3));
        assertEquals(7, instanceMin.getDist(6));
        assertEquals(9, instanceMax.getDist(0));
        assertEquals(2, instanceMax.getDist(3));
        assertEquals(6, instanceMax.getDist(6));
        checkHeapPriority(instanceMin, true);
        checkHeapPriority(instanceMax, false);
    }

    @Test
    public void getRootTest() throws Exception {

        assertEquals(1, instanceMin.getRoot().getDist());
        assertEquals(9, instanceMax.getRoot().getDist());

        System.out.println(instanceMin.toString());
        System.out.println(instanceMax.toString());

    }

    @Test
    public void changePriorityTest() throws Exception {
        // System.out.println(instanceMax.toString());
        instanceMax.changeDistance(0, 5);
        instanceMax.changeDistance(4, 88);
        instanceMax.changeDistance(2, 2);
        instanceMax.changeDistance(0, 4);
        // System.out.println(instanceMax.toString());
        // System.out.println(instanceMax.toString());
        instanceMax.changeDistance(3, 9);
        instanceMax.changeDistance(2, 1);
        instanceMax.changeDistance(5, 11);
        instanceMax.changeDistance(6, 3);
        // System.out.println(instanceMax.toString());



        checkHeapPriority(instanceMin, true);
        checkHeapPriority(instanceMax, false);
    }

    @Test
    public void addNodeTest() throws Exception {
        instanceMax.addNode(100 + "", 7);
        System.out.println(instanceMax.toString());
        instanceMax.addNode(101 + "", 11);
        System.out.println(instanceMax.toString());

        instanceMax.addNode(100 + "", 4);
        System.out.println(instanceMax.toString());
        checkHeapPriority(instanceMax, false);

    }

    private void checkHeapPriority(Heap h, boolean typeMin){
        Node root = h.getRoot();
        Node last = root;
        while (root != null){
            if(typeMin){
                if(root.getDist() < last.getDist()){
                    assertTrue(false);
                }
            } else {
                if(root.getDist() > last.getDist()){
                    assertTrue(false);
                }
            }
            last = root;
            root = h.getRoot();
        }
    }
}