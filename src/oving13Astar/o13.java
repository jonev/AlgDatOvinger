package oving13Astar;

import Misc.Edge;
import Misc.GraphLinkedEdgelist;
import Misc.Node;
import Misc.TimeTaking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class o13 {
    public static void main(String[] args) {
        GraphLinkedEdgelist g = new GraphLinkedEdgelist();
        g.readGraphFileToNeighborList1ToArrayListAstar("src/oving13Astar/kanter.txt");

        BufferedReader rd = null;
        FileReader fr = null;
        try{
            fr = new FileReader("src/oving13Astar/noder.txt");
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
                g.setLongLat(nodenr, longitude, latitude);
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

        /*

        TimeTaking t = new TimeTaking(1);
        t.start();
        dijkstas(g.getNodelst(), 1);
        System.out.println("Utført på " + (double)t.finish()/1000000000 + " sekunder");

        ArrayList<Node> nodelst = g.getNodelst();
        for (Node n : nodelst) {
            System.out.println(n.getNodename() + " " + n.getPredecessor() + "  " + n.getDist());
        }
        */

    }



    private static void dijkstas(ArrayList<Node> nodes, int startnode){
        nodes.get(startnode).setDist(0);
        nodes.get(startnode).setPredecessor("Start");
        // TODO nodene legges inn i køen ut i fra koordinater
        PriorityQueue<Node> queue = new PriorityQueue<Node>(nodes);

        Node node = queue.poll();
        while (node != null){
            Edge[] edges = node.getEdges().toArray(new Edge[node.getEdges().size()]);
            for (Edge e : edges){
                // TODO får rolover på denne slik at den blir -2 milliarder
                // TODO bruk spesialtilfellet for Integer.MAX og behandle det deretter
                Node change = nodes.get(e.getTo());
                if(change.getDist() > (node.getDist() + e.getWeight())){
                    change.setDist(node.getDist() + e.getWeight());
                    change.setPredecessor(node.getNodename());
                    queue.remove(change);
                    queue.add(change);
                }
            }
            node = queue.poll();
        }
    }

    //Jordens radius er 6371 km, høyeste fartsgrense 110km/t, 3600 sek/time
//For å få hundredels sekunder: 2*6371/110*3600*100 = 41701090.90909090909090909091
    int avstand (Node n1, Node n2) {
        double sin_bredde = Math.sin((n1.getLatitude()-n2.getLatitude())/2.0);
        double sin_lengde = Math.sin((n1.getLongitude()-n2.getLongitude())/2.0);
        //return (int) (41701090.90909090909090909091*Math.asin(Math.sqrt(
                // TODO sin_bredde*sin_bredde+n1.cos_bredde*n2.cos_bredde*sin_lengde*sin_lengde)));
        return 0;
    }

}
