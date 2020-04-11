package ui.components;

import ui.components.panel.PanelTopUI;

import java.util.concurrent.locks.ReentrantLock;

public class Counter implements Runnable {
    private final PanelTopUI panelTopUI;
    private final ReentrantLock counterLock = new ReentrantLock(true); // enable fairness policy
    private int counter = 0;
    private volatile boolean done = false;

    public Counter(PanelTopUI panelTopUI) {
        this.panelTopUI = panelTopUI;
    }

    private void incrementCounter() throws InterruptedException {
        counterLock.lock();
        try {
            counter++;
            panelTopUI.setTime(counter);
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

    public void stop() {
        done = true;
    }

    public void start() {
        counter = 0;
        done = false;
    }

}
