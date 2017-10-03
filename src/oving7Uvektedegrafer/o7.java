package oving7Uvektedegrafer;

import java.io.BufferedReader;
import java.io.FileReader;

public class o7 {
}


class Graf{
    private int[] indexInEdgeTbl;
    private int[][] edgeTbl;

    public Graf(){
    }

    public void readGraphFile(String filename){
        BufferedReader rd = null;
        FileReader fr = null;
        try{
            fr = new FileReader(filename);
            rd = new BufferedReader(fr);

            String line = rd.readLine();
            String[] heading = line.split(" ");
            int nodecount = Integer.parseInt(heading[0]);
            int edgecount = Integer.parseInt(heading[1]);
            indexInEdgeTbl = new int[nodecount];
            int[][] unsortedEdgTbl = new int[edgecount][2];

            int node = -1;
            int from = -1;
            int to = -1;
            int index = 0;

            while ((line = rd.readLine()) != null){
                String splitt[] = line.split(" ");
                from = Integer.parseInt(splitt[0]);
                to = Integer.parseInt(splitt[1]);

                unsortedEdgTbl[index][0] = from;
                unsortedEdgTbl[index++][1] = to;
            }

            // sort
            edgeTbl = new int[edgecount][2];
            int u = 0;
            for (int i = 0; i < nodecount; i++) {
                for (int j = 0; j < edgecount; j++) {
                    if(unsortedEdgTbl[j][0] == i){
                        try {
                            edgeTbl[u][0] = unsortedEdgTbl[j][0];
                            edgeTbl[u][1] = unsortedEdgTbl[j][1];
                            u++;
                        }catch (Exception e){

                        }
                    }
                }
            }

            index = 0;
            int nodenr = 0;
            indexInEdgeTbl[index] = nodenr;
            for (int i = 0; i < edgecount; i++) {
                if(edgeTbl[i][0] != nodenr){
                    nodenr = edgeTbl[i][0];
                    indexInEdgeTbl[++index] = i;
                }
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
    }

    public int[] getIndexInEdgeTbl() {
        return indexInEdgeTbl;
    }

    public int[][] getEdgeTbl() {
        return edgeTbl;
    }
}