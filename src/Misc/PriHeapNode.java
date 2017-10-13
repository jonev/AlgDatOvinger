package Misc;

import sun.plugin.viewer.context.PluginBeansContext;

/**
 * @author jonev on 07.10.2017.
 */
public interface PriHeapNode {
    public int getIntPri();
    public void setIntPri(int priority);
    public int getDist();
    public void setDist(int dist);
    public int getNr();
    public void setNr(int nr);
    public boolean gotEdges();
    public Edge getEdge();
    public int getPrecesessor();
    public void setPrecesessor(int pre);
}
