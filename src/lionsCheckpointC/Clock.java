package lionsCheckpointC;

public class Clock {
    private int startTime = 0;
    private int endTime = 0;
    boolean simulationRunning = true;

    public void start() {
        startTime = (int) (System.currentTimeMillis() / 1000);
        simulationRunning = true;
    }

    public void stop() {
        endTime = (int) (System.currentTimeMillis() / 1000);
        simulationRunning = false;
    }

    public int getCurrentTime() {
        if (simulationRunning) {
            return (int) (System.currentTimeMillis() / 1000) - startTime;
        } else {
            return endTime - startTime;
        }
    }
}

