package homework;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrieTask implements Runnable {

    private CyclicBarrier barrier;

    private int result;


    public CyclicBarrieTask(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    public int getResult() {
        return result;
    }

    @Override
    public void run() {
        try {
            System.out.println("阻塞");
            this.barrier.await(); // 線程阻塞
            System.out.println("執行");
            result = Q1.sum();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
