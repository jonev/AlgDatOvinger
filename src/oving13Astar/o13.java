package oving13Astar;

import Misc.Edge;
import Misc.GraphLinkedEdgelist;
import Misc.Node;
import Misc.TimeTaking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class o13 {
    public static double EARTH_RADIUS = 6371000;
    public static double _2R = 41701090.90909090909090909091;
    private static int nodesUsedInDijkstras = 0;
    private static int nodesUsedInAstar = 0;

    public static void main(String[] args) {
        GraphLinkedEdgelist g = new GraphLinkedEdgelist();
        g.readGraphFileToNeighborList1ToArrayListAstar("src/oving13Astar/albania-kanter.txt");

        BufferedReader rd = null;
        FileReader fr = null;
        try{
            fr = new FileReader("src/oving13Astar/albania-noder.txt");
            rd = new BufferedReader(fr);

            String line = rd.readLine().trim();
            String[] heading = line.split(" +");
            int nodecount = Integer.parseInt(line.trim());
            System.out.println(nodecount);
            while ((line = rd.readLine()) != null){
                String splitt[] = line.trim().split(" +|\t+");
                int nodenr = Integer.parseInt(splitt[0]);
                double latitude = Double.parseDouble(splitt[1]);
                double longitude = Double.parseDouble(splitt[2]);
                //System.out.println("" + nodenr + " " + latitude + " " + longitude);
                g.setLongLat(nodenr, (Math.PI*longitude)/180, (Math.PI*latitude)/180);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                fr.close();
                rd.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        TimeTaking t = new TimeTaking(1);
        //t.start();
        //dijkstas(g.getNodelst(), 1, 50);
        //System.out.println("Utført på " + (double)t.finish()/1000000000 + " sekunder");
        //System.out.println("Dijkstras");
        //System.out.println("Antall noder plukket ut av køen " + nodesUsedInDijkstras);
        t = new TimeTaking(1);
        t.start();
        aStar(g.getNodelst(), 1, 50);
        System.out.println("Utført på " + (double)t.finish()/1000000000 + " sekunder");
        System.out.println("Astar");
        System.out.println("Antall noder plukket ut av køen " + nodesUsedInAstar);

        ArrayList<Node> nodelst = getRoad(g.getNodelst(), 50);
        System.out.println("Antall noder i ruten: " + nodelst.size());
        for (Node n : nodelst) {
            //System.out.println(n.getNodename() + " " + n.getPredecessor() + "  " + n.getDist());
            System.out.println((180*n.getRadlatitude())/Math.PI + ", " + (180*n.getRadlongitude())/Math.PI);
        }


    }

    private static ArrayList<Node> getRoad(ArrayList<Node> nodes, int endnode){
        ArrayList<Node> road = new ArrayList<>();
        Node end = nodes.get(endnode);
        road.add(end);
        Node n = nodes.get(end.getIntPredecessor());
        while (n.getIntPredecessor() != -1){
            road.add(n);
            n = nodes.get(n.getIntPredecessor());
        }
        road.add(n);
        return road;
    }

    private static void aStar(ArrayList<Node> nodes, int startnode, int endnodenr){
        Node endnode = nodes.get(endnodenr);
        System.out.println("Avstand fra start til mål: " + avstand(nodes.get(startnode), endnode));
        nodes.get(startnode).setDist(0);
        nodes.get(startnode).setPredecessor("Start", -1);
        PriorityQueue<Node> queue = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if(o1.getDist() > o2.getDist()){
                    return 1;
                } else if(o1.getDist() < o2.getDist()){
                    return -1;
                } else return 0;
            }
        });

        Node node = nodes.get(startnode);
        while (node != null){
            Edge[] edges = node.getEdges().toArray(new Edge[node.getEdges().size()]);
            for (Edge e : edges){
                Node change = nodes.get(e.getTo());
                if(change.getDist() == Long.MAX_VALUE){
                    int avs = avstand(change, endnode);
                    //System.out.println(avs);
                    change.setDistanceToEnd(avs);
                    change.setDist(node.getDist() + e.getDrivetime()+ avs);
                    change.setPredecessor(node.getNodename(), node.getIndex());
                    //change.setDistanceToEnd(node.getDist() + avstand(change, endnode));
                    //System.out.println("Dist: " + change.getDist());
                    queue.add(change);
                }else if(change.getDist() > (node.getDist() + e.getDrivetime())){
                    //System.out.println(change.getDistanceToEnd());
                    change.setDist(node.getDist() + e.getDrivetime() + );
                    change.setPredecessor(node.getNodename(), node.getIndex());
                    //change.setDistanceToEnd(node.getDist() + avstand(change, endnode));
                    //System.out.println("Dist: " + change.getDist());
                    queue.remove(change);
                    queue.add(change);
                }
            }
            node = queue.poll();
            //System.out.println("Tok ut " + node.getDist());
            nodesUsedInAstar++;
            if(node.getIndex() == endnodenr)return;
        }
    }


    private static void dijkstas(ArrayList<Node> nodes, int startnode, int endnode){
        nodes.get(startnode).setDist(0);
        nodes.get(startnode).setPredecessor("Start", -1);
        PriorityQueue<Node> queue = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if(o1.getDist() > o2.getDist()){
                    return 1;
                } else if(o1.getDist() < o2.getDist()){
                    return -1;
                } else return 0;
            }
        });

        Node node = nodes.get(startnode);
        while (node != null){
            Edge[] edges = node.getEdges().toArray(new Edge[node.getEdges().size()]);
            for (Edge e : edges){
                Node change = nodes.get(e.getTo());
                if(change.getDist() == Integer.MAX_VALUE){
                    change.setDist(node.getDist() + e.getDrivetime());
                    change.setPredecessor(node.getNodename(), node.getIndex());
                    queue.add(change);
                }else if(change.getDist() > (node.getDist() + e.getDrivetime())){
                    change.setDist(node.getDist() + e.getDrivetime());
                    change.setPredecessor(node.getNodename(), node.getIndex());
                    queue.remove(change);
                    queue.add(change);
                }
            }
            node = queue.poll();
            nodesUsedInDijkstras++;
            if(node.getIndex() == endnode)return;
        }
    }

    //Jordens radius er 6371 km, høyeste fartsgrense 110km/t, 3600 sek/time
    //For å få hundredels sekunder: 2*6371/110*3600*100 = 41701090.90909090909090909091
    private static int avstand (Node n1, Node n2) {
        double sin_bredde = Math.sin((n1.getRadlatitude()-n2.getRadlatitude())/2.0);
        double sin_lengde = Math.sin((n1.getRadlongitude()-n2.getRadlongitude())/2.0);
        int res = (int) (_2R*Math.asin(Math.sqrt(
                sin_bredde*sin_bredde+n1.getCosLat()*n2.getCosLat()*sin_lengde*sin_lengde)));
        //System.out.println("Avstand: " + res);
        return res;
    }

}
