package Misc;

/**
 * @author jonev on 12.10.2017.
 */
public class Edge {
    private int to;
    private int weight;

    public Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }

    public int getTo() {
        return to;
    }

    public int getWeight() {
        return weight;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
