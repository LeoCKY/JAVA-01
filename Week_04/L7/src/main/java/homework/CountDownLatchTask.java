package homework;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTask implements Runnable {

    private CountDownLatch latch;

    public CountDownLatchTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        Q1.sum();
        this.latch.countDown();
    }
}
