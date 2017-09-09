package Misc;

/**
 * Created by JoneSkole on 06.09.2017.
 */
public class TimeTaking {
    private int inUse = 0;
    private long[] samples;
    private long tempStart = 0;
    private boolean running = false;


    public TimeTaking(int maxSamples) {
        samples = new long[maxSamples];
    }

    public void start(){
        if(running) stop();
        tempStart = System.nanoTime();
        running = true;
    }

    public void stop(){
        if(!running) return;
        inUse++;
        samples[inUse-1] = System.nanoTime() - tempStart;
        running = false;
    }

    public long finish(){
        if(running) stop();
        long sum = 0;
        for (int i = 0; i < inUse; i++) {
            sum += samples[i];
        }
        return sum/inUse;
    }
}
