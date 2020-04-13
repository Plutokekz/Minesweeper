package ui.components.runnables;

import ui.components.panels.InformationPanel;

import java.util.concurrent.locks.ReentrantLock;

public class Counter implements Runnable {
    private final InformationPanel informationPanel;
    private final ReentrantLock counterLock = new ReentrantLock(true);
    private int counter = 0;
    private volatile boolean done = false;

    public Counter(InformationPanel informationPanel) {
        this.informationPanel = informationPanel;
    }

    /**
     * Updates the timeCounter int the informationPanel every second
     *
     * @throws InterruptedException because the Thread has to sleep 1000 millis
     * @see InformationPanel
     */
    private void incrementCounter() throws InterruptedException {
        counterLock.lock();
        try {
            counter++;
            informationPanel.setTime(counter);
        } finally {
            counterLock.unlock();
        }
        Thread.sleep(1000);
    }

    @Override
    public void run() {
        while (!done) {
            try {
                incrementCounter();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Stops the Thread
     */
    public void stop() {
        done = true;
    }

    /**
     * Starts the Thread
     */
    public void start() {
        counter = 0;
        done = false;
    }

}
