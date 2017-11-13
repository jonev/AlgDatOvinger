package Misc;

/**
 * @author jonev on 12.10.2017.
 */
public class Edge {
    private int to;
    private int weight;
    private int drivetime;
    private int length;
    private int speedlimit;

    public Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }

    public Edge(int to, int drivetime, int length, int speedlimit) {
        this.to = to;
        this.drivetime = drivetime;
        this.length = length;
        this.speedlimit = speedlimit;
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

    public void setDrivetime(int drivetime) {
        this.drivetime = drivetime;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setSpeedlimit(int speedlimit) {
        this.speedlimit = speedlimit;
    }

    public int getDrivetime() {
        return drivetime;
    }

    public int getLength() {
        return length;
    }

    public int getSpeedlimit() {
        return speedlimit;
    }
}
